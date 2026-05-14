package com.javarush.controller;

import com.javarush.model.QuestStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.javarush.service.QuestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Основной класс логики игры.
 * Использует HttpSession для хранения состояний игры
 *
 */

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(GameServlet.class);

    private final QuestService service = new QuestService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String stepId = req.getParameter("step");
        String nextStepText = req.getParameter("nextStepText");
        Integer lucidityDiff = safeParse(req.getParameter("lucidityDiff"));
        Integer fragmentDiff = safeParse(req.getParameter("fragmentDiff"));
        Integer lucidity = (Integer)session.getAttribute("lucidity") + lucidityDiff;
        Integer fragments = (Integer)session.getAttribute("fragments") + fragmentDiff;
        String playerName = (String) session.getAttribute("playerName");

        if (stepId == null || stepId.isBlank()) {
            stepId = "start";
        }

        //Выход на финал timelessness
        if (stepId.equals("finalStep") && lucidity < 40){
            QuestStep step = service.getStep("timelessness");
            if (nextStepText != null && !nextStepText.isBlank()){
                step.setPrevText(nextStepText);
            }
            String stepText = step.getText();
            if (stepText.contains("%s") && playerName != null){
                stepText = String.format(stepText, playerName);
            }
            req.setAttribute("stepText", stepText);
            req.setAttribute("step", step);
            session.setAttribute("lucidity", lucidity);
            session.setAttribute("fragments", fragments);

            logger.info("User {} goes to timelessness final. Fragments: {}, lucidity: {}", playerName, fragments, lucidity);

            req.getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }

        QuestStep step = service.getStep(stepId);

        if (stepId.equals("trueFinal")){
            if (lucidity < 30 || fragments < 2){
                step = service.getStep("defeat");
            }
        }
        //Добавляем имя игрока в текст
        String stepText = step.getText();

        if (stepText.contains("%s") && playerName != null){
            stepText = String.format(stepText, playerName);
        }

        if (nextStepText != null && !nextStepText.isBlank()){
            step.setPrevText(nextStepText);
        }

        req.setAttribute("stepText", stepText);
        req.setAttribute("step", step);
        session.setAttribute("lucidity", lucidity);
        session.setAttribute("fragments", fragments);


        if (service.isFinalStep(step)){
            logger.info("User {} goes to {} final. Fragments: {}, lucidity: {}. Session id: {}", playerName, step.getId(), fragments, lucidity, session.getId());
            req.getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }

        logger.debug("User {} goes to {} step. Fragments: {}, lucidity: {}. Session id: {}", playerName, step.getId(), fragments, lucidity, session.getId());

        req.getRequestDispatcher("/game.jsp").forward(req, resp);
    }

    private Integer safeParse(String str){
        try {
            return (str != null && !str.isEmpty()) ? Integer.parseInt(str) : 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}

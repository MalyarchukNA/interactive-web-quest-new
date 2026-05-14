package com.javarush.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(StartServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("User visited start page.");
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String playerName = req.getParameter("playerName");
        session.setAttribute("playerName", playerName);
        session.setAttribute("fragments", 0);
        session.setAttribute("lucidity", 100);

        logger.info("User chose name '{}' and started a game session. Fragments: {}, lucidity: {}. Session id: {}", playerName, 0, 100, session.getId());

        req.getRequestDispatcher("game").forward(req, resp);
    }
}

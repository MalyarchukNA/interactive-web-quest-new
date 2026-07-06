<%--
  Created by IntelliJ IDEA.
  User: senio
  Date: 02.05.2026
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>Отель "Забвение"</title>
</head>
<body>
<div class="game-container">
    <h1>Отель "Забвение"</h1>
    <c:if test="${not empty step.prevText}" >
        <p class="common-text">
                ${step.prevText}<br>
        </p>
    </c:if>

    <p class="common-text">
        ${stepText}
    </p>

    <form action="game" method="post" class="choice-form">

        <input type="hidden" id="nextStepText" name="nextStepText" value="">
        <input type="hidden" id="fragmentDiff" name="fragmentDiff" value="0">
        <input type="hidden" id="lucidityDiff" name="lucidityDiff" value="0">

        <div class="choice-row">
            <input type="radio" id="choice1" name="step" value="${step.nextStep1id}"
                   data-text = "${step.nextStepText1id}"
                   data-fragment = "${step.fragment1id}"
                   data-lucidity = "${step.lucidity1id}" onclick="updateText(this)" required>
            <label for="choice1">${step.option1}</label><br>
        </div>

        <div class="choice-row">
            <input type="radio" id="choice2" name="step" value="${step.nextStep2id}"
                   data-text = "${step.nextStepText2id}"
                   data-fragment = "${step.fragment2id}"
                   data-lucidity = "${step.lucidity2id}" onclick="updateText(this)" required>
            <label for="choice2">${step.option2}</label><br><br>
        </div>

        <button type ="submit" class="submit-btn">Подтвердить выбор</button>

    </form>

    <script>
        function updateText(radio) {
            document.getElementById('nextStepText').value = radio.getAttribute('data-text');
            document.getElementById('fragmentDiff').value = radio.getAttribute('data-fragment');
            document.getElementById('lucidityDiff').value = radio.getAttribute('data-lucidity');
        }
    </script>
</div>

<div class="hud-panel">
    <div class="hud-metric">Имя игрока: ${sessionScope.playerName}</div>
    <div class="hud-metric">Осколки памяти: <span>${sessionScope.fragments}</span></div>
    <div class="hud-metric ${sessionScope.lucidity < 50 ? 'danger' : ''}" >Разум: <span>${sessionScope.lucidity}</span></div>
    <div class="hud-metric">Текущий шаг: ${step.id}</div>
    <div class="hud-metric">ID сессии: ${pageContext.session.id}</div>
</div>

</body>
</html>

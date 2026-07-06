<%--
  Created by IntelliJ IDEA.
  User: senio
  Date: 01.05.2026
  Time: 18:10
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

    <p class = "common-text" >
        ${stepText}<br>
    </p>

    <form action="restart" method="post" class="choice-form">
        <button type="submit" class="submit-btn">Начать новую игру</button>
    </form>
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

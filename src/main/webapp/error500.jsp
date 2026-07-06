<%--
  Created by IntelliJ IDEA.
  User: senio
  Date: 11.05.2026
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>500 - Ошибка сервера</title>
</head>
<body>
<div class="game-container">
    <h1 class="error-title">500</h1>
    <p class="common-text" style="text-align: center; font-style: italic; color: #858585;">
        «Стены отеля пошли трещинами, а пространство вокруг подернулось пеплом. Реальность этого места не выдержала вашего присутствия... Попробуйте вернуться назад, пока тьма не поглотила вас окончательно».
    </p>

    <p class="common-text" style="text-align: center; margin-top: 10px;">
        На сервере отеля произошел критический сбой.
    </p>

    <div class="error-actions">
        <a href="${pageContext.request.contextPath}/start" class="error-link">В холл отеля</a>
        <a href="${pageContext.request.contextPath}/game?step=start" class="error-link">Проснуться на старте</a>
    </div>
</div>
</body>
</html>

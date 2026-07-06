<%--
  Created by IntelliJ IDEA.
  User: senio
  Date: 11.05.2026
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <title>404 - Страница не найдена</title>
</head>
<body>
<div class="game-container">
    <h1 class="error-title">404</h1>

    <p class="common-text" style="text-align: center; font-style: italic; color: #858585;">
        «Вы долго плутали по темным этажам, но эта дверь оказалась заколочена. За ней нет ничего, кроме сырости и глухого эха. Отель "Забвение" играет с вашим разумом...»
    </p>

    <p class="common-text" style="text-align: center; margin-top: 10px;">
        Запрашиваемая страница не существует или была стерта из памяти.
    </p>

    <a href="${pageContext.request.contextPath}/start" class="error-link">Вернуться в холл</a>
</div>
</body>
</html>

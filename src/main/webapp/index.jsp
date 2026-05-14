<%--
  Created by IntelliJ IDEA.
  User: senio
  Date: 21.04.2026
  Time: 21:36
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
    <c:if test="${not empty restart}">
        <p class="common-text">«Снова этот запах... Пыль, старый бархат и ледяное чувство дежавю. Отель «Забвение» узнает вашу походку.

            Книга постояльцев сама открывается на той же странице, где вы когда-то оставили след. Золоченое перо дрожит в ожидании. Вы уже были здесь раньше, не так ли? Отель помнит ваше имя, но помните ли его вы?»</p>
    </c:if>


    <c:if test="${empty restart}">
        <p class="common-text">Вы открываете глаза. В воздухе пахнет старой бумагой и пылью, осевшей на бархатных шторах. Вы стоите в пустом холле перед массивной стойкой из темного дуба. На ней лежит раскрытая «Книга постояльцев».

            Тишина здесь кажется осязаемой, почти тяжелой. Чернила на страницах еще не высохли. Последняя строка пуста. Кажется, отель ждет только вашего подтверждения, чтобы запереть за вами двери».</p>
    </c:if>

    <form action="start" method="post" class="choice-form">
        <label for="playerName" class="form-instruction">"Оставьте запись в книге, чтобы получить ключ от номера."</label>
        <input type = "text" id="playerName" name="playerName" class="input-name"
               placeholder="Ваше имя" required
               value="${sessionScope.playerName != null ? sessionScope.playerName : ''}"> <br><br>
        <button type="submit" class="submit-btn">Позвонить в колокольчик</button>
    </form>
</div>
</body>
</html>

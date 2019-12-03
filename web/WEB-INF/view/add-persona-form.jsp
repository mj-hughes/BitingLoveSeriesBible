<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Mirza" rel="stylesheet">
    <title>Biting Love Series Bible::Add Character</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/BLBible.css">
</head>
<body>
<div id="wrapper">
    <div class="navGrid">
        <button class="regularButton" onclick="location.href='/BitingLoveSeriesBible'">Home</button>
        <button class="selectedButton" onclick="location.href='/BitingLoveSeriesBible/persona/personaList'">Character</button>
        <button class="regularButton">World</button>
        <button class="regularButton">Orgs</button>
        <button class="regularButton">Plot</button>
    </div>
    <div id="header">
        <h1>Biting Love Series Bible</h1>
        <h2>Add/Edit Character</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <form:form action="save" modelAttribute="persona" enctype="multipart/form-data" method="post">
            <form:hidden path="id" value="${persona.id}"/>
            <form:hidden path="pictureLink" value="${persona.pictureLink}"/>

            <table>
                <tr>
                    <td><label>Name</label></td>
                    <td><form:input path="name"/>
                        <form:errors path="name" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label>Age year zero</label></td>
                    <td><form:input type="number" path="ageYearZero"/></td>
                </tr>
                <tr>
                    <td><label>Height</label></td>
                    <td><form:input type="text" maxlength="10" path="height"/>
                        <form:errors path="height" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label>Eye color</label></td>
                    <td><form:input type="color" path="eyeColor"/></td>
                </tr>
                <tr>
                    <td><label>Hair color</label></td>
                    <td><form:input maxlength="20" path="hairColor"/>
                        <form:errors path="hairColor" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label>Body type</label></td>
                    <td><form:input maxlength="50" path="bodyType"/>
                        <form:errors path="bodyType" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label>Picture file link</label></td>
                    <td><input type="file" name="personaImage"/></td>
                </tr>
                <tr>
                    <td><label>Notes</label></td>
                    <td><form:input path="notes"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save" class="save"></td>
                </tr>
            </table>

            <table>
                <caption>Goal/Motivation/Conflict</caption>
                <tr>
                    <th>GMC Type</th>
                    <th>Goal</th>
                    <th>Motivation</th>
                    <th>Conflict</th>
                </tr>
                <c:forEach var="tempGmc" items="${gmcs}">
                    <tr>
                        <td>${tempGmc.formattedGmcType}</td>
                        <td>${tempGmc.goal}</td>
                        <td>${tempGmc.motivation}</td>
                        <td>${tempGmc.conflict}</td>
                    </tr>
                </c:forEach>
            </table>
            <table>
                <caption>Backstories</caption>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                </tr>
                <c:forEach var="tempBackstory" items="${backstories}">
                    <tr>
                        <td>${tempBackstory.backstoryName}</td>
                        <td>${tempBackstory.description}</td>
                    </tr>
                </c:forEach>
            </table>
            <table>
                <caption>Books</caption>
                <tr>
                    <th>Title</th>
                    <th>Book Month</th>
                    <th>Book Year</th>
                </tr>
                <c:forEach var="tempBook" items="${books}">
                    <tr>
                        <td>${tempBook.title}</td>
                        <td>${tempBook.formattedMonth}</td>
                        <td>${tempBook.bookYear}</td>
                    </tr>
                </c:forEach>
                <tr><td><button class="regularButton" onclick="window.location.href='/BitingLoveSeriesBible/book/list'; return false;">
                    See all books</button>
                </td></tr>
            </table>
        </form:form>
    </div>
</div>
<footer>
    <h6>
        Copyright © 2019 Mary Hughes. All rights reserved.<br>
    </h6>
</footer>

<!-- Scripts are for jquery and this site -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/BLBible.js"></script>

</body>
</html>
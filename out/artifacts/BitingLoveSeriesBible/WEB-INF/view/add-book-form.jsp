<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Mirza" rel="stylesheet">
    <title>Biting Love Series Bible::Add Book</title>
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
        <h2>Add Book</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <form:form action="save" modelAttribute="book">
            <form:hidden path="id" value="${book.id}"/>
            <table>
                <tr>
                    <td><label>Title</label></td>
                    <td><form:input path="title"/>
                    <form:errors path="title" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label>Book Year</label></td>
                    <td><form:input path="bookYear"/>
                    <form:errors path="bookYear" cssClass="error"/></td>
                </tr>
                <tr>
                    <td><label>Book Month</label></td>
                    <td><form:input path="bookMonth"/>
                    <form:errors path="bookMonth" cssClass="error"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save" class="save"></td>
                </tr>
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
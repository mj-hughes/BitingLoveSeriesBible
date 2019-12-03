<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Mirza" rel="stylesheet">
    <title>Biting Love Series Bible::Book List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/BLBible.css">
</head>
<body>
    <div id="wrapper">
        <div class="navGrid">
            <button class="selectedButton" onclick="location.href='/BitingLoveSeriesBible'">Home</button>
            <button class="regularButton" onclick="location.href='/BitingLoveSeriesBible/persona/personaList'">Character</button>
            <button class="regularButton">World</button>
            <button class="regularButton">Orgs</button>
            <button class="regularButton">Plot</button>
        </div>

        <div id="header">
            <h1>Biting Love Series Bible</h1>
            <h2>Book List</h2>
        </div>
    </div>
    <div id="container">
        <div id="content">
            <button class="regularButton" onclick="window.location.href='addBookForm'; return false;">Add book</button>
                <form:form action="search" method="GET">
                    Search books by title
                    <input type="search" name="searchTerm"/>
                    <input type="submit" value="Search" class="regularButton"/>
                </form:form>
            <table>
                <tr>
                    <th>Title</th>
                    <th>Month</th>
                    <th>Year</th>
                    <th>Action</th>
                </tr>
                <c:forEach var="tempBook" items="${books}">
                    <!-- Get ids for update and delete -->
                    <c:url var ="updateLink" value="/book/updateBookForm">
                        <c:param name="bookId" value="${tempBook.id}"/>
                    </c:url>
                    <c:url var="deleteLink" value="/book/delete">
                        <c:param name="bookId" value="${tempBook.id}"/>
                    </c:url>

                    <tr>
                        <td>${tempBook.title}</td>
                        <td>${tempBook.formattedMonth}</td>
                        <td>${tempBook.bookYear}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            &nbsp; | &nbsp;
                            <a href="${deleteLink}" onclick="if(!confirm('Are you sure?')) return false">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
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
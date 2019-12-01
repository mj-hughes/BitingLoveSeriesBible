<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Mirza" rel="stylesheet">
    <title>Oops! An Error Occurred</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/BLBible.css">
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
        <h2>Biting Love Series Bible</h2>
    </div>
</div>

<div id="container">
    <div id="content">
        <h3>An error has occurred.</h3>
        <p>${errorMessage}</p>
        <div>
            <h2>404-Page Not Found</h2>
            <figure>
                <picture alt="404 Page Not Found">
                    <img class="img-rounded img-thumbnail" src="resources/img/notFound/PunkNixie.jpg" alt="Should not be found">
                </picture>
                <figcaption>Page Not Found</figcaption>
            </figure>
            <h2>40-Yikes!-Page Should Not Be Found</h2>
            <figure>
                <picture alt="404 Page Should Not BE Found">
                    <img class="img-rounded img-thumbnail" src="resources/img/notFound/PunkSanta.jpg" alt="Should not be found">
                </picture>
                <figcaption>Page Should Not BE Found</figcaption>
            </figure>
            <h2>Don't bump the jams!</h2>
            <h2>(Don't worry. Just click <a href="/BitingLoveSeriesBible">home</a>.)</h2>
        </div>
    </div>
</div>
<footer>
    <h6>
        Copyright © 2019 Mary Hughes. All rights reserved.<br>
    </h6>
</footer>

<!-- Scripts are for jquery and this site -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="resources/js/BLBible.js"></script>

</body>
</html>

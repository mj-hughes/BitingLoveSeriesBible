<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Biting Love Series Bible::Official Website</title>
    <!-- <link rel="shortcut icon" href="images/icons/MHicon.png" type="image/x-icon" /> -->
    <!-- Additional open-source font from Google -->
    <link href="https://fonts.googleapis.com/css?family=Kaushan+Script|Mirza" rel="stylesheet">
    <!-- Stylesheets -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/BLBible.css">
</head>
<body>
<div class="navGrid">
    <button class="selectedButton" onclick="location.href='/BitingLoveSeriesBible'">Home</button>
    <button class="regularButton" onclick="location.href='/BitingLoveSeriesBible/persona/personaList'">Character</button>
    <button class="regularButton">World</button>
    <button class="regularButton">Orgs</button>
    <button class="regularButton">Plot</button>
</div>
<header>
    <h1>Biting Love Series Bible</h1>
</header>
<div class="bodyGrid">
    <button class="bigFont" onclick="location.href='persona/personaList'">Character</button>
    <button class="bigFont">World</button>
    <button class="bigFont">Orgs</button>
    <button class="bigFont">Plot</button>
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
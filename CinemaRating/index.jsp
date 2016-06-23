<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
    <head>
        <title>Index</title>
    </head>
    <body>
        <c:set var="locale" value="en" scope="session"/> <!-- todo -->
        <c:set var="size" value="100" scope="session"/> <!-- todo -->
        <jsp:forward page="/jsp/login.jsp"/>
    </body>
</html>
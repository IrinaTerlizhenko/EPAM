<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="customtags" prefix="ctg"%>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
    <head>
        <title><fmt:message key="title.welcome" bundle="${rb}"/></title>
        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/slider.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/slider.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <c:set var="page" value="path.page.main" scope="session"/>
        <%@ include file="common/menu.jsp"%>

        <div class="wrapper container">
            <div class="content">
                <h3>
                    <fmt:message key="title.welcome" bundle="${rb}"/>
                </h3>
                <br/>
                ${user_id}, <fmt:message key="label.hello" bundle="${rb}"/>!
                <br/>

                <div class="slider">
                    <ul>
                        <c:forEach var="movie" items="${movies}">
                            <li>
                                <a href="${pageContext.request.contextPath}/controller?command=show_movie&movie_id=${movie.id}">
                                    <img src="${pageContext.request.contextPath}/${movie.ref}" alt="${movie.name}">
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
            <%@ include file="common/footer.jsp"%>
        </div>
    </body>
</html>
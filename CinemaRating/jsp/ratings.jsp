<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.pagecontent" var="rb" />

<html>
    <head>
        <title><fmt:message key="title.my_rating" bundle="${rb}"/></title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/cinemarating.js"></script>
    </head>
    <body>
        <c:set var="page" value="path.page.ratings" scope="session"/>
        <%@ include file="common/menu.jsp"%>

        <div class="wrapper container">
            <div class="content text-center">
                <div id="num_page" data-num_page="${num_page}"></div>

                <h1>
                    <fmt:message key="title.my_rating" bundle="${rb}"/>
                </h1>

                <ul class="pagination">
                    <c:if test="${num_page > 0}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${num_page - 1}">&laquo;</a></li>
                    </c:if>
                    <c:forEach var="index" begin="0" end="${fn:length(rating_map) / 10}"> <!-- todo divide -->
                        <li id="top-${index}"><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${index}">${index + 1}</a></li>
                    </c:forEach>
                    <c:if test="${num_page <= fn:length(rating_map) / 10 - 1}"> <!-- todo -->
                        <li><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${num_page + 1}">&raquo;</a></li>
                    </c:if>
                </ul>

                <c:forEach var="entry" items="${rating_map}" begin="${num_page * 10}" end="${num_page * 10 + 9}">
                    <div class="container">
                        <div class="picture col-sm-2 col-sm-offset-2">
                            <img src="${pageContext.request.contextPath}/${entry.key.ref}" alt="${entry.key.name}">
                        </div>
                        <div class="col-sm-6 text-container">
                            <a href="${pageContext.request.contextPath}/controller?command=show_movie&movie_id=${entry.key.id}">
                                <h2>${entry.key.name}</h2>
                            </a>
                            <h4 class="sub-text">
                                <fmt:message key="label.rating" bundle="${rb}"/>: ${entry.key.rating}
                                <br/>
                                <fmt:message key="label.my_rating" bundle="${rb}"/>: ${entry.value}
                            </h4>
                            <div class="justify">${entry.key.description}</div>
                        </div>
                    </div>
                </c:forEach>

                <ul class="pagination">
                    <c:if test="${num_page > 0}">
                        <li><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${num_page - 1}">&laquo;</a></li>
                    </c:if>
                    <c:forEach var="index" begin="0" end="${fn:length(rating_map) / 10}"> <!-- todo divide -->
                        <li id="bottom-${index}"><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${index}">${index + 1}</a></li>
                    </c:forEach>
                    <c:if test="${num_page <= fn:length(rating_map) / 10 - 1}"> <!-- todo -->
                        <li><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${num_page + 1}">&raquo;</a></li>
                    </c:if>
                </ul>
            </div>
            <%@ include file="common/footer.jsp"%>
        </div>
    </body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.pagecontent" var="rb" />

<html>
    <head>
        <title>
            <fmt:message key="title.add_movie" bundle="${rb}"/>
        </title>
        <link rel='stylesheet' href="${pageContext.request.contextPath}/css/style.css" type='text/css'>
        <link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">
    </head>
    <body>
        <c:set var="page" value="path.page.add_movie" scope="session"/>
        <%@ include file="common/menu.jsp"%>

        <div class="wrapper container">
            <form role="form" class="content form-horizontal" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload">
                <input type="hidden" name="command" value="add_movie"/>
                <h3>
                    <fmt:message key="label.add_movie" bundle="${rb}"/>
                </h3>
                <div class="form-group">
                    <label for="movie_name" class="compulsory control-label">
                        <fmt:message key="label.movie_name" bundle="${rb}"/>
                    </label>
                    <input id="movie_name" name="movie_name" class="form-control" type="text" value="" required minlength="1" maxlength="50"
                           pattern=".{1,50}"
                           placeholder=<fmt:message key="label.movie_name" bundle="${rb}"/>>
                </div>
                <div class="form-group">
                    <label class="compulsory control-label">
                        <fmt:message key="label.description" bundle="${rb}"/>
                    </label>
                    <textarea id="description" name="description" class="form-control" required maxlength="1000"
                              placeholder=<fmt:message key="label.description" bundle="${rb}"/>></textarea>
                </div>
                <div class="form-group">
                    <label for="year" class="compulsory control-label">
                        <fmt:message key="label.year" bundle="${rb}"/>
                    </label>
                    <input id="year" name="year" class="form-control" type="number" value="" required min="1888"
                           pattern="[1-9]\d{3}"
                           placeholder=<fmt:message key="label.year" bundle="${rb}"/>>
                </div>
                <div class="form-group">
                    <label for="country" class="compulsory control-label">
                        <fmt:message key="label.country" bundle="${rb}"/>
                    </label>
                    <input id="country" name="country" class="form-control" type="text" value="" required minlength="1" maxlength="100"
                           pattern="([A-Z][A-Za-z]*(, [A-Z][A-Za-z]*)*)|([А-Я][А-Яа-я]*(, [А-Я][А-Яа-я]*)*)"
                           placeholder=<fmt:message key="label.country" bundle="${rb}"/>>
                </div>
                <div class="form-group">
                    <!--<label for="picture" class="control-label">
                        <fmt:message key="label.picture" bundle="${rb}"/>
                    </label>-->
                    <!--<input id="picture" name="picture" class="form-control" type="file" accept="image/*,image/jpeg,image/png">-->
                    <span class="btn btn-success btn-file">
                        <i class="fa fa-plus"> </i>
                        <span>
                            <fmt:message key="label.picture" bundle="${rb}"/>
                        </span>
                        <input id="picture" name="picture" type="file" accept="image/*,image/jpeg,image/png">
                    </span>
                </div>

                <input type="submit" class="btn btn-success" value=<fmt:message key="button.add_movie" bundle="${rb}"/>>
                <!-- todo where to return -->
                <c:choose>
                    <c:when test="${page_type eq 'top'}">
                        <a role="button" href="${pageContext.request.contextPath}/controller?command=top_movies" class="btn btn-default">
                            <fmt:message key="button.cancel" bundle="${rb}"/>
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a role="button" href="${pageContext.request.contextPath}/controller?command=all_movies" class="btn btn-default">
                            <fmt:message key="button.cancel" bundle="${rb}"/>
                        </a>
                    </c:otherwise>
                </c:choose>
            </form>
            <%@ include file="common/footer.jsp"%>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/cinemarating.js"></script> <!-- todo -->
    </body>
</html>

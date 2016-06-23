<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.pagecontent" var="rb" />

<html>
    <head>
        <title>
            <fmt:message key="title.edit_movie" bundle="${rb}"/>
        </title>
        <link rel='stylesheet' href="${pageContext.request.contextPath}/css/style.css" type='text/css'>
    </head>
    <body>
        <c:set var="page" value="path.page.edit_movie" scope="session"/>
        <%@ include file="common/menu.jsp"%>

        <div class="wrapper container">
            <form role="form" class="content form-horizontal" method="POST" enctype="multipart/form-data" action="${pageContext.request.contextPath}/upload">
                <input type="hidden" name="command" value="edit_movie"/>
                <input type="hidden" name="movie_id" value="${movie.id}"/>
                <h3>
                    <fmt:message key="label.edit_movie" bundle="${rb}"/>
                </h3>
                <div class="form-group">
                    <label for="movie_name" class="compulsory control-label">
                        <fmt:message key="label.movie_name" bundle="${rb}"/>
                    </label>
                    <input id="movie_name" name="movie_name" class="form-control" type="text" required minlength="1" maxlength="50"
                           value="${movie.name}"
                           pattern=".{1,50}"
                           placeholder=<fmt:message key="label.movie_name" bundle="${rb}"/>>
                </div>
                <div class="form-group">
                    <label class="compulsory control-label">
                        <fmt:message key="label.description" bundle="${rb}"/>
                    </label>
                            <textarea id="description" name="description" class="form-control" required maxlength="1000"
                                      placeholder=<fmt:message key="label.description" bundle="${rb}"/>>${movie.description}</textarea>
                </div>
                <div class="form-group">
                    <label for="year" class="compulsory control-label">
                        <fmt:message key="label.year" bundle="${rb}"/>
                    </label>
                    <input id="year" name="year" class="form-control" type="number" required min="1888"
                           value="${movie.year}"
                           pattern="[1-9]\d{3}"
                           placeholder=<fmt:message key="label.year" bundle="${rb}"/>>
                </div>
                <div class="form-group">
                    <label for="country" class="compulsory control-label">
                        <fmt:message key="label.country" bundle="${rb}"/>
                    </label>
                    <input id="country" name="country" class="form-control" type="text" required minlength="1" maxlength="100"
                           value="${movie.country}"
                           pattern="([A-Z][A-Za-z]*(, [A-Z][A-Za-z]*)*)|([А-Я][А-Яа-я]*(, [А-Я][А-Яа-я]*)*)"
                           placeholder=<fmt:message key="label.country" bundle="${rb}"/>>
                </div>
                <div class="form-group">
                    <label for="picture" class="control-label">
                        <fmt:message key="label.picture" bundle="${rb}"/>
                    </label>
                    <img src="${pageContext.request.contextPath}/${movie.ref}" alt="no picture">
                    <input id="picture" name="picture" class="form-control" type="file" accept="image/*,image/jpeg,image/png">
                </div>
                <input type="submit" class="btn btn-success" value=<fmt:message key="button.edit_movie" bundle="${rb}"/>>
                <a role="button" href="${pageContext.request.contextPath}/controller?command=show_movie&movie_id=${movie.id}" class="btn btn-default">
                    <fmt:message key="button.cancel" bundle="${rb}"/>
                </a>
            </form>
            <%@ include file="common/footer.jsp"%>
        </div>
    </body>
</html>

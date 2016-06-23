<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.pagecontent" var="rb" />

<html>
    <head>
        <title><fmt:message key="title.ban" bundle="${rb}"/></title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <c:set var="page" value="path.page.ban" scope="session"/>
        <%@ include file="common/menu.jsp"%>

        <div class="wrapper container">
            <form class="content form-horizontal" method="POST" action="${pageContext.request.contextPath}/controller">
                <input type="hidden" name="command" value="ban"/>
                <input type="hidden" name="id" value="${user.id}"/>
                <div class="form-group">
                    <label for="user" class="control-label">
                        <fmt:message key="ban.user" bundle="${rb}"/>
                    </label>
                    <input id="user" type="text" class="form-control disabled" maxlength="30" value="${user.login}"/>
                </div>
                <div class="form-group">
                    <label for="type" class="control-label">
                        <fmt:message key="ban.type" bundle="${rb}"/>
                    </label>
                    <select id="type" name="type" class="form-control">
                        <option id="ABUSE" value="ABUSE">ABUSE</option>
                        <option id="SPAM" value="SPAM">SPAM</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="time" class="control-label">
                        <fmt:message key="ban.time" bundle="${rb}"/>
                    </label>
                    <input id="time" type="date" name="expiration" class="form-control"/> <!-- todo -->
                </div>
                <div class="form-group">
                    <label for="reason" class="control-label">
                        <fmt:message key="ban.reason" bundle="${rb}"/>
                    </label>
                    <textarea id="reason" name="reason" class="form-control" maxlength="300"></textarea>
                </div>
                <input type="submit" class="btn btn-warning" value=<fmt:message key="button.ban" bundle="${rb}"/>>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/controller?command=profile&id=${user.id}" role="button">
                    <fmt:message key="button.cancel" bundle="${rb}"/>
                </a>
            </form>
            <%@ include file="common/footer.jsp"%>
        </div>
    </body>
</html>

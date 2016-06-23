<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title><fmt:message key="title.profile" bundle="${rb}"/></title>
    </head>
    <body>
        <c:set var="page" value="path.page.profile" scope="session"/>
        <%@ include file="common/menu.jsp"%>

        <div class="wrapper container">
            <div class="content content-container">
                <div class="picture col-sm-3 col-sm-offset-2">
                    <a data-toggle="modal" data-target="#full-picture">
                        <img src="${pageContext.request.contextPath}/${user.photo}" alt="">
                    </a>

                    <div id="full-picture" class="modal fade" tabindex="-1">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
                                    <h4 class="modal-title">${user.login}</h4>
                                </div>
                                <div class="modal-body">
                                    <img src="${pageContext.request.contextPath}/${user.photo}" alt="">
                                </div>
                                <div class="modal-footer">
                                    <button class="btn btn-default" type="button" data-dismiss="modal">
                                        <fmt:message key="button.close" bundle="${rb}"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <c:choose>
                        <c:when test="${user_id eq user.id}">
                            <a class="btn btn-default btn-block" href="${pageContext.request.contextPath}/controller?command=init_edit_profile" role="button">
                                <div>
                                    <fmt:message key="profile.edit" bundle="${rb}"/>
                                </div>
                            </a>

                            <a class="btn btn-default btn-block" data-toggle="modal" data-target="#delete-profile">
                                <fmt:message key="profile.delete" bundle="${rb}"/>
                            </a>

                            <div id="delete-profile" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
                                            <h4 class="modal-title"><fmt:message key="label.profile_deletion" bundle="${rb}"/></h4>
                                        </div>
                                        <div class="modal-body">
                                            <fmt:message key="label.profile_deletion.question" bundle="${rb}"/>? <fmt:message key="label.deletion.warning" bundle="${rb}"/>.
                                        </div>
                                        <div class="modal-footer">
                                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/controller?command=delete_profile" role="button">
                                                <fmt:message key="button.delete" bundle="${rb}"/>
                                            </a>
                                            <button class="btn btn-default" type="button" data-dismiss="modal">
                                                <fmt:message key="button.close" bundle="${rb}"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <ctg:admin>
                                <a class="btn btn-default btn-block" href="${pageContext.request.contextPath}/controller?command=init_ban&id=${user.id}" role="button">
                                    <div>
                                        <fmt:message key="profile.ban" bundle="${rb}"/>
                                    </div>
                                </a>
                            </ctg:admin>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="col-sm-5 text-container">
                    <h2>${user.login}</h2>
                    <h4 class="sub-text">
                        <fmt:message key="label.status" bundle="${rb}"/>: ${user.status}
                        <br/>
                        <fmt:message key="label.num_rated" bundle="${rb}"/>: ${user.numRated}
                    </h4>
                    <table class="info table-hover">
                        <tbody>
                            <tr>
                                <td class="type">
                                    <fmt:message key="profile.login" bundle="${rb}"/>
                                </td>
                                <td>${user.login}</td>
                            </tr>
                            <tr>
                                <td class="type">
                                    <fmt:message key="profile.name" bundle="${rb}"/>
                                </td>
                                <td>${user.name}</td>
                            </tr>
                            <tr>
                                <td class="type">
                                    <fmt:message key="profile.surname" bundle="${rb}"/>
                                </td>
                                <td>${user.surname}</td>
                            </tr>
                            <tr>
                                <td class="type">
                                    <fmt:message key="profile.email" bundle="${rb}"/>
                                </td>
                                <td>${user.email}</td>
                            </tr>
                            <tr>
                                <td class="type">
                                    <fmt:message key="profile.reg_date" bundle="${rb}"/>
                                </td>
                                <td>${user.regDate}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <%@ include file="common/footer.jsp"%>
        </div>
    </body>
</html>
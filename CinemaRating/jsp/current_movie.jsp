<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="customtags" prefix="ctg"%>
<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="resources.pagecontent" var="rb" />
<html>
    <head>
        <title>${movie.name}</title>
        <link type='text/css' rel='stylesheet' href="${pageContext.request.contextPath}/css/style.css">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/review.css">
    </head>
    <body>
        <c:set var="page" value="path.page.current_movie" scope="session"/>
        <%@ include file="common/menu.jsp"%>

        <div class="wrapper container">
            <div class="content">
                <h1 class="section-title">
                    <fmt:message key="label.about" bundle="${rb}"/> ${movie.name}
                </h1>
                <div class="container content-container">
                    <div class="picture col-sm-3 col-sm-offset-2">
                        <a data-toggle="modal" data-target="#full-picture">
                            <img src="${pageContext.request.contextPath}/${movie.ref}" alt="${movie.name}">
                        </a>

                        <div id="full-picture" class="modal fade" tabindex="-1">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
                                        <h4 class="modal-title">${movie.name}</h4>
                                    </div>
                                    <div class="modal-body">
                                        <img src="${pageContext.request.contextPath}/${movie.ref}" alt="${movie.name}">
                                    </div>
                                    <div class="modal-footer">
                                        <button class="btn btn-default" type="button" data-dismiss="modal">
                                            <fmt:message key="button.close" bundle="${rb}"/>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-5 text-container">
                        <h2>${movie.name}</h2>
                        <ctg:admin>
                            <a class="movie-icon" data-toggle="modal" data-target="#delete-movie" href="#">
                                <img src="${pageContext.request.contextPath}/img/delete.png" alt="delete">
                            </a>

                            <div id="delete-movie" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header"><button class="close" type="button" data-dismiss="modal">×</button>
                                            <h4 class="modal-title"><fmt:message key="label.movie_deletion" bundle="${rb}"/></h4>
                                        </div>
                                        <div class="modal-body">
                                            <fmt:message key="label.movie_deletion.question" bundle="${rb}"/> ${movie.name}? <fmt:message key="label.deletion.warning" bundle="${rb}"/>.
                                        </div>
                                        <div class="modal-footer">
                                            <a class="btn btn-danger" href="${pageContext.request.contextPath}/controller?command=delete_movie&movie_id=${movie.id}" role="button">
                                                <fmt:message key="button.delete" bundle="${rb}"/>
                                            </a>
                                            <button class="btn btn-default" type="button" data-dismiss="modal">
                                                <fmt:message key="button.close" bundle="${rb}"/>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <a class="movie-icon" href="${pageContext.request.contextPath}/controller?command=init_edit_movie&movie_id=${movie.id}">
                                <img src="${pageContext.request.contextPath}/img/edit.png" alt="edit">
                            </a>
                        </ctg:admin>
                        <h4 class="sub-text">
                            <fmt:message key="label.rating" bundle="${rb}"/>: ${movie.rating}
                        </h4>
                        <table class="info table-hover">
                            <tbody>
                                <tr>
                                    <td class="type">
                                        <fmt:message key="label.year" bundle="${rb}"/>
                                    </td>
                                    <td>${movie.year}</td>
                                </tr>
                                <tr>
                                    <td class="type">
                                        <fmt:message key="label.country" bundle="${rb}"/>
                                    </td>
                                    <td>${movie.country}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">${movie.description}</td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <div class="panel-group" id="collapse-group">
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a data-toggle="collapse" data-parent="#collapse-group" href="#el1">
                                                            <fmt:message key="label.rating" bundle="${rb}"/>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="el1" class="panel-collapse collapse in">
                                                    <!-- <a class="btn btn-primary" href="${pageContext.request.contextPath}/controller?command=init_rating&movie_id=${movie.id}" role="button">
                                                        <fmt:message key="label.rate" bundle="${rb}"/>
                                                    </a> -->

                                                    <form role="form" name="rating-form" class="rating-form form-horizontal" action="${pageContext.request.contextPath}/controller">
                                                        <input type="hidden" name="command" value="rate"/>
                                                        <input type="hidden" name="movie_id" value="${movie_id}"/>
                                                        <h3>
                                                            <fmt:message key="header.rating_review" bundle="${rb}"/>
                                                        </h3>
                                                        <div class="form-group">
                                                            <label for="rating" class="control-label">
                                                                <fmt:message key="label.rating" bundle="${rb}"/>:
                                                            </label>
                                                            <input id="rating" name="rating" class="form-control" type="number" size="2" name="rating" min="0" max="10" value="${rating}" required>
                                                        </div>
                                                        <div class="form-group">
                                                            <c:choose>
                                                                <c:when test="${banned == false}">
                                                                    <label for="review" class="control-label">
                                                                        <fmt:message key="label.review" bundle="${rb}"/>:
                                                                    </label>
                                                                    <textarea id="review" name="review" class="form-control" maxlength="1000"
                                                                              placeholder=<fmt:message key="placeholder.review" bundle="${rb}"/>>${review}</textarea>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <label>
                                                                        <fmt:message key="label.review-banned" bundle="${rb}"/>.
                                                                    </label>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                        <br/>
                                                        <input class="btn btn-success" type="submit" value=<fmt:message key="button.rate" bundle="${rb}"/>>
                                                    </form>

                                                </div>
                                            </div>
                                            <div class="panel panel-default">
                                                <div class="panel-heading">
                                                    <h4 class="panel-title">
                                                        <a data-toggle="collapse" data-parent="#collapse-group" href="#el2">
                                                            <fmt:message key="label.lazy" bundle="${rb}"/>
                                                        </a>
                                                    </h4>
                                                </div>
                                                <div id="el2" class="panel-collapse collapse panel-rating">
                                                    <div class="rating">
                                                        <a href="${pageContext.request.contextPath}/controller?command=rate&movie_id=${movie.id}&rating=9">
                                                            <span>☆</span>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/controller?command=rate&movie_id=${movie.id}&rating=7">
                                                            <span>☆</span>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/controller?command=rate&movie_id=${movie.id}&rating=5">
                                                            <span>☆</span>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/controller?command=rate&movie_id=${movie.id}&rating=3">
                                                            <span>☆</span>
                                                        </a>
                                                        <a href="${pageContext.request.contextPath}/controller?command=rate&movie_id=${movie.id}&rating=1">
                                                            <span>☆</span>
                                                        </a>
                                                    </div>
                                                    <fmt:message key="label.lazy.rules.1" bundle="${rb}"/>
                                                    <br/>
                                                    <fmt:message key="label.lazy.rules.2" bundle="${rb}"/>
                                                    <br/>
                                                    <fmt:message key="label.lazy.rules.3" bundle="${rb}"/>
                                                    <br/>
                                                    <fmt:message key="label.lazy.rules.4" bundle="${rb}"/>
                                                    <br/>
                                                    <fmt:message key="label.lazy.rules.5" bundle="${rb}"/>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <c:if test="${(reviewed == false) && (banned == false)}">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-1 col-sm-offset-2">
                                <div class="thumbnail">
                                    <a href="${pageContext.request.contextPath}/controller?command=profile&id=${user_id}">
                                        <img class="img-responsive user-photo" src="${pageContext.request.contextPath}/${user.photo}" alt="">
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-7 review-main-info">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <a href="${pageContext.request.contextPath}/controller?command=profile&id=${user_id}">
                                            <strong>${user.login}</strong>
                                        </a>
                                    </div>
                                    <div class="panel-body">
                                        <form method="POST" action="${pageContext.request.contextPath}/controller">
                                            <input type="hidden" name="command" value="edit_review">
                                            <input type="hidden" name="user_id" value="${user_id}">
                                            <input type="hidden" name="movie_id" value="${movie.id}">
                                            <textarea name="review" class="form-control" maxlength="1000"></textarea>
                                            <input type="submit" class="btn btn-success" value=<fmt:message key="button.leave_review" bundle="${rb}"/>>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:forEach var="review" items="${reviews}">
                    <%@ include file="common/review.jsp"%>
                </c:forEach>
            </div>
            <%@ include file="common/footer.jsp"%>
        </div>
    </body>
</html>
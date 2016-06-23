<ul class="pagination">
    <c:if test="${num_page > 0}">
        <li><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${num_page - 1}">&laquo;</a></li>
    </c:if>
    <c:forEach var="index" begin="0" end="${fn:length(movies) / 10}"> <!-- todo divide -->
        <li id="bottom-${index}"><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${index}">${index + 1}</a></li>
    </c:forEach>
    <c:if test="${num_page <= fn:length(movies) / 10 - 1}"> <!-- todo -->
        <li><a href="${pageContext.request.contextPath}/controller?command=change_page&num_page=${num_page + 1}">&raquo;</a></li>
    </c:if>
</ul>

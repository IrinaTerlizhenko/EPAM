<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css">

<footer class="footer-distributed footer">
    <div class="footer-left">
        <!-- <h3>Company<span>logo</span></h3> -->
        <img src="${pageContext.request.contextPath}/img/CinemaRating_small.png" alt="<fmt:message key="footer.project" bundle="${rb}"/>">
        <p class="footer-links">
            <a href="${pageContext.request.contextPath}/controller?command=redirect&nextPage=path.page.main">Home</a>
            |
            <a href="#">About</a>
            |
            <a href="#">Contact</a>
        </p>
        <p class="footer-company-name"><fmt:message key="footer.company" bundle="${rb}"/> &copy; <fmt:message key="footer.year" bundle="${rb}"/></p>
    </div>
    <div class="footer-center">
        <div>
            <i class="fa fa-map-marker"></i>
            <p><span><fmt:message key="footer.address" bundle="${rb}"/></span> <fmt:message key="footer.city" bundle="${rb}"/></p>
        </div>
        <div>
            <i class="fa fa-phone"></i>
            <p><fmt:message key="footer.phone" bundle="${rb}"/></p>
        </div>
        <div>
            <i class="fa fa-envelope"></i>
            <p><a href="mailto:<fmt:message key="footer.email" bundle="${rb}"/>"><fmt:message key="footer.email" bundle="${rb}"/></a></p>
        </div>
    </div>
    <div class="footer-right">
        <p class="footer-company-about">
            <span><fmt:message key="footer.about" bundle="${rb}"/></span>
            <fmt:message key="footer.info" bundle="${rb}"/>
        </p>
        <div class="footer-icons">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-github"></i></a>
        </div>
    </div>
</footer>
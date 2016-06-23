<div class="container content-container">
    <div class="picture col-sm-2 col-sm-offset-2">
        <img src="${pageContext.request.contextPath}/${user.photo}" alt="${user.login}">
    </div>
    <div class="col-sm-6 text-container">
        <a href="${pageContext.request.contextPath}/controller?command=profile&id=${user.id}">
            <h2>${user.login}</h2>
        </a>
        <h4 class="sub-text">
            <fmt:message key="label.status" bundle="${rb}"/>: ${user.status}
            <br/>
            <fmt:message key="label.num_rated" bundle="${rb}"/>: ${user.numRated}
        </h4>
        <table class="info table-hover">
            <tbody>
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
            </tbody>
        </table>
    </div>
</div>

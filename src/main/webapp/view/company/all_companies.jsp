<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en-us">
<head>
    <title>Company list</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<table>
    <tbody>
    <c:if test="${not empty companies}">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Company name</th>
                <th>City</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${companies}" var="company">
                <tr>
                    <td>
                        <c:out value="${company.id}"/>
                    </td>
                    <td>
                        <c:out value="${company.name}"/>
                    </td>
                    <td>
                        <c:out value="${company.city}"/>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
    </tbody>
</table>

</body>
</html>
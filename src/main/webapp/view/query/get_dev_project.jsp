<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en-us">
<head>
    <title>Developers</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<table>
    <tbody>
    <c:if test="${not empty message}">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Gender</th>
                <th>Salary</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${message}" var="developer">
                <tr>
                    <td>
                        <c:out value="${developer.id}"/>
                    </td>
                    <td>
                        <c:out value="${developer.name}"/>
                    </td>
                    <td>
                        <c:out value="${developer.age}"/>
                    </td>
                    <td>
                        <c:out value="${developer.gender}"/>
                    </td>
                    <td>
                        <c:out value="${developer.salary}"/>
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
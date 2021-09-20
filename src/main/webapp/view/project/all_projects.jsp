<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en-us">
<head>
    <title>View all projects</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<table>
    <tbody>

    <c:if test="${not empty projects}">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Project Name</th>
                <th>Field</th>
                <th>Cost</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${projects}" var="project">
                <tr>
                    <td>
                        <c:out value="${project.id}"/>
                    </td>
                    <td>
                        <c:out value="${project.name}"/>
                    </td>
                    <td>
                        <c:out value="${project.field}"/>
                    </td>
                    <td>
                        <c:out value="${project.cost}"/>
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
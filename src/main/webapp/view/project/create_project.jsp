<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Create project</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<form method="post" action="createProject">
    <table>
        <tbody>
        <tr>
            <td>
                <p>ID</p>
            </td>
            <td>
                <input type="number" name="id" tabindex="1"></td>
        </tr>
        <tr>
            <td>
                <p>Name</p>
            </td>
            <td>

                <input type="text" name="name" tabindex="2"></td>
        </tr>
        <tr>
            <td>
                <p>Field</p>
            </td>
            <td>
                <input type="text" name="field" tabindex="3"></td>
        </tr>
        <tr>
            <td>
                <p>Cost</p>
            </td>
            <td><input type="number" name="cost" tabindex="4"></td>
        </tr>
        </tbody>
    </table>
    <button type="submit" class="button">Create</button>
</form>
<c:if test="${not empty errors}">
    <c:forEach items="${errors}" var="error">
        <p style="color: red">${error.field} ${error.error}</p><br>
    </c:forEach>
</c:if>
<c:if test="${not empty message}">
    <p style="color: darkslateblue">${message}</p><br>

</c:if>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en-us">
<head>
    <title>Create developer</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To create Developer, provide an ID, name, age, gender: </h3>
<form method="post" action="createDeveloper">
    <table>
        <tbody>
        <tr>
            <td>
                <p>ID</p>
            </td>
            <td>
                <input type="text" name="id" tabindex="1"></td>
        </tr>
        <tr>
            <td>
                <p>Name</p>
            </td>
            <td>

                <input type="text" name="name" tabindex="1"></td>
        </tr>
        <tr>
            <td>
                <p>Age</p>
            </td>
            <td>

                <input type="number" name="age" tabindex="2">
            </td>
        </tr>
        <tr>
            <td>
                <p>Gender</p>
            </td>
            <td>
               <input type="text" name="gender" tabindex="5">
            </td>
        </tr>
        <tr>
            <td>
                <p>Salary</p>
            </td>
            <td>
                <input type="number" name="salary" tabindex="5">
            </td>
        </tr>
        <tr>
        <tr>
        </tr></tbody>
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

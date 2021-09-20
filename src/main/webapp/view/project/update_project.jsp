<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Update Project</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To update Project Cost, provide an ID and new Cost: </h3>
<form method="post" action="updateProject">
    <table>
        <tbody>
        <tr>
            <td>
                <p>Project ID:</p>
            </td>
            <td><input type="number" name="id"></td>
        </tr>
        <tr>
            <td>
                <p>New Cost: </p>
            </td>
            <td><input type="number" name="cost"></td>
        </tr>
        </tbody>
    </table>
    <button type="submit" class="button">Update</button>
</form>
<c:if test="${not empty message}">
    <p style="color: darkslateblue">${message}</p><br>
</c:if>
</body>
</html>
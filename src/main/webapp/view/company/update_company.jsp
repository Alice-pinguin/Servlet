<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Update Company</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To update Company provide an ID and new city and industry: </h3>
<form method="post" action="updateCompany">
    <table>
        <tbody>
        <tr>
            <td>
                <p>Company ID:</p>
            </td>
            <td><input type="number" name="id"></td>
        </tr>
        <tr>
            <td>
                <p>New city: </p>
            </td>
            <td><input type="text" name="city"></td>
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
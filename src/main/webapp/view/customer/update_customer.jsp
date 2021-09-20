<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en-us">
<head>
    <title>Update Customer</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To update Customer Phone number, provide an ID and new Phone number: </h3>
<form method="post" action="updateCustomer">
    <table>
        <tbody>
        <tr>
            <td>
                <p>Customer ID:</p>
            </td>
            <td><input type="number" name="id"></td>
        </tr>
        <tr>
            <td>
                <p>new name:</p>
            </td>
            <td> <input type="text" name="name"></td>
            <td>
                <p>new industry: </p>
            </td>
            <td><input type="text" name="industry"></td>
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
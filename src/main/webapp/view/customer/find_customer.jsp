<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en-us">
<head>
    <title>Find by ID</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<form method="post" action="findCustomer">
    <table>
        <tbody>
        <tr>
            <td>
                <p>ID</p>
            </td>
            <td>

                <input type="number" name="id" tabindex="1"></td>
        </tr>

        </tbody>
    </table>
    <button type="submit" class="button">Find</button>
</form>
<c:if test="${not empty message}">
    <p style="color: darkslateblue">${message}</p><br>
</c:if>

</body>
</html>
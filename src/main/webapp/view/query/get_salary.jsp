<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en-us">
<head>
    <title>Developers salary on project</title>
    <style>
        <%@include file="/view/style.css" %>
    </style>
</head>
<body>
<c:import url="/view/navigation.jsp"/>
<h3>To find developers salary enter ID project</h3>
<form method="post" action="getSalary">
    <table>
        <tbody>
        <tr>
            <td>
                <p>ID project</p>
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
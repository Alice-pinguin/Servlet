<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en-us">
<head>
    <title>Customers list</title>
            <style>
                <%@include file="/view/style.css" %>
            </style>
        </head>
<body>
<c:import url="/view/navigation.jsp"/>
<table>
    <tbody>

    <c:if test="${not empty customers}">
        <table class="table">
            <thead>
            <tr>
                <th>Customer ID</th>
                <th> Name</th>
                <th>City</th>
                <th>Industry</th>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${customers}" var="customer">
                <tr>
                    <td>
                        <c:out value="${customer.id}"/>
                    </td>
                    <td>
                        <c:out value="${customer.name}"/>
                    </td>
                    <td>
                        <c:out value="${customer.city}"/>
                    </td>
                    <td>
                        <c:out value="${customer.industry}"/>
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

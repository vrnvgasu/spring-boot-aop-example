<%--isELIgnored - используем конструкции типа ${message} (не игнорим их)--%>
<%@ page isELIgnored="false" %>
<%--c - как алиас--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <meta charset="UTF-8">
    <title>ALL USERS</title>
</head>
<body>
<h1>All users:</h1>
<c:forEach items="${userList}" var="user">
  ID: ${user.id} <br>
  NAME: ${user.name} <br><hr>
</c:forEach>
</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: ttl
  Date: 2020/6/20
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>展示所有用户</title>
</head>
<body>
<h1>用户列表</h1>
<c:forEach items="${requestScope.users}" var="user">
    ${user.id} -- ${user.name} -- ${user.age} -- <fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd"/>
</c:forEach>
</body>
</html>

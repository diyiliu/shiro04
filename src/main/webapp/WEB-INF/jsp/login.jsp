<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
    <style>.error{color:red;}</style>
</head>
<body>

<div class="error">
    <c:if test="${not empty param.kickout}">您被踢出登录。<br/></c:if>
    ${error}
</div>
<form action="/login.htm" method="post">
    用户名：<input type="text" name="username" value="<shiro:principal/>" size="15"><br/><br/>
    密  码：<input type="password" name="password" size="16"><br/><br/>
    自动登录：<input type="checkbox" name="rememberMe" value="true"><br/><br/>
    <input type="submit" value="登录">
</form>
</body>
</html>
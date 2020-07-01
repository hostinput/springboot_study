<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div style=" text-align:  center;width: 100%;margin-top: 120px">
    <h1>登录页</h1>
    <form action="/first_demo/view/userlogin" accept="post">
        <label>用户名： <input type="text" name="username"></label>
        <br/>
        <label>密&nbsp;&nbsp;码： <input type="text" name="password"></label>
        <br/>
        <input type="submit" value="登录"/>
        <c:if test="${not empty msg}">
            <br/>
            <label style="color: red">错误提示：<input  readonly value="${msg}" style="color: red"></label>
        </c:if>
    </form>

</div>
</body>
</html>
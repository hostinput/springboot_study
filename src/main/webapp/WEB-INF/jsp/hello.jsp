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

    hello JSP. 现在时间是 ${now}
    <hr>
    <br/>
    ${content} /  ${ value}
    <h1>______________________</h1>
    <c:if test="${ not empty content }">
        hello spring xubo
    </c:if>

    <!--测试发现更改jsp文件,也需要重启启动类(清缓存也不行!) -->
</div>
</body>
</html>
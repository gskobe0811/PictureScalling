<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2017/7/27
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="css/style.css" />
</head>
<body>
<a href="jsp/index.jsp">图片预定义缩放&nbsp&nbsp&nbsp&nbsp</a>
<a href="">根据配置生成图片&nbsp&nbsp&nbsp&nbsp</a>
<a href="">图片裁剪&nbsp&nbsp&nbsp&nbsp</a>
<br/>
<br/>
</body>
</html>

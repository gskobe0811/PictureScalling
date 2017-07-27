<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.6.2.min.js" ></script>
<script >
    function addPicture() {
    form = document.getElementById("productAdd");
    var formData = new FormData(form);
    $.ajax(
    {
    async: false,
    url: "upload",
    type: "POST",
    data: formData,
    mimeType: "multipart/form-data",
    dataType:'json',
    contentType: false,
    cache: false,
    processData: false,
    success: function (result) {
        var obj=eval(result);
        if (obj.status=="empty"){
            alert("上传的图片为空")
        }else if (obj.status=="n"){
            alert("上传的图片格式仅支持jpg,png,jpeg,webp格式")
        }else {
            window.location.href="index";
        }

    },

    });
    }

    </script>
</head>

<html>
<body>
<div class="error-box"></div>
<form class="productAdd" id="productAdd" method="post" enctype="multipart/form-data" >
<input type="file" class="text" name="file" id="picture" multiple="multiple"
/><span></span>
<input type="submit" id="submit" name="submit" value="生成预定义尺寸的图片" onclick="addPicture()"/>

</form>
<img src="scalle?id=obj.id"/>



</body>
</html>

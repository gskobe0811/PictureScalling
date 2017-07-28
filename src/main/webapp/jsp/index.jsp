<%@ page import="javax.imageio.ImageIO" %>
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
    <title>图片预定义缩放</title>
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script>
    var name=${status};
    console.log(name);
    if(name==1){
    alert("图片为空") ;
    }else if(name==0){
    alert("仅支持(jpg)|(png)|(gif)|(jpeg)格式");
    }
    </script>
</head>

<html>
<body>
<jsp:include page="head.jsp"/>
<div>
    <form class="productAdd" id="productAdd" method="post" enctype="multipart/form-data" action="upload">
        <input type="file" class="text" name="file" id="picture" multiple="multiple"
        /><span></span>
        <input type="submit" id="submit" name="submit" value="生成预定义尺寸的图片"/>
        <%--onclick="addPicture()"--%>
    </form>
</div>
<div id="test">
    <c:if test="${pic!=null}">
        <table>
            <tr>
                <td>
                    <label>尺寸：1080*480</label>
                </td>
            </tr>
            <tr>
                <td>
                    <img src="scalle?id=${pic.id}&w=1080&h=480"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>尺寸：1080*450</label>
                </td>
            </tr>
            <tr>
                <td>
                    <img src="scalle?id=${pic.id}&w=1080&h=450"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>尺寸：1080*330</label>
                </td>
            </tr>
            <tr>
                <td>
                    <img src="scalle?id=${pic.id}&w=1080&h=330"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label>尺寸：1080*392</label>
                </td>
            </tr>
            </tr>
            <tr>
                <td>
                    <img src="scalle?id=${pic.id}&w=1080&h=392"/>
                </td>
            </tr>
        </table>
    </c:if>
</div>


</body>
</html>

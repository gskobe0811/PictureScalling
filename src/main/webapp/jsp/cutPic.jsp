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
    <title>图片剪裁</title>
    <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
    <script>
        var name=${status};
        console.log(name);
        if(name==1){
            alert("图片为空") ;
        }else if(name==0){
            alert("仅支持(jpg)|(png)|(gif)|(jpeg)格式");
        }

        jQuery(document).ready(function(){
            jQuery('#imgCrop').Jcrop({
                onChange: showCoords,
                onSelect: showCoords
            });
            jQuery('#cropButton').click(function(){
                var x = jQuery("#x").val();
                var y = jQuery("#y").val();
                var w = jQuery("#w").val();
                var h = jQuery("#h").val();
                if(w == 0 || h == 0 ){
                    alert("您还没有选择图片的剪切区域,不能进行剪切图片!");
                    return;
                }
                alert("你要剪切图片的X坐标: "+x + ",Y坐标: " + y + ",剪切图片的宽度: " + w + ",高度：" + h );
                if(confirm("确定按照当前大小剪切图片吗")){
                    document.form1.submit();
                }
            });
        });
        function showCoords(c)
        {
            jQuery('#x').val(c.x);
            jQuery('#y').val(c.y);
            jQuery('#x2').val(c.x2);
            jQuery('#y2').val(c.y2);
            jQuery('#w').val(c.w);
            jQuery('#h').val(c.h);

            jQuery('#labelX').val(c.x);
            jQuery('#labelY').val(c.y);
            jQuery('#labelX2').val(c.x2);
            jQuery('#labelY2').val(c.y2);
            jQuery('#labelW').val(c.w);
            jQuery('#labelH').val(c.h);
            //显示剪切按键
            jQuery('#cropTd').css("display","");

        }
    </script>
</head>

<html>
<body>
<jsp:include page="head.jsp"/>
<div>
    <form class="productAdd" id="productAdd" method="post" enctype="multipart/form-data" action="uploadCut">
        <input type="file" class="text" name="file" id="picture" multiple="multiple"
        /><span></span>
        <input type="submit" id="submit" name="submit" value="上传头像"/>
        <%--onclick="addPicture()"--%>
    </form>
</div>
    <div id="test">
        <c:if test="${pic!=null}">
            <img id="element_id" src="${pic.src}"/>
        </c:if>
    </div>
</body>
</html>
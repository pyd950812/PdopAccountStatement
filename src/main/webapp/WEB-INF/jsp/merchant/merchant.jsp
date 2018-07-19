<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String downloadPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/downloadFile";
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<base href="<%=basePath %>"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>select</title>
    <link
            href="${pageContext.request.contextPath}/static/css/application.css"
            rel="stylesheet">
    <script
            src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<button onclick="toHome()"
        style="background-color:#ff2832;color:#fff;text-align:center;letter-spacing:5px;width: 200px;height: 30px;">回到首页
</button>

<form action="hello/downloadExcle" method="post" name="selectForm" id="selectForm">
    <div id="selectJsp">
        <table width="100%" border="1" cellpadding="0" cellspacing="1"
               class="tableLine DoubleColorTable">

            <tr>
                <td colspan="5" align="center" class="tableLineBg">查询条件列表</td>
            </tr>
            <tr>
                <td>商户名称(单选):<div name="merchantName">
                    <c:forEach var="m" items="${merchantList}" varStatus="index">
                        <input type="checkbox" name="merchantList" id="${m.ID}" value="${m.ID}"/>${m.NAME}
                    </c:forEach>
                </div></td>


                <td>
                    <button onclick="$('#selectForm').submit()"
                            style="background-color:#ff2832;color:#fff;text-align:center;letter-spacing:5px;width: 200px;">
                        开始下载账单
                    </button>
                </td>
            </tr>
        </table>
    </div>
</form>


<script>

    function toHome() {
        window.location.href = "hello/login";
    }




</script>


</body>
</html>
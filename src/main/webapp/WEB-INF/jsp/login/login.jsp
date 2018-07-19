<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<base href="<%=basePath %>"/>
<head>
    <title>首页</title>
    <link
            href="${pageContext.request.contextPath}/static/css/application.css"
            rel="stylesheet">
    <script
            src="${pageContext.request.contextPath}/static/js/select.js"></script>
    <script
            src="${pageContext.request.contextPath}/static/js/login.js"></script>
    <script
            src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>

<div style="width: 100%; height: 50px; color: black;background-color: #7EAAEB;font-size: 20px;" align="center"  >
    <span style="font-size: 20px;">欢迎登陆 ：   上海聚泛信息科技有限公司-----Pdop账单系统</span>
</div>
<div>
    <button style="width: 200px; height: 100px;" onclick="toMerchant()">查询、下载商户账单</button>
</div>
<div style="margin-top: 30px;">
    <button style="width: 200px; height: 100px;" onclick="toVintage()">查询、下载数据源账单</button>
</div>


<script>
    function toMerchant() {
        window.location.href="hello/toMerchant";
    }

    function toVintage() {
        window.location.href="hello/vintage";
    }


</script>

</body>
</html>

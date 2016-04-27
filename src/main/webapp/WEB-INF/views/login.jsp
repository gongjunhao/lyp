<%--
  Created by IntelliJ IDEA.
  User: junhao
  Date: 2016/4/24
  Time: 21:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>乐优品管理平台登录</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/static/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/static/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/static/lyp/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/static/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="container">
    <div class="row" style="margin-top: 50px;">
        <div class="col-md-4 col-md-offset-4">
            <div class="col-md-12 text-center">
                <h1>乐优品科技</h1>
            </div>
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">请登录</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${not empty message_login}">
                        <div class="alert alert-danger alert-dismissable" >
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                                ${message_login}
                        </div>
                    </c:if>
                    <form role="form" action="${pageContext.request.contextPath}/login" method="post" data-toggle="validator" role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入登录名" name="username" type="text" autofocus required>
                            </div>
                            <div class="form-group">
                                <input class="form-control" placeholder="请输入密码" name="password" type="password" value="" data-minlength="6" required>
                            </div>
                            <div class="checkbox">
                                <label>
                                    <input name="remember" type="checkbox" value="1">记住我
                                </label>
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button type="submit" class="btn btn-success btn-lg btn-block">登录</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/static/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/static/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/static/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath}/static/lyp/js/sb-admin-2.js"></script>

<!-- validator JavaScript -->
<script src="${pageContext.request.contextPath}/static/validator/validator.js"></script>

</body>

</html>

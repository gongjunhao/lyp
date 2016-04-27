<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/27
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>乐优品科技后台管理-密码修改</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <jsp:include page="../static.jsp"/>
</head>

<body>

<div id="wrapper">

  <!-- Navigation -->
  <jsp:include page="../navigation.jsp"/>

  <!-- Page Content -->
  <div id="page-wrapper">
    <div class="container-fluid">
      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header">修改密码</h1>
        </div>
        <div class="col-md-4 col-md-offset-4">
          <div class="login-panel panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">修改密码</h3>
            </div>
            <div class="panel-body">
              <c:if test="${not empty message}">
                <div class="alert alert-success alert-dismissable" >
                  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                    ${message}
                </div>
              </c:if>
              <form role="form" action="${pageContext.request.contextPath}/admin/user/changePwd" method="post" data-toggle="validator" role="form">
                <fieldset>
                  <div class="form-group">
                    <input class="form-control" placeholder="请输入原密码" name="oldPwd" type="password" data-minlength="6" autofocus required data-remote="${pageContext.request.contextPath}/admin/user/pwdValidate">
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" placeholder="请输入新密码" name="newPwd1" type="password" id="newPwd1" value="" data-minlength="6" required>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <input class="form-control" placeholder="确认新密码" name="newPwd2" type="password" value="" data-minlength="6" required data-match="#newPwd1">
                    <div class="help-block with-errors"></div>
                  </div>
                  <button type="submit" class="btn btn-primary btn-lg btn-block">修改密码</button>
                </fieldset>
              </form>
            </div>
          </div>
        </div>
      </div>
      <!-- /.row -->
    </div>
    <!-- /.container-fluid -->
  </div>

</div>
<!-- /#wrapper -->

</body>

</html>

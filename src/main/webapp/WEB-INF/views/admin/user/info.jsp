<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/4/27
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
  <title>乐优品科技后台管理-用户信息</title>
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
        <div class="col-md-6 col-md-offset-3">
          <div class="login-panel panel panel-default">
            <div class="panel-heading">
              <h3 class="panel-title">用户信息</h3>
            </div>
            <div class="panel-body">
              <dl class="dl-horizontal">
                <dt>用户昵称</dt>
                <dd>${sessionScope.user.nickName}</dd>
                <dt>登 录 名</dt>
                <dd>${sessionScope.user.loginName}</dd>
                <dt>注册日期</dt>
                <dd><fmt:formatDate value="${sessionScope.user.createTime}"  type="both" /> </dd>
                <dt>拥有角色</dt>
                <dd>
                    <c:forEach items="${roles}" var="role">
                      <kbd>${role.roleName}</kbd>
                    </c:forEach>
                </dd>
              </dl>
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

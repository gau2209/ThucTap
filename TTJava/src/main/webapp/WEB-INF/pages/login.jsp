<%-- 
    Document   : login
    Created on : Aug 19, 2023, 1:35:24 PM
    Author     : Jackie's PC
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-danger">ĐĂNG NHẬP</h1>

<c:if test="${param.error != null}">
    <div class="alert alert-danger">
        Sai tài khoản hoặc mật khẩu! Bạn vui lòng kiểm tra lại!
    </div>
</c:if>

<c:if test="${param.accessDenied !=null}">
    <div class="alert alert-danger">
        Bạn không có quyền này để truy cập!
    </div>
</c:if>

<c:url value="/login" var="action" />
<form method="post" action="${action}">
    <div class="form-floating mb-3 mt-3">
        <input type="text" class="form-control" id="username" placeholder="Nhập username..." name="username">
        <label for="username">Tên đăng nhập</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="password" class="form-control" id="pwd" placeholder="Nhập mật khẩu..." name="password">
        <label for="pwd">Mật khẩu</label>
    </div>

    <div class="form-floating mt-3 mb-3">
        <input type="submit" value="Đăng nhập" class="btn btn-danger" />
    </div>

    <div class="form-floating mt-3 mb-3">
        <a href="<c:url value='/register'/>" class="btn btn-danger">Đăng ký</a>
    </div>

</form>


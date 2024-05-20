<%-- 
    Document   : contact
    Created on : Aug 29, 2023, 12:09:40 AM
    Author     : Jackie's PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1 class="text-center text-info mt-1">THÊM CỬA HÀNG</h1>

<c:url value="/store_admin/stores" var="action" />
<form:form method="post" action="${action}" modelAttribute="stores" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />
    <form:hidden path="storeId" />
    <form:hidden path="imgfoodstore" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="name" storeId="name" placeholder="Tên cửa hàng..."  />
        <label for="name">Tên cửa hàng</label>
        <form:errors path="name" element="div" cssClass="text-danger" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" 
                    path="location" id="location" placeholder="Nhập địa chỉ..."/>
        <label for="location">Địa chỉ</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" 
                    path="file" id="file" multiple="multiple" placeholder="Ảnh cửa hàng..." />
        <label for="file">Ảnh cửa hàng</label>
        <c:if test="${stores.imgfoodstore != null}">
            <img src="${stores.imgfoodstore}" width="120" />
        </c:if>
    </div>
    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">

            <c:choose>
                <c:when test="${stores.storeId == null}">Thêm sản phẩm</c:when>
                <c:otherwise>Cập nhật sản phẩm</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
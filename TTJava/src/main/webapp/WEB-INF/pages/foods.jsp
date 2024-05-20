<%-- 
    Document   : foods
    Created on : Sep 7, 2023, 12:08:51 AM
    Author     : Jackie's PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-info mt-1">THÊM MÓN ĂN</h1>

<c:url value="/store_admin/foods" var="action" />
<form:form method="post" action="${action}" modelAttribute="foods" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="text-danger" />
    <form:hidden path="imgfood" />
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" storeId="name"
                    placeholder="Tên sản phẩm..." />
        <label for="name">Tên</label>
    </div>

    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="price" id="price"
                    placeholder="Nhập giá..." />
        <label for="price">Giá</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="foodType" id="foodType"
                    placeholder="Nhập loại thực phẩm..." />
        <label for="foodType">Loại thực phẩm</label>
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="status" id="status"
                    placeholder="Nhập loại trạng thái..." />
        <label for="foodType">Trạng Thái</label>
    </div>


    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control" path="file" id="file" />
        <label for="file">Ảnh sản phẩm</label>
        <c:if test="${foods.imgfood != null}">
            <img src="${foods.imgfood}" width="120" />
        </c:if>
    </div>

    <div class="form-floating mb-3 mt-3">
        <button class="btn btn-info" type="submit">
            <c:choose>
                <c:when test="${foods.foodId ==null}">Thêm sản phẩm</c:when>
                <c:otherwise>Cập nhật sản phẩm</c:otherwise>
            </c:choose>
        </button>
    </div>

</form:form>
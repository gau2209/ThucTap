<%-- 
    Document   : listfood
    Created on : Sep 7, 2023, 10:09:13 PM
    Author     : Jackie's PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec"
           uri="http://www.springframework.org/security/tags" %> 
<sec:authorize access="hasRole('ROLE_STORE')">
    <div>
        <a href="<c:url value="/store_admin/foods"/>" class = "btn btn-danger">Thêm Đồ Ăn</a>
    </div>
</sec:authorize>

<h1 class="text-center text-danger">MENU MÓN ĂN</h1>
<c:url value="/listfood" var="action" />

<c:if test="${counter > 1}">
    <ul class="pagination mt-1">
        <li class="page-item"><a class="page-link" href="${action}">Tất cả</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/" var="pageAction">
                    <c:param name="page" value="${i}" />
                </c:url>
            <li class="page-item"><a class="page-link" href="${pageAction}">${i}</a></li>
            </c:forEach>

    </ul>
</c:if>
<section class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>STT</th>
                <th>Tên món ăn</th>
                <th>Giá tiền</th>
                <th>Trạng thái</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${foods}" var="f">

                <tr>
                    <td>
                        <img src="${f.imgfood}" alt="${f.name}" width="120" />
                    </td>
                    <td>${f.foodId}</td>
                    <td>${f.name}</td>
                    <td>${f.price} VNĐ</td>
                    <td>${f.status}</td>
                    <td>
                        <a href="<c:url value="/buy"/>">
                            <button>Mua</button>
                        </a>
                    </td>
                    <td>
                        <a href="<c:url value='/#   ' />">
                            <button>Đặt hàng</button>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>

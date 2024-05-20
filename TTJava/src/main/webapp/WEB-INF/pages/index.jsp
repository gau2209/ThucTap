<%-- 
    Document   : index
    Created on : Aug 24, 2023, 8:38:54 PM
    Author     : Jackie's PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<h1 class="text-center text-danger">TRANG CHỦ</h1>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1">
        <li class="page-item"><a class="page-link" href="<c:url value="/" />">Tất cả</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
            <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
            </c:forEach>
    </ul>
</c:if>
<section class="container">
    <table class="table table-hover">
        <thead>
            <tr>
                <th></th>
                <th>STT</th>
                <th>Tên cửa hàng</th>
                <th>Địa chỉ</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${stores}" var="st">
                <tr>
                    <td>
                        <img src="${st.imgfoodstore}" alt="${st.name}" width="120" />
                    </td>
                    <td>${st.storeId}</td>
                    <td>${st.name}</td>
                    <td>${st.location}</td>
                    <td class="ggmaps">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3919.6244964665116!2d106.66679857872309!3d10.763395662013432!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752f5a94082ddf%3A0x4c7a1fb5117f6c71!2zMjMzIE5nw7QgR2lhIFThu7EsIFBoxrDhu51uZyA0LCBRdeG6rW4gMTAsIFRow6BuaCBwaOG7kSBI4buTIENow60gTWluaCwgVmnhu4d0IE5hbQ!5e0!3m2!1svi!2s!4v1694196695476!5m2!1svi!2s"
                                width="200" height="200" style="border:0;" allowfullscreen="" 
                                loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                    </td>
                    <td>
                    <td>
                        <a href="<c:url value='/detail' />" onclick="Xem('${st.storeId}')">
                            <button>Xem</button>
                        </a>
                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>


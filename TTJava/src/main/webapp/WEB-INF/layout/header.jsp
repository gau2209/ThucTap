<%-- 
    Document   : header
    Created on : Aug 19, 2023, 1:03:36 AM
    Author     : Jackie's PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:url value="/" var="action" />
<header>

    <nav>
        <div class="dropdown">
            <ul  >
                <li><a href="<c:url value="/"/>">Trang Chủ</a></li>
                <li><a href="<c:url value="/detail"/>">Cửa Hàng</a></li>
                <li><a href="<c:url value="/listfood"/>">Món Ăn</a></li>
                <li><a href="">Giỏ Hàng</a></li>
                <li><a href="">Bình Luận</a></li>


                <div class="login">
                    <c:choose>
                        <c:when test="${pageContext.request.userPrincipal.name != null}">
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/" />">${pageContext.request.userPrincipal.name}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/logout" />">Logout</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="<c:url value="/login" />">Đăng nhập</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </div>
            </ul>
        </div>
        <c:url value="/" var="action" />
        <div class="search-bar">
            <form class="search-bar" action="${action}">
                <input type="text" name ="kw" placeholder="Tìm cửa hàng hoặc món ăn..."> 
                <button type="submit">Tìm</button>
            </form> 
        </div> 


        <div class="header__img">
            <img src="" alt="">
        </div>
        <div class="clear"></div>

    </nav>


</header>
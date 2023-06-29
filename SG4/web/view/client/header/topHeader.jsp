
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>
<!-- start header top  -->
<div class="aa-header-top">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-header-top-area">
                    <!-- start header top left -->
                    <div class="aa-header-top-left">
                        <div class="cellphone hidden-xs">
                            <p><span class="fas fa-home"></span>Chào mừng đến với SG4 Store</p>
                        </div>
                        <!-- start language -->
                        <div class="aa-language">
                            <div class="dropdown">
                                <a class="btn dropdown-toggle" href="#" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                    <img src="${url}/img/flag/vietnam.png" alt="english flag">Việt Nam
                                </a>

                            </div>
                        </div>
                        <!-- / language -->
                    </div>
                    <!-- / header top left -->
                    <div class="aa-header-top-right">
                        <ul class="aa-head-top-nav-right">

                            <c:if test="${sessionScope.username != null}">
                                <li>
                                    <a><strong><img style="width: 25px;height: 25px;object-fit: cover;border-radius: 50%;margin: 0 5px;" src="${pageContext.request.contextPath}/view/client/assets/images/avatar/${user.avatar}" alt="Ảnh"></strong> ${username}</a>
                                </li>
                                <li class="hidden-xs"><a href="${pageContext.request.contextPath}/view/client/logout">Đăng xuất</a></li>
                                <div class="aa-infomation-account">
                                <a href="${pageContext.request.contextPath}/view/client/account?type=1">Tài khoản của tôi</a>
                                <a href="${pageContext.request.contextPath}/view/client/account?type=2">Đơn mua</a>
                            </div>
                                </c:if>

                            <c:if test="${sessionScope.username == null}">
                                <li class="hidden-xs"><a href="${pageContext.request.contextPath}/view/client/register">Đăng ký</a></li>
                                <li><a href="${pageContext.request.contextPath}/view/client/login">Đăng nhập</a></li>
                                </c:if>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- / header top  -->

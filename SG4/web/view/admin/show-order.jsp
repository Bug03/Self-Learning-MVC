<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");

    if (session.getAttribute("admin-username") == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>
<!-- Start header section -->
<jsp:include page = "./header/header.jsp" flush = "true" />
<div class="content-wrapper">
    <div class="container-fluid">
        <!--End Row-->


        <div class="row">

            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách đơn hàng</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Tài khoản đặt hàng</th>
                                        <th scope="col">Địa chỉ</th>
                                        <th scope="col">Tổng tiền</th>
                                        <th scope="col">Thanh toán</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Người bán</th>
                                        <th scope="col">Hành động</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${order}" var="order">
                                        <tr>
                                            <td scope="row">${order.id}</td>
                                            <td>${order.user_session}</td>
                                            <td>${order.address}</td>
                                            <td>${order.amount} VNĐ</td>
                                            <td>  <c:choose>
                                                    <c:when test="${order.payment == 0}"> 
                                                        <c:out value="COD"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="Thẻ nội địa ATM"/>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>
                                            <td> 
                                                ${order.status}
                                            </td>
                                            <c:choose>
                                                <c:when test="${order.admin_id == 0}">
                                                    <td>NULL</td>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${admin}" var="a">
                                                        <c:if test="${a.id == order.admin_id}">
                                                            <td>${a.username}</td>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.status == 'Đã hủy'}">
                                                        <button class="btn btn-danger" data-toggle="tooltip" data-placement="top"  title="${order.id}"><a href="${pageContext.request.contextPath}/admin/order/delete?id=${order.id}">Xóa</a></button>
                                                        <button class="btn btn-info" data-toggle="tooltip" data-placement="top"  title="${order.id}"><a href="${pageContext.request.contextPath}/admin/order/edit?id=${order.id}">Thông tin</a></button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${order.confirm == false}">
                                                            <button class="btn btn-danger" data-toggle="tooltip" data-placement="top"  title="${order.id}"><a href="${pageContext.request.contextPath}/admin/order/delete?id=${order.id}">Xóa</a></button>
                                                            <button class="btn btn-success" data-toggle="tooltip" data-placement="top"  title="${order.id}"><a href="${pageContext.request.contextPath}/admin/order/confirm?id=${order.id}">Duyệt đơn</a></button>
                                                        </c:if>
                                                        <c:if test="${order.confirm == true}">
                                                            <button class="btn btn-danger" data-toggle="tooltip" data-placement="top"  title="${order.id}"><a href="${pageContext.request.contextPath}/admin/order/delete?id=${order.id}">Xóa</a></button>
                                                            <button class="btn btn-success" data-toggle="tooltip" data-placement="top"  title="${order.id}"><a href="${pageContext.request.contextPath}/admin/order/edit?id=${order.id}">Sửa</a></button>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>                          
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="pagination" style="align-items: center;justify-content: center">

                                <c:if test="${tag > 1}">
                                    <a href="${pageContext.request.contextPath}/admin/order/list?index=${tag - 1}">&laquo;</a>
                                </c:if>

                                <c:forEach begin="${beginP}" end="${maxP}" var="i">
                                    <a class="${tag == i?"active" : ""}" href="${pageContext.request.contextPath}/admin/order/list?index=${i}">${i}</a>
                                </c:forEach>
                                <c:if test="${tag < maxP}">
                                    <a href="${pageContext.request.contextPath}/admin/order/list?index=${tag + 1}">&raquo;</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page = "./footer/footer.jsp" flush = "true" />
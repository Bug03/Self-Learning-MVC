<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <div class="row mt-3">
            <div class="col-lg-12">
                <button class="add-catalog"><a href="${pageContext.request.contextPath}/admin/product/add">Thêm sản phẩm</a></button>
            </div>
            <div class="col-lg-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Danh sách sản phẩm</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Tên sản phẩm</th>
                                        <th scope="col">Hình ảnh</th>
                                        <th scope="col">Giá(VNÐ)</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Giảm giá</th>
                                        <th scope="col">Hành động</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${productlist}" var="product">
                                        <tr>
                                            <th scope="row">${product.id}</th>
                                            <td>${product.name}</td>
                                            <td><img style="    width: 110px;height: 67px; object-fit: cover;border: 1px solid #fff;" src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="${product.name}"></td>
                                            <td>${product.price }</td>
                                            <td>

                                                <c:choose>
                                                    <c:when test="${product.status == 1}"> 
                                                        <c:out value="Còn hàng"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:out value="Hết hàng"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>${product.discount }%</td>
                                            <td>
                                                <button class="btn btn-danger" data-toggle="tooltip" data-placement="top"  title="${product.name}"><a href="${pageContext.request.contextPath}/admin/product/delete?id=${product.id}">Xóa</a></button>

                                                <button class="btn btn-success" data-toggle="tooltip" data-placement="top"  title="${product.name}"><a href="${pageContext.request.contextPath}/admin/product/edit?id=${product.id}">Chi tiết</a></button>
                                                
                                                 <button class="btn btn-light" data-toggle="tooltip" data-placement="top"  title="${product.name}"><a href="${pageContext.request.contextPath}/admin/product/import-sold?id=${product.id}">Nhập hàng</a></button>

                                            </td>S
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            <div class="pagination" style="align-items: center;justify-content: center">

                                <c:if test="${tag > 1}">
                                    <a href="${pageContext.request.contextPath}/admin/product/list?index=${tag - 1}">&laquo;</a>
                                </c:if>

                                <c:forEach begin="${beginP}" end="${maxP}" var="i">
                                    <a class="${tag == i?"active" : ""}" href="${pageContext.request.contextPath}/admin/product/list?index=${i}">${i}</a>
                                </c:forEach>
                                <c:if test="${tag < maxP}">
                                    <a href="${pageContext.request.contextPath}/admin/product/list?index=${tag + 1}">&raquo;</a>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>


<jsp:include page = "./footer/footer.jsp" flush = "true" />
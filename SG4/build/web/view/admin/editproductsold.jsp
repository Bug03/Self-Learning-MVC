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
                <div class="card">
                    <div class="card-body">
                        <div class="card-title">Nhập hàng</div>
                        <hr>
                        <form method="post" action="${pageContext.request.contextPath}/admin/product/import-sold">

                            <div class="form-group">
                                <label for="input-1">Mã sản phẩm</label>
                                <input type="text" class="form-control" readonly="readonly" id="input-1" placeholder="Mã sản phẩm" name="product-sku" value="${product.id}">
                            </div>

                            <div class="form-group">
                                <label for="input-1">Tên sản phẩm</label>
                                <input type="text" class="form-control" readonly="readonly" id="input-1" placeholder="Tên sản phẩm" name="product-name" value="${product.name}">
                            </div>
                            <div class="form-group">
                                <label for="input-1">Tồn kho</label>
                                <input type="text" class="form-control" readonly="readonly" id="input-1" placeholder="Sản phẩm" name="product-inventory" value="${product.inventory}">
                            </div>
                            <div class="form-group">
                                <label for="input-1">Nhập về</label>
                                <input type="text" class="form-control" id="input-1" placeholder="Sản phẩm" name="product-import-sold" >
                            </div>
                            <div class="form-footer">
                                <button class="btn btn-danger"><a href="${pageContext.request.contextPath}/admin/product/list">Hủy</a></button>

                                <button type="submit" class="btn btn-success">Cập nhật</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="overlay toggle-menu"></div>
    </div>
</div>

<jsp:include page = "./footer/footer.jsp" flush = "true" />
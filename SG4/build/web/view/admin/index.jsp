<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    if (session.getAttribute("admin-username") == null) {
        response.sendRedirect(request.getContextPath() + "/admin/login");
    }
%>

<%
    Long totalRevenue = (Long) request.getAttribute("totalRevenue");
    String monthParam = request.getParameter("month");
    String yearParam = request.getParameter("year");
    boolean showTotalRevenue = (totalRevenue != null && monthParam != null && yearParam != null);
%>

<!-- Start header section -->
<jsp:include page = "./header/header.jsp" flush = "true" />
<head>
    <style>
        select option {
            background-color: #fff;
            
        }
        </style>
</head>
<div class="content-wrapper">
    <div class="container-fluid">
        <div class="card mt-3">
            <div class="card-content">
                <div class="row row-group m-0">
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0">
                                <%=request.getAttribute("place-order")%>
                                <span class="float-right"><i class="fa fa-shopping-cart"></i></span>
                            </h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Đơn hàng đã đặt</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0"><%=request.getAttribute("sold-price")%> <span class="float-right">VNĐ</span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Số tiền thu được</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0"><%=request.getAttribute("sold-quantity")%><span class="float-right"><i class="fa fa-eye"></i></span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Sản phẩm bán ra</p>
                        </div>
                    </div>
                    <div class="col-12 col-lg-6 col-xl-3 border-light">
                        <div class="card-body">
                            <h5 class="text-white mb-0"><%=request.getAttribute("sold-sum")%><span class="float-right"><i class="fa fa-envira"></i></span></h5>
                            <div class="progress my-3" style="height:3px;">
                                <div class="progress-bar" style="width:55%"></div>
                            </div>
                            <p class="mb-0 text-white small-font">Đơn hàng đã hoàn thành</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12 col-lg-4 col-xl-4">
                <div class="card">
                    <div class="card-header">Danh mục bán chạy
                    </div>
                    <div class="card-body">
                        <div class="chart-container-2">
                            <canvas id="chart2"></canvas>
                        </div>
                    </div>
                    <div class="table-responsive">
                        <table class="table align-items-center">
                            <tbody>
                                <tr>
                                    <td><i class="fa fa-circle text-white mr-2"></i>Iphone</td>
                                    <td><%=request.getAttribute("sold-iphone")%> VNĐ</td>

                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-1 mr-2"></i>SamSung</td>
                                    <td id="price-cd"><%=request.getAttribute("sold-samsung")%> VNĐ</td>

                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-2 mr-2"></i>Oppo</td>
                                    <td id="price-chair"><%=request.getAttribute("sold-oppo")%> VNĐ</td>

                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-3 mr-2"></i>Xiaomi</td>
                                    <td id="price-category"><%=request.getAttribute("sold-xiaomi")%> VNĐ</td>
                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-3 mr-2"></i>Vivo</td>
                                    <td id="price-category"><%=request.getAttribute("sold-vivo")%> VNĐ</td>
                                </tr>
                                <tr>
                                    <td><i class="fa fa-circle text-light-3 mr-2"></i>Phụ kiện khác</td>
                                    <td id="price-category"><%=request.getAttribute("sold-other")%> VNĐ</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <div>
            <form action="homepage" method="GET">
                <label for="month">Chọn tháng:</label>
                <select name="month" id="month">
                    <option  value="1">Tháng 1</option>
                    <option value="2">Tháng 2</option>
                    <option value="3">Tháng 3</option>
                    <option value="4">Tháng 4</option>
                    <option value="5">Tháng 5</option>
                    <option value="6">Tháng 6</option>
                    <option value="7">Tháng 7</option>
                    <option value="8">Tháng 8</option>
                    <option value="9">Tháng 9</option>
                    <option value="10">Tháng 10</option>
                    <option value="11">Tháng 11</option>
                    <option value="12">Tháng 12</option>
                </select>

                <label for="year">Chọn năm:</label>
                <select name="year" id="year">
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>

                </select>

                <button type="submit">Xem tổng doanh thu</button>
            </form>

            <% if (showTotalRevenue) {%>
            <p>Tổng doanh thu: <%= totalRevenue%></p>
            <% }%>
        </div>
    </div>
</div>
<a href="javaScript:void();" class="back-to-top"><i class="fa fa-angle-double-up"></i> </a>
<jsp:include page = "./footer/footer.jsp" flush = "true" />
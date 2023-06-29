<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>
<!-- Start header section -->
<jsp:include page = "./header/mainHeader.jsp" flush = "true" />
<!-- / header section -->

<!--  content -->
<!-- catg header banner section -->
<section id="aa-catg-head-banner">
    <c:if test="${id_banner == 1}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-product_2.jpg" alt="banner game">
    </c:if>
    <c:if test="${id_banner == 2}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-chair.png" alt="banner ghế gaming">
    </c:if>
    <c:if test="${id_banner == 6}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-selling.jpg" alt="banner sản phẩm bán chạy">
    </c:if>
    <c:if test="${id_banner == 4}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-categories.jpg" alt="banner phụ kiện">
    </c:if>
    <c:if test="${id_banner == null}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-product.jpg" alt="banner sản phẩm">
    </c:if>
    <c:if test="${id_banner == 3}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-cd.jpg" alt="banner đĩa game">
    </c:if>
    <c:if test="${id_banner == 5}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-new.jpg" alt="banner sản phẩm mới">
    </c:if>
    <c:if test="${id_banner == 7}">
        <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-sale.jpg" alt="banner sản phẩm giảm giá">
    </c:if>
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Sản phẩm</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}/">Trang chủ</a></li>         
                    <li style="color:#fff">Danh sách sản phẩm</li>
                        <c:forEach items="${catelist}" var="c">
                            <c:if test="${c.id == idCatalog}">
                            <li style="color:#fff">${c.name}</li>
                            </c:if>
                        </c:forEach>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- product category -->
<section id="aa-product-category">
    <div class="container">
        <div class="row">
            <div class="col-lg-9 col-md-9 col-sm-8 col-md-push-3">
                <div class="aa-product-catg-content">
                    <div class="aa-product-catg-body">
                        <ul class="aa-product-catg">
                            <!-- start single product item -->
                            <c:if test="${empty productlist}">
                                <p style="margin-left: 30px">Chưa có sản phẩm!</p>
                            </c:if>
                            <c:forEach items="${productlist}" var="product">
                                <li>
                                    <c:forEach items="${product_sellings}" var="i">
                                        <c:if test="${product.id == i.id}">
                                            <span class="aa-product-selling">Hot</span> 
                                        </c:if>
                                    </c:forEach> 
                                    <figure>
                                        <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="${product.name}"></a>
                                    </figure>
                                    <div class="aa-product-number-sold-wrapper">
                                        <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                    </div>  
                                <figcaption>
                                    <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name}</a></h4>

                                    <c:choose>
                                        <c:when test="${product.discount == 0}">
                                            <span class="aa-product-price">${product.price} ₫</span><span class="aa-product-price"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${productlist1}" var="product1">
                                                <c:if test="${product1.id == product.id}">
                                                    <span class="aa-product-price color-red">${product1.price} ₫</span>
                                                    <span class="aa-product-price"><del>${product.price} ₫</del></span>
                                                </c:if>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </figcaption>
                                <c:if test="${product.inventory == 0}">
                                    <span class="aa-out">Tạm hết hàng</span>
                                </c:if>
                                <c:if test="${product.discount != 0}">
                                    <span class="aa-badge aa-sale">- ${product.discount}%</span>
                                </c:if>
                                <c:forEach items="${product_news}" var="i">
                                    <c:choose>
                                        <c:when test="${i.discount != 0 && i.id == product.id}">
                                            <span class="aa-badge aa-new pad-t"> Mới</span>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${i.id == product.id}">
                                                <span class="aa-badge aa-new"> Mới</span>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                </li>
                            </c:forEach>                                       
                        </ul>
                    </div>

                </div>
                <!-- Paging -->
                <c:if test="${maxP > 1}">
                    <div class="pagination">

                        <c:if test="${tag > 1}">
                            <a href="${pageContext.request.contextPath}/view/client/product-id?index=${tag - 1}&id=<%=request.getAttribute("idCatalog")%>">&laquo;</a>
                        </c:if>

                        <c:forEach begin="1" end="${maxP}" var="i">
                            <a class="${tag == i?"active" : ""}" href="${pageContext.request.contextPath}/view/client/product-id?index=${i}&id=<%=request.getAttribute("idCatalog")%>">${i}</a>
                        </c:forEach>
                        <c:if test="${tag < maxP}">
                            <a href="${pageContext.request.contextPath}/view/client/product-id?index=${tag + 1}&id=<%=request.getAttribute("idCatalog")%>">&raquo;</a>
                        </c:if>
                    </div>
                </c:if>

                <!-- /Paging -->
            </div>
            <div class="col-lg-3 col-md-3 col-sm-4 col-md-pull-9">
                <aside class="aa-sidebar">
                    <!-- single sidebar -->
                    <div class="aa-sidebar-widget">
                        <h3>Danh mục</h3>
                        <ul class="aa-catg-nav">
                            <c:forEach items="${catelist}" var="cate">
                                <li class="${cate.id == idCatalog ? "is-choose-category" : ""}"><i class="${cate.id == idCatalog ? "fas fa-caret-right" : ""}"></i><a class="${cate.id == idCatalog ? "color-red" : ""}" href="${pageContext.request.contextPath}/view/client/product-id?id=${cate.id}">${cate.name}</a></li>
                                    </c:forEach>                
                        </ul>
                    </div>
                    <!-- single sidebar -->

                    <!-- single sidebar -->
                    <div class="aa-sidebar-widget">
                        <h3>Sản phẩm gần đây</h3>
                        <div class="aa-recently-views">
                            <ul>
                                <c:forEach items="${productlist}" var="product" end="2">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}" class="aa-cartbox-img"><img alt="img" src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}"></a>
                                        <div class="aa-cartbox-info">
                                            <h4><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
                                                <c:choose>
                                                    <c:when test="${product.discount == 0}">
                                                    <p>${product.price} ₫</p>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${productlist1}" var="product1">
                                                        <c:if test="${product1.id == product.id}">
                                                            <p>${product1.price} ₫</p>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>                    
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>                                 
                    </div>
                    <!-- single sidebar -->

                </aside>
            </div>

        </div>
    </div>
</section>
<!-- / product category -->
<!--  end content-->

<!--  footer-->
<jsp:include page = "./footer/footer.jsp" flush = "true" />
<!-- end footer-->




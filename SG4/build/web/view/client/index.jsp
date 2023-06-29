<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>

<!-- Start header section -->
<jsp:include page = "./header/mainHeader.jsp" flush = "true" />
<!-- / header section -->

<!-- Start slider -->
<jsp:include page = "./banner-slider/slider.jsp" flush = "true" />
<!-- / slider -->

<!-- Start Promo section -->
<!--<section id="aa-promo">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-promo-area">
                    <div class="row">
                         promo left 
                        <div class="col-md-5 no-padding">                
                            <div class="aa-promo-left">
                                <div class="aa-promo-banner">                    
                                    <img src="${url}/images/promo_slide.jpg" alt="img">                    
                                </div>
                            </div>
                        </div>
                         promo right 
                        <div class="col-md-7 no-padding">
                            <div class="aa-promo-right">
                                <div class="aa-single-promo-right">
                                    <div class="aa-promo-banner">                      
                                        <img src="${url}/images/section2.jpg" alt="img">                      
                                        <div class="aa-prom-content">

                                        </div>
                                    </div>
                                </div>
                                <div class="aa-single-promo-right">
                                    <div class="aa-promo-banner">                      
                                        <img src="${url}/images/section3.jpg" alt="img">                      
                                        <div class="aa-prom-content">

                                        </div>
                                    </div>
                                </div>
                                <div class="aa-single-promo-right">
                                    <div class="aa-promo-banner">                      
                                        <img src="${url}/images/section4.jpg" alt="img">                      
                                        <div class="aa-prom-content">

                                        </div>
                                    </div>
                                </div>
                                <div class="aa-single-promo-right">
                                    <div class="aa-promo-banner">                      
                                        <img src="${url}/images/section5.jpg" alt="img">                      
                                        <div class="aa-prom-content">

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>-->
<!-- / Promo section -->
<!-- Products section -->
<!--<div class="aa-banner-animation">
    <img src="${url}/images/noel.gif">
</div>-->
<section id="aa-product">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="aa-product-area">
                        <div class="aa-product-inner">
                            <!-- start prduct navigation -->
                            <ul class="nav nav-tabs aa-products-tab">
                                <li class="active"><a href="#iphone" data-toggle="tab">Iphone</a></li>
                                <li><a href="#samsung" data-toggle="tab">Samsung</a></li>
<!--                                <li><a href="#xiaomi" data-toggle="tab">Xiaomi</a></li>
                                <li><a href="#oppo" data-toggle="tab">Oppo</a></li>
                                <li><a href="#vivo" data-toggle="tab">Vivo</a></li>-->
                                <li><a href="#phukien" data-toggle="tab">Phụ kiện</a></li>
                            </ul>
                            <!-- Tab panes -->
                            <div class="tab-content">
                                <!-- Start men product category -->
                                <div class="tab-pane fade in active" id="iphone">
                                    <ul class="aa-product-catg">
                                        <!-- start single product item -->
                                        <c:forEach items="${apple}" var="product" end="7">
                                            <li>
                                                <c:forEach items="${product_sellings}" var="i">
                                                    <c:if test="${product.id == i.id}">
                                                        <span id="is-true" class="aa-product-selling">Hot</span> 
                                                    </c:if>
                                                </c:forEach>                                       
                                                <figure>
                                                    <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>     
                                                </figure>
                                                <div class="aa-product-number-sold-wrapper">
                                                    <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                </div>  
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a>

                                                </h4>

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
                                        <!-- start single product item -->

                                    </ul>

                                </div>
                                <!-- / men product category -->
                                <!-- start women product category -->
                                <div class="tab-pane fade" id="samsung">
                                    <ul class="aa-product-catg">
                                        <!-- start single product item -->
                                        <c:forEach items="${samsung}" var="product" end="7">
                                            <li>
                                                <c:forEach items="${product_sellings}" var="i">
                                                    <c:if test="${product.id == i.id}">
                                                        <span class="aa-product-selling">Hot</span> 
                                                    </c:if>
                                                </c:forEach> 
                                                <figure>
                                                    <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                </figure>   
                                                <div class="aa-product-number-sold-wrapper">
                                                    <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                </div>  
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                        <!-- start single product item -->

                                    </ul>

                                </div>
                                <!-- / women product category -->
                                <!-- start sports product category -->
                                <div class="tab-pane fade" id="oppo">
                                    <ul class="aa-product-catg">
                                        <!-- start single product item -->
                                        <c:forEach items="${oppo}" var="product" end="7">
                                            <li>
                                                <c:forEach items="${product_sellings}" var="i">
                                                    <c:if test="${product.id == i.id}">
                                                        <span class="aa-product-selling">Hot</span> 
                                                    </c:if>
                                                </c:forEach> 
                                                <figure>
                                                    <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                </figure>
                                                <div class="aa-product-number-sold-wrapper">
                                                    <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                </div>  
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                        <!-- start single product item -->

                                    </ul>

                                </div>
                                <!-- / sports product category -->
                                <!-- start electronic product category -->
                                <div class="tab-pane fade" id="xiaomi">
                                    <ul class="aa-product-catg">
                                        <!-- start single product item -->
                                        <c:forEach items="${xiaomi}" var="product" end="7">
                                            <li>
                                                <c:forEach items="${product_sellings}" var="i">
                                                    <c:if test="${product.id == i.id}">
                                                        <span class="aa-product-selling">Hot</span> 
                                                    </c:if>
                                                </c:forEach> 
                                                <figure>
                                                    <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                </figure>   
                                                <div class="aa-product-number-sold-wrapper">
                                                    <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                </div>  
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                        <!-- start single product item -->

                                    </ul>

                                </div>
                                <div class="tab-pane fade" id="vivo">
                                    <ul class="aa-product-catg">
                                        <!-- start single product item -->
                                        <c:forEach items="${vivo}" var="product" end="7">
                                            <li>
                                                <c:forEach items="${product_sellings}" var="i">
                                                    <c:if test="${product.id == i.id}">
                                                        <span class="aa-product-selling">Hot</span> 
                                                    </c:if>
                                                </c:forEach> 
                                                <figure>
                                                    <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                </figure>   
                                                <div class="aa-product-number-sold-wrapper">
                                                    <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                </div>  
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                        <!-- start single product item -->

                                    </ul>

                                </div>
                                <div class="tab-pane fade" id="phukien">
                                    <ul class="aa-product-catg">
                                        <!-- start single product item -->
                                        <c:forEach items="${other}" var="product" end="7">
                                            <li>
                                                <c:forEach items="${product_sellings}" var="i">
                                                    <c:if test="${product.id == i.id}">
                                                        <span class="aa-product-selling">Hot</span> 
                                                    </c:if>
                                                </c:forEach> 
                                                <figure>
                                                    <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                </figure>   
                                                <div class="aa-product-number-sold-wrapper">
                                                    <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                </div>  
                                            <figcaption>
                                                <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                        <!-- start single product item -->

                                    </ul>

                                </div>
                                <!-- / electronic product category -->
                            </div>
                            <div class="more-product">
                                <a class="aa-browse-btn" href="${pageContext.request.contextPath}/view/client/product">Xem Tất Cả Sản Phẩm <span class="fa fa-long-arrow-right"></span></a>
                            </div>


                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Products section -->
<!-- banner section -->
<section id="aa-banner">
    <div class="container">
        <div class="row">
            <div class="col-md-12">        
                <div class="row">
                    <div class="aa-banner-area">
                        <a href="#"><img src="${url}/images/banner_tc.png" alt="banner trang chủ"></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- popular section -->
<section id="aa-popular-category">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="row">
                    <div class="aa-popular-category-area">
                        <!-- start prduct navigation -->
                        <ul class="nav nav-tabs aa-products-tab">
                            <li class="active"><a href="#sanphammoi" data-toggle="tab">Sản Phẩm Mới</a></li>
<!--                            <li><a href="#sanphambanchay" data-toggle="tab">Sản Phẩm Bán Chạy</a></li>-->
                            <li><a href="#sanphamgiamgia" data-toggle="tab">Sản Phẩm Giảm Giá</a></li>                    
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <!-- Start men popular category -->
                            <div class="tab-pane fade in active" id="sanphammoi">
                                <c:choose>
                                    <c:when test="${empty product_news}">
                                        <p style="text-align: center">Không có sản phẩm mới.</p>
                                    </c:when>
                                    <c:otherwise>
                                        <ul class="aa-product-catg aa-popular-slider">
                                            <!-- start single product item -->
                                            <c:forEach items="${product_news}" var="product">
                                                <li>
                                                    <c:forEach items="${product_sellings}" var="i">
                                                        <c:if test="${product.id == i.id}">
                                                            <span class="aa-product-selling">Hot</span> 
                                                        </c:if>
                                                    </c:forEach> 
                                                    <figure>
                                                        <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                    </figure>
                                                    <div class="aa-product-number-sold-wrapper">
                                                        <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                    </div>  
                                                <figcaption>
                                                    <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                            <!-- start single product item -->
                                            <!-- start single product item -->

                                        </ul>
                                    </c:otherwise> 
                                </c:choose>

                            </div>
                            <!-- / popular product category -->

                            <!-- start featured product category -->
                            <div class="tab-pane fade" id="sanphambanchay">
                                <c:choose>
                                    <c:when test="${empty product_sellings}">
                                        <p style="text-align: center">Không có sản phẩm bán chạy.</p>
                                    </c:when>
                                    <c:otherwise>
                                        <ul class="aa-product-catg aa-popular-slider">
                                            <!-- start single product item -->
                                            <c:forEach items="${product_sellings}" var="product">
                                                <li>
                                                    <c:forEach items="${product_sellings}" var="i">
                                                        <c:if test="${product.id == i.id}">
                                                            <span class="aa-product-selling">Hot</span> 
                                                        </c:if>
                                                    </c:forEach> 
                                                    <figure>
                                                        <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                    </figure>    
                                                    <div class="aa-product-number-sold-wrapper">
                                                        <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                    </div>  
                                                <figcaption>
                                                    <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                            <!-- start single product item -->
                                            <!-- start single product item -->

                                        </ul>
                                    </c:otherwise> 
                                </c:choose>

                            </div>
                            <!-- / featured product category -->

                            <!-- start latest product category -->
                            <div class="tab-pane fade" id="sanphamgiamgia">
                                <c:choose>
                                    <c:when test="${empty product_sales}">
                                        <p style="text-align: center">Không có sản phẩm giảm giá.</p>
                                    </c:when>
                                    <c:otherwise>
                                        <ul class="aa-product-catg aa-popular-slider">
                                            <!-- start single product item -->
                                            <c:forEach items="${product_sales}" var="product">
                                                <li>
                                                    <c:forEach items="${product_sellings}" var="i">
                                                        <c:if test="${product.id == i.id}">
                                                            <span class="aa-product-selling">Hot</span> 
                                                        </c:if>
                                                    </c:forEach> 
                                                    <figure>
                                                        <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt="polo shirt img"></a>
                                                    </figure>
                                                    <div class="aa-product-number-sold-wrapper">
                                                        <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                                    </div>  
                                                <figcaption>
                                                    <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name }</a></h4>
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
                                            <!-- start single product item -->
                                            <!-- start single product item -->

                                        </ul>
                                    </c:otherwise> 
                                </c:choose>

                            </div>
                            <!-- / latest product category -->              
                        </div>
                        <div class="more-product">
                            <a class="aa-browse-btn" href="${pageContext.request.contextPath}/view/client/product">Xem Tất Cả Sản Phẩm <span class="fa fa-long-arrow-right"></span></a>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
    </div>
</section>
<!-- / popular section -->
<!-- Support section -->
<section id="aa-support">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-support-area">
                    <!-- single support -->
                    <div class="col-md-4 col-sm-4 col-xs-12">
                        <div class="aa-support-single">
                            <span class="fa fa-truck"></span>
                            <h4>MIỄN PHÍ VẬN CHUYỂN</h4>
                            <P>Chúng tôi hiện tại áp dụng miễn phí cho mọi đơn hàng mua trên Mado Gaming.</P>
                        </div>
                    </div>
                    <!-- single support -->
                    <div class="col-md-4 col-sm-4 col-xs-12">
                        <div class="aa-support-single">
                            <span class="fa fa-clock-o"></span>
                            <h4>GIAO HÀNG NHANH</h4>
                            <P>Chúng tôi đảm bảo hàng đến tay khách hàng nhanh và đảm bảo sản phẩm an toàn.</P>
                        </div>
                    </div>
                    <!-- single support -->
                    <div class="col-md-4 col-sm-4 col-xs-12">
                        <div class="aa-support-single">
                            <span class="fa fa-phone"></span>
                            <h4>HỖ TRỢ 24/7</h4>
                            <P>Hỗ trợ tư vấn và đặt hàng mọi lúc mọi nơi, đảm bảo thời gian cho khách hàng.</P>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Support section -->
<!-- Slider Logo -->
<jsp:include page = "./flexsiel.jsp" flush = "true" />
<!-- / Slider Logo -->
<!-- Latest Blog -->
<!--<section id="aa-latest-blog">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-latest-blog-area">
                    <h2><i class="far fa-newspaper"></i> TIN TỨC</h2>
                    <div class="row aa-animation-board-new" style="display: flex;gap: 0 20px;">
                         single latest blog 
                        <c:forEach items="${boardnewlist}" var="boardnew">
                            <div class="col-md-3 col-sm-3 pd-0">
                                <div class="aa-latest-blog-single">
                                    <figure class="aa-blog-img">                                 
                                        <a href="${pageContext.request.contextPath}/view/client/news-list-detail?id=${boardnew.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/news/${boardnew.image_link}" alt="Tin tức ${boardnew.title}"></a>  
                                        <figcaption class="aa-blog-img-caption">
                                            <span href="#"><i class="fa fa-clock-o"></i>${boardnew.created}</span>
                                        </figcaption>   
                                    </figure>

                                    <div class="aa-blog-info">
                                        <h3 class="aa-blog-title"><a href="${pageContext.request.contextPath}/view/client/news-list-detail?id=${boardnew.id}">${boardnew.title}</a></h3>
                                        <p class="desc-boardnews">${boardnew.description}</p> 
                                        <a href="${pageContext.request.contextPath}/view/client/news-list-detail?id=${boardnew.id}" class="aa-read-mor-btn">Xem thêm<span class="fa fa-long-arrow-right"></span></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>    
        </div>
    </div>
</section>-->
<!-- / Latest Blog -->

<!-- Client Brand -->

<!-- / Client Brand -->  
<!--  footer-->
<jsp:include page = "./footer/footer.jsp" flush = "true" />
<!-- end footer-->


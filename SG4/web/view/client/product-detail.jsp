<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>

<!-- Start header section -->
<jsp:include page = "./header/mainHeader.jsp" flush = "true" />
<!-- / header section -->

<!--  content -->
<!-- catg header banner section -->
<section id="aa-catg-head-banner">
    <img src="${pageContext.request.contextPath}/view/client/assets/images/banner-product-detail.jpg" alt="banner sản phẩm">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Chi tiết sản phẩm</h2>
                <ol class="breadcrumb">
                    <li><a href="${pageContext.request.contextPath}">Trang chủ</a></li>         
                    <li><a href="${pageContext.request.contextPath}/view/client/product">Sản phẩm</a></li>
                    <li style="color:#fff">${detail_product.name }</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- product category -->
<section id="aa-product-details">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-product-details-area">
                    <div class="aa-product-details-content">
                        <div class="row">
                            <!-- Modal view slider -->

                            <div class="col-md-5 col-sm-5 col-xs-12">                              
                                <div class="aa-product-view-slider">                                
                                    <div id="demo-1" class="simpleLens-gallery-container">
                                        <div class="simpleLens-container">
                                            <div class="simpleLens-big-image-container"><a data-lens-image="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${detail_product.image_link}" class="simpleLens-lens-image"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${detail_product.image_link}" class="simpleLens-big-image"></a></div>
                                                    <c:if test="${detail_product.discount != 0}">
                                                <span class="aa-badge aa-sale">- ${detail_product.discount}%</span>
                                            </c:if>
                                            <c:forEach items="${product_news}" var="i">
                                                <c:choose>
                                                    <c:when test="${i.discount != 0 && i.id == detail_product.id}">
                                                        <span class="aa-badge aa-new" style="top : 13% !important"> Mới</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:if test="${i.id == detail_product.id}">
                                                            <span class="aa-badge aa-new"> Mới</span>
                                                        </c:if>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- Modal view content -->

                            <div class="col-md-7 col-sm-7 col-xs-12">
                                <div class="aa-product-view-content">
                                    <h3 style="line-height: calc(40/26)">${detail_product.name }
                                    </h3>
                                    <p class="aa-product-detail-info">${reviewQuantity} bình luận | ${detail_product.sold} đã bán | ${detail_product.inventory} tồn kho</p>
                                    <div class="aa-price-block">
                                        <c:forEach items="${product_sellings}" var="i">
                                            <c:if test="${detail_product.id == i.id}">
                                                <p class="aa-product-detail-selling">Bán chạy</p> 
                                            </c:if>
                                        </c:forEach> 
                                        <p class="aa-product-avilability">Tình trạng: 
                                            <c:choose>
                                                <c:when test="${detail_product.status == 1}"> 
                                                    <span style="color:green;font-weight: 700;font-size: 15px""><c:out value="Còn hàng"/></span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span style="color:red;font-weight: 700;font-size: 15px"><c:out value="Hết hàng"/></span>
                                                </c:otherwise>
                                            </c:choose>
                                        </p>
                                        <p>${detail_product.description}</p>
                                        <p class="aa-prod-category">
                                            <c:forEach items="${name_cate_of_product}" var="name_cate">
                                                <strong>Danh mục:</strong> <a href="${pageContext.request.contextPath}/view/client/product-id?id=${name_cate.id}">${name_cate.name}
                                                </a>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="aa-price-product-detail">
                                        <c:choose>
                                            <c:when test="${detail_product.discount == 0}">
                                                <span class="aa-product-price">${detail_product.price} ₫</span><span class="aa-product-price"></span>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach items="${productlist1}" var="product1">
                                                    <c:if test="${product1.id == detail_product.id}">
                                                        <span class="aa-product-price color-red"><strong>${product1.price} ₫</strong></span>
                                                        <span class="aa-product-price"><del>${detail_product.price} ₫</del></span>
                                                    </c:if>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                    <div class="aa-prod-view-bottom">
                                        <c:if test="${detail_product.inventory != '0'}">
                                            <a class="aa-add-to-cart-btn" href="${pageContext.request.contextPath}/view/client/add-cart?product-id=${detail_product.id}">Thêm vào giỏ hàng </a>
                                        </c:if>
                                        <c:if test="${detail_product.inventory == '0'}">
                                            <p class="aa-over-product">Hết hàng</p>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="aa-product-details-bottom">
                        <ul class="nav nav-tabs aa-products-tab" id="myTab2">
                            <li class="active"><a href="#description" data-toggle="tab" class="">Mô tả</a></li>
                            <li><a href="#review" data-toggle="tab">Bình luận</a></li>                
                        </ul>

                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="description">
                                <p class="aa-product-detail-content">${detail_product.content}</p>
                            </div>
                            <div class="tab-pane fade " id="review">
                                <div class="aa-product-review-area">
                                    <ul class="aa-review-nav">
                                        <c:forEach items="${reviewbyid}" var="review" >
                                            <li>
                                                <div class="media">
                                                    <div class="media-body">
                                                        <h4 class="media-heading"><strong>${review.name}</strong> - <span>${review.created}</span></h4>
                                                        <div class="aa-product-rating">
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                            <span class="fa fa-star"></span>
                                                        </div>
                                                        <p>${review.content}</p>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                    <h4>Thêm đánh giá</h4>

                                    <!-- review form -->
                                    <form action="${pageContext.request.contextPath}/view/client/review?id=${detail_product.id}" method="post" class="aa-review-form">
                                        <div class="form-group">
                                            <label for="name">Tên</label>
                                            <input type="text" class="form-control" id="name" placeholder="Name" name="name" required>
                                        </div>  
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" id="email" placeholder="example@gmail.com" name="email" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="message">Đánh giá của bạn</label>
                                            <textarea class="form-control" rows="3" id="content" name="content" required></textarea>
                                        </div>

                                        <button type="submit" class="btn btn-default aa-review-submit">Gửi</button>
                                    </form>
                                </div>
                            </div>            
                        </div>
                    </div>
                    <!-- Related product -->
                    <div class="aa-product-related-item">
                        <h3 style="color : #3d9645">SẢN PHẨM LIÊN QUAN</h3>
                        <ul class="aa-product-catg aa-related-item-slider">
                            <!-- start single product item -->
                            <c:forEach items="${productById}" var="product">
                                <li>
                                    <c:forEach items="${product_sellings}" var="i">
                                        <c:if test="${product.id == i.id}">
                                            <span class="aa-product-selling">Hot</span> 
                                        </c:if>
                                    </c:forEach> 
                                    <figure>
                                        <a class="aa-product-img" href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${product.image_link}" alt=""></a>
                                    </figure>     
                                    <div class="aa-product-number-sold-wrapper">
                                        <span class="aa-product-number-sold">Đã bán ${product.sold}</span> 
                                    </div>  
                                <figcaption>
                                    <h4 class="aa-product-title"><a href="${pageContext.request.contextPath}/view/client/product-detail?id=${product.id}">${product.name}</a></h4>

                                    <c:choose>
                                        <c:when test="${product.discount == 0}">
                                            <span class="aa-product-price">${product.price} <u> ₫</u></span><span class="aa-product-price"></span> 
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
                                            <span class="aa-badge aa-new" style="top : 15% !important"> Mới</span>
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
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / product category -->
<!--  end content-->

<!--  footer-->
<jsp:include page = "./footer/footer.jsp" flush = "true" />
<!-- end footer-->



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>
<!-- Start header section -->
<jsp:include page = "./header/mainHeader.jsp" flush = "true" />
<section id="aa-user-profile">
    <div class="aa-user-profile-wrapper">
        <div class="aa-user-profile-left">
            <div class="aa-user-profile-title">
                <div class="aa-user-avatar">
                    <img src="${pageContext.request.contextPath}/view/client/assets/images/avatar/${user.avatar}" alt="Ảnh đại diện"/>
                </div>
                <p class="aa-user-name">${username}</p>
            </div>
            <ul class="nav nav-tabs aa-user-edit">
                <p><i class="fas fa-user"></i> <span>Tài khoản của tôi</span></p>
                <c:if test="${type == 1}">
                    <li class="aa-user-profile-edit active"><a data-toggle="tab" href="#menu1"><span>Hồ sơ</span></a></li>
                    <li class="aa-user-password"><a data-toggle="tab" href="#menu2"><span>Đổi mật khẩu</span></a></li>
                    <li class="aa-my-order"><a data-toggle="tab" href="#menu3"><i class="fas fa-book"></i><span>Đơn mua</span> </a></li>
                    <li class="aa-my-comment"><a data-toggle="tab" href="#menu4"><i class="fas fa-edit"></i><span>Đánh giá</span> </a></li>
                            </c:if>
                            <c:if test="${type == 2}">
                    <li class="aa-user-profile-edit"><a data-toggle="tab" href="#menu1"><span>Hồ sơ</span></a></li>
                    <li class="aa-user-password"><a data-toggle="tab" href="#menu2"><span>Đổi mật khẩu</span></a></li>
                    <li class="aa-my-order active"><a data-toggle="tab" href="#menu3"><i class="fas fa-book"></i><span>Đơn mua</span> </a></li>
                    <li class="aa-my-comment"><a data-toggle="tab" href="#menu4"><i class="fas fa-edit"></i><span>Đánh giá</span> </a></li>
                            </c:if>

            </ul>
        </div>
        <div class="aa-user-profile-right tab-content">
            <c:choose>
                <c:when test="${type == 1}">
                    <div id="menu1" class="aa-user-account-edit tab-pane fade in active"> 
                    </c:when>
                    <c:otherwise>
                        <div id="menu1" class="aa-user-account-edit tab-pane fade"> 
                        </c:otherwise>
                    </c:choose>
                    <div class="aa-user-account-heading">
                        <p>Hồ sơ của tôi</p>
                        <p>Quản lý thông tin bảo mật tài khoản</p>
                    </div>
                    <form action="${pageContext.request.contextPath}/view/client/user/edit-profile" method="POST" enctype='multipart/form-data'>
                        <div class="aa-user-account-content-wrapper">
                            <div class="aa-user-account-content">
                                <div class="form-edit">
                                    <label>Tên đăng nhập</label>
                                    <p>${user.username}</p>
                                </div>
                                <div class="form-edit" style="position: relative">
                                    <label>Mật khẩu</label>
                                    <input type="password" id="myinput" value="${user.password}" readonly="readonky">
                                    <input style="display: none" type="checkbox" onclick="myFunction1()" id="show">
                                    <label class="aa-user-show-password" for="show"></label>
                                    <script>
                                        function myFunction1() {
                                            var x = document.getElementById("myinput");
                                            if (x.type === "password") {
                                                x.type = "text";
                                            } else {
                                                x.type = "password";
                                            }
                                        }
                                    </script> 
                                </div>
                                <div class="form-edit" style="display: none">
                                    <label>ID</label>
                                    <input type="text" value="${user.id}" name="id">
                                </div>
                                <div class="form-edit">
                                    <label>Email</label>
                                    <input type="text" value="${user.email}" name="email">
                                </div>
                                <div class="form-edit">
                                    <label>Số điện thoại</label>
                                    <input type="text" value="${user.phone}" name="phone">
                                </div>
                                <div class="form-edit">
                                    <label>Địa chỉ</label>
                                    <select id="address" name="address">
                                        <option selected="selected">${user.address}</option>
                                        <c:forEach items="${city}" var="i">
                                            <c:if test="${i.cityName != user.address}">
                                                <option>${i.cityName}</option> 
                                            </c:if>                                       
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="aa-user-account-upload-image">
                                <div class="aa-user-image-upload" id="background-preview-image">
                                    <img id="image" src="${pageContext.request.contextPath}/view/client/assets/images/avatar/${user.avatar}" alt="Ảnh đại diện">
                                </div>
                                <div class="aa-user-avatar-upload">
                                    <input type="file" id="image-upload" name="image" onchange="preview()">
                                    <label for="image-upload">
                                        <p id="btn-choose-image">Chọn ảnh</p>
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="aa-user-account-save">
                            <input id="btn-save-infomation" type="submit" value="Lưu">
                        </div>
                    </form>
                </div>
                <div id="menu2" class="aa-user-account-edit tab-pane fade">
                    <div class="aa-user-account-heading">
                        <p>Đổi mật khẩu</p>
                        <p>Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</p>
                    </div>
                    <form action="${pageContext.request.contextPath}/view/client/user/change-password" method="post">
                        <div class="aa-user-account-password">
                            <div class="form-edit">
                                <label>Mật khẩu mới</label>
                                <input class="wid-50" type="password" name="newpassword">
                            </div>
                            <div class="form-edit">
                                <label>Xác nhận mật khẩu</label>
                                <input class="wid-50" type="password">
                            </div>
                            <div class="form-edit aa-user-account-save">
                                <label></label>
                                <input class="wid-20" type="submit" value="Xác nhận">
                            </div>
                        </div>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${type == 2}">
                        <div id="menu3" class="aa-user-account-edit tab-pane fade in active bg-0 pd-0">
                        </c:when>
                        <c:otherwise>
                            <div id="menu3" class="aa-user-account-edit tab-pane fade bg-0 pd-0">
                            </c:otherwise>
                        </c:choose>
                        <div class="aa-user-account-heading bg-white pd-20">
                            <p>Các đơn hàng của tôi</p>
                            <p>Xem tiến trình đơn hàng để biết thời gian nhận hàng</p>
                        </div>
                        <form>
                            <div class="aa-user-my-order">
                                <ul class="nav nav-tabs nav nav-pills nav-justified mg-15 bg-white pd-20">
                                    <li class="active"><a  data-toggle="tab" href="#choxacnhan">Chờ xác nhận</a></li>
                                    <li><a data-toggle="tab" href="#danggiao">Đang giao</a></li>
                                    <li><a data-toggle="tab" href="#dagiao">Đã giao</a></li>
                                    <li><a data-toggle="tab" href="#dahuy">Đã hủy</a></li>
                                </ul>
                                <div class="tab-content">
                                    
                                    <div id="choxacnhan" class="aa-user-my-order-detail tab-pane fade in active">
                                        <c:if test="${empty transConfirms}">
                                            <p style="text-align: center;margin:30px">Không có đơn hàng nào chờ xác nhận.</p>
                                        </c:if>
                                        <c:forEach items="${transConfirms}" var="tran">
                                            <div class="aa-user-my-order-item mg-15 bg-white pd-20">
                                                <c:forEach items="${confirm}" var="i">
                                                    <c:if test="${i.transaction_id == tran.id}">
                                                        <c:forEach items="${productlist}" var="p">
                                                            <c:if test="${i.product_id == p.id}">
                                                                <div class="aa-user-my-order-product">
                                                                    <div class="aa-user-my-order-image">
                                                                        <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${p.image_link}" alt="Ảnh sản phẩm"></a>
                                                                    </div>
                                                                    <div class="aa-user-my-order-info">
                                                                        <div class="aa-user-my-order-name">
                                                                            <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><span>${p.name}</span></a>
                                                                        </div>
                                                                        <div class="aa-user-my-order-qty">
                                                                            <span>x${i.qty}</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>                            
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                                <p class="aa-user-order-price"  style="text-align: right">Tổng số tiền : <b>${tran.amount} ₫</b></p>
                                                <div class="aa-user-my-order-button">
                                                    <a href="${pageContext.request.contextPath}/view/client/user/order/process?tranId=${tran.id}&classify=2">Hủy đơn hàng</a>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                 
                                    <div id="danggiao" class="aa-user-my-order-detail tab-pane fade">
                                        <c:if test="${empty tranDelivering}">
                                            <p style="text-align: center;margin:30px">Không có đơn hàng nào đang giao.</p>
                                        </c:if>
                                        <c:forEach items="${tranDelivering}" var="tran">
                                            <div class="aa-user-my-order-item mg-15 bg-white pd-20">
                                                <c:forEach items="${delivering}" var="i">
                                                    <c:if test="${i.transaction_id == tran.id}">
                                                        <c:forEach items="${productlist}" var="p">
                                                            <c:if test="${i.product_id == p.id}">
                                                                <div class="aa-user-my-order-product">
                                                                    <div class="aa-user-my-order-image">
                                                                        <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${p.image_link}" alt="Ảnh sản phẩm"></a>
                                                                    </div>
                                                                    <div class="aa-user-my-order-info">
                                                                        <div class="aa-user-my-order-name">
                                                                            <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><span>${p.name}</span></a>
                                                                        </div>
                                                                        <div class="aa-user-my-order-qty">
                                                                            <span>x${i.qty}</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>                            
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                                <p class="aa-user-order-price"  style="text-align: right">Tổng số tiền : <b>${tran.amount} ₫</b></p>
                                                <div class="aa-user-my-order-button">
                                                    <a href="${pageContext.request.contextPath}/view/client/user/order/process?tranId=${tran.id}&classify=1">Đã nhận hàng</a>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <div id="dagiao" class="aa-user-my-order-detail tab-pane fade">
                                        <c:if test="${empty tranDelivered}">
                                            <p style="text-align: center;margin:30px">Không có đơn hàng nào đã giao.</p>
                                        </c:if>
                                        <c:forEach items="${tranDelivered}" var="tran">
                                            <div class="aa-user-my-order-item mg-15 bg-white pd-20">
                                                <c:forEach items="${delivered}" var="i">
                                                    <c:if test="${i.transaction_id == tran.id}">
                                                        <c:forEach items="${productlist}" var="p">
                                                            <c:if test="${i.product_id == p.id}">
                                                                <div class="aa-user-my-order-product">
                                                                    <div class="aa-user-my-order-image">
                                                                        <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${p.image_link}" alt="Ảnh sản phẩm"></a>
                                                                    </div>
                                                                    <div class="aa-user-my-order-info">
                                                                        <div class="aa-user-my-order-name">
                                                                            <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><span>${p.name}</span></a>
                                                                        </div>
                                                                        <div class="aa-user-my-order-qty">
                                                                            <span>x${i.qty}</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>                            
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                                <p class="aa-user-order-price"  style="text-align: right">Tổng số tiền : <b>${tran.amount} ₫</b></p>
                                                <div class="aa-user-my-order-button">
                                                    <a style="cursor: pointer" onclick="showModal(${tran.id})">Đánh giá</a>
                                                    <script>
                                                        function showModal(id) {
                                                            document.getElementById("aa-modal-" + id).style.display = "block";
                                                        }
                                                    </script>
                                                    <a href="${pageContext.request.contextPath}/view/client/user/order/process?tranId=${tran.id}&classify=3">Mua lại</a>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                    <c:forEach items="${tranDelivered}" var="tran">
                                        <div class="modal" id="aa-modal-${tran.id}">
                                            <div class="modal-overplay"></div>
                                            <div class="modal-body">
                                                <div class="modal-inner" id="aa-modal-inner">
                                                    <i onclick="closeModal(${tran.id})" class="btn-close-modal far fa-window-close"></i>
                                                    <script>
                                                        function closeModal(id) {
                                                            document.getElementById("aa-modal-" + id).style.display = "none";
                                                        }
                                                    </script>
                                                    <h3>Chọn sản phẩm muốn đánh giá</h3>
                                                    <c:forEach items="${delivered}" var="o">
                                                        <c:if test="${o.transaction_id == tran.id}">
                                                            <c:forEach items="${productlist}" var="p">
                                                                <c:if test="${o.product_id == p.id}">
                                                                    <div class="aa-item-rate">
                                                                        <input class="rate-item" style="display: none" type="radio" id="rate-item-${o.id}" name="tran-${tran.id}">
                                                                        <div class="aa-user-my-order-product">
                                                                            <div class="aa-user-my-order-image">
                                                                                <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${p.image_link}" alt="Ảnh sản phẩm"></a>
                                                                            </div>
                                                                            <div class="aa-user-my-order-info">
                                                                                <div class="aa-user-my-order-name">
                                                                                    <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><span>${p.name}</span></a>
                                                                                </div>
                                                                                <div class="aa-user-my-order-qty">
                                                                                    <span>x${i.qty}</span>
                                                                                </div>
                                                                            </div>
                                                                        </div>
                                                                        <label class="aa-rate-order-item" for="rate-item-${o.id}"></label>
                                                                    </div> 
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                    <div id="dahuy" class="aa-user-my-order-detail tab-pane fade">
                                        <c:if test="${empty tranCancelled}">
                                            <p style="text-align: center;margin:30px">Không có đơn hàng nào đã hủy.</p>
                                        </c:if>
                                        <c:forEach items="${tranCancelled}" var="tran">
                                            <div class="aa-user-my-order-item mg-15 bg-white pd-20">
                                                <c:forEach items="${cancelled}" var="i">
                                                    <c:if test="${i.transaction_id == tran.id}">
                                                        <c:forEach items="${productlist}" var="p">
                                                            <c:if test="${i.product_id == p.id}">
                                                                <div class="aa-user-my-order-product">
                                                                    <div class="aa-user-my-order-image">
                                                                        <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><img src="${pageContext.request.contextPath}/view/client/assets/images/products/img-test/${p.image_link}" alt="Ảnh sản phẩm"></a>
                                                                    </div>
                                                                    <div class="aa-user-my-order-info">
                                                                        <div class="aa-user-my-order-name">
                                                                            <a href="${pageContext.request.contextPath}/view/client/product-detail?id=${p.id}"><span>${p.name}</span></a>
                                                                        </div>
                                                                        <div class="aa-user-my-order-qty">
                                                                            <span>x${i.qty}</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:if>                            
                                                        </c:forEach>
                                                    </c:if>
                                                </c:forEach>
                                                <p class="aa-user-order-price"  style="text-align: right">Tổng số tiền : <b>${tran.amount} ₫</b></p>
                                                <div class="aa-user-my-order-button">
                                                    <a href="${pageContext.request.contextPath}/view/client/user/order/process?tranId=${tran.id}&classify=3">Mua lại</a>
                                                    <a href="#">Lý do hủy đơn</a>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div id="menu4" class="aa-user-account-edit tab-pane fade">
                        <div class="aa-user-account-heading">
                            <p>Đổi mật khẩu</p>
                            <p>Để bảo mật tài khoản, vui lòng không chia sẻ mật khẩu cho người khác</p>
                        </div>
                        <form>
                            <div class="aa-user-account-password">
                                <div class="form-edit">
                                    <label>Mật khẩu mới</label>
                                    <input class="wid-50" type="password">
                                </div>
                                <div class="form-edit">
                                    <label>Xác nhận mật khẩu</label>
                                    <input class="wid-50" type="password">
                                </div>
                                <div class="form-edit aa-user-account-save">
                                    <label></label>
                                    <input class="wid-20" type="submit" value="Xác nhận">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            </section>
            <script>
                function preview() {
                    document.getElementById("image").src = URL.createObjectURL(event.target.files[0]);
                }
            </script>
            <jsp:include page = "./footer/footer.jsp" flush = "true" />

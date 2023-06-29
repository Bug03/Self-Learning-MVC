<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>

<!-- Start header section -->
<jsp:include page = "./header/mainHeader.jsp" flush = "true" />
<!-- / header section -->

<!--  content -->
<!-- catg header banner section -->
<section class="aa-checkout-success-done">
    <section class="aa-checkout-success-wrapper">
        <div class="aa-checkout-success-notify">
            <div class="aa-checkout-success-content">
                <div class="aa-checkout-success-title">
                    <i class="fas fa-check-circle"></i>
                    <p>Cảm ơn bạn đã đặt hàng. ID đơn hàng : ${successTransaction.id}</p>
                </div>
            </div>
            <div class="aa-checkout-success-btn">
                <a href="${pageContext.request.contextPath}/view/client/account?type=2">Xem đơn hàng của bạn</a>
            </div>
        </div>
    </section>
</section>

<!--  footer-->
<jsp:include page = "./footer/footer.jsp" flush = "true" />
<!-- end footer-->




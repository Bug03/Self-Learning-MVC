<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>

<!-- footer -->  
<footer id="aa-footer">
    <div class="footer-main">
        <div class="footer-logo">
            <a href="${pageContext.request.contextPath}/"><img src="${url}/images/logo1.png" alt="7Seven" style="width:200px"></a>
        </div>
        <ul class="footer-about">
            <li class="footer-item"><a>Về chúng tôi</a></li>
            <li class="footer-item"><a href="${pageContext.request.contextPath}/view/client/introduce">Giới thiệu về chúng tôi</a></li>
            <li class="footer-item"><a href="${pageContext.request.contextPath}/view/client/security">Chính sách bảo mật</a></li>
            <li class="footer-item"><a href="#">Hệ thống của hàng</a></li>
            <li class="footer-item"><a href="${pageContext.request.contextPath}/view/client/contact">Liên hệ</a></li>
        </ul>
        <div class="footer-support">
            <li class="footer-item"><a href="#">Hỗ trợ khách hàng</a></li>
            <li class="footer-item"><a href="#">Hướng dẫn đặt hàng</a></li>
            <li class="footer-item"><a href="#">Hướng dẫn thanh toán</a></li>
            <li class="footer-item"><a href="#">Tuyển dụng</a></li>
            <li class="footer-item"><a href="https://www.facebook.com/7sevenmobile"  target="_blank">Đặt câu hỏi</a></li>
        </div>
        <div class="footer-policy">
            <li class="footer-item"><a href="#">Quy định chính sách</a></li>
            <li class="footer-item"><a href="#">Chính sách đổi trả</a></li>
            <li class="footer-item"><a href="#">Quy định bảo hành</a></li>
            <li class="footer-item"><a href="#">Chính sách vận chuyển</a></li>
            <li class="footer-item"><a href="#">Chính sách bảo mật</a></li>
        </div>
        <div class="footer-subscribe">
            <p>Subscribe</p>
            <div class="footer-subscribe-link">
                <input type="text" name="subscribe">
                <div class="footer-subscribe-icon">
                    <i class="far fa-paper-plane"></i>
                </div>
            </div>
            <p>Nhập email của bạn tại đây</p>
            <div class="footer-image">
                <img srcset="${url}/images/dadangky.png" alt="Đăng ký">
            </div>
        </div>
    </div>
    <div class="footer-bottom">
        <div class="footer-design">
            <p>@CopyRight SG4Store 2023</p>
        </div>
        <div class="footer-social">
            <i class="fab fa-facebook-square"></i>
            <i class="fab fa-instagram"></i>
            <i class="fab fa-linkedin-in"></i>
            <i class="fab fa-twitter"></i>
        </div>
    </div>
</footer>
<!-- / footer -->

</body>
<jsp:include page = "script.jsp" flush = "true" />

</html>

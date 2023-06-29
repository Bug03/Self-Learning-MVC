<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="${url}/css/flexsiel.css"/>
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
        <title>JSP Page</title>
        <script>
            $(window).load(function () {
                $("#flexiselDemo3").flexisel({
                    visibleItems: 3,
                    itemsToScroll: 1,
                    autoPlay: {
                        enable: true,
                        interval: 5000,
                        pauseOnHover: true
                    }
                });
            });
        </script>
    </head>
    <body id="page2">
        <section class="aa-banner-flexsiel">
            <ul id="flexiselDemo3">
                <li><img src="${url}/images/apple_logo.svg" alt="Ảnh" width="200px"></li>
                <li><img src="${url}/images/samsung_logo.svg" alt="Ảnh" width="200px"></li>
                <li><img src="${url}/images/oppo_logo.svg" alt="Ảnh" width="200px"></li>
                <li><img src="${url}/images/xiaomi_logo.svg" alt="Ảnh" width="200px"></li>
                <li><img src="${url}/images/vivo_logo.svg" alt="Ảnh" width="200px"></li>
                <li><img src="${url}/images/asus_logo.svg" alt="Ảnh" width="200px"></li>
            </ul>
            <div class="clearout"></div>
        </section>
    </body>
</html>

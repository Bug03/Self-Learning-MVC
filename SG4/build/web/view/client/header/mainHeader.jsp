<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<c:url value = "/view/client/assets" var="url"/>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>  
        <title>SG4Store mua điện thoại i mọi người</title>

        <!-- Font awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
              integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
        <link href="${url}/css/font-awesome.css" rel="stylesheet">

        <!-- Bootstrap -->
        <link href="${url}/css/bootstrap.css" rel="stylesheet">   
        <link rel="icon" href="${url}/images/logo.svg" type="image/x-icon">
        <!-- SmartMenus jQuery Bootstrap Addon CSS -->
        <link href="${url}/css/jquery.smartmenus.bootstrap.css" rel="stylesheet">
        <!-- Product view slider -->
        <link rel="stylesheet" type="text/css" href="${url}/css/jquery.simpleLens.css">    
        <!-- slick slider -->
        <link rel="stylesheet" type="text/css" href="${url}/css/slick.css">
        <!-- Theme color -->
        <link id="switcher" href="${url}/css/theme-color/default-theme.css" rel="stylesheet">

        <!-- Top Slider CSS -->
        <link href="${url}/css/sequence-theme.modern-slide-in.css" rel="stylesheet" media="all">

        <!-- Main style sheet -->
        <link href="${url}/css/style.css" rel="stylesheet">    

        <!-- Google Font -->
        <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
        <link href='https://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css'>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@1,700&display=swap" rel="stylesheet">

    </head>
    <body>

        <header id="aa-header">
            <jsp:include page = "topHeader.jsp" flush = "true" />
            <!-- start header bottom  -->
            <jsp:include page = "bottomHeader.jsp" flush = "true" />
            <!-- / header bottom  -->
        </header>

        <!-- menu -->
        <jsp:include page = "menu.jsp" flush = "true" />
        <!-- / menu -->


        <!-- wpf loader Two -->
<!--        <div id="wpf-loader-two">          
            <div class="wpf-loader-two-inner">
                <span>Loading...</span>
            </div>
        </div> -->
        <!-- / wpf loader Two -->       
        <!-- SCROLL TOP BUTTON -->
        <a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
        <!-- END SCROLL TOP BUTTON -->

<%@page import="javax.naming.InitialContext"%>
<%@page import="ejb.UserSessionBean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js">
    <head>
        <!-- Mobile Specific Meta -->
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Favicon-->
        <link rel="shortcut icon" href="assets/img/fav.png">
        <!-- Author Meta -->
        <meta name="author" content="Colorlib">
        <!-- Meta Description -->
        <meta name="description" content="">
        <!-- Meta Keyword -->
        <meta name="keywords" content="">
        <!-- meta character set -->
        <meta charset="UTF-8">
        <!-- Site Title -->
        <title>Patio de las culturas</title>

        <link href="https://fonts.googleapis.com/css?family=Poppins:100,200,400,300,500,600,700" rel="stylesheet">
        <!--
        CSS
        ============================================= -->
        <link rel="stylesheet" href="assets/css/linearicons.css">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css" integrity="sha384-fnmOCqbTlWIlj8LyTjo7mOUStjsKC4pOpQbqyi7RrhN7udi9RwhKkMHpvLbHG9Sr" crossorigin="anonymous">
        <link rel="stylesheet" href="assets/css/font-awesome.min.css">
        <link rel="stylesheet" href="assets/css/magnific-popup.css">
        <link rel="stylesheet" href="assets/css/nice-select.css">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="assets/css/bootstrap.css">
        <link rel="stylesheet" href="assets/css/main.css">
    </head>
    <body>
        <header class="default-header">
            <div class="container">
                <div class="header-wrap">
                    <div class="header-top d-flex justify-content-between align-items-center">
                        <div class="logo">
                            <a href="./#Home"><img src="assets/img/logo.png" alt=""></a>
                        </div>
                        <div class="main-menubar d-flex align-items-center">
                            <nav>
                                <a href="./">Inicio</a>
                                <a href="./#project">Proyectos</a>
                                <a href="./#about">Nosotros</a>
                                <a href="./#donate">Donar</a>
                                <c:if test="${sessionScope.privilegeLevel == 0 || sessionScope.privilegeLevel == 1}">
                                    <a href="./Home?command=DashBoardCommand">Administrar</a>
                                </c:if>
                                <c:if test="${sessionScope.nickName != null}">
                                    Hola, ${sessionScope.nickName} 
                                    <a href="./Home?command=LoginCommand&param=logout">Logout</a>
                                </c:if>
                            </nav>
                            <div class="menu-bar"><span class="lnr lnr-cross"></span></div>
                        </div>
                    </div>
                </div>
            </div>
        </header>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <link rel="stylesheet" href="bootstrap/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/menu.css" type="text/css">
        <link rel="stylesheet" href="css/styles.css" type="text/css"/>
        <title>Otica Nova</title>
        
    </head>
    <body>
        <div id="container">
            
            
            <div id="menu">
                <jsp:include page="template/menuInicial.jsp"></jsp:include>
            </div><!-- Fim da div menu -->
            
            <main>
                <div id="conteudo" class="bg-background">
                    <h1 class="pt-lg-5 text-center">
                        Seu sistema de ordem de serviços fácil de usar
                    </h1>

                </div><!-- Fim da div conteudo -->
            </main><!-- Fim da div conteudo -->
        </div><!-- Fim da div container -->
     
    </body>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="bootstrap/bootstrap.min.js"></script>

</html>

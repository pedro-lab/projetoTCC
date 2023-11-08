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
        <link rel="shortcut icon" href="./imagens/logo.png">
        <title>Otica Nova</title>

    </head>
    <body>
        <div id="container">


            <div id="conteudo" class="bg-background">
                <%
                HttpSession sessao = request.getSession();
                if(sessao.getAttribute("msg") != null){
                    
                    String msg = (String) sessao.getAttribute("msg");
                    if (msg != null) {
                           
                    %>
                    <div class="alert alert-danger" role="alert">
                        <%= msg %>
                        <button type="button" class="close" data-dismiss="alert"
                                >
                            <span>&times;</span>
                        </button>
                    </div>
                    
                    <%
                        
                    }
                        
                }
                
                %>
                <div class="container">
                    <form action="" method="post" class="form-group">
                        <h3 class="text-center mt-5">Página de login</h3>
                        
                        <div class="form-group row mt-5 offset-md-2">
                            <label class="col-md-3">Login</label>
                            <div class="col-md-5">
                                <input type="text" name="login" class="form-control">
                            </div>
                        </div>
                        <div class="form-group row offset-md-2">
                            <label class="col-md-3">Senha</label>
                            <div class="col-md-5">
                                <input type="text" name="senha" 
                                       class="form-control">
                            </div>
                        </div>
                        <div class="container-fluid  row mt-5">
                            <a href="opcoes.jsp" class="btn btn-primary mr-3 offset-5" role="button">Logar</a>
                            <a href="index.jsp" 
                               class="btn  btn-warning" role="button">Voltar
                            </a>
                        </div>
                        
                    </form>

                </div>

            </div><!-- Fim da div conteudo -->
        </div><!-- Fim da div container -->

    </body>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="bootstrap/bootstrap.min.js"></script>

</html>
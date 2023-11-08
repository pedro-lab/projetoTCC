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
                <jsp:include page="template/menu.jsp"></jsp:include>
            </div><!-- Fim da div menu -->

            <main>
                <div id="conteudo" class="container mr-auto ml-auto">
                    <div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex;">
                        <img src="./imagens/clipcardRegistrar.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Ver ordem de serviço</h5>
                            <p class="card-text">Ver a ordem de serviços dos laboratórios</p>
                            <a href="gerenciarOS?acao=listar" class="btn btn-primary">Ver servicos</a>
                        </div>
                    </div>
                    <div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex">
                        <img src="imagens/clipcardLaboratorio.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Consultar lentes</h5>
                            <p class="card-text">Consulte e registre lentes na nossa base de dados</p>
                            <a href="gerenciarLente?acao=listar" class="btn btn-primary">Ver lentes</a>
                        </div>
                    </div>
                    <div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex">
                        <img src="imagens/clipcardLab.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Consultar Laboratórios</h5>
                            <p class="card-text">Consulte e registre laboratórios na nossa base de dados</p>
                            <a href="gerenciarLaboratorio?acao=listar" class="btn btn-primary">Ver laboratórios</a>
                        </div>
                    </div>
                    <div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex">
                        <img src="imagens/clipcardRetirada.png" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Consultar Clientes</h5>
                            <p class="card-text">Consulte e registre clientes na nossa base de dados</p>
                            <a href="gerenciarCliente?acao=listar" class="btn btn-primary">Ver Clientes</a>
                        </div>
                    </div>
                    <div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex;">
                        <img src="./imagens/clipcardMenu.png" class="card-img-top" alt="..." style="height: 190.7px">
                        <div class="card-body">
                            <h5 class="card-title">Consultar Menus</h5>
                            <p class="card-text">Consulte e registre menus na nossa base de dados</p>
                            <a href="gerenciarMenu?acao=listar" class="btn btn-primary">Ver Menus</a>
                        </div>
                    </div>
                    <div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex;">
                        <img src="./imagens/clipcardPerfil.png" class="card-img-top" alt="..." style="height: 190.7px">
                        <div class="card-body">
                            <h5 class="card-title">Consultar Perfil</h5>
                            <p class="card-text">Consulte e registre perfis na nossa base de dados</p>
                            <a href="gerenciarMenu?acao=listar" class="btn btn-primary">Ver Perfil</a>
                        </div>
                    </div>
                </div><!-- Fim da div conteudo -->
            </main><!-- Fim da div conteudo -->
        </div><!-- Fim da div container -->

    </body>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="bootstrap/bootstrap.min.js"></script>

</html>
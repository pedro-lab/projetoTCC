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
        <title>Ótica Nova</title>

    </head>
    <body>
        <div id="container">

            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
                </div><!-- Fim da div menu -->
                <main>
                    <div id="conteudo" class="bg-background">
                    <%
                        HttpSession sessao = request.getSession();
                        if (sessao.getAttribute("msg") != null) {

                            String msg = (String) sessao.getAttribute("msg");
                            if (msg != null) {

                    %>
                    <div class="alert alert-danger" role="alert">
                        <%= msg%>
                        <button type="button" class="close" data-dismiss="alert">
                            <span>&times;</span>
                        </button>
                    </div>

                    <%
                                
                            }
                            sessao.removeAttribute("msg");
                        }

                    %>
                    <div class="container">
                        <form action="gerenciarUsuario?acao=cadastrar" method="post" class="form-group">
                            <h3 class="text-center">Cadastro de Usuário</h3>
                            <input type="hidden" name="idUsuario" value="${usuario.idUsuario}">

                            <div class="form-group row mt-5 offset-md-2">
                                <label class="col-md-3">Nome</label>
                                <div class="col-md-5">
                                    <input type="text" name="nome" value="${usuario.nome}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Login</label>
                                <div class="col-md-5">
                                    <input type="text" name="login" value="${usuario.login}" 
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Senha</label>
                                <div class="col-md-5">
                                    <input type="password" name="senha" 
                                           class="form-control">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Status</label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="status">
                                        <option value="">Escolha uma opção</option>
                                        <option value="1"
                                                <c:if test="${usuario.status == 1}">
                                                    selected
                                                </c:if>>Ativado</option>
                                        <option value="0"
                                                <c:if test="${usuario.status == 0}">
                                                    selected
                                                </c:if>>Desativado</option>
                                    </select>

                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Perfil</label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="idPerfil">
                                        <option value="">Escolha uma opção</option>
                                        <jsp:useBean class="dao.PerfilDAO" id="pdao"/>
                                        <c:forEach items="${pdao.lista}" var="p">
                                            <option value="${p.idPerfil}"
                                                    <c:if test="${p.idPerfil == usuario.perfil.idPerfil}">
                                                        selected</c:if>>
                                                    ${p.nome}</option>
                                            </c:forEach>
                                    </select>

                                </div>
                            </div>
                            <div class="d-md-flex justify-content-md-end mt-5 mr-5">
                                <button class="btn btn-primary mr-3">Gravar</button>
                                <a href="gerenciarUsuario?acao=listar" 
                                   class="btn  btn-warning" role="button">Voltar
                                </a>
                            </div>

                        </form>

                    </div>

                </div><!-- Fim da div conteudo -->
            </main>
        </div><!-- Fim da div container -->

    </body>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="bootstrap/bootstrap.min.js"></script>

</html>

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
                        <button type="button" class="close" data-dismiss="alert"
                                >
                            <span>&times;</span>
                        </button>
                    </div>

                    <%

                            }
                            sessao.removeAttribute("msg");
                        }

                    %>
                    <div class="container">
                        <form action="gerenciarLaboratorio?acao=cadastrar" method="post" class="form-group">
                            <h3 class="text-center">Cadastro de Laboratorio</h3>
                            <input type="hidden" name="idLaboratorio" value="${laboratorio.idLaboratorio}">

                            <div class="form-group row mt-5 offset-md-2">
                                <label class="col-md-3">Nome<sup class="text-danger">*</sup></label>
                                <div class="col-md-5">
                                    <input type="text" name="nome" value="${laboratorio.nome}" class="form-control">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Endereco</label>
                                <div class="col-md-5">
                                    <input type="text" name="endereco" 
                                           class="form-control" value="${laboratorio.endereco}">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Telefone</label>
                                <div class="col-md-5">
                                    <input type="tel" name="telefone" id="telefone" maxlength="15" 
                                           class="form-control" value="${laboratorio.telefone}">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Email</label>
                                <div class="col-md-5">
                                    <input type="email" name="email" 
                                           class="form-control" value="${laboratorio.email}">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Status<sup class="text-danger">*</sup></label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="status">
                                        <option value="">Escolha uma opção</option>
                                        <option value="1"
                                                <c:if test="${laboratorio.status == 1}">
                                                    selected
                                                </c:if>>Ativado</option>
                                        <option value="0"
                                                <c:if test="${laboratorio.status == 0}">
                                                    selected
                                                </c:if>>Desativado</option>
                                    </select>

                                </div>
                            </div>
                            <div class="d-md-flex justify-content-md-end mt-5 mr-5">
                                <button class="btn btn-primary mr-3">Gravar</button>
                                <a href="gerenciarLaboratorio?acao=listar" 
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
    <script>
        // Mascara para telefone
        const tel = document.getElementById('telefone') // Seletor do campo de telefone

        tel.addEventListener('keypress', (e) => mascaraTelefone(e.target.value)) // Dispara quando digitado no campo
        tel.addEventListener('change', (e) => mascaraTelefone(e.target.value)) // Dispara quando autocompletado o campo

        const mascaraTelefone = (valor) => {
            valor = valor.replace(/\D/g, "")
            valor = valor.replace(/^(\d{2})(\d)/g, "($1) $2")
            valor = valor.replace(/(\d)(\d{4})$/, "$1-$2")
            tel.value = valor // Insere o(s) valor(es) no campo
        }
    </script>

</html>
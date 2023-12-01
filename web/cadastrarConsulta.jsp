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
                                arial-label="Close">
                            <span>&times;</span>
                        </button>
                    </div>

                    <%

                            }
                            sessao.removeAttribute("msg");
                        }

                    %>
                    <div class="container">
                        <form action="gerenciarAgendaConsulta?acao=cadastrar" method="post" class="form-group">
                            <h3 class="text-center mb-5">Agendamento de Consulta</h3>
                            <input type="hidden" name="idConsulta" value="${consulta.idConsulta}">

                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Dia e hora</label>
                                <div class="col-md-5">
                                    <input type="datetime-local" name="diaHora" 
                                           class="form-control" value="${consulta.diaHora}">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Observacoes</label>
                                <div class="col-md-5">
                                    <textarea id="observacoes" name="observacoes" rows="5" cols="10" class="w-100">
                                        
                                    </textarea>
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Confirmacoes</label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="idCliente">
                                        <option value="">Escolha uma opção</option>
                                        <option value="Confirmado">Confirmado</option>
                                        <option value="Cancelou">Cancelou</option>
                                        <option value="Remarcou">Remarcou</option>
                                        <option value="Sem Resposta">Sem Resposta</option>
                                    </select>

                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Nome do Cliente<sup class="text-danger">*</sup></label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="idCliente">
                                        <option value="">Escolha uma opção</option>
                                        <jsp:useBean class="dao.ClienteDAO" id="cdao"/>
                                        <c:forEach items="${cdao.lista}" var="c">
                                            <option value="${c.idCliente}"
                                                    <c:if test="${c.idCliente == ordemServico.cliente.idCliente}">
                                                        selected</c:if>>
                                                    ${c.nome}</option>
                                            </c:forEach>
                                    </select>

                                </div>
                            </div>
                            <div class="d-md-flex justify-content-md-end mt-5 mr-5">
                                <button class="btn btn-primary mr-3">Gravar</button>
                                <a href="" id="backListagem" 
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

<script>
    var voltar = document.querySelector("#backListagem");
    voltar.href = sessionStorage.getItem("uri");
</script>
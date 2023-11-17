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
        <title>Otica</title>

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

                        }

                    %>
                    <div class="container">
                        <form action="gerenciarOrdemServico?acao=cadastrar" method="post" class="form-group">
                            <h3 class="text-center mb-5">Cadastro de Ordem de Servico</h3>
                            <input type="hidden" name="idOs" value="${ordemServico.idOs}">
                            <input type="hidden" name="statusEntrega" value="${ordemServico.statusEntrega}">
                            <input type="hidden" name="idUsuario" value="${ulogado.idUsuario}">
                            <input type="hidden" name="ano" id="ano">
                            <input type="hidden" name="mes" id="mes">
                            <input type="hidden" name="dia" id="dia">

                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Data de Venda<sup class="text-danger">*</sup></label>
                                <div class="col-md-5">
                                    <input type="date" name="dataSolicitacao" 
                                           class="form-control" value="${ordemServico.dataSolicitacao}">
                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Data de Vencimento</label>
                                <div class="col-md-5">
                                    <input type="date" name="vencimento" 
                                           class="form-control" value="${ordemServico.vencimento}">
                                </div>
                            </div>
                            <div class="form-group row mt-5 offset-md-2">
                                <label class="col-md-3">data do OS</label>
                                <div class="col-md-5">
                                    <input type="date" name="dataOS" value="${ordemServico.dataOS}" 
                                           class="form-control" id="dataOS">
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
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Nome da Lente<sup class="text-danger">*</sup></label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="idLente">
                                        <option value="">Escolha uma opção</option>
                                        <jsp:useBean class="dao.LenteDAO" id="ldao"/>
                                        <c:forEach items="${ldao.lista}" var="l">
                                            <option value="${l.idLente}"
                                                    <c:if test="${l.idLente == ordemServico.lente.idLente}">
                                                        selected</c:if>>
                                                    ${l.nome}/${l.modelo}</option>
                                            </c:forEach>
                                    </select>

                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Nome da Laboratório<sup class="text-danger">*</sup></label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="idLaboratorio">
                                        <option value="">Escolha uma opção</option>
                                        <jsp:useBean class="dao.LaboratorioDAO" id="labdao"/>
                                        <c:forEach items="${labdao.lista}" var="lab">
                                            <option value="${lab.idLaboratorio}"
                                                    <c:if test="${lab.idLaboratorio == ordemServico.laboratorio.idLaboratorio}">
                                                        selected</c:if>>
                                                    ${lab.nome}</option>
                                            </c:forEach>
                                    </select>

                                </div>
                            </div>
                            <div class="form-group row offset-md-2">
                                <label class="col-md-3">Status</label>
                                <div class="col-md-5">
                                    <select class="form-control-sm" name="status">
                                        <option value="">Escolha uma opção</option>
                                        <option value="1"
                                                <c:if test="${ordemServico.status == 1}">
                                                    selected
                                                </c:if>>Ativado</option>
                                        <option value="0"
                                                <c:if test="${ordemServico.status == 0}">
                                                    selected
                                                </c:if>>Desativado</option>
                                    </select>

                                </div>
                            </div>
                            <div class="d-md-flex justify-content-md-end mt-5 mr-5">
                                <button class="btn btn-primary mr-3">Gravar</button>
                                <button href="" id="backListagem" 
                                   class="btn  btn-warning" role="button">Voltar
                                </button>
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
        var link = document.querySelector("#backListagem");
        var ano = sessionStorage.getItem("ano");
        var mes = sessionStorage.getItem("mes");
        link.href = "gerenciarOrdemServico?acao=listar&ano=" + ano +
                "&mes=" + mes;

        //Setar input dataOS
        var data = new Date;
        var dia = data.getDate();
        dia = dia.toString()
        if (dia.length === 1) {
            dia = "0" + dia;
            console.log("entrou")
        }

        var dataOS = document.getElementById("dataOS");
        dataOS.value = ano + "-" + mes + "-" + dia;
        
        //input ano e mes 
        var inputAno = document.getElementById("ano");
        var inputMes = document.getElementById("mes");
        inputAno.value = ano;
        inputMes.value = mes;
    </script>
</html>
<%-- 
    Document   : listarConsulta
    Created on : 30/11/2023, 18:48:03
    Author     : Pedro 
--%>

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
        <link rel="stylesheet" href="datatables/dataTables.bootstrap4.min.css" type="text/css"/>
        <link rel="stylesheet" href="datatables/jquery.dataTables.min.css" type="text/css"/>
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
                        <div class="container">
                        <%
                            HttpSession sessao = request.getSession();

                            String dataInicial = (String) sessao.getAttribute("dataInicial");
                            String dataFinal = (String) sessao.getAttribute("dataFinal");

                            String[] dataSeparadaInicial = dataInicial.split("-");
                            String[] dataSeparadaFinal = dataFinal.split("-");

                            dataInicial = "";
                            dataFinal = "";

                            dataInicial = dataSeparadaInicial[2] + "/" + dataSeparadaInicial[1] + "/" + dataSeparadaInicial[0];
                            dataFinal = dataSeparadaFinal[2] + "/" + dataSeparadaFinal[1] + "/" + dataSeparadaFinal[0];
                        %>
                        <h3 class="text-center">Listagem de Consultas de <%=dataInicial%> até <%=dataFinal%></span> </h3>
                        <a href="cadastrarConsulta.jsp" class="btn-sm btn-primary mb-5" 
                           role="button" style="text-decoration: none;display:inline-block;">Agendamento</a>
                        <table class="table table-hover table-striped table-bordered mt-3" id="mytable">
                            <thead>
                                <tr class="thead-dark">
                                    <th scope="col">Data/hora</th>
                                    <th scope="col">Dia Sem</th>
                                    <th scope="col">Cliente</th>
                                    <th scope="col">Data de Nasc</th>
                                    <th scope="col">Idade</th>
                                    <th scope="col">Observações</th>
                                    <th scope="col">Contato</th>
                                    <th scope="col">Confirmação</th>
                                    <th scope="col">Ação</th>
                                </tr>
                            </thead>
                            <tbody class="table-light">
                                <c:forEach items="${consultas}" var="c">
                                    <tr>
                                        <td style="width: 100px"><fmt:formatDate pattern="dd/MM/yyyy  HH:mm" value="${c.diaHora}"></fmt:formatDate></td>
                                            <td id="colunaDiaSemana">
                                                <input type="hidden" id="diaSemana" name="diaSemana" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${c.diaHora}"></fmt:formatDate>">
                                            <fmt:formatDate pattern="yyyy-MM-dd" value="${c.diaHora}"></fmt:formatDate>
                                            </td>
                                            <td>${c.cliente.nome}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty c.cliente.dataNasc}">
                                                    Não registrado
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${c.cliente.dataNasc}"></fmt:formatDate>
                                                </c:otherwise>
                                            </c:choose>
                                            </td>
                                            <td>
                                            <c:choose>
                                                <c:when test="${c.cliente.idade == 0}">
                                                    Não registrado
                                                </c:when>
                                                <c:otherwise>
                                                    ${c.cliente.idade}
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${c.observacoes}</td>
                                        <td>${c.cliente.telefone}</td>
                                        <td>${c.confirmacao}</td>
                                        <td>
                                            <a href="gerenciarAgendaConsulta?acao=alterar&idConsulta=${c.idConsulta}" 
                                               class="btn btn-warning btn-sm" role="button">Alterar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="d-md-flex justify-content-md-end mt-5 mr-5">
                            <a href="filtroConsulta.jsp" 
                               class="btn  btn-warning" role="button">Voltar
                            </a>
                        </div>

                    </div>

                </div><!-- Fim da div conteudo -->
            </main>
        </div><!-- Fim da div container -->
        <!-- JQuery -->
        <script src="js/jquery-3.6.0.min.js"></script>
        <!-- JQuery.Datatables -->
        <script src="datatables/jquery.dataTables.min.js"></script>
        <!-- Bootstrap.min -->
        <script src="bootstrap/bootstrap.min.js"></script>
        <!-- Datables.Bootstrap.min -->
        <script src="datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Configuracao da tabela com JQuery -->
        <script>
            $(document).ready(function () {
                $('#mytable').dataTable({
                    "bJQueryUI": true,
                    "lengthMenu": [[5, 10, 20, 25, -1], [5, 10, 20, 25, "Todos"]],
                    "oLanguage": {
                        "sProcessing": "Processando",
                        "sLenghtMenu": "Montrar _MENU_ registros",
                        "sZeroRecords": "Não foram encontrados resultados",
                        "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                        "sInfoEmpty": "Monstrado de 0 até 0 de 0 registros",
                        "sInfoFiltered": "",
                        "sInfoPostFix": "",
                        "sSearch": "Pesquisar",
                        "sUrl": "",
                        "oPaginate": {
                            "sFirst": "Primeiro",
                            "sPrevious": "Anterior",
                            "sNext": "Próximo",
                            "sLast": "Último"
                        }

                    }
                });
            });
        </script>
    </body>
</html>
<script>
    //Guarda na secao a uri da pagina
    var uri = window.location.href;
    sessionStorage.setItem("uri", uri);

    //Pega o dia da semana segundo a data
    var inputDiaSemana = document.querySelectorAll("#diaSemana")
    var colunaDiaSemana = document.querySelectorAll("#colunaDiaSemana")
    var data = new Date(inputDiaSemana.value)
    var contador = 0;
    var diaDaSemana

    inputDiaSemana.forEach(function (d) {
        var data = new Date(d.value)
        diaDaSemana = data.getDay()
        switch (diaDaSemana) {
            case 0:
                colunaDiaSemana[contador].innerHTML = "Seg";
                break
            case 1:
                colunaDiaSemana[contador].innerHTML = "Ter";
                break
            case 2:
                colunaDiaSemana[contador].innerHTML = "Qua";
                break
            case 3:
                colunaDiaSemana[contador].innerHTML = "Qui";
                break
            case 4:
                colunaDiaSemana[contador].innerHTML = "Sex";
                break
            case 5:
                colunaDiaSemana[contador].innerHTML = "Sab";
                break
            case 6:
                colunaDiaSemana[contador].innerHTML = "Dom";
                break
        }
        contador += 1
    })

    //Titulo da pagina
    var inf = document.querySelector("#inf")
    var dataInicial = sessionStorage.getItem("dataInicial")
    var dataFinal = sessionStorage.getItem("dataFinal")
    inf.innerHTML = "de " + dataInicial + " até " + dataFinal;
</script>
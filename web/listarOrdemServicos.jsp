<%@page import="model.OrdemServico"%>
<%@page import="dao.OrdemServicoDAO"%>
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
        <title>Projeto ETB</title>

    </head>
    <body>
        <div id="container">
            <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
                </div><!-- Fim da div menu -->
                <main>
                    <div id="conteudo" class="bg-background">
                        <div class="container">
                            <h3 class="text-center">Listagem de Ordem de Servico</h3>
                            <a href="cadastrarOS.jsp" class="btn-sm btn-primary mb-5" 
                               role="button" style="text-decoration: none;display:inline-block;">Cadastrar OS</a>
                            <table class="table table-hover table-striped table-bordered mt-3" id="mytable">
                                <thead>
                                    <tr class="thead-dark">
                                        <th scope="col">Código</th>
                                        <th scope="col">Cliente</th>
                                        <th scope="col">Lente</th>
                                        <th scope="col">Laboratorio</th>
                                        <th scope="col">Data Venda</th>
                                        <th scope="col">Vencimento</th>
                                        <th scope="col">Ent. na loja</th>
                                        <th scope="col">Status de Entrega</th>
                                        <th scope="col">Status</th>
                                        <th scope="col">Ação</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${ordemServicos}" var="os">
                                    <tr>
                                        <td>${os.idOs}</td>
                                        <td>${os.cliente.nome}</td>
                                        <td>${os.lente.nome}</td>
                                        <td>${os.laboratorio.nome}</td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${os.dataSolicitacao}"></fmt:formatDate></td>
                                        <td><fmt:formatDate pattern="dd/MM/yyyy" value="${os.vencimento}"></fmt:formatDate></td>
                                            <td>
                                            <c:choose>
                                                <c:when test="${empty os.dataEntrega}">
                                                    Sem entrada
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:formatDate pattern="dd/MM/yyyy" value="${os.dataEntrega}"></fmt:formatDate>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>

                                        <td>
                                            <jsp:useBean class="dao.OrdemServicoDAO" id="osdao"/>
                                            <c:if test="${empty os.statusEntrega}">
                                                <c:choose>
                                                    <c:when test="${os.statusVencimento == 'No prazo'}">
                                                        No prazo
                                                    </c:when>
                                                    <c:otherwise>
                                                        Atrasado
                                                    </c:otherwise>
                                                </c:choose>    
                                            </c:if>
                                            <c:if test="${not empty os.statusEntrega}">
                                                ${os.statusEntrega}
                                            </c:if>


                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${os.status == 1}">
                                                    Ativado
                                                </c:when>
                                                <c:otherwise>
                                                    Desativado
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <script type="text/javascript">
                                                function confirmDesativar(id) {
                                                    if (confirm("Deseja desativar a ordem de servico de numero " +
                                                            id + "?")) {
                                                        location.href = "gerenciarOrdemServico?acao=desativar&idOrdemServico=" + id;

                                                    }

                                                }

                                                function confirmAtivar(id) {

                                                    if (confirm("Deseja a ordem de servico de numero " +
                                                            id + "?")) {
                                                        location.href = "gerenciarOrdemServico?acao=ativar&idOrdemServico=" + id;

                                                    }
                                                }
                                            </script>
                                            <a href="gerenciarOrdemServico?acao=alterar&idOrdemServico=${os.idOs}" 
                                               class="btn btn-warning btn-sm" role="button">Alterar</a>
                                            <c:choose>
                                                <c:when test="${os.status == 1}">
                                                    <a class="btn btn-sm btn-danger " style="text-decoration: none"
                                                       onclick="confirmDesativar('${os.idOs}')">Desativar</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a class="btn btn-success btn-sm" 
                                                       onclick="confirmAtivar('${os.idOs}')">Ativar</a>
                                                </c:otherwise>
                                            </c:choose>
                                            <a href="gerenciarOrdemServico?acao=atualizarEntrega&idOrdemServico=${os.idOs}&statusEntrega=Na loja" 
                                               class="btn btn-dark btn-sm" role="button">Confirmar</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <div class="d-md-flex justify-content-md-end mt-5 mr-5">
                            <a href="opcoes.jsp" 
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
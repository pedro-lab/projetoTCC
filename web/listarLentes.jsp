
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
            <div id="header">
                <jsp:include page="template/banner.jsp"></jsp:include>
                </div><!-- Fim da div header -->

                <div id="menu">
                <jsp:include page="template/menu.jsp"></jsp:include>
                </div><!-- Fim da div menu -->

                <div id="conteudo" class="bg-background">
                    <div class="container">
                        <h3 class="text-center">Listagem de Lentes</h3>
                        <a href="cadastrarCliente.jsp" class="btn-sm btn-primary " 
                           style="text-decoration: none">Cadastrar Lente</a>
                        <table class="table table-hover table-striped table-bordered mt-3" id="asd">
                            <thead>
                                <tr class="thead-dark">
                                    <th scope="col">Código</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Modelo</th>
                                    <th scope="col">Fabricante</th>
                                    <th scope="col">Preço</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${lentes}" var="l">
                                <tr>
                                    <td>${l.idLente}</td>
                                    <td>${l.nome}</td>
                                    <td>${l.modelo}</td>
                                    <td>${l.fabricante}</td>
                                    <td>${l.preco}</td>
                                    <c:choose>
                                        <c:when test="${l.status == 1}">
                                            Ativado
                                        </c:when>
                                        <c:otherwise>
                                            Desativado
                                        </c:otherwise>
                                    </c:choose>
                                    </td>
                                    <td>
                                        <script type="text/javascript">
                                            function confirmDesativar(id,nome) {
                                                if (confirm("Deseja desativar a Lente " +  
                                                    nome + "?")){
                                                    location.href="gerenciarLente?acao=desativar&idLente="+id;
                                                         
                                                }
                                                
                                            }
                                            
                                            function confirmAtivar(id,nome) {

                                                if (confirm("Deseja ativar a Lente " +  
                                                    nome + "?")){
                                                    location.href="gerenciarLente?acao=ativar&idLente="+id;
                                                         
                                                }
                                            }
                                        </script>
                                        <a href="gerenciarLente?acao=alterar&idLente=${l.idLente}" 
                                       class="btn btn-warning btn-sm" role="button">Alterar</a>
                                       <c:choose>
                                           <c:when test="${l.status == 1}">
                                               <a class="btn btn-sm btn-danger " style="text-decoration: none"
                                                  onclick="confirmDesativar('${l.idCliente}','${l.nome}')">Desativar</a>
                                           </c:when>
                                           <c:otherwise>
                                               <a class="btn btn-success btn-sm" 
                                                  onclick="confirmAtivar('${l.idCliente}','${l.nome}')">Ativar</a>
                                           </c:otherwise>
                                       </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>

            </div><!-- Fim da div conteudo -->
        </div><!-- Fim da div container -->

    </body>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script src="bootstrap/bootstrap.min.js"></script>

</html>

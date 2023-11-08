
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

                <div id="conteudo" class="bg-background">
                    <div class="container">
                        <h3 class="text-center">Listagem de Laboratorios</h3>
                        <a href="cadastrarLaboratorio.jsp" class="btn-sm btn-primary " 
                           style="text-decoration: none">Cadastrar Laboratorios</a>
                        <table class="table table-hover table-striped table-bordered mt-3">
                            <thead>
                                <tr class="thead-dark">
                                    <th scope="col">Código</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Endereco</th>
                                    <th scope="col">Telefone</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Ação</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${laboratorios}" var="lab">
                                <tr>
                                    <td>${lab.idLaboratorio}</td>
                                    <td>${lab.nome}</td>
                                    <td>${lab.endereco}</td>
                                    <td>${lab.telefone}</td>
                                    <td>${lab.email}</td>
                                    <td>
                                    <c:choose>
                                        <c:when test="${lab.status == 1}">
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
                                                if (confirm("Deseja desativar o Laboratorio " +  
                                                    nome + "?")){
                                                    location.href="gerenciarLaboratorio?acao=desativar&idLaboratorio="+id;
                                                         
                                                }
                                                
                                            }
                                            
                                            function confirmAtivar(id,nome) {

                                                if (confirm("Deseja ativar a Lente " +  
                                                    nome + "?")){
                                                    location.href="gerenciarLaboratorio?acao=ativar&idLaboratorio="+id;
                                                         
                                                }
                                            }
                                        </script>
                                        <a href="gerenciarLaboratorio?acao=alterar&idLaboratorio=${lab.idLaboratorio}" 
                                       class="btn btn-warning btn-sm" role="button">Alterar</a>
                                       <c:choose>
                                           <c:when test="${lab.status == 1}">
                                               <a class="btn btn-sm btn-danger " style="text-decoration: none"
                                                  onclick="confirmDesativar('${lab.idLaboratorio}','${lab.nome}')">Desativar</a>
                                           </c:when>
                                           <c:otherwise>
                                               <a class="btn btn-success btn-sm" 
                                                  onclick="confirmAtivar('${lab.idLaboratorio}','${lab.nome}')">Ativar</a>
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

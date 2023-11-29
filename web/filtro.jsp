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

                    <div class="form-group row mt-5 offset-md-2">
                        <label class="col-md-3">Informe o ano da OS</label>
                        <div class="col-md-2">
                            <input type="number" name="filtro" id="ano" class="form-control">
                        </div>


                    </div>
                    <div class="form-group row mt-5 offset-md-2">
                        <label class="col-md-3">Informe o mês dos Registros</label>
                        <div class="col-md-5">
                            <select name="meses" id="meses" class="form-control-sm">
                                <option value="1">Janeiro</option>
                                <option value="2">Fevereito</option>
                                <option value="3">Março</option>
                                <option value="4">Abril</option>
                                <option value="5">Maio</option>
                                <option value="6">Junho</option>
                                <option value="7">Julho</option>
                                <option value="8">Agosto</option>
                                <option value="9">Setembro</option>
                                <option value="10">Outubro</option>
                                <option value="11">Novembro</option>
                                <option value="12">Dezembro</option>
                            </select>

                        </div>

                    </div>
                    <div class="d-md-flex justify-content-md-center mt-5 mr-5">
                        <button class="btn btn-info" id="filtro">Filtrar</button>
                        
                        <a href="opcoes.jsp" 
                           class="btn btn-warning ml-4" role="button">Voltar
                        </a>
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

            var ano = document.querySelector("#ano");

            // Esse trecho serve para o input ano ficar com o ano atual
            const dataAtual = new Date();
            const anoAtual = dataAtual.getFullYear();
            ano.value = anoAtual;
            // Esse trecho serve para o filtro do mes fique por padrao no
            // mes atual

            var meses = document.getElementById("meses");
            const mesAtual = dataAtual.getMonth();

            meses.options = mesAtual;
            meses.childNodes[mesAtual * 2 + 1].selected = true;


            filtro.addEventListener("click", function () {

                /*O sessionStorage ele armazenará no navegador
                 * o ano e o mes para ser usado no butão de voltar
                 * para o pagina cadastrarOS.jsp
                 */
                sessionStorage.setItem("ano", ano.value);
                sessionStorage.setItem("mes", meses.value);
                location.href = "gerenciarOrdemServico?acao=listar&ano=" + ano.value +
                        "&mes=" + meses.value;
            });


        </script>
    </body>


</html>
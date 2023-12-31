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
        <script>
            window.onload = function () {

                var arrayOS = [];
                var os1 = document.querySelectorAll("#os1");
                os1.forEach(function (elemento) {

                    arrayOS.push(parseInt(elemento.value));
                });
                console.log(arrayOS);
                var arrayOS2 = [];
                var os2 = document.querySelectorAll("#os2");
                os2.forEach(function (elementos) {

                    arrayOS2.push(parseInt(elementos.value));
                });
                console.log(arrayOS2);
                var ano = sessionStorage.getItem("ano");
                var ano2 = sessionStorage.getItem("ano2");
                var chart = new CanvasJS.Chart("chartContainer", {
                    exportEnabled: true,
                    animationEnabled: true,
                    title: {
                        text: "Quantidades de ordens de serviços no mês"
                    },
                    subtitles: [{
                            text: "Clica na legenda para mais informações"
                        }],
                    axisX: {
                        title: "Meses"
                    },
                    axisY: {

                        title: ano,
                        titleFontColor: "#4F81BC",
                        lineColor: "#4F81BC",
                        labelFontColor: "#4F81BC",
                        tickColor: "#4F81BC",
                        includeZero: true
                    },
                    axisY2: {

                        title: ano2,
                        titleFontColor: "#C0504E",
                        lineColor: "#C0504E",
                        labelFontColor: "#C0504E",
                        tickColor: "#C0504E",
                        includeZero: true
                    },
                    toolTip: {
                        shared: true
                    },
                    legend: {
                        cursor: "pointer",
                        itemclick: toggleDataSeries
                    },
                    data: [{
                            type: "column",
                            name: ano,
                            showInLegend: true,
                            yValueFormatString: "#",
                            dataPoints: [
                                {label: "Jan", y: arrayOS[0]},
                                {label: "Fev", y: arrayOS[1]},
                                {label: "Mar", y: arrayOS[2]},
                                {label: "Abr", y: arrayOS[3]},
                                {label: "Maio", y: arrayOS[4]},
                                {label: "Jun", y: arrayOS[5]},
                                {label: "Jul", y: arrayOS[6]},
                                {label: "Ago", y: arrayOS[7]},
                                {label: "Set", y: arrayOS[8]},
                                {label: "Out", y: arrayOS[9]},
                                {label: "Nov", y: arrayOS[10]},
                                {label: "Dez", y: arrayOS[11]}
                            ]
                        },
                        {
                            type: "column",
                            name: ano2,
                            axisYType: "secondary",
                            showInLegend: true,
                            yValueFormatString: "#",
                            dataPoints: [
                                {label: "Jan", y: arrayOS2[0]},
                                {label: "Fev", y: arrayOS2[1]},
                                {label: "Mar", y: arrayOS2[2]},
                                {label: "Abr", y: arrayOS2[3]},
                                {label: "Maio", y: arrayOS2[4]},
                                {label: "Jun", y: arrayOS2[5]},
                                {label: "Jul", y: arrayOS2[6]},
                                {label: "Ago", y: arrayOS2[7]},
                                {label: "Set", y: arrayOS2[8]},
                                {label: "Out", y: arrayOS2[9]},
                                {label: "Nov", y: arrayOS2[10]},
                                {label: "Dez", y: arrayOS2[11]}
                            ]
                        }]
                });
                chart.render();
                function toggleDataSeries(e) {
                    if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                        e.dataSeries.visible = false;
                    } else {
                        e.dataSeries.visible = true;
                    }
                    e.chart.render();
                }

            }
        </script>
    </head>
    <body>
        <main>
            <c:forEach items="${os1}" var="os1">
                <input type="hidden" value="${os1}" id="os1">
            </c:forEach>
            <br>
            <c:forEach items="${os2}" var="os2">
                <input type="hidden" value="${os2}" id="os2">
            </c:forEach>
            <div id="chartContainer" style="height: 300px; width: 100%;"></div>
            <!-- JQuery -->
            <script src="js/jquery-3.6.0.min.js"></script>
            <script src="js/canvasjs.min.js"></script>
            <script src="js/jquery.canvasjs.min.js"></script>
            <a class="btn btn-warning mt-5 ml-5" href="opcoes.jsp">Voltar</a>
        </main>
    </body>
</html>

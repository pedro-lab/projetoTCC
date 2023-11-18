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
        <script>
            window.onload = function () {

            var options = {
            animationEnabled: true,
                    theme: "light3",
                    title: {
                    text: "Quantidade de OS"
                    },
                    axisX: {
                    valueFormatString: "DD MMM"
                    },
                    axisY: {
                    title: "Number of Sales",
                            suffix: "",
                            minimum: 30
                    },
                    toolTip: {
                    shared: true
                    },
                    legend: {
                    cursor: "pointer",
                            verticalAlign: "bottom",
                            horizontalAlign: "left",
                            dockInsidePlotArea: true,
                            itemclick: toogleDataSeries
                    },
                    data: [{
                    type: "line",
                            showInLegend: true,
                            name: "Projected Sales",
                            markerType: "square",
                            xValueFormatString: "DD MMM, YYYY",
                            color: "#F08080",
                            yValueFormatString: "#,##",
                            dataPoints: [
            <c:forEach items="${os1}" var="os1">
                            {x: new Date(2017, 10, 1), y: ${os1}}
            </c:forEach>
                            ]
                    },
                    {
                    type: "line",
                            showInLegend: true,
                            name: "Actual Sales",
                            lineDashType: "dash",
                            yValueFormatString: "#,##0K",
                            dataPoints: [
                            {x: new Date(2017, 10, 1), y: 60},
                            {x: new Date(2017, 10, 2), y: 57},
                            {x: new Date(2017, 10, 3), y: 51},
                            {x: new Date(2017, 10, 4), y: 56},
                            {x: new Date(2017, 10, 5), y: 54},
                            {x: new Date(2017, 10, 6), y: 55},
                            {x: new Date(2017, 10, 7), y: 54},
                            {x: new Date(2017, 10, 8), y: 69},
                            {x: new Date(2017, 10, 9), y: 65},
                            {x: new Date(2017, 10, 10), y: 66},
                            {x: new Date(2017, 10, 11), y: 63},
                            {x: new Date(2017, 10, 12), y: 67},
                            {x: new Date(2017, 10, 13), y: 66},
                            {x: new Date(2017, 10, 14), y: 56},
                            {x: new Date(2017, 10, 15), y: 64}
                            ]
                    }]
            };
            $("#chartContainer").CanvasJSChart(options);
            function toogleDataSeries(e) {
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
            <div id="chartContainer" style="height: 300px; width: 100%;"></div>
            <!-- JQuery -->
            <script src="js/jquery-3.6.0.min.js"></script>
            <script src="js/canvasjs.min.js"></script>
            <script src="js/jquery.canvasjs.min.js"></script>
            <a class="btn btn-warning mt-5 ml-5" href="opcoes.jsp">Voltar</a>
        </main>
    </body>
</html>

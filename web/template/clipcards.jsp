<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Usuario, controller.GerenciarLogin" %>

<%
    Usuario ulogado = GerenciarLogin.verificarAcesso(request, response);
    request.setAttribute("ulogado", ulogado);


%>

<c:if test="${ulogado != null && ulogado.perfil != null}">
    <c:forEach var="menu" items="${ulogado.perfil.menus}">
        <c:if test="${menu.exibir == 1 && menu.status == 1}">
            <div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex;">
                <img src="/imagens/clipcard${menu.nome}.png" class="card-img-top" alt="${menu.nome}">
                <div class="card-body">
                    <h5 class="card-title">Consultar ${menu.nome}</h5>
                    <p class="card-text">Consulte e registre ${menu.nome} na nossa base de dados</p>
                    <a href="${menu.link}" class="btn btn-primary">Ver ${menu.nome}</a>
                </div>
            </div>
        </c:if>
    </c:forEach>

</c:if>
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
            <div class="card mt-4 ml-lg-2" id="card" style="width: 16rem;height: 359px;display: inline-flex;">
                <img src="<%= request.getContextPath()%>/imagens/clipcard${menu.nome}.png" class="card-img-top" alt="${menu.nome}"
                     style="height: 190.7px">
                <div class="card-body">
                    <h5 class="card-title">Ver ${menu.nome}</h5>
                    <p class="card-text">Consulte ${menu.nome} na nossa base de dados</p>
                    <c:choose>
                        <c:when test="${menu.nome == 'Ordem de Servico'}">
                            <a href="filtro.jsp" class="btn btn-primary">Ver ${menu.nome}</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${menu.link}" class="btn btn-primary">Ver ${menu.nome}</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </c:if>
    </c:forEach>
</c:if>
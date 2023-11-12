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
                <img src="./imagens/clipcardRegistrar.jpg" class="card-img-top" alt="${menu.nome}">
                <div class="card-body">
                    <h5 class="card-title">Consultar ${menu.nome}</h5>
                    <p class="card-text">Consulte e registre ${menu.nome} na nossa base de dados</p>
                    <a href="${menu.link}" class="btn btn-primary">Ver ${menu.nome}</a>
                </div>
            </div>
                ${ulogado.perfil.menus}
        </c:if>
    </c:forEach>

</c:if>
<div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex">
    <img src="imagens/clipcardLaboratorio.jpg" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title">Consultar lentes</h5>
        <p class="card-text">Consulte e registre lentes na nossa base de dados</p>
        <a href="gerenciarLente?acao=listar" class="btn btn-primary">Ver lentes</a>
    </div>
</div>
<div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex">
    <img src="imagens/clipcardLab.png" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title">Consultar Laboratórios</h5>
        <p class="card-text">Consulte e registre laboratórios na nossa base de dados</p>
        <a href="gerenciarLaboratorio?acao=listar" class="btn btn-primary">Ver laboratórios</a>
    </div>
</div>
<div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex">
    <img src="imagens/clipcardRetirada.png" class="card-img-top" alt="...">
    <div class="card-body">
        <h5 class="card-title">Consultar Clientes</h5>
        <p class="card-text">Consulte e registre clientes na nossa base de dados</p>
        <a href="gerenciarCliente?acao=listar" class="btn btn-primary">Ver Clientes</a>
    </div>
</div>
<div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex;">
    <img src="./imagens/clipcardMenu.png" class="card-img-top" alt="..." style="height: 190.7px">
    <div class="card-body">
        <h5 class="card-title">Consultar Menus</h5>
        <p class="card-text">Consulte e registre menus na nossa base de dados</p>
        <a href="gerenciarMenu?acao=listar" class="btn btn-primary">Ver Menus</a>
    </div>
</div>
<div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex;">
    <img src="./imagens/clipcardPerfil.png" class="card-img-top" alt="..." style="height: 190.7px">
    <div class="card-body">
        <h5 class="card-title">Consultar Perfil</h5>
        <p class="card-text">Consulte e registre perfis na nossa base de dados</p>
        <a href="gerenciarPerfil?acao=listar" class="btn btn-primary">Ver Perfil</a>
    </div>
</div>
<div class="card mt-4 ml-lg-5" id="card" style="width: 18rem;display: inline-flex;">
    <img src="./imagens/Usuario.jpg" class="card-img-top" alt="..." style="height: 190.7px">
    <div class="card-body">
        <h5 class="card-title">Consultar Usuário</h5>
        <p class="card-text">Consulte e registre usuários na nossa base de dados</p>
        <a href="gerenciarUsuario?acao=listar" class="btn btn-primary">Ver Usuário</a>
    </div>
</div>
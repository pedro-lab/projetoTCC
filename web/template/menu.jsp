<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="model.Usuario, controller.GerenciarLogin" %>

<header>

    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="d-flex">
            <a class="navbar-brand icone" href="index.jsp" style="color: inherit;line-height: normal;vertical-align: central">
                <img src="./imagens/logo.png" alt="Logo" width="50" height="50" 
                     class="d-inline-block align-text-top logo">
                <div class="d-inline-block mt-2" style="vertical-align: text-top;">Ótica Nova</div>
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <c:if test="${ulogado != null && ulogado.perfil != null}">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-lg-auto">
                <li class="nav-item">
                    <a class="btn btn-outline-success btn-nav mr-xl-5" href="opcoes.jsp"
                       >Menu</a>
                </li>

            </ul>
        </div>
        </c:if>
        <a href="gerenciarLogin?">
            <div class="circulo" id="circulo">
                <p class="nickname" id="nomeLogin">${ulogado.login}</p>
            </div>
        </a>
    </nav>
</header>
            
<script>
    
    var p = document.getElementById("nomeLogin");
    var primeiraLetra = p.innerHTML.charAt(0).toUpperCase();
    p.innerHTML = primeiraLetra;
    
    if (primeiraLetra == "") {
    document.getElementById("circulo").style.display = 'none';
}

</script>
<header>

    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="d-flex">
            <a class="navbar-brand icone" href="index.jsp" style="color: inherit;">
                <img src="./imagens/logo1.png" alt="Logo" width="36" height="30" 
                     class="d-inline-block align-text-top logo">
                Registrex
            </a>
        </div>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-lg-auto">
                <li class="nav-item">
                    <a class="btn btn-outline-success btn-nav mr-xl-5" href="opcoes.jsp"
                       >Menu</a>
                </li>

            </ul>
        </div>
        <a href="gerenciarLogin?">
            <div class="circulo">
                <p class="nickname" id="nomeLogin">${ulogado.login}</p>
            </div>
        </a>
    </nav>
</header>
            
<script>
    var p = document.getElementById("nomeLogin");
    var primeiraLetra = p.innerHTML.charAt(0).toUpperCase();
    p.innerHTML = primeiraLetra;
</script>
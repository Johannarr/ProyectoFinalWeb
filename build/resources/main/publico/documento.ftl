<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="../assets/css/main.css"/>
    <noscript>
        <link rel="stylesheet" href="../assets/css/noscript.css"/>
    </noscript>
    <title>Log In</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.10/styles/default.min.css">


</head>

<body class="is-preload">

<div id="wrapper">
    <!-- Header -->
    <header id="header">
        <#--    <a href="/" class="logo">Massively</a>-->
    </header>

    <!-- Nav -->
    <nav id="nav" class="login">
        <ul class="links">
            <li><a href="/">Inicio</a></li>
            <li><a href="/top">Top pastes</a></li>

            <#if usuario??>
                <li class=""><a href="/historial">Historial</a></li>
                <#if usuario.administrador>
                    <li class=""><a href="/usuarios">Usuarios</a></li>
                </#if>
            <#else>
                <li class="login"><a href="/login">Log in</a></li>
                <li class=""><a href="/registro">Registro</a></li>

            </#if>
        </ul>
    </nav>

    <div id="main">
        <section class="post">

            <h2>${documento.nombre}</h2>
            <div class="row gtr-uniform">
                <div class="col-6 col-12-small">
                    <div class="actions stacked">
                        <input type="text" id="direccion" readonly
                               value="http://app.itdom.me/documento/${documento.direccion}">
                    </div>
                </div>
                <div class="col-6 col-12-small">
                    <ul class="actions stacked">
                        <li>
                            <button onclick="myFunction()" class="button primary small fit">Copiar</button>
                        </li>
                    </ul>
                </div>
            </div>

            <h5>Visitas: ${documento.visitas}</h5>
            <h5>Fecha Creaci&oacute;n: <label id="fecha"></label></h5>
            <pre>
               <code class="${documento.lenguaje.nombre}">
                    ${documento.contenido}
               </code>
           </pre>

        </section>
    </div>
</div>


<!-- Scripts -->
<script src="../assets/js/jquery.min.js"></script>
<script src="../assets/js/jquery.scrollex.min.js"></script>
<script src="../assets/js/jquery.scrolly.min.js"></script>
<script src="../assets/js/browser.min.js"></script>
<script src="../assets/js/breakpoints.min.js"></script>
<script src="../assets/js/util.js"></script>
<script src="../assets/js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.10/highlight.min.js"></script>

<script>
    $(document).ready(function () {

        hljs.initHighlighting();

        console.log("fecha ${documento.fechaCreacion}");
        $("#fecha").html(timeSince(new Date("${documento.fechaCreacion}")));


    });


    function myFunction() {
        var copyText = document.getElementById("direccion");
        copyText.select();
        copyText.setSelectionRange(0, 99999);
        document.execCommand("copy");
        alert("Copied the text: " + copyText.value);
    }

    function timeSince(date) {

        var seconds = Math.floor((new Date() - date) / 1000);

        var interval = Math.floor(seconds / 31536000);

        if (interval > 1) {
            return interval + " años";
        }
        interval = Math.floor(seconds / 2592000);
        if (interval > 1) {
            return interval + " meses";
        }
        interval = Math.floor(seconds / 86400);
        if (interval > 1) {
            return interval + " días";
        }
        interval = Math.floor(seconds / 3600);
        if (interval > 1) {
            return interval + " horas";
        }
        interval = Math.floor(seconds / 60);
        if (interval > 1) {
            return interval + " minutos";
        }
        return Math.floor(seconds) + " segundos";
    }
</script>


</body>

</html>
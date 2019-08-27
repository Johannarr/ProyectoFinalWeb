<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
    <link rel="stylesheet" href="assets/css/main.css"/>
    <noscript>
        <link rel="stylesheet" href="assets/css/noscript.css"/>
    </noscript>
    <title>Inicio</title>

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
            <li class=""><a href="/">Inicio</a></li>
            <li class="active"><a href="/top">Top pastes</a></li>
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


            <h3>Top Documentos</h3>
            <div class="table-wrapper">
                <table>
                    <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Lenguaje</th>
                        <th>Visitas</th>
                        <th>Link</th>
                    </tr>
                    </thead>
                    <tbody>

                    <#list documentos as documento>
                        <tr>
                            <td>${documento.nombre}</td>
                            <td>${documento.lenguaje.nombre}</td>
                            <td>${documento.visitas}</td>
                            <td><a href="http://localhost:4567/documento/${documento.direccion}">Link</a></td>
                        </tr>
                    </#list>
                    </tbody>

                </table>
            </div>

        </section>
    </div>
</div>


<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.scrollex.min.js"></script>
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/browser.min.js"></script>
<script src="assets/js/breakpoints.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.15.10/highlight.min.js"></script>

<script>
    $(document).ready(function () {


        console.log("lenguajes: ", hljs.listLanguages());

    });


</script>

</body>

</html>
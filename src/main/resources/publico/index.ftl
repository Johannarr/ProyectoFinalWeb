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
            <li class="active"><a href="/">Inicio</a></li>
            <li><a href="/top">Top pastes</a></li>

            <#if usuario??>
                <li class=""><a href="/historial">Historial</a></li>
                <li class=""><a href="/salir">Salir</a></li>
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

            <h2>Nuevo documento</h2>
            <form action="/crearDocumento" method="post">
                <div class="row gtr-uniform">

                    <div class="col-12">
                        <input type="text" name="nombre" id="nombre" value="" placeholder="Nombre" required/>
                    </div>

                    <!-- Break -->
                    <div class="col-12">
                            <textarea name="contenido" id="demo-message" placeholder="Codigo para formatear ..."
                                      rows="6" required></textarea>
                    </div>


                    <div class="col-6 col-12-xsmall">
                        <select name="lenguaje" id="demo-category" required>
                            <option value="">- Lenguaje -</option>
                            <#list lenguajes as l>
                                <option value="${l.id}">${l.nombre?capitalize}</option>
                            </#list>

                        </select>
                    </div>

                    <div class="col-6 col-12-xsmall">
                        <select name="tiempoExpiracion" id="demo" required>
                            <option value="">- Tiempo expiraci&oacute;n -</option>
                            <#list tiempoExpiracion as t>
                                <option value="${t.id}">${t.texto}</option>

                            </#list>
                        </select>
                    </div>

                    <div class="col-6 col-12-xsmall">
                        <select name="exposicion" id="demo" required>
                            <option value="">- Tipo exposici&oacute;n -</option>
                            <#list tipoExposicion as t>
                                <option value="${t}">${t}</option>

                            </#list>
                        </select>
                    </div>


                    <div class="col-12">

                        <ul class="actions">
                            <li><input type="submit" value="Crear" class="primary"></li>
                            <li><input type="reset" value="Cancelar"></li>
                        </ul>
                    </div>
                </div>
            </form>


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
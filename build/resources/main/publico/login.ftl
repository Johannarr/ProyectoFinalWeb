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
    <title>Log In</title>


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
            <li class=""><a href="/top">Top pastes</a></li>

            <li class="active login"><a href="/login">Log in</a></li>
            <li class=""><a href="/registro">Registro</a></li>

        </ul>
    </nav>

    <div id="main">
        <section class="post">

            <h2>Log In</h2>

            <form method="post" action="/validarLogIn">
                <div class="row gtr-uniform">
                    <div class="col-6 col-12-xsmall">
                        <input type="text" name="usuario" id="demo-name" value="" placeholder="Usuario"/>
                    </div>
                    <div class="col-6 col-12-xsmall">
                        <input type="password" name="password" id="demo-email" value="" placeholder="Contrase&ncaron;a"/>
                    </div>

                    <!-- Break -->
                    <div class="col-12">
                        <ul class="actions">
                            <li><input type="submit" value="Aceptar" class="primary"/></li>
                            <li><input type="reset" value="Cancelar"/></li>
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


</body>

</html>
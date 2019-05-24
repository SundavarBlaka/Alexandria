<%@ page import="it.alexandria.hibernate.model.Categoria"%>
<html lang="it">

<head>
    <title>AleXandria-Login</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/AleXa.bmp">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles/login_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">


</head>

<body>

    <div class="super_container">
        <div class="container">
            <div class="register_container" id="register">
                <h4><b>Registrati</b></h4>
                <form class="registration_form" action="loginsubmit" method="get">
                	<input type="hidden" name="type" value="registra">
                    Username:<br>
                    <input type="text" name="username" class="reg_username" placeholder="Username"><br> Nome: <br>
                    <input type="text" name="name" class="reg_name" placeholder="Nome"><br> Cognome: <br>
                    <input type="text" name="surname" class="reg_surname" placeholder="Cognome"> <br> Data di Nascit&agrave;:<br>
                    <input type="date" name="date" class="reg_date" placeholder="Data di Nascit&agrave;"><br> Email: <br>
                    <input type="text" name="email" class="reg_email" placeholder="Email"><br> Telefono: <br>
                    <input type="text" name="telefono" class="reg_telefono" placeholder="Numero di Telefono"><br> Indirizzo: <br>
                    <input type="text" name="indirizzo" class="reg_telefono" placeholder="Indirizzo"><br> Interessi: <br>
                    <!-- INTERESSI QUI -->
                    <%
                    for(Categoria c: Categoria.values()){
                    	out.println("<input type=\"checkbox\" name=\""+c.toString()+"\" value=\""+c.toString()+"\"> "+c.toString()+"<br>");
                    }
                    %>
                    <input type="password" name="password" class="reg_password" placeholder="Password"><br>Conferma password: <br>
                    <input type="password" class="reg_password_confirm" placeholder="Conferma Password"><br> <br>
                    <input type="submit" class="reg_btn" value="Registrati"><br> <br> Hai gi&agrave; un account?
                    <a class="login_link" href="login.html">Accedi</a>
                </form>
            </div>
        </div>
    </div>
    <script src="js/register.js "></script>
    <script src="js/jquery-3.2.1.min.js "></script>
    <script src="styles/bootstrap4/popper.js "></script>
    <script src="styles/bootstrap4/bootstrap.min.js "></script>


</body>

</html>
<%@ page import="it.alexandria.hibernate.model.Categoria"%>
<html lang="Italiano">

<head>
    <title>AleXandria-Registrazione</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="images/AleXandria_Logo.png">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="styles/login_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">
</head>

<body>

    <div class="super_container">
        <div class="container">
            <div class="register_container" id="register">
                <h4><b>Registrati</b></h4>
                <form class="registration_form" action="">
                    Username:<br>
                    <input type="text" class="reg_username" placeholder="Username"><br> Nome: <br>
                    <input type="text" class="reg_name" placeholder="Nome"><br> Cognome: <br>
                    <input type="text" class="reg_surname" placeholder="Cognome"> <br> Data di Nascit&agrave;:<br>
                    <input type="date" class="reg_date" placeholder="Data di Nascit&agrave;"><br> Email: <br>
                    <input type="email" class="reg_email" placeholder="Email"><br> Telefono: <br>
                    <input type="tel" class="reg_telefono" placeholder="Numero di Telefono"><br>Indirizzo: <br>
                    <input type="text" class="reg_address" placeholder="Indirizzo"><br>
                    <div id="interests" class="interests" tabindex="100" >
                        <span class="anchor" onclick="showInterests()">Interessi</span>
                        <ul class="items" id="items">
                          <%
                            for(Categoria c: Categoria.values()){
                              out.println("<li><input type=\"checkbox\" name=\""+c.toString()+"\" value=\""+c.toString()+"\"> "+c.toString()+"<li>");
                            }
                            %>
                        </ul>
                    </div>
                    <br> Crea una password: <br>
                    <input type="password" class="reg_password" placeholder="Password"><br>Conferma password: <br>
                    <input type="password" class="reg_password_confirm" placeholder="Conferma Password"><br> <br>
                    <input type="submit" class="reg_btn" value="Registrati"><br> <br> Hai gi&agrave; un account?
                    <a class="login_link" href="login.html">Accedi</a>
                </form>
            </div>
        </div>
    </div>
    <script src="js/login.js "></script>
    <script src="js/jquery-3.2.1.min.js "></script>
    <script src="styles/bootstrap4/popper.js "></script>
    <script src="styles/bootstrap4/bootstrap.min.js "></script>

</body>

</html>

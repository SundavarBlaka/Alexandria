<%@ page import="it.alexandria.hibernate.model.Profilo"%>
<%@ page import="it.alexandria.hibernate.model.Risorsa"%>
<%@ page import="it.alexandria.hibernate.model.Categoria"%>
<%@ page import="it.alexandria.hibernate.model.Carrello"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%
	HttpSession sessione=request.getSession();
	List<Risorsa> libreria = (List<Risorsa>) sessione.getAttribute("libreria");
	Carrello carrello=(Carrello) request.getSession().getAttribute("carrello");
%>
<html lang="it">

<head>
    <title>AleXandria-Library</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" href="images/AleXandria_Logo.png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="styles/library_styles.css">
    <link rel="stylesheet" type="text/css" href="styles/responsive.css">

</head>

<body>

    <div class="super_container" style="overflow:scroll;">

        <!-- Header -->

        <header class="header trans_300">

            <!-- Top Navigation -->

            <div class="top_nav">
                <div class="container">
                    <div class="row">
                        <div class="col-md-6 text-right">
                            <div class="top_nav_right">
                                <ul class="top_nav_menu">

                                    <!-- Language -->

                                    <li class="language">
                                        <a href="#">
                                            Italiano
                                            <i class="fa fa-angle-down"></i>
                                        </a>
                                        <ul class="language_selection">
                                            <li><a href="#">English</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Main Navigation -->

            <div class="main_nav_container">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12 text-right">
                            <nav class="navbar">
                                <ul class="navbar_menu">
                                    <li><a href="search">home</a></li>
                                    <li><a href="library">libreria</a></li>
                                </ul>
                                <ul class="navbar_user">
                                    <li><a href="messages.html"><i class="fa fa-envelope" aria-hidden="true"></i></a>
                                    </li>
                                    <li><a href="profile.jsp"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                                    <li class="checkout">
                                        <a href="cart.jsp">
                                            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                            <span id="checkout_items" class="checkout_items"><%=carrello.getRisorseSelezionate().size()%></span>
                                        </a>
                                    </li>
                                </ul>
                                <div class="hamburger_container">
                                    <i class="fa fa-bars" aria-hidden="true"></i>
                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>

        </header>

        <div class="empty"></div>

        <div class="fs_menu_overlay"></div>

        <!-- Hamburger Menu -->

        <div class="hamburger_menu">
            <div class="hamburger_close"><i class="fa fa-times" aria-hidden="true"></i></div>
            <div class="hamburger_menu_content text-right">
                <ul class="menu_top_nav">
                    <li class="menu_item has-children">
                        <a href="#">
                            Italiano
                            <i class="fa fa-angle-down"></i>
                        </a>
                        <ul class="menu_selection">
                            <li><a href="#">English</a></li>
                        </ul>
                    </li>
                    <li class="menu_item"><a href="index.html">home</a></li>
                    <li class="menu_item"><a href="library.html">libreria</a></li>
                </ul>
            </div>
        </div>



        <!-- Library -->

        <div class="library_container">
        <% for(Risorsa r : libreria) { %>
            <div class="product_item">
                <img src=<%=r.getUrl()%> alt="No Image Found!">
                <div class="product_op">
                <form method="get" action="library">
                	<input type="hidden" value="rimuovi" name="type">
                	<input type="hidden" value=<%=r.getId()%> name="id">
                    <input type="submit" class="delete_product" class="fa fa-trash">
                </form>
                </div>
                <div class="product_data">
                    <h4><%=r.getTitolo()%></h4>
                    <h5><%=r.getCategoria().toString()%></h5>
                    <h6><%=r.getAutori()%></h6>
                    <%
                    SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd");
					Calendar rapr=Calendar.getInstance();
					rapr.setTime(r.getData());
					String formatted=format.format(rapr.getTime());
                    %>
                    <h6><%=formatted%></h6>
                    <p><%=r.getDescrizione()%></p>
                    &euro;<b><%=r.getPrezzo()%></b>
                </div>
            </div>
        <% } %>
        </div>

        <div class="buttons">
            <button type="button" class="add_button" onclick="showInsertProduct()"><b>Inserisci una nuova
                    risorsa</b><br><i class="fa fa-plus"></i></button>
            <button type="button" class="edit_button" onclick="showModifyProduct()"><b>Modifica Risorse</b><br><i
                    class="fa fa-edit"></i></button>
        </div>
        <div id="modifyproduct" class="modify_product">
            <form class="modify_product_form" action="resource" method="get">
            	<input type="hidden" name="type" value="modifica">
                Risorsa da Modificare: <br>
                <select name="id" class="title_list">
                <% for(Risorsa r : libreria) { %>
                    <option value=<%=r.getId()%>><%=r.getTitolo()%></option>
                <% } %>
                </select><br> Prezzo: <br>
                <input type="number" name="price" class="modify_product_price" placeholder="&euro;"><br> Descrizione:
                <br>
                <textarea type="text" name="description" rows="2" cols="30"
                    class="modify_product_description"></textarea><br> <br>
                <input class="modify_product_button" type="submit" value="Modifica">
            </form>
        </div>
        <div id="insertproduct" class="insert_product">
            <form class="insert_product_form" action="library" method="get">
            	<input type="hidden" name="type" value="inserisci">
                <input type="file" name="image" placeholder="Carica Foto" value="Carica Foto"
                    class="insert_product_image" id="img_up" accept="image/*"><br> Titolo: <br>
                <input type="text" name="title" class="insert_product_title" placeholder="Titolo"><br> Prezzo: <br>
                <input type="text" name="price" placeholder="&euro; Prezzo" class="insert_product_price"> <br>Autori/e:
                <br>
                <input type="text" name="authors" placeholder="Autori" class="insert_product_authors"><br>Anno
                Pubblicazione: <br>
                <input type="date" name="releaseDate" placeholder="Data Pubblicazione" class="insert_product_date"> <br>
                <select name="interest" class="interests">
                   <%
                      for(Categoria c: Categoria.values()){
                        out.println("<option value=\""+c.toString()+"\"/> "+c.toString()+"</option>");
                      }
                    %>
                </select> <br> Descrizione: <br>
                <textarea type="text" name="description" rows="3" cols="40"
                    class="insert_product_description"></textarea><br><br>
                <input class="insert_product_button" type="submit" value="Inserisci">
            </form>
        </div>
    </div>
    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-lg-6">
                    <div
                        class="footer_nav_container d-flex flex-sm-row flex-column align-items-center justify-content-lg-start justify-content-center text-center">
                        <ul class="footer_nav">
                            <li><a href="contact.html">Contattaci</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div
                        class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center">
                        <ul>
                            <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                            <li><a href="https://www.instagram.com/_.alex_andria._"><i class="fa fa-instagram"
                                        aria-hidden="true"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="footer_nav_container">
                        <div class="cr">©2019 All Rights Reserverd.</div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <script src="js/library.js"></script>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="styles/bootstrap4/popper.js"></script>
    <script src="styles/bootstrap4/bootstrap.min.js"></script>
    <script src="js/contact_custom.js"></script>

</body>

</html>
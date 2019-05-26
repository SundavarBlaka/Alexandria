<%@ page import="it.alexandria.hibernate.model.Carrello"%>
<%@ page import="it.alexandria.hibernate.model.Risorsa"%>
<%@ page import="java.util.*"%>
<%
	Carrello carrello=(Carrello) request.getSession().getAttribute("carrello");
%>
<html lang="it">

<head>
    <title>AleXandria-Carrello</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="icon" href="images/AleXandria_Logo.png">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
    <link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="styles/main_styles.css">
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
                                    <li><a href="messages.html"><i class="fa fa-envelope" aria-hidden="true"></i></a></li>
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

        <!--Cart Table-->

        <div class="row mb-5" style="overflow:scroll;">
            <div class="col-md-12" >
                <div class="site-blocks-table">
                
                    <table class="table table-bordered">
                            <tr>
                                <th class="product-thumbnail">Imagine</th>
                                <th class="product-name">Prodotto</th>
                                <th class="product-price">Prezzo</th>
                            </tr>
                        </table>
                        <% for(Risorsa r : carrello.getRisorseSelezionate()) { %>
                        
                        <table class="table table-bordered">
                            <tr>
                                <td class="product-thumbnail">
                                    <img src=<%=r.getUrl()%> alt="Image" class="img-fluid">
                                </td>
                                <td class="product-name">
                                    <span class="product_name"><b><%=r.getTitolo()%></b></span>
                                </td>
                                <td>&euro;<%=r.getPrezzo()%></td>

                            </tr>
                        </table>
                        <form method="get" action="profile">
                        	<input type="hidden" name="type" value="rimuovi">
                            <input type="hidden" name="id" value="<%=r.getId()%>">
                            <input type="submit" value="rimuovi">
                        </form>
                        <% } %>
                    <br>
                    <div class="buy_button">
                    <form action="profile" method="get">
                    	<input type="hidden" name="type" value="ordine">
                    	<input type="submit" value="Procedi all'ordine">
                    </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <footer class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="footer_nav_container d-flex flex-sm-row flex-column align-items-center justify-content-lg-start justify-content-center text-center">
                            <ul class="footer_nav">
                                <li><a href="contact.html">Contattaci</a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center">
                            <ul>
                                <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                                <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                                <li><a href="https://www.instagram.com/_.alex_andria._"><i class="fa fa-instagram" aria-hidden="true"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="footer_nav_container">
                            <div class="cr">©2019 All Rights Reserved.</div>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
    </div>

    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="styles/bootstrap4/popper.js"></script>
    <script src="styles/bootstrap4/bootstrap.min.js"></script>
    <script src="js/custom.js"></script>
</body>

</html>
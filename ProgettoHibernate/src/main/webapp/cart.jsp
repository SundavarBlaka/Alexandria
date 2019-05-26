<%@ page import="it.alexandria.hibernate.model.UCMapping"%>
<jsp:useBean id="cart" scope="session" class="it.alexandria.hibernate.model.Carrello" />
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

    <div class="super_container">

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
                            <div class="logo_container">
                                <div class="logo_image"><img src="images/AleXandria_Logo.png" alt="AleXandria"></div>
                                <div class="logo_name"><a href="index.html">AleX<span>andria</span></a></div>
                            </div>
                            <nav class="navbar">
                                <ul class="navbar_menu">
                                    <li><a href="index.html">home</a></li>
                                    <li><a href="library.html">libreria</a></li>
                                </ul>
                                <ul class="navbar_user">
                                    <li><a href="messages.html"><i class="fa fa-envelope" aria-hidden="true"></i></a></li>
                                    <li><a href="profile.html"><i class="fa fa-user" aria-hidden="true"></i></a></li>
                                    <li class="checkout">
                                        <a href="cart.html">
                                            <i class="fa fa-shopping-cart" aria-hidden="true"></i>
                                            <span id="checkout_items" class="checkout_items">2</span>
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

        <div class="row mb-5">
            <form class="col-md-12" method="post">
                <div class="site-blocks-table">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th class="product-thumbnail">Imagine</th>
                                <th class="product-name">Prodotto</th>
                                <th class="product-price">Prezzo</th>
                                <th class="product-remove">Rimuovi</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td class="product-thumbnail">
                                    <img src="images/AleXa.bmp" alt="Image" class="img-fluid">
                                </td>
                                <td class="product-name">
                                    <span class="product_name"><b>AleXandria</b></span>
                                </td>
                                <td>&euro;49.00</td>

                                <td>
                                    <a href="#" class="fa fa-trash"></a>
                                </td>
                            </tr>
                            <tr>
                                <td class="product-thumbnail">
                                    <img src="images/AleXa.bmp" alt="Image" class="img-fluid">
                                </td>
                                <td class="product-name">
                                    <span class="product_name"><b>AleXandria</b></span>
                                </td>
                                <td>&euro;49.00</td>
                                <td>
                                    <a href="#" class="fa fa-trash"></a>                                  
                                </td>
                            </tr>

                            <tr>
                                <td class="product-thumbnail">
                                    <img src="images/AleXa.bmp" alt="Image" class="img-fluid">
                                </td>
                                <td class="product-name">
                                    <span class="product_name"><b>AleXandria</b></span>
                                </td>
                                <td>&euro;49.00</td>

                                <td>
                                    <a href="#" class="fa fa-trash"></a> <br>
                                    
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <br>
                    <div class="buy_button"><a href="#">Procedi all'ordine </a>
                    </div>
                </div>
            </form>
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
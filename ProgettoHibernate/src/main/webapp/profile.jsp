<%@ page import="java.util.*"%>
<html lang="it">

<%
		HttpSession sessione=request.getSession();
		String username = (String) sessione.getAttribute("username");
		String nome = (String) sessione.getAttribute("nome");
		String cognome = (String) sessione.getAttribute("cognome");
		Date data = (Date) sessione.getAttribute("data");
		String email = (String) sessione.getAttribute("email");
		String numeroTel = (String) sessione.getAttribute("numeroTel");
		String indirizzo = (String) sessione.getAttribute("indirizzo");
%>
<head>
	<title>AleXandria-Profile</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
	<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/profile_styles.css">
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
								<div class="logo_image"><img src="images/AleXa.bmp" alt="AleXandria"></div>
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



		<!-- Profile -->

		<div class="profile_container">
			<div class="profile">
				<i class="fa fa-user" aria-hidden="true" style="font-size:50px;"></i>
					<h5 class="username"><%=username%></h5>
					<h6 class="name_surname"><%=nome+" "+cognome%></h6>
					<h6 class="date"><%=data.toString()%></h6>
					<h6 class="numeroTel"><%=numeroTel%></h6>
					<h6 class="address"><%=indirizzo%></h6>
					<h6 class="email"><%=email%></h6>
					Interessi:
					<ul class="interests">
						<li>Fantascienza</li>
						<li>Scienze</li>
						<li>Bla Bla</li>
						<li>Anime</li>
						<li>Cucina</li>
					</ul>


			</div>
			<div class="profile_modify">
				<b>Clicca per Modificare i dati Personali!</b><br>
				<button onclick="showModify()"><i class="fa fa-edit"></i></button>
				<div id="modify">
					<form class="modify_form" action="#" method="">
						Email: <br>
						<input type="email" name="email"><br>
						Tel: <br>
						<input type="tel" name="tel"><br>
						Indirizzo: <br>
						<textarea type="text" name="address" rows="2" cols="30"></textarea><br>
						<input id="modify_button" type="submit" value="Modifica">
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
					<div
						class="footer_nav_container d-flex flex-sm-row flex-column align-items-center justify-content-lg-start justify-content-center text-center">
						<ul class="footer_nav">
							<li><a href="contact.html">Contact us</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="footer_social d-flex flex-row align-items-center justify-content-lg-end justify-content-center">
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
	<script src="js/profile.js"></script>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="styles/bootstrap4/popper.js"></script>
	<script src="styles/bootstrap4/bootstrap.min.js"></script>
	<script src="js/contact_custom.js"></script>

</body>

</html>

<%@ page import="it.alexandria.hibernate.model.Profilo"%>
<%@ page import="it.alexandria.hibernate.model.Risorsa"%>
<%@ page import="it.alexandria.hibernate.model.Categoria"%>
<%@ page import="it.alexandria.hibernate.model.Commento"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%>
<%
	Risorsa risorsa=(Risorsa)request.getSession().getAttribute("risorsa");
%>
<html lang="Italiano">

<head>
	<title>AleXandria-Single  </title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="description" content="Single Product">
	<link rel="icon" href="images/AleXandria_Logo.png">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="styles/bootstrap4/bootstrap.min.css">
	<link href="plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="styles/single_styles.css">
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
							<nav class="navbar">
								<ul class="navbar_menu">
									<li><a href="index.jsp">home</a></li>
									<li><a href="library">libraria</a></li>
								</ul>
								<ul class="navbar_user">
									<li><a href="messages.html"><i class="fa fa-envelope" aria-hidden="true"></i></a>
									</li>
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
					<li class="menu_item"><a href="index.jsp">home</a></li>
					<li class="menu_item"><a href="library">libreria</a></li>
				</ul>
			</div>
		</div>




		<!-- Product -->

		<div class="container single_product_container">
			<div class="row">
				<div class="col-lg-7">
					<div class="single_product_">
						<div class="col-lg-9 image_col order-lg-2 order-1">
							<div class="single_product_image">
								<img src=<%=risorsa.getUrl()%> alt="No Image Found!">
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-5">

					<div class="product_details">
						<div class="product_details_title">
							<h2><%=risorsa.getTitolo()%></h2>
							<p><%=risorsa.getDescrizione()%></p>
						</div>
						<div class="seller">
							<span>
								<h5><%=risorsa.getProprietario().getNome()+" "+risorsa.getProprietario().getCognome()%></h5>
							</span>
							<button type="button" class="send_button"><i class="fa fa-envelope" aria-hidden="true"></i>
								Contatta il Venditore</a>
						</div>
					</div>
					<div class="product_price">&euro;<%=risorsa.getPrezzo()%></div>

					<div class="quantity d-flex flex-column flex-sm-row align-items-sm-center">
						<span>Quantit&agrave;:</span>
						<div class="quantity_selector">
							<span class="minus"><i class="fa fa-minus" aria-hidden="true"></i></span>
							<span id="quantity_value">1</span>
							<span class="plus"><i class="fa fa-plus" aria-hidden="true"></i></span>
						</div>
						<div class="red_button add_to_cart_button"><a href="#">Aggiungi al Carrello</a>
						</div> <br>
						
					</div>
					<br> <br>
					<div class="buy_button"><a href="#">Acquista</a></div>
				</div>
			</div>
		</div>
	</div>
	<br />

	<div class="comments_container">
		<h3>Commenti <i class="fa fa-comments-o" aria-hidden="true"></i></h3>
		<div class="comments_box">
		<% for(Commento c: risorsa.getCommenti()) { %>
			<div class="comments" id="<%=c.getId()%>">

				<div class="users_comments">
					<span class="username_comment"><strong><i class="fa fa-user"></i><%=c.getMittente().getUsername()%></strong></span>
					<div class="comment">
						<p><%=c.getTesto()%>
						</p>
						<span class="time_date"> 
					<%
                    SimpleDateFormat format=new SimpleDateFormat("hh:mm | MM dd");
					Calendar rapr=Calendar.getInstance();
					rapr.setTime(c.getData());
					String formatted=format.format(rapr.getTime());
                    %>
                    <%=formatted%></span>
					</div>
				</div>
			</div>
		<% } %>
			<div class="add_comment">
				<div class="input_write_comment">
					<form class="comment_form" action="#">
						<input type="text" class="write_comment" placeholder="  Aggiungi un Commento..." />
						<button class="comment_send_btn" type="button"><i class="fa fa-comment"
								aria-hidden="true"></i></button>
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

	</div>
	<script type="text/javascript">
		var element = document.getElementById("comments");
		element.scrollTop = element.scrollHeight;
	</script>
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="styles/bootstrap4/popper.js"></script>
	<script src="styles/bootstrap4/bootstrap.min.js"></script>
	<script src="js/single_custom.js"></script>
</body>
</html>
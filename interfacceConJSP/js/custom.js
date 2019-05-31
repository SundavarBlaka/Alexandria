/* JS Document */

/*******************************************************************************
 * 
 * [Table of Contents]
 * 
 * 1. Vars and Inits 
 * 2. Set Header 
 * 3. Init Menu
 ******************************************************************************/
function deleteProduct(product_id){
	var xhr = new XMLHttpRequest();
	var url = "url";
	xhr.open("GET", "url?product=" + product_id, true);
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
			var element=document.getElementById(product_id);
			element.parentNode.removeChild(element);
		}
	};
	xhr.send(null);
	
}
function addToCart(){
						var product_id;	
						var  span = document.getElementById('checkout_items');
						var text = span.textContent;
						var currentValue = Number(text);
						var xhr = new XMLHttpRequest();
						var url = "url";
						xhr.open("GET", "url?product=" + product_id, true);
						xhr.onreadystatechange = function() {
							if (xhr.readyState === 4 && xhr.status === 200) {
								var json = JSON.parse(xhr.responseText);
								document.getElementById("checkout_items").innerHTML=currentValue+1;
							}
						};
						xhr.send(null);
					}


jQuery(document).ready(function($) {
			
					"use strict";

					/*
					 * 
					 * 1. Vars and Inits
					 * 
					 */

					var header = $('.header');
					var topNav = $('.top_nav')
					var mainSlider = $('.main_slider');
					var hamburger = $('.hamburger_container');
					var menu = $('.hamburger_menu');
					var menuActive = false;
					var hamburgerClose = $('.hamburger_close');
					var fsOverlay = $('.fs_menu_overlay');

					setHeader();

					$(window).on('resize', function() {
						setHeader();
					});

					$(document).on('scroll', function() {
						setHeader();
					});

					initMenu();

					/*
					 * 
					 * 2. Set Header
					 * 
					 */
					
					function setHeader() {
						if (window.innerWidth < 992) {
							if ($(window).scrollTop() > 100) {
								header.css({
									'top' : "0"
								});
							} else {
								header.css({
									'top' : "0"
								});
							}
						} else {
							if ($(window).scrollTop() > 100) {
								header.css({
									'top' : "-50px"
								});
							} else {
								header.css({
									'top' : "0"
								});
							}
						}
						if (window.innerWidth > 991 && menuActive) {
							closeMenu();
						}
					}

					/*
					 * 
					 * 3. Init Menu
					 * 
					 */

					function initMenu() {
						if (hamburger.length) {
							hamburger.on('click', function() {
								if (!menuActive) {
									openMenu();
								}
							});
						}

						if (fsOverlay.length) {
							fsOverlay.on('click', function() {
								if (menuActive) {
									closeMenu();
								}
							});
						}

						if (hamburgerClose.length) {
							hamburgerClose.on('click', function() {
								if (menuActive) {
									closeMenu();
								}
							});
						}

						if ($('.menu_item').length) {
							var items = document
									.getElementsByClassName('menu_item');
							var i;

							for (i = 0; i < items.length; i++) {
								if (items[i].classList.contains("has-children")) {
									items[i].onclick = function() {
										this.classList.toggle("active");
										var panel = this.children[1];
										if (panel.style.maxHeight) {
											panel.style.maxHeight = null;
										} else {
											panel.style.maxHeight = panel.scrollHeight
													+ "px";
										}
									}
								}
							}
						}
					}

					function openMenu() {
						menu.addClass('active');
						// menu.css('right', "0");
						fsOverlay.css('pointer-events', "auto");
						menuActive = true;
					}

					function closeMenu() {
						menu.removeClass('active');
						fsOverlay.css('pointer-events', "none");
						menuActive = false;
					}

					function search() {
						var search = document.search.search.value;
						if (search === null || search === "") {
							return false;
						} else {
							var product = "";
							var xhr = new XMLHttpRequest();
							var url = "url";
							xhr.open("GET", "url?search=" + search, true);
							xhr.onreadystatechange = function() {
								if (xhr.readyState === 4 && xhr.status === 200) {
									var json = JSON.parse(xhr.responseText);										
									document.getElementById("product-grid").innerHTML="";
									for (i in json.products) {
										product = "";
										product += '<div class="product-item" id="'
												+ json.products[i].id
												+ '"><div class="product"><div class="product_image"><img src="'
												+ json.products[i].image
												+ '"alt="Imagine non Trovata!"></div><div class="product_info"><h6 class="product_name"><a onclick="$("#mostra_risorsa'											
												+ json.products[i].id
												+'").submit()">'
												+ json.products[i].title
												+ '</a></h6><div class="product_price">€'
												+ json.products[i].price
												+ '</div></div></div><div class="red_button add_to_cart_button"><a onclick="$("#aggiungi_al_carrello'
												+ json.products[i].id
												+'").submit()">Aggiungi al Carrello</a></div><form method="get" action="profile" id="aggiungi_al_carrello'
												+ json.products[i].id
												'"><input type="hidden" value="aggiungi" name="type"><input type="hidden" name="id" value="'
												+ json.products[i].id
												'"><input type="hidden" id="quantity" name="quantity" value="1"></form></div><form  method="get" action="resource" id="mostra_risorsa'
												+ json.products[i].id
												'"><input type="hidden" name="type" value="mostra"><input type="hidden" name="id" value="'
												+ json.products[i].id
												'"></form></div>';
										document.getElementById("product-grid").insertAdjacentHTML('beforeend', product);
									}
								}
							};
							xhr.send(null);
						}
					}
					
					function addToCart(){
						var product_id;	
						var  span = document.getElementById('checkout_items');
						var text = span.textContent;
						var currentValue = Number(text);
						var xhr = new XMLHttpRequest();
						var url = "url";
						xhr.open("GET", "url?product=" + product_id, true);
						xhr.onreadystatechange = function() {
							if (xhr.readyState === 4 && xhr.status === 200) {
								var json = JSON.parse(xhr.responseText);
								document.getElementById("checkout_items").innerHTML=currentValue+1;
							}
						};
						xhr.send(null);
					}
				});

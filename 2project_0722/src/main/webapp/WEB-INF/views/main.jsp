<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">
<title>반려동물종합관리플랫폼</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="resources/assets/img/favicon.png" rel="icon">
<link href="resources/assets/img/apple-touch-icon.png"
	rel="apple-touch-icon">

<!-- Fonts -->
<link href="https://fonts.googleapis.com" rel="preconnect">
<link href="https://fonts.gstatic.com" rel="preconnect" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&family=Montserrat:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&family=Poppins:ital,wght@0,100;0,200;0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,100;1,200;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="resources/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="resources/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="resources/assets/vendor/aos/aos.css" rel="stylesheet">
<link href="resources/assets/vendor/glightbox/css/glightbox.min.css"
	rel="stylesheet">
<link href="resources/assets/vendor/swiper/swiper-bundle.min.css"
	rel="stylesheet">

<!-- Main CSS File -->
<link href="resources/assets/css/main.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: Impact
  * Template URL: https://bootstrapmade.com/impact-bootstrap-business-website-template/
  * Updated: Jun 29 2024 with Bootstrap v5.3.3
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

 
<!-- 공통 css -->
<link rel="stylesheet" href="resources/css/common.css">

	
<style>
@font-face {
    font-family: 'RixInooAriDuriR';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2207-01@1.0/RixInooAriDuriR.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
@font-face {
	font-family: 'Noto Sans KR';
	font-style: normal;
	font-weight: 500;
	src: url(//fonts.gstatic.com/ea/notosanskr/v2/NotoSansKR-Medium.woff2)
		format('woff2'),
		url(//fonts.gstatic.com/ea/notosanskr/v2/NotoSansKR-Medium.woff)
		format('woff'),
		url(//fonts.gstatic.com/ea/notosanskr/v2/NotoSansKR-Medium.otf)
		format('opentype');
}

body {
	/* font-family: 'RixInooAriDuriR'; */
	font-family: 'Noto Sans KR', sans-serif !important;
}

input {
	font-family: 'Noto Sans KR', sans-serif !important;
}

.icon-box {
	border-radius: 20px !important;
}
</style>

</head>




<!--  부트스트랩 스타일 수정  -->
<style>
.accent-background {
	background-color: white !important;
	color: black;
}

.accent-background p {
	color: black;
}

.topbar {
	background-color: #FADA5A !important;
}

.branding, .branding a, .branding h1 {
	background-color: gray !important;
	color: black;
}

.icon-box {
	background-color: #FADA5A !important;
}

#footer {
	background-color: gray !important;
}
</style>


<body class="index-page">
	<header id="header" class="header fixed-top">

		<div class="topbar d-flex align-items-center">
			<div
				class="container d-flex justify-content-center justify-content-md-between">
				<div class="contact-info d-flex align-items-center">
					<i class="bi bi-envelope d-flex align-items-center"><a
						href="mailto:contact@example.com">contact@example.com</a></i> <i
						class="bi bi-phone d-flex align-items-center ms-4"><span>+1
							5589 55488 55</span></i>
				</div>
				<div class="social-links d-none d-md-flex align-items-center">
					<a href="#" class="twitter"><i class="bi bi-twitter-x"></i></a> <a
						href="#" class="facebook"><i class="bi bi-facebook"></i></a> <a
						href="#" class="instagram"><i class="bi bi-instagram"></i></a> <a
						href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
				</div>
			</div>
		</div>
		<!-- End Top Bar -->

		<div class="branding d-flex align-items-cente">

			<div
				class="container position-relative d-flex align-items-center justify-content-between">
				<a href="index.html" class="logo d-flex align-items-center"> <!-- Uncomment the line below if you also wish to use an image logo -->
					<!-- <img src="resources/assets/img/logo.png" alt=""> -->
					<h1 class="sitename">LOGO</h1>
				</a>

				<nav id="navmenu" class="navmenu">
					<ul>
						<li><a href="#" class="active">다이어리<br></a></li>
						<li class="dropdown"><a href="blog.html"><span>커뮤니티</span>
								<i class="bi bi-chevron-down toggle-dropdown"></i></a>
							<ul>
								<li><a href="#">Dropdown 1</a></li>
								<li><a href="#">Dropdown 2</a></li>
								<li><a href="#">Dropdown 3</a></li>
								<li><a href="#">Dropdown 4</a></li>
							</ul></li>
						<li><a href="#">플레이스</a></li>
						<li><a href="#portfolio">뉴스</a></li>
						<li><a href="#team">쇼핑몰</a></li>

						<li><a href="#">문의</a></li>
					</ul>
					<i class="mobile-nav-toggle d-xl-none bi bi-list"></i>
				</nav>

			</div>

		</div>

	</header>







	<main class="main">




		<!-- Hero Section -->
		<section id="hero" class="hero section accent-background">

			<div class="container position-relative" data-aos="fade-up"
				data-aos-delay="100">
				<div class="row gy-5 justify-content-between">
					<div
						class="col-lg-6 order-2 order-lg-1 d-flex flex-column justify-content-center">
						<h2>
							<span>Welcome to </span><span class="accent">(Logo name)</span>
						</h2>
						<p>사람과 반려동물이 함께 더불어 행복한 세상을 만들어갑니다.</p>
					</div>
					<div class="col-lg-5 order-1 order-lg-2">
						<img src="resources/assets/img/hero-img.png" class="img-fluid"
							alt="" style="border-radius: 20px;">
					</div>
				</div>
			</div>

			<div class="icon-boxes position-relative" data-aos="fade-up"
				data-aos-delay="200">
				<div class="container position-relative">
					<div class="row gy-4 mt-5">

						<div class="col-xl-3 col-md-6">
							<div class="icon-box">
								<div class="icon">
									<i class="bi bi-easel"></i>
								</div>
								<h4 class="title">
									<a href="" class="stretched-link">다이어리</a>
								</h4>
							</div>
						</div>
						<!--End Icon Box -->

						<div class="col-xl-3 col-md-6">
							<div class="icon-box">
								<div class="icon">
									<i class="bi bi-gem"></i>
								</div>
								<h4 class="title">
									<a href="" class="stretched-link">커뮤니티</a>
								</h4>
							</div>
						</div>
						<!--End Icon Box -->

						<div class="col-xl-3 col-md-6">
							<div class="icon-box">
								<div class="icon">
									<i class="bi bi-geo-alt"></i>
								</div>
								<h4 class="title">
									<a href="" class="stretched-link">플레이스</a>
								</h4>
							</div>
						</div>
						<!--End Icon Box -->

						<div class="col-xl-3 col-md-6">
							<div class="icon-box">
								<div class="icon">
									<i class="bi bi-command"></i>
								</div>
								<h4 class="title">
									<a href="" class="stretched-link">뉴스</a>
								</h4>
							</div>
						</div>
						<!--End Icon Box -->

					</div>
				</div>
			</div>

		</section>
		<!-- /Hero Section -->






		<!-- Services Section -->
		<!-- ## 뉴스 -->
		<section id="services" class="services section">

			<!-- Section Title -->
			<div class="container section-title" data-aos="fade-up">
				<h2>반려동물 뉴스</h2>
				<p>Necessitatibus eius consequatur ex aliquid fuga eum quidem
					sint consectetur velit</p>
			</div>
			<!-- End Section Title -->

			<div class="container">

				<div class="row gy-4">

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="100">
						<div class="service-item  position-relative">
							<div class="icon">
								<i class="bi bi-activity"></i>
							</div>
							<h3>Nesciunt Mete</h3>
							<p>Provident nihil minus qui consequatur non omnis maiores.
								Eos accusantium minus dolores iure perferendis tempore et
								consequatur.</p>
							<a href="service-details.html" class="readmore stretched-link">Read
								more <i class="bi bi-arrow-right"></i>
							</a>
						</div>
					</div>
					<!-- End Service Item -->

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="200">
						<div class="service-item position-relative">
							<div class="icon">
								<i class="bi bi-broadcast"></i>
							</div>
							<h3>Eosle Commodi</h3>
							<p>Ut autem aut autem non a. Sint sint sit facilis nam iusto
								sint. Libero corrupti neque eum hic non ut nesciunt dolorem.</p>
							<a href="service-details.html" class="readmore stretched-link">Read
								more <i class="bi bi-arrow-right"></i>
							</a>
						</div>
					</div>
					<!-- End Service Item -->

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="300">
						<div class="service-item position-relative">
							<div class="icon">
								<i class="bi bi-easel"></i>
							</div>
							<h3>Ledo Markt</h3>
							<p>Ut excepturi voluptatem nisi sed. Quidem fuga consequatur.
								Minus ea aut. Vel qui id voluptas adipisci eos earum corrupti.</p>
							<a href="service-details.html" class="readmore stretched-link">Read
								more <i class="bi bi-arrow-right"></i>
							</a>
						</div>
					</div>
					<!-- End Service Item -->

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="400">
						<div class="service-item position-relative">
							<div class="icon">
								<i class="bi bi-bounding-box-circles"></i>
							</div>
							<h3>Asperiores Commodit</h3>
							<p>Non et temporibus minus omnis sed dolor esse consequatur.
								Cupiditate sed error ea fuga sit provident adipisci neque.</p>
							<a href="service-details.html" class="readmore stretched-link">Read
								more <i class="bi bi-arrow-right"></i>
							</a>
						</div>
					</div>
					<!-- End Service Item -->

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="500">
						<div class="service-item position-relative">
							<div class="icon">
								<i class="bi bi-calendar4-week"></i>
							</div>
							<h3>Velit Doloremque</h3>
							<p>Cumque et suscipit saepe. Est maiores autem enim facilis
								ut aut ipsam corporis aut. Sed animi at autem alias eius labore.</p>
							<a href="service-details.html" class="readmore stretched-link">Read
								more <i class="bi bi-arrow-right"></i>
							</a>
						</div>
					</div>
					<!-- End Service Item -->

					<div class="col-lg-4 col-md-6" data-aos="fade-up"
						data-aos-delay="600">
						<div class="service-item position-relative">
							<div class="icon">
								<i class="bi bi-chat-square-text"></i>
							</div>
							<h3>Dolori Architecto</h3>
							<p>Hic molestias ea quibusdam eos. Fugiat enim doloremque aut
								neque non et debitis iure. Corrupti recusandae ducimus enim.</p>
							<a href="service-details.html" class="readmore stretched-link">Read
								more <i class="bi bi-arrow-right"></i>
							</a>
						</div>
					</div>
					<!-- End Service Item -->

				</div>

			</div>

		</section>
		<!-- /Services Section -->


		<!-- Recent Posts Section -->
		<!-- ## 커뮤니티 글 -->
		<section id="recent-posts" class="recent-posts section">

			<!-- Section Title -->
			<div class="container section-title" data-aos="fade-up">
				<h2>커뮤니티 최신글</h2>
				<p>Necessitatibus eius consequatur ex aliquid fuga eum quidem
					sint consectetur velit</p>
			</div>
			<!-- End Section Title -->

			<div class="container">

				<div class="row gy-4">

					<div class="col-xl-4 col-md-6" data-aos="fade-up"
						data-aos-delay="100">
						<article>

							<div class="post-img">
								<img src="resources/assets/img/blog/blog-1.jpg" alt=""
									class="img-fluid">
							</div>

							<p class="post-category">Politics</p>

							<h2 class="title">
								<a href="blog-details.html">Dolorum optio tempore voluptas
									dignissimos</a>
							</h2>

							<div class="d-flex align-items-center">
								<img src="resources/assets/img/blog/blog-author.jpg" alt=""
									class="img-fluid post-author-img flex-shrink-0">
								<div class="post-meta">
									<p class="post-author">Maria Doe</p>
									<p class="post-date">
										<time datetime="2022-01-01">Jan 1, 2022</time>
									</p>
								</div>
							</div>

						</article>
					</div>
					<!-- End post list item -->

					<div class="col-xl-4 col-md-6" data-aos="fade-up"
						data-aos-delay="200">
						<article>

							<div class="post-img">
								<img src="resources/assets/img/blog/blog-2.jpg" alt=""
									class="img-fluid">
							</div>

							<p class="post-category">Sports</p>

							<h2 class="title">
								<a href="blog-details.html">Nisi magni odit consequatur
									autem nulla dolorem</a>
							</h2>

							<div class="d-flex align-items-center">
								<img src="resources/assets/img/blog/blog-author-2.jpg" alt=""
									class="img-fluid post-author-img flex-shrink-0">
								<div class="post-meta">
									<p class="post-author">Allisa Mayer</p>
									<p class="post-date">
										<time datetime="2022-01-01">Jun 5, 2022</time>
									</p>
								</div>
							</div>

						</article>
					</div>
					<!-- End post list item -->

					<div class="col-xl-4 col-md-6" data-aos="fade-up"
						data-aos-delay="300">
						<article>

							<div class="post-img">
								<img src="resources/assets/img/blog/blog-3.jpg" alt=""
									class="img-fluid">
							</div>

							<p class="post-category">Entertainment</p>

							<h2 class="title">
								<a href="blog-details.html">Possimus soluta ut id suscipit
									ea ut in quo quia et soluta</a>
							</h2>

							<div class="d-flex align-items-center">
								<img src="resources/assets/img/blog/blog-author-3.jpg" alt=""
									class="img-fluid post-author-img flex-shrink-0">
								<div class="post-meta">
									<p class="post-author">Mark Dower</p>
									<p class="post-date">
										<time datetime="2022-01-01">Jun 22, 2022</time>
									</p>
								</div>
							</div>

						</article>
					</div>
					<!-- End post list item -->

				</div>
				<!-- End recent posts list -->

			</div>

		</section>
		<!-- /Recent Posts Section -->






	</main>

	<footer id="footer" class="footer accent-background">

		<div class="container footer-top">
			<div class="row gy-4">
				<div class="col-lg-5 col-md-12 footer-about">
					<a href="index.html" class="logo d-flex align-items-center"> <span
						class="sitename">Impact</span>
					</a>
					<p>Cras fermentum odio eu feugiat lide par naso tierra. Justo
						eget nada terra videa magna derita valies darta donna mare
						fermentum iaculis eu non diam phasellus.</p>
					<div class="social-links d-flex mt-4">
						<a href=""><i class="bi bi-twitter-x"></i></a> <a href=""><i
							class="bi bi-facebook"></i></a> <a href=""><i
							class="bi bi-instagram"></i></a> <a href=""><i
							class="bi bi-linkedin"></i></a>
					</div>
				</div>

				<div class="col-lg-2 col-6 footer-links">
					<h4>Useful Links</h4>
					<ul>
						<li><a href="#">Home</a></li>
						<li><a href="#">About us</a></li>
						<li><a href="#">Services</a></li>
						<li><a href="#">Terms of service</a></li>
						<li><a href="#">Privacy policy</a></li>
					</ul>
				</div>

				<div class="col-lg-2 col-6 footer-links">
					<h4>Our Services</h4>
					<ul>
						<li><a href="#">Web Design</a></li>
						<li><a href="#">Web Development</a></li>
						<li><a href="#">Product Management</a></li>
						<li><a href="#">Marketing</a></li>
						<li><a href="#">Graphic Design</a></li>
					</ul>
				</div>

				<div
					class="col-lg-3 col-md-12 footer-contact text-center text-md-start">
					<h4>Contact Us</h4>
					<p>A108 Adam Street</p>
					<p>New York, NY 535022</p>
					<p>United States</p>
					<p class="mt-4">
						<strong>Phone:</strong> <span>+1 5589 55488 55</span>
					</p>
					<p>
						<strong>Email:</strong> <span>info@example.com</span>
					</p>
				</div>

			</div>
		</div>

		<div class="container copyright text-center mt-4">
			<p>
				Â© <span>Copyright</span> <strong class="px-1 sitename">Impact</strong>
				<span>All Rights Reserved</span>
			</p>
			<div class="credits">
				<!-- All the links in the footer should remain intact. -->
				<!-- You can delete the links only if you've purchased the pro version. -->
				<!-- Licensing information: https://bootstrapmade.com/license/ -->
				<!-- Purchase the pro version with working PHP/AJAX contact form: [buy-url] -->
				Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
			</div>
		</div>

	</footer>

	<!-- Scroll Top -->
	<a href="#" id="scroll-top"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<!-- Preloader -->
	<div id="preloader"></div>

<!-- Vendor JS Files -->
	<script
		src="resources/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="resources/assets/vendor/php-email-form/validate.js"></script>
	<script src="resources/assets/vendor/aos/aos.js"></script>
	<script src="resources/assets/vendor/glightbox/js/glightbox.min.js"></script>
	<script src="resources/assets/vendor/swiper/swiper-bundle.min.js"></script>
	<script
		src="resources/assets/vendor/purecounter/purecounter_vanilla.js"></script>
	<script
		src="resources/assets/vendor/imagesloaded/imagesloaded.pkgd.min.js"></script>
	<script
		src="resources/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script> 


<!-- Main JS File -->
<script src="resources/assets/js/main.js"></script>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign up</title>

<link type="text/css" rel="stylesheet" href="css/signup.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<link
	href="https://fonts.googleapis.com/css?family=Montserrat:400,500,700"
	rel="stylesheet">

<!-- Bootstrap -->
<link type="text/css" rel="stylesheet" href="css/bootstrap.min.css" />

<!-- Slick -->
<link type="text/css" rel="stylesheet" href="css/slick.css" />
<link type="text/css" rel="stylesheet" href="css/slick-theme.css" />

<!-- nouislider -->
<link type="text/css" rel="stylesheet" href="css/nouislider.min.css" />

<!-- Font Awesome Icon -->
<link rel="stylesheet" href="css/font-awesome.min.css">

<!-- Custom stlylesheet -->
<link type="text/css" rel="stylesheet" href="css/style.css" />

<link type="text/javascript" rel="stylesheet" href="jquery.js" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
		  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
		  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
		<![endif]-->
<body>
	<%@ include file="header_logSign.jsp"%>

	<div class="wrapper">
		<div class="logo">
			<img src="./img/logo.png" alt="">
		</div>
		<div class="text-center mt-4 name"></div>
		<form class="p-3 mt-3" id="form-1" action="SignUp" method="POST">
			<div class="form-field d-flex align-items-center form-group">
				<span class="far fa-user form-message"> ${error1 == null ? "" : error1}</span>
			</div>
			<div class="form-field d-flex align-items-center form-group">

			<input type="text" name="name" id="Name" placeholder="Full name" value ="${acc.name }">
				<span class="far fa-user form-message"></span>
			</div>
			<div class="form-field d-flex align-items-center form-group">

				<input type="text" name="username" id="email" placeholder="Email" value ="${acc.usr }">
				<span class="far fa-user form-message"></span>
			</div>
			<div class="form-field d-flex align-items-center  form-group">

				<input type="password" name="password" id="password"
					placeholder="Password" value ="${acc.pwd }"> <span
					class="far fa-user form-message"></span>
			</div>
			<div class="form-field d-flex align-items-center form-group">

				<input type="password" name="re_password" id="password_confirmation"
					placeholder="Password"> <span
					class="far fa-user form-message"></span>
			</div>
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="exampleCheck1"
					name="rememberMe"> <label class="form-check-label"
					id="rememberAccount" for="rememberAccount">Remember me </label>
			</div>
			<button class="btn mt-3">Sign up</button>
		</form>

		<!-- <a  href="login.jsp"><button class="btn mt-3">Login</button></a> -->
	</div>




	<%@ include file="footer.jsp"%>


	<!-- jQuery Plugins -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/slick.min.js"></script>
	<script src="js/nouislider.min.js"></script>
	<script src="js/jquery.zoom.min.js"></script>
	<script src="js/main.js"></script>
	<script src="js/formValidation.js"></script>
	<script>

      document.addEventListener('DOMContentLoaded', function () {
        // Mong muốn của chúng ta
        Validator({
          form: '#form-1',
          formGroupSelector: '.form-group',
          errorSelector: '.form-message',
          rules: [
            Validator.isRequired('#Name', 'Vui lòng nhập tên đầy đủ của bạn'),
            Validator.isEmail('#email'),
            Validator.minLength('#password', 6),
            Validator.isRequired('#password_confirmation'),
            Validator.isConfirmed('#password_confirmation', function () {
              return document.querySelector('#form-1 #password').value;
            }, 'Mật khẩu nhập lại không chính xác')
          ],
          onSubmit: function (data) {
            // Call API
            console.log(data);
          }
        });


 
      });

    </script>
</body>
</html>
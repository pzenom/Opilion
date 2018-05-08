<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<jsp:include page="includes/head_simple.jsp" flush="true" />
<body id="login">
	<header>
		<div id="logo">
			
		</div>
	</header>
	<section id="content">
		<form action="Login.action" id="loginform" method="POST">
			<fieldset>
				<section><label for="login">Usuario</label>
					<div><input type="text" id="login" name="login" key="login" autofocus tabindex="1"></div>
				</section>
				<section><label for="pass">Contrase&ntilde;a</label>
					<div><input type="password" id="pass" name="pass" key="pass" tabindex="2"></div>
				</section>
				<section>
					<div><button id="start" class="fr" tabindex="3">Inicio</button></div>
				</section>
			</fieldset>
		</form>
		<fieldset>
			<label><p>Cambio de entorno</p></label>
			<a href="/preOpilion/"><button id="btEntornoPruebas" style="min-width: 48%;">Pruebas</button></a>
			<a href="/opilion/"><button id="btEntornoReal" style="min-width: 48%;">Tierrina</button></a>
		</fieldset>
	</section>
	<footer>&copy; Induserco Desarrollo S.L. <%= new java.text.SimpleDateFormat("yyyy").format(new java.util.Date()) %></footer>
</body>
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="js/md5-min.js"></script>
</html>
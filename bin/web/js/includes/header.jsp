	<div  id="header">
		<div id="profile_info">
			<img src="img/avatar.jpg" id="avatar" alt="avatar" width="20" />
			<p id="saludo">Bienvenido 
				<span id="user_session">
					<s:push value="%{#session.usuario}">
						<s:property value="login" />
					</s:push>
				</span>
			</p>
			<p id="logout"><a href="Logout.action">Cerrar sesi&oacute;n</a></p>
		</div><!-- end profile_info --> 	
		<img id="logo" src="images/logo_tierrina.png" alt="Logotipo de Tierrina Vaqueira" />
    	<h1 id="slogan">ERP - Tierrina Vaqueira</h1>
	<!--div id="header"><h1>Ext Layout Browser</h1></div-->
    </div><!-- end header -->
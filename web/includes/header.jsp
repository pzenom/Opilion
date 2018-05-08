<%@ taglib uri="/struts-tags" prefix="s"%>  
  <div id="header">
  	<div id="profile_info">
			<img src="img/avatar.jpg" id="avatar" alt="avatar" width="20" />
			<p>Bienvenido 
			<strong>
				<s:push value="%{#session.usuario}">
					<s:property value="login" />
				</s:push>
			</strong>
			</p>
			<p><a href="Logout.action">Cerrar sesión</a></p>
		</div><!-- end profile_info -->  
  	<h1><s:text name="index.bienvenida" /> - <s:text name="index.mensaje" /></h1>
  </div><!-- end header -->
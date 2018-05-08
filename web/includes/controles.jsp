<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="pageoptions">
	<ul>
		Tierrina Vaqueira S.L. - <span class="capitalize">
			<s:push value="%{#session.usuario}">
				<s:property value="login" />
			</s:push></span>
		<li><a href="Logout.action">Salir</a></li>
		<li><a href="#" id="wl_config">Configuraci&oacute;n</a></li>
	</ul>
	<div id="configura_usuario">
		<h3>Datos propios</h3>
		<p>Por favor, modifica tus datos si lo considera apropiado</p>
		<div style="width: 30%; float:left; position:relative;">
			<label for="nuevoNombre">Nuevo nombre</label>
			<input id="nuevoNombre" value="<s:property value="%{#session.usuario.login}" />"/>
		</div>
		<s:hidden id="loginUsuario" value="%{#session.usuario.login}" />
		<div style="width: 30%; float:left; margin-left: 3%; position:relative;">
			<label for="nuevoPass">Nueva contrase&ntilde;a</label>
			<input type="password" id="nuevoPass" style="width: 100% !important;" />
		</div>
		<div style="width: auto; float:left; margin-left: 3%; position:relative; margin-top: 8px;">
			<button id="bot_volver" class="i_arrow_left icon verdeOpilion" onclick="javascript:guardarConfiguracionPersonal();">Guardar</button>
		</div>
		<div style="float:left; margin-left: 3%; position:relative; margin-top: 8px;">
			<p id="mensajeResultadoConfiguracion">DATOS SALVADOS CON EXITO</p>
		</div>
	</div>
</div>
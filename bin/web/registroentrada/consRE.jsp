<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar registros de entrada<span class="screenCode">CONS_RE</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="formulario" action="ConsOE" validate="true">
				<s:hidden id="idTipo" name="idTipo" key="idTipo" value="%{#session.idTipo}" />
				<fieldset><!-- Informacion Basica -->
					<section style="width: 50%;">
						<label for="dropdown_cuantos_mostrar">Filtrar</label>
						<div>
							<select name="dropdown_cuantos_mostrar" id="dropdown_cuantos_mostrar" onchange="javascript:filtraRE();">
								<optgroup label="Mostrar...">
									<option value="0">Todas las entradas</option>
									<option value="1" selected>&Uacute;ltimas 20</option>
									<option value="2">&Uacute;ltimas 50</option>
									<option value="3">&Uacute;ltima semana</option>
									<option value="4">&Uacute;ltimo mes</option>
									<option value="5">&Uacute;ltimo a&ntilde;o</option>
								</optgroup>
							</select>
						</div>
					</section>
				</fieldset><!--end Informacion Basica-->
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaRE" cellpadding="0" cellspacing="0" border="0" style="display: none;">
				<thead>
					<tr>
						<th><s:text name='consulta_re.codigo' /></th>
						<th><s:text name='consulta_re.fecha2' /></th>
						<th><s:text name='consulta_re.proveedor' /></th>
						<th><s:text name='consulta_re.origen' /></th>
						<th><s:text name='consulta_re.albaran' /></th>
						<th><s:text name='consulta_re.producto' /></th>
						<th>Saldo</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entrada" value="%{#session.listaentradas}">
						<input id="<s:property value="codigoEntrada" />" class="tipoReg" type="hidden" value="<s:property value="idTipoRegistro" />" />
						<s:hidden cssClass="registrosBorrar" id="podemosBorrar_%{#entrada.codigoEntrada}" value="%{#entrada.podemosBorrar}" />
						<span id="tipo_<s:property value="codigoEntrada" />" class="tipos" style="display: none;">
							<s:property value="idTipoRegistro" />
						</span>
						<tr>
							<td><s:property value="codigoEntrada" /></td>
							<td><s:property value="fecha" /></td>
							<td><s:property value="descProveedor" /></td>
							<td><s:property value="origen" /></td>
							<td><s:property value="albaran" /></td>
							<td><s:property value="descProducto" /></td>
							<td>
								<span id="saldo_<s:property value="codigoEntrada" />" class="numeroMilesDecimal"><s:property value="saldo" /></span>
							</td>
							<td style="vertical-align: middle;">
								<a href="javascript:editRE('<s:property value="codigoEntrada" />', '<s:property value="idTipoRegistro" />');">
									<img src="img/note_edit.png" alt="Editar" title="Editar"/>
								</a>
								<a id="bultos_<s:property value="codigoEntrada" />" href="javascript:bultos('<s:property value="codigoEntrada" />');" style="display: none; ">
									<img src="img/book.png" alt="Gesti&oacute;n de bultos" title="Gesti&oacute;n de bultos" />
								</a>
								<a id="etiqueta_<s:property value="codigoEntrada" />" href="#" onClick="javascript:parent.get_ventana_emergente('ERE','EtiqREJR.action?codigoEntrada=<s:property value="codigoEntrada" />','no','no','590','420','','');" style="display: none; ">
									<img src="img/report.png" alt="Etiqueta" title="Etiqueta" />
								</a>
								<a id="etiquetaProductoFinal_<s:property value="codigoEntrada" />" href="#" onClick="javascript:parent.get_ventana_emergente('ERE','EtiqENJR.action?orden=<s:property value="codigoEntrada" />','no','no','590','420','','');" style="display: none; ">
									<img src="img/report.png" alt="Etiqueta" title="Etiqueta" />
								</a>
								<a id="bt_deshabilitarRE_<s:property value="codigoEntrada" />" href="#" onClick="javascript:deshabilitarRE('<s:property value="codigoEntrada" />');" style="display: none; ">
									<img src="img/cancel.png" alt="Eliminar" title="Eliminar" />
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div> <!-- END DEMO -->
	</div> <!-- end divNecesarioWidget -->
</s:i18n>
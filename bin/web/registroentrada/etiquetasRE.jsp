<%@page contentType="text/html; charset=iso-8859-15" pageEncoding="iso-8859-15"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Orden de entrada <s:label value="%{#session.codigoorden}"/>. Registros de entrada.<span class="screenCode">ADD_RE4</span></h3>
	<s:form id="formu" name="formu" action="InseOE" validate="true">
		<s:hidden id="previa" value="%{#session.previa}"/>
		<s:hidden id="idProve" value="%{#session.idProveedor}"/>
		<s:hidden id="idTrans" value="%{#session.idTransportista}"/>
		<s:hidden id="editable" value="%{#session.editable}"/>
		<s:hidden id="guardado" value="S"/>
		<div>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_orden">Codigo orden</label>
					<div>
						<s:textfield id="text_orden" key="text_orden" cssStyle="text-align:right;" value="%{#session.codigoorden}" disabled="true" />
						<!-- codigoOrden -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_responsable">Responsable</label>
					<div>
						<s:textfield key="idOperario" cssStyle="text-align:right;" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" disabled="true" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="date_fechaRegistro">Fecha de registro</label>
					<div>
						<input id="date_fechaRegistro" name="date_fechaRegistro" type="text" disabled="true" disabled="disabled" class="date" value="<s:property value="%{#session.fecharegistro}" />">
					<!-- fecha -->
					</div>
				</section>
			</fieldset>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_albaran">Albar&aacute;n</label>
					<div>
						<s:textfield id="text_albaran" key="albaran" cssStyle="text-align:right;" label="%{getText('entry.albaran')}" value="%{#session.albaran}" disabled="true" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_origen">Origen</label>
					<div>
						<s:textfield id="text_origen" key="origen" cssStyle="text-align:right;" value="%{#session.origen}" disabled="true"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_proveedores">Proveedor</label>
					<div>
						<select name="dropdown_proveedores" id="dropdown_proveedores" disabled="disabled">
							<option value="0">Seleccione un proveedor</option>
							<optgroup label="Proveedor">
								<s:iterator id="proveedores" value="%{#session.listaproveedores}">
									<option value="<s:property value="idUsuario" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_transportistas">Transportista</label>
					<div>
						<select name="dropdown_transportistas" id="dropdown_transportistas" disabled="disabled" onchange="javascript:checkTransportista();">
							<option value="0">Seleccione transportista</option>
							<optgroup label="Trasnportista">
								<s:iterator id="tranportistas" value="%{#session.listatransportistas}">
									<option value="<s:property value="idUsuario" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<!-- idTransportista -->
					</div>
				</section>
				<section class="sectionMitad" id="otrosVehiculos">
					<label for="text_descVehiculo">Descripci&oacute;n veh&iacute;culo</label>
					<div>
						<s:textfield id="text_descVehiculo" key="text_descVehiculo" cssStyle="text-align:right;" value="%{#session.descripcionVehiculo}" disabled="true" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_notasVehiculo">Notas veh&iacute;culo</label>
					<div>
						<s:textfield id="text_notasVehiculo" key="text_notasVehiculo" cssStyle="text-align:right;" value="%{#session.notasVehiculo}" disabled="true" />
					</div>
				</section>
			</fieldset>
			<fieldset id="fieldsetRegistros">
				<table class="tabla" border=0 cellpadding="2" cellspacing="2" width="100%" >
					<thead>
						<tr>
							<th><b>C&oacute;digo</b></th>
							<th><b>Producto</b></th>
							<th><b>Fecha</b></th>
							<th><b>Kilos/Unidades</b></th>
							<th><b>Tipo</b></th>
							<th><b>Opciones</b></th>
						</tr>
					</thead>
					<tbody id="tbody">
						<s:iterator id="entrada" value="%{#session.listareorden}">
							<tr>
								<s:hidden cssClass="registrosBorrar" id="podemosBorrar_%{#entrada.codigoEntrada}" value="%{#entrada.podemosBorrar}" />
								<td><s:property value="codigoEntrada" /></td>
								<td>
									<s:if test="#entrada.idProducto == 0">
										<s:property value="idEnvase" />
									</s:if>
									<s:else>
										<s:property value="idProducto" />
									</s:else>
									- <s:property value="descProducto" />
									</td>
								<td><s:property value="fecha" /></td>
								<td><s:property value="cantidad" /></td>
								<td><span id="<s:property value="codigoEntrada" />" class="tipoReg"><s:property value="idTipoRegistro" /></span></td>
								<td>
									<a id="etiquetaNormal_<s:property value="codigoEntrada" />" href="javascript:parent.get_ventana_emergente('ERE','EtiqREJR.action?codigoEntrada=<s:property value="codigoEntrada" />','no','no','590','420','','');" >
										<img src="img/report.png" alt="Etiqueta" title="Etiqueta"/>
									</a>
									<a id="bultos_<s:property value="codigoEntrada" />" href="javascript:bultos('<s:property value="codigoEntrada" />',
										'<s:property value="idTipoRegistro" />');">
										<img src="img/book.png" alt="Gesti&oacute;n de bultos" title="Gesti&oacute;n de bultos" />
									</a>
									<a href="javascript:editRE('<s:property value="codigoEntrada" />', '<s:property value="idTipoRegistro" />');">
										<img src="img/note_edit.png" alt="Editar" title="Editar"/>
									</a>
									<a id="todasEti_<s:property value="codigoEntrada" />" href="javascript:parent.get_ventana_emergente('ToRE','EtiquetaTodosBultosRE.action?codigoEntrada=<s:property value="codigoEntrada" />','no','no','590','420','','');">
										<img src="img/pdf_icon_16.gif" alt="Todas las etiquetas" title="Todas las etiquetas"/>
									</a>
									<!--
										<a id="unaEti_<s:property value="codigoEntrada" />" onclick="javascript:parent.get_ventana_emergente('ERE','EtiquetaBultoRE.action?idEntrada=<s:property value="codigoEntrada" />&numBulto=1&pBruto=1.0&pNeto=1.0&numBultosPalet=0','no','no','590','420','','');">
											<img src="img/pdf_icon_16.gif" alt="Una etiqueta" title="Una etiqueta"/>
										</a>
									-->
									<a id="bt_deshabilitarRE_<s:property value="codigoEntrada" />" href="javascript:deshabilitarRE('<s:property value="codigoEntrada" />');" style="vertical-align: middle; display: none;" >
										<img src="img/cancel.png" alt="Eliminar" title="Eliminar" />
									</a>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</fieldset>
		</div>
	</s:form>
</s:i18n>
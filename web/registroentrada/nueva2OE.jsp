<%@page contentType="text/html; charset=iso-8859-15" pageEncoding="iso-8859-15"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Orden de entrada<span class="screenCode">ADD_RE3</span></h3>
	<s:form id="formu" name="formu" action="InseOE" validate="true">
		<div>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_orden">Codigo</label>
					<div>
						<s:textfield key="codigoOrden" cssStyle="text-align:right;" value="%{#session.codigoorden}" disabled="true" />
						<!-- codigoOrden -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_responsable">Responsable</label>
					<div>
						<s:textfield key="idOperario" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" disabled="true" cssStyle="text-align:right;"/>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="date_fechaRegistro">Fecha</label>
					<div>
						<input id="date_fechaRegistro" name="date_fechaRegistro" type="text" disabled="true" class="date" value="<s:property value="%{#session.fecharegistro}" />">
					<!-- fecha -->
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_albaran">Albar&aacute;n</label>
					<div>
						<s:textfield key="albaran" label="%{getText('entry.albaran')}" value="%{#session.albaran}" disabled="true" cssStyle="text-align:right;"/>
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_origen">Origen</label>
					<div>
						<s:textfield key="origen" cssStyle="text-align:right;" value="%{#session.origen}" disabled="true"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_proveedor">Proveedor</label>
					<div>
						<s:select id="selectProveedores" key="idProveedor" label="%{getText('entry.proveedor')}" list="%{#session.listaproveedores}" listKey="idUsuario" listValue="nombre" headerKey="%{#session.idproveedor}" headerValue="%{#session.proveedor}" disabled="true" cssStyle="text-align:left;" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_transportistas">Transportista</label>
					<div>
						<s:select key="idTransportista" label="%{getText('entry.transportista')}" list="%{#session.listatransportistas}" listKey="idUsuario" listValue="nombre" headerKey="0" headerValue="%{#session.transportista}" disabled="true" cssStyle="text-align:left;" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_transportistas">Descripci&oacute;n veh&iacute;culo</label>
					<div>
						<s:textfield key="descVehiculo" value="%{#session.descvehiculo}" cssStyle="text-align:right;" disabled="true"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_transportistas">Notas veh&iacute;culo</label>
					<div>
						<s:textarea key="notasVehiculo" value="%{#session.notasvehiculo}" rows="2" cols="30" disabled="true" />
					</div>
				</section>
			</fieldset>
			<fieldset>
				<table class="tabla" border=0 cellpadding="2" cellspacing="2" width="100%" >
					<thead>
						<tr>
							<th><b>C&oacute;digo</b></th>
							<th><b>Producto</b></th>
							<th><b>Fecha</b></th>
							<th><b>Kilos/Unidades</b></th>
						</tr>
					</thead>
					<tbody id="tbody">
						<s:iterator id="entrada" value="%{#session.listareorden}">
							<tr class="fila">
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
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</fieldset>
		</div>
	</s:form>
</s:i18n>
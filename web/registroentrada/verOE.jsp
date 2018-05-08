<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="nueva_oe.bienvenida" /><span class="screenCode">Ver OE</span></h3>
	<s:form id="formulario" name="formulario" action="NuevaOETmp" validate="true">
		<div>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_orden">Codigo orden</label>
					<div>
						<input type="text" id="text_orden" name="text_orden" readonly="true" value="<s:property value="%{#session.codigoorden}" />"/>
						<!-- codigoOrden -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_responsable">Responsable</label>
					<div>
						<input type="text" id="text_responsable" name="text_responsable" readonly="true" value="<s:property value="%{#session.usuario.login}" />"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="date_fechaRegistro">Fecha de registro</label>
					<div><input id="date_fechaRegistro" name="date_fechaRegistro" type="text" readonly="true" disabled="disabled" class="date" value="<s:property value="%{#session.fecharegistro}" />">
					<!-- fecha -->
					</div>
				</section>
			</fieldset>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_albaran">Albar&aacute;n</label>
					<div>
						<input type="text" id="text_albaran" name="text_albaran" value="<s:property value="%{#session.albaran}" />"/>
						<!-- albaran -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_origen">Origen</label>
					<div>
						<input type="text" id="text_origen" name="text_origen" value="<s:property value="%{#session.origen}" />"/>
						<!-- origen -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_tipoRegistro">Tipo de registro</label>
					<div>
						<input type="text" id="text_tipoRegistro" name="text_tipoRegistro" value="<s:property value="%{#session.tiporegistro}" />" readonly="true"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_proveedores">Proveedor</label>
					<div>
						<s:iterator id="proveedor" value="%{#session.proveedor}">
							<s:select id="dropdown_proveedores" key="idUsuario" onchange="javascript:cambiaProveedor();" label="Proveedor" list="%{#session.listaProveedores}" listKey="idUsuario" listValue="nombre" headerKey="0" headerValue="-- Proveedor --" cssStyle="width:195px;"/>
						</s:iterator>
						<!-- <select name="dropdown_proveedores" id="dropdown_proveedores" onchange="javascript:cambiaProveedor();">
							<optgroup label="Proveedor">
								<s:iterator id="proveedores" value="%{#session.listaproveedores}">
									<option value="<s:property value="idUsuario" />_<s:property value="idTipo" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select> -->
						<!-- proveedor -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_transportistas">Trasnportista</label>
					<div>
						<s:select id="dropdown_transportistas" key="idTransportista" onchange="javascript:checkTransportista();" label="Transportista" list="%{#session.listatransportistas}" listKey="idUsuario" listValue="nombre" headerKey="0" headerValue="-- Transportista --" cssStyle="width:195px;" value="%{#session.idTransportista}"/>
						<!-- select name="dropdown_transportistas" id="dropdown_transportistas" onchange="javascript:checkTransportista();">
							<optgroup label="Trasnportista">
								<s:iterator id="tranportistas" value="%{#session.listatransportistas}">
									<option value="<s:property value="idUsuario" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select> -->
						<!-- idTransportista -->
					</div>
				</section>
				<section class="sectionMitad" id="nuestrosVehiculos" style="display: none;">
					<label for="dropdown_vehiculos">Veh&iacute;culo</label>
					<div>
						<select name="dropdown_vehiculos" id="dropdown_vehiculos" onchange="javascript:seleccionVehiculoPropio();">
							<optgroup label="Vehiculo">
								<s:iterator id="vehiculos" value="%{#session.listavehiculos}">
									<option value="<s:property value="nombre" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<!-- proveedor -->
					</div>
				</section>
				<section class="sectionMitad" id="otrosVehiculos" style="display: none;">
					<label for="text_descVehiculo">Descripci&oacute;n veh&iacute;culo</label>
					<div>
						<input type="text" id="text_descVehiculo" name="text_descVehiculo" value="<s:property value="%{#session.descvehiculo}" />"/>
						<!-- descVehiculo -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_notas">Notas veh&iacute;culo</label>
					<div>
						<input type="text" id="text_notas" name="text_notas" value="<s:property value="%{#session.notasvehiculo}" />"/>
						<!-- notasVehiculo -->
					</div>
				</section>
			</fieldset>
		</div>
	</s:form>
	<button class="i_arrow_left icon verdeOpilion" onclick="javascript:listaOE();">Volver</button>
	<button class="i_plus icon verdeOpilion" onclick="javascript:actualizarOE();">Actualizar</button>
</s:i18n>
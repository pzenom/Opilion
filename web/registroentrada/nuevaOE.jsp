<%@page contentType="text/html; charset=iso-8859-15" pageEncoding="iso-8859-15"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="nueva_oe.bienvenida" /><span class="screenCode"><s:text name="nueva_oe.codigoPantalla" /></span></h3>
	<s:form id="formulario" name="formulario" action="NuevaOETmp" validate="true">
		<div>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_orden">Codigo orden</label>
					<div>
						<input type="text" id="text_orden" name="text_orden" disabled="disabled" value="<s:property value="%{#session.codigoorden}" />"/>
						<!-- codigoOrden -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_responsable">Responsable</label>
					<div>
						<input type="text" id="text_responsable" name="text_responsable" disabled="disabled" value="<s:property value="%{#session.usuario.login}" />"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="date_fechaRegistro">Fecha de registro</label>
					<div>
						<input id="date_fechaRegistro" name="date_fechaRegistro" type="text" disabled="disabled" class="date" value="<s:property value="%{#session.fecharegistro}" />">
					<!-- fecha -->
					</div>
				</section>
			</fieldset>
			<fieldset>
				<section class="sectionMitad">
					<label for="text_albaran">Albar&aacute;n</label>
					<div>
						<input type="text" id="text_albaran" name="text_albaran" />
						<!-- albaran -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_origen">Origen</label>
					<div>
						<input type="text" id="text_origen" name="text_origen" />
						<!-- origen -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_proveedores">Proveedor</label>
					<!-- <div id="divMensajeTipo">
						<p style="color: red;">Seleccione el tipo de registros que contendr&aacute; la orden de entrada</p>
					</div> -->
					<div id="divProveedores">
						<select name="dropdown_proveedores" id="dropdown_proveedores">
							<option value="0">Seleccione un proveedor</option>
							<optgroup label="Proveedor">
								<s:iterator id="proveedores" value="%{#session.listaproveedores}">
									<option value="<s:property value="idUsuario" />_<s:property value="idTipo" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
						<!-- proveedor -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="dropdown_transportistas">Transportista</label>
					<div>
						<select name="dropdown_transportistas" id="dropdown_transportistas" onchange="javascript:checkTransportista();">
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
				<section class="sectionMitad" id="nuestrosVehiculos" style="display: none;">
					<label for="dropdown_vehiculos">Veh&iacute;culo</label>
					<div>
						<select name="dropdown_vehiculos" id="dropdown_vehiculos" onchange="javascript:seleccionVehiculoPropio();">
							<option value="0">Seleccione un veh&iacute;culo</option>
							<optgroup label="Vehiculo">
								<s:iterator id="vehiculos" value="%{#session.listavehiculos}">
									<option value="<s:property value="matricula" />">
										<s:property value="matricula" />
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
						<input type="text" id="text_descVehiculo" name="text_descVehiculo" />
						<!-- descVehiculo -->
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_notas">Notas veh&iacute;culo</label>
					<div>
						<input type="text" id="text_notas" name="text_notas" />
						<!-- notasVehiculo -->
					</div>
				</section>
			</fieldset>
		</div>
	</s:form>
</s:i18n>
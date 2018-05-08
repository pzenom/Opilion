<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" language="java"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Trazabilidad registro entrada <s:property value="#session.lote" /><span class="screenCode">TRAZA_III</span></h3>
	<s:form id="formulario" name="formulario" action="ActualizaProducto" validate="true" method="post" enctype="multipart/form-data">
		<s:iterator id="producto" value="%{#session.lotes}">
			<fieldset>
				<label>Lote</label>
				<section class="" style="width: 25em !important;">
					<label for="text_lote">Lote</label>
					<div style="width: auto !important;">
						<s:textfield id="text_lote" key="lote" label="Lote" readonly="true" cssStyle="width: 12em !important; " />
					</div>
				</section>
				<section class="" style="width: 40em !important;">
					<label for="text_nombre">Producto</label>
					<div style="width: auto !important;">
						<s:textfield id="text_nombre" key="nombre" label="Nombre" readonly="true" cssStyle="width: 25em !important; " />
					</div>
				</section>
				<section class="" style="width: 25em !important;">
					<label for="text_cantidad">Cantidad utilizada</label>
					<div style="width: auto !important;">
						<s:textfield id="text_cantidad" key="cantidad" label="Cantidad" readonly="true" cssStyle="width: 12em !important; text-align: right; " />
					</div>
				</section>
				<section class="" style="width: 25em !important;">
					<label for="text_fecha_<s:property value="#producto.lote"/>">Fecha</label>
					<div style="width: auto !important;">
						<s:textfield id="text_fecha_%{#producto.lote}" key="descripcion" label="Fecha" readonly="true" cssClass="fechas" cssStyle="width: 12em !important; " />
					</div>
				</section>
			</fieldset>
		</s:iterator>
		
		
		
		
		
		
		<!-- Informacion Venta-->
		<fieldset id="fieldsetInformacionVenta">
			<label><s:text name='trazabilidad.informacionVenta' /></label>
			<s:iterator id="salidas" value="%{#session.salidas}">
				<s:hidden cssClass="informacionVenta" />
				<fieldset>
					<br />
					<legend>Venta del producto</legend>
					<section class="sectionMitad">
						<label for="text_descripcion">Cliente</label>
						<div>
							<s:textfield id="nombreCliente" readonly="true" key="nombreCliente" label="%{getText('trazabilidad.nombreCliente')}" cssStyle="width:400px;" value="%{#salidas.nombreCliente}" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_descripcion">NIF cliente</label>
						<div>
							<s:textfield id="nifCliente" readonly="true" key="nifCliente" label="%{getText('trazabilidad.nifCliente')}" cssStyle="width:400px;" value="%{#salidas.nifCliente}" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_descripcion">Albar&aacute;n</label>
						<div>
							<s:textfield id="codigoAlbaran" readonly="true" key="codigoAlbaran" label="%{getText('trazabilidad.codigoAlbaran')}" cssStyle="width:400px;" value="%{#salidas.codigoAlbaran}" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_descripcion">Fecha entrega</label>
						<div>
							<s:textfield id="fechaAlbaran" readonly="true" key="fechaAlbaran" label="%{getText('trazabilidad.fechaAlbaran')}" cssStyle="width:400px;" value="%{#salidas.fecha}" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_descripcion">Unidades</label>
						<div>
							<s:textfield id="cantidadUnitaria" readonly="true" key="cantidad" label="Unidades" cssStyle="width:400px;" value="%{#salidas.cantidad}" />
						</div>
					</section>
				</fieldset>
			</s:iterator>
		</fieldset>
		<fieldset id="fieldsetInformacionDevoluciones">
			<label><s:text name='trazabilidad.informacionDevoluciones' /></label>
			<s:iterator id="devoluciones" value="%{#session.devoluciones}">
				<s:hidden cssClass="informacionDevoluciones" />
				<fieldset>
					<br />
					<legend>Devoluci&oacute;n del producto del producto</legend>
					<section class="sectionTercio">
						<label for="text_descripcion">Cliente</label>
						<div>
							<s:textfield id="nombreClienteDevolucion" readonly="true" key="nombreClienteDevolucion" label="%{getText('trazabilidad.nombreClienteDevolucion')}" cssStyle="width:400px;" value="%{#devoluciones.nombreClienteDevolucion}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_codigo_devolucion">C&oacute;digo</label>
						<div>
							<s:textfield id="codigoEntrada" readonly="true" key="codigoEntrada" label="%{getText('trazabilidad.codigoEntrada')}" cssStyle="width:400px;" value="%{#devoluciones.codigoEntrada}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_codigo_cantidad">Cantidad</label>
						<div>
							<s:textfield id="cantidad" readonly="true" key="cantidad" label="%{getText('trazabilidad.cantidad')}" cssStyle="width:400px;" value="%{#devoluciones.cantidad}" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_codigo_devolucion">Incidencias</label>
						<div>
							<s:textfield id="notasIncidencias" readonly="true" key="notasIncidencias" label="%{getText('trazabilidad.notasIncidencias')}" cssStyle="width:400px;" value="%{#devoluciones.notasIncidencias}" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_fecha_devol">Fecha devolucion</label>
						<div>
							<s:textfield id="fechaLlegada" readonly="true" key="fechaLlegada" label="%{getText('trazabilidad.fechaLlegada')}" cssStyle="width:400px;" value="%{#devoluciones.fechaLlegada}" />
						</div>
					</section>
				</fieldset>
			</s:iterator>
		</fieldset>
		
		
		
		
		
		
	</s:form>
</s:i18n>
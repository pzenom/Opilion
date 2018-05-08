<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%@ page contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" language="java"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name='trazabilidad.titulo' /> <s:property value="#session.lote" /><span class="screenCode">TRAZA_II</span></h3>
	<s:form id="formulario" name="formulario" action="ActualizaProducto" validate="true" method="post" enctype="multipart/form-data">
		<s:iterator id="producto" value="%{#session.producto}">
			<fieldset>
				<label>Datos del producto</label>
				<section class="sectionTercio">
					<label for="text_id">ID</label>
					<div>
						<s:textfield id="text_id" key="idProducto" label="ID" readonly="true" />
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_ean">EAN</label>
					<div>
						<s:textfield id="text_ean" key="codigoEan" label="Codigo EAN" readonly="true" />
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_ean">Stock del producto</label>
					<div>
						<s:textfield id="text_stock" key="stock" label="Stock" readonly="true" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_nombre"><s:text name="registroCliente.nombre" /></label>
					<div>
						<s:textfield id="text_nombre" key="nombre" label="%{getText('registro.label.nombre')}" onkeypress="return validarSinComas(event);" readonly="true" />
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_descripcion">Descripci&oacute;n</label>
					<div>
						<s:textfield id="text_descripcion" key="descripcion" label="Descripcion" onkeypress="return validarSinComas(event);" readonly="true" />
					</div>
				</section>
			</fieldset>
		</s:iterator>
		<!-- modificaciones stock (07/10/2013) -->
			<fieldset id="fieldsetModificacionesStock">
				<label>Modificaciones stock <img src="img/warnings.png" height="24" width="24" alt="Modificaciones manuales del stock" title="Modificaciones manuales del stock" /></label>
				<s:iterator id="modificacion" value="%{#session.modificacionesStock}">
					<fieldset>
						<br />
						<legend>Modificaci&oacute;n de stock</legend>
						<section class="sectionTercio">
							<label for="text_cantidad">Incremento</label>
							<div>
								<s:textfield id="text_cantidad" readonly="true" key="incremento" label="Incremento" value="%{#modificacion.cantidad}" />
							</div>
						</section>
						<section class="sectionTercio">
							<label for="text_destino">Ubicacion</label>
							<div>
								<s:textfield id="text_destino" readonly="true" key="ubicacion" label="Ubicacion" value="%{#modificacion.descripcionHueco}" />
							</div>
						</section>
						<section class="sectionTercio">
							<label for="text_fecha">Fecha</label>
							<div>
								<s:textfield id="text_fecha" readonly="true" key="fecha" label="Fecha" value="%{#modificacion.fecha}" />
							</div>
						</section>
						<section class="sectionUnoDeTres">
							<label for="text_fecha_responsable">Responsable</label>
							<div>
								<s:textfield id="text_fecha_responsable" readonly="true" key="responsable" label="Responsable" value="%{#modificacion.responsable}" />
							</div>
						</section>
						<section class="sectionDosDeTres">
							<label for="text_causas">Causas</label>
							<div>
								<s:textarea id="text_causas" readonly="true" key="causas" label="Causas" value="%{#modificacion.causa}" />
							</div>
						</section>
					</fieldset>
				</s:iterator>
			</fieldset>
		<!-- end modificaciones stock -->
		<!-- Informacion Entrada-->
		<fieldset id="fieldsetInformacionEntrada">
			<label><s:text name='trazabilidad.informacionEntrada' /></label>
			<s:iterator id="entrada" value="%{#session.MP_EN_envasadoEntrada}">
				<fieldset>
					<br />
					<s:if test="%{#entrada.idTipoRegistro==#entrada.materia}">
						<legend><s:text name='trazabilidad.materiaPrima' /></legend>
					</s:if>
					<s:if test="%{#entrada.idTipoRegistro==#entrada.envase}">
						<legend><s:text name='trazabilidad.envase' /></legend>
					</s:if>
					<section class="sectionMitad">
						<label for="text_descripcion">Producto</label>
						<div>
							<s:textfield id="Producto" readonly="true" key="Producto" label="%{getText('trazabilidad.producto')}" cssStyle="width:400px;" value="%{#entrada.descProducto}" />
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_descripcion">Proveedor</label>
						<div>
							<s:textfield id="proveedor_%{#entrada.codigoEntrada}" readonly="true" key="Fecha llegada" label="%{getText('trazabilidad.fechaLlegada')}" cssStyle="width:400px;" value="%{#entrada.descProveedor}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_descripcion">C&oacute;digo entrada</label>
						<div>
							<s:textfield id="Codigo entrada" readonly="true" key="Codigo entrada" label="%{getText('trazabilidad.codigoEntrada')}" cssStyle="width:400px;" value="%{#entrada.codigoEntrada}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_descripcion">Fecha de llegada</label>
						<div>
							<s:textfield id="fechaLlegada_%{#entrada.codigoEntrada}" readonly="true" cssClass="fechas" key="Fecha llegada" label="%{getText('trazabilidad.fechaLlegada')}" cssStyle="width:400px;" value="%{#entrada.fechaLlegada}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_cantidad_entrada">Cantidad de entrada</label>
						<div>
							<s:textfield id="cantidad_entrada_%{#entrada.codigoEntrada}" readonly="true" label="Cantidad de entrada" cssStyle="width:400px;" value="%{#entrada.cantidad}" />
						</div>
					</section>
					<s:iterator id="incidencias" value="%{#entrada.incidencias}">
						<section class="sectionMitad">
							<label for="text_descripcion">Incidencia</label>
							<div>
								<s:textfield id="Incidencia" readonly="true" key="Incidencia" label="%{getText('trazabilidad.incidencia')}" cssStyle="width:400px;" value="%{#incidencias.nombre}" />
							</div>
						</section>
					</s:iterator>
				</fieldset>
			</s:iterator>
		</fieldset><!--end Informacion Entrada-->
		<!-- Informacion Procesos-->
		<fieldset id="fieldsetInformacionEnvasado">
			<label><s:text name='trazabilidad.informacionEnvasado' /></label>
			<s:iterator id="envasado" value="%{#session.envasado}">
				<s:hidden cssClass="informacionEnvasado" />
				<section class="sectionTercio">
					<label for="text_descripcion">Responsable</label>
					<div>
						<s:textfield id="Responsable" readonly="true" key="Responsable" label="%{getText('trazabilidad.responsable')}" cssStyle="width:180px;" value="%{#envasado.usuarioResponsable}" />
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_descripcion">Fecha</label>
					<div>
						<s:textfield id="fechaProceso_%{#envasado.orden}" readonly="true" key="Fecha" cssClass="fechas" label="%{getText('trazabilidad.fecha')}" cssStyle="width:180px;" value="%{#envasado.fecha}" />
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_descripcion">Cantidad envasada</label>
					<div>
						<s:textfield id="cantidad_envasada_%{#envasado.orden}" readonly="true" key="cantidadProducto" label="%{getText('trazabilidad.fecha')}" cssStyle="width:180px;" value="%{#envasado.resultado}" />
					</div>
				</section>
				<section class="sectionTercio">
					<label for="text_descripcion">Orden</label>
					<div>
						<s:textfield id="Orden" readonly="true" key="Orden" label="%{getText('trazabilidad.orden')}" cssStyle="width:180px;" value="%{#envasado.orden}" />
					</div>
				</section>
				<section class="sectionDosDeTres">
					<label for="text_descripcion">Observaciones</label>
					<div>
						<s:textfield id="Observaciones" readonly="true" key="Observaciones" label="%{getText('trazabilidad.observaciones')}" value="%{#envasado.observaciones}" />
					</div>
				</section>
			</s:iterator>
		</fieldset><!--end Informacion Procesos-->
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
		<fieldset id="fieldsetMovimientos">
			<label><s:text name='trazabilidad.movimientos' /></label>
			<s:iterator id="movimiento" value="%{#session.movimientos}">
				<fieldset>
					<br />
					<legend>Movimiento</legend>
					<section class="sectionTercio">
						<label for="text_origen">Origen</label>
						<div>
							<s:textfield id="text_origen" readonly="true" key="origen" label="Origen"  value="%{#movimiento.origen}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_destino">Destino</label>
						<div>
							<s:textfield id="text_destino" readonly="true" key="destino" label="Destino"  value="%{#movimiento.destino}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_cantidad_movimiento">Cantidad</label>
						<div>
							<s:textfield id="text_cantidad_movimiento" readonly="true" key="cantidad" label="Unidades" value="%{#movimiento.cantidad}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_fecha_movimiento">Fecha</label>
						<div>
							<s:textfield id="text_fecha_movimiento" readonly="true" key="fecha" label="%{getText('trazabilidad.fecha')}" value="%{#movimiento.fecha}" />
						</div>
					</section>
					<section class="sectionTercio">
						<label for="text_responsable_movimiento">Responsable</label>
						<div>
							<s:textfield id="text_responsable_movimiento" readonly="true" key="responsable" label="%{getText('trazabilidad.responsable')}" value="%{#movimiento.responsable}" />
						</div>
					</section>
				</fieldset>
			</s:iterator>
		</fieldset>
	</s:form>
</s:i18n>
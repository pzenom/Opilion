<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<sx:div name="ajax" id="ajaxDiv" theme="ajax">
		<!-- <p>&nbsp;</p> -->
		 <s:form target="ajaxDiv" id="formu" name="formu" action="EnvasadoAjax" validate="true" method="get">
			<s:select listValue="cantidad" id="eanCantidad" list="%{#session.listaEanCantidad}" value="cantidad" listKey="codigoEan" cssStyle="display: none;"/>
			<p>Lista de productos a envasar</p>
				<s:select onchange="javascript:seleccionProducto()" listValue="descripcion" id="productosEnvasar" list="%{#session.listaproductosenvasar}" value="idProducto" listKey="codigoEan"/>
				<!--<s:hidden name="productoSeleccionado" key="productoSeleccionado" value="%{#session.productoSeleccionado}"/>-->
			<s:submit theme="ajax" targets="ajaxDiv" value="Seleccionar producto"/>
		</s:form>
		<s:form id="formulario" name="formulario" action="InsertaOrdenEnv" validate="true" method="get">
			<!--<s:hidden name="productoSeleccionado" key="productoSeleccionado" value="%{#session.productoSeleccionado}"/>-->
			<!-- <ss:iterator id="productos" value="%{#session.listaproductosenvasar}" status="status"> -->
				<fieldset><!-- Filtro Campos-->
					<legend><span>Envasado del producto <s:property value="codigoEan" /></span></legend>
					<div value="" class="contenedorProducto" id="contenedorProducto_<s:property value="codigoEan"/>" class="contenedorProducto">
						<table class="tabla" width="100%" cellpadding="2" cellspacing="2" border="0">
							<tr>
								<td class="label"><s:label name="producto" value="Producto" cssStyle="text-align:right;"/></td>
								<td>
									<s:textfield onchange="javascript:actualizaCantidad()" id="productos" key="codigoEan" disabled="true" label="producto" cssStyle="width:500px;" value="%{#session.codigoEan}"/>
								</td>
							</tr>
							<tr>
								<td class="label"><s:label name="%{getText('registro.label.descripcion')}" value="%{getText('registro.label.descripcion')}" cssStyle="text-align:right;"/></td>
								<td>
									<s:textfield id="productos" key="descripcion" disabled="true" label="%{getText('registro.label.descripcion')}" cssStyle="width:500px;" value="%{#session.descripcion}"/>
								</td>
							</tr>
							<tr>
								<td class="label"><s:label name="%{getText('envasado.numeroLote')}" value="%{getText('envasado.numeroLote')}" cssStyle="text-align:right;"/></td>
								<td>
									<s:textfield id="nombre" key="nombre" disabled="true" label="%{getText('envasado.numeroLote')}" cssStyle="width:500px;"/>
								</td>
							</tr>
							<tr>
								<td class="label"><s:label name="%{getText('envasado.unidades')}" value="%{getText('envasado.unidades')}" cssStyle="text-align:right;"/></td>
								<td>
									<s:textfield key="cantidad" id="cantidad" cssStyle="width:180px;" label="%{getText('envasado.unidades')}" cssStyle="text-align:right;" value="%{#session.cantidad}"/>
									<s:hidden id="cantidadNecesaria" key="cantidadNecesaria" name="cantidadNecesaria" value="%{#session.cantidad}"/>
								</td>
							</tr>
							<tr>
								<td class="label">Fecha envasado
								</td>
								<td>
									<s:datetimepicker name="fechaLlegada" key="fechaLlegada" id="fechaLlegada" label="Format (dd/MM/yyyy)" displayFormat="dd/MM/yyyy" value="%{#session.fechaRegistro}"/>
								</td>
							</tr>
						</table>
						<!-- <table class="tabla" border="1" width="100%">
							<tbody>
							</tbody>
						</table> -->
						<div id="escogerIngredientes">
							<fieldset><!-- Filtro Campos-->
								<legend><span><s:text name="envasado.ingredientesDisponibles" /></span></legend>
								<table id="disponibleMP" class="tabla" border="1" width="70%">
								<thead>
									<tr>
										<th><s:text name="envasado.codigoEntrada" /></th>
										<th><s:text name="envasado.fechaEntrada" /></th>
										<th><s:text name="envasado.fechaCaducidad" /></th>
										<th><s:text name="envasado.lote" /></th>
										<th><s:text name="envasado.saldo" /></th>
										<th><s:text name="envasado.proveedor" /></th>
									</tr>
								</thead>
								<tbody id="escogerIngredientesBody">
								<s:iterator id="ingres" value="%{#session.listaregistroingredientes}">
									<s:hidden id="cantidad_%{#ingres.entrada}" name="cantidad_%{#ingres.entrada}" value="%{#ingres.teorica}" />
									<s:if test="%{#ingres.idLinea==#ingres.idAnterior}">
									</s:if>
									<s:else>
										<tr id="escogerIngredientesNuevo<s:property value='entrada' />">
											<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" ><s:text name="envasado.ingredienteEscoger" /><s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
										</tr>
									</s:else>
									<tr id="escogerIngredientes<s:property value='entrada' />">
										<td><s:property value="entrada" /></td>
										<td><s:property value="fecha" /></td>
										<td><s:property value="fechaCaducidad" /></td>
										<td id="loteMP<s:property value='entrada'/>"><s:property value="lote" /></td>
										<td id="saldo_<s:property value='entrada'/>"><s:property value="disponible" /></td>
										<td id="proveedorMP<s:property value='entrada'/>"><s:property value="proveedor" /></td>
										<td>
											<a id="bot_ingredientes" href="javascript:insertaMe('<s:property value="entrada" />')" title="Envasar estas unidades">
												<img src="img/anadir_01.gif" alt="Unidades a envasar " title="Unidades a envasar"/>
											</a>
										</td>
									</tr>
								</s:iterator>
								</tbody>
								</table>
							</fieldset>
						</div><!--escoger ingredientes-->
						<div id="electedIngredients">
							<table id="electedIngredientsTable" class="tabla" border="1" width="100%">
								<thead>
									<tr>
										<th class="destaca" rowspan=2><s:text name="envasado.ingredientes" /></th>
										<th class="destaca" colspan=4><s:text name="envasado.cantidadIngre" /></th>
										<th class="destaca" rowspan=2><s:text name="envasado.proveedor" /></th>
										<th class="destaca" rowspan=2><s:text name="envasado.loteAlbaran" /></th>
									</tr>
									<tr>
										<th><s:text name="envasado.disponible" /></th>
										<th><s:text name="envasado.teorica" /></th>
										<th><s:text name="envasado.real" /></th>
										<th><s:text name="envasado.mermas" /></th>
									</tr>
								</thead>
								<tbody id="electedIngredientsBody" >
								</tbody>
							</table>
						</div> <!-- electedIngredients-->
						<div id="escogerEnvases">
							<fieldset><!-- Filtro Campos-->
								<legend><span><s:text name="envasado.envasesDisponibles" /></span></legend>
								<table id="disponibleENV" class="tabla" border="1" width="70%">
								<thead>
									<tr>
										<th><s:text name="envasado.codigoEntrada" /></th>
										<th><s:text name="envasado.fechaEntrada" /></th>
										<th><s:text name="envasado.fechaCaducidad" /></th>
										<th><s:text name="envasado.lote" /></th>
										<th><s:text name="envasado.saldo" /></th>
										<th><s:text name="envasado.proveedor" /></th>
									</tr>
								</thead>
								<tbody id="escogerEnvasesBody">
								<s:iterator id="envax" value="%{#session.listaregistroenvases}">
									<s:hidden id="cantidad_%{#envax.entrada}" name="cantidad_%{#envax.entrada}" value="%{#envax.teorica}" />
									<s:if test="%{#envax.idLinea==#envax.idAnterior}">
									</s:if>
									<s:else>
										<tr id="escogerEnvasesNuevo<s:property value='entrada' />">
											<td colspan="6" style="text-align:center; background-color: #CEF6CE !important;" ><s:text name="envasadoAuto.envaseEscoger" />: <s:property value="nombre" /></td>
										</tr>
									</s:else>
									<tr id="escogerEnvases<s:property value='entrada' />">
										<td><s:property value="entrada" /></td>
										<td><s:property value="fecha" /></td>
										<td><s:property value="fechaCaducidad" /></td>
										<td id="loteEN<s:property value='entrada'/>"><s:property value="lote" /></td>
										<td id="saldo_<s:property value='entrada'/>"><s:property value="disponible" /></td>
										<td id="proveedorEN<s:property value='entrada'/>"><s:property value="idProveedor" /></td>
										<td>
											<a id="bot_envases" href="javascript:insertaENV('<s:property value="entrada" />')" title="Envasar estas unidades">
												<img src="img/anadir_01.gif" alt="Unidades a envasar " title="Unidades a envasar"/>
											</a>
										</td>
									</tr>
								</s:iterator>
								</tbody>
								</table>
							</fieldset>
						</div><!--escoger envases-->
						<div id="electedPackages">
							<table id="electedEnvasesTable" class="tabla" border="1" width="100%">
								<thead>
									<tr>
										<th class="destaca" rowspan=2><s:text name="envasado.envases" /></th>
										<th class="destaca" colspan=4><s:text name="envasado.cantidadEnvase" /></th>
										<th class="destaca" rowspan=2><s:text name="envasado.proveedor" /></th>
										<th class="destaca" rowspan=2><s:text name="envasado.loteAlbaran" /></th>
									</tr>
									<tr id="">
										<th><s:text name="envasado.disponible" /></th>
										<th><s:text name="envasado.teorica" /></th>
										<th><s:text name="envasado.real" /></th>
										<th><s:text name="envasado.mermas" /></th>
									</tr>
								</thead>
								<tbody id="electedEnvasesBody">
								</tbody>
							</table>
						</div><!-- electedPackages -->
						<div style="clear:left;">
							<p><s:text name="envasadoAuto.observaciones" /></p>
							<textarea id="explica" type="text" style="height: 70px; width: 100%;" name="explica"><s:text name="envasadoAuto.aclaracion" /></textarea><br>
							&nbsp;
						</div>
				</fieldset>
			<s:submit id="envasarTodo" theme="ajax" targets="ajaxDiv" value="Envasar"/>
		</s:form>
	</sx:div>
</s:i18n>
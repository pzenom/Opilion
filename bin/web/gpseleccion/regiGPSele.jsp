<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rsel.paso2" /></h3>
	<div id="divNecesarioWidget">
		<s:iterator id="entrada" value="%{#session.listaregistrosupd}">
			<s:form name="formulario" action="InseRESele" validate="true">
				<fieldset>
					<label>Datos registro de entrada original</label>
					<section class="sectionMitad">
						<label for="codigoEntrada">Entrada</label>
						<div>
							<s:textfield key="codigoEntrada" label="%{getText('entry.entrada')}" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="idOperario">Responsable</label>
						<div>
							<s:textfield key="idOperario" id="idOperario" label="%{getText('registro.label.responsable')}" value="%{#session.usuario.login}" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="fecha">Fecha</label>
						<div>
							<s:textfield id="fecha" key="fecha" value="%{#session.fecharegistro}" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="descProveedor">Proveedor</label>
						<div>
							<s:textfield id="descProveedor" key="descProveedor" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="lote">Lote</label>
						<div>
							<s:textfield id="lote" key="lote" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="origen">Origen</label>
						<div>
							<s:textfield id="origen" key="origen" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="albaran">Albar&aacute;n</label>
						<div>
							<s:textfield id="albaran" key="albaran" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="descCategoria">Categor&iacute;a</label>
						<div>
							<s:textfield id="descCategoria" key="descCategoria" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="descCategoriaEntrada">Categor&iacute;a de entrada</label>
						<div>
							<s:textfield id="descCategoriaEntrada" key="descCategoriaEntrada" readonly="true"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="cantidadOriginal">Cantidad original</label>
						<div>
							<s:textfield id="cantidadOriginal" key="saldo" readonly="true"/>
						</div>
					</section>
				</fieldset>
				<fieldset>
					<label>Configuraci&oacute;n de la selecci&oacute;n</label>
					<ul class="breadcrumb" data-connect="breadcrumbcontent">
						<li><a href="#">Cantidad</a></li>
						<li><a href="#">Ubicaci&oacute;n</a></li>
						<li><a href="#">Resultados</a></li>
					</ul>
					<div class="widget" id="widget_1">
						<h3 class="handle">Configuraci&oacute;n del proceso</h3>
						<div id="breadcrumbcontent">
							<div>
								<h3>Cantidad</h3>
								<p>
									Introduzca la cantidad que se va a seleccionar
									<fieldset>
										<section>
											<label for="cantidadSeleccionar">Cantidad</label>
											<div>
												<s:textfield id="cantidadSeleccionar" key="saldoregistro" />
											</div>
										</section>
									</fieldset>
									<!-- <button class="next">Siguiente</button> -->
								</p>
							</div>
							<div>
								<h3>Ubicaci&oacute;n</h3>
								<p>
									Seleccione la ubicaci&oacute;n de la que se va a sacar el registro de entrada
									<table id="electedIngredientsTable" class="tabla" border="1" width="100%">
										<thead>
											<tr>
												<th class="destaca" rowspan=2 style="width: 80px;">REG. ENTRADA</th>
												<th class="destaca">CANTIDAD (Kilos)</th>
											</tr>
										</thead>
										<tbody id="electedIngredientsBody">
											<s:iterator id="infomp" value="%{#session.ingredientes}">
												<s:if test="%{#infomp.idLinea == #infomp.idAnterior}">
												</s:if>
												<s:else>
													<tr id="escogerIngredientesNuevo<s:property value='idLinea' />">
														<td colspan="2" style="text-align:center; background-color: #CEF6CE !important;" >INGREDIENTE: <s:property value="nombre" /> - <s:property value="nombreCategoria" /></td>
														</tr>
												</s:else>
												<tr id="<s:property value='registroEntrada' />" class="ingrediente" style="font-size:14px;">
													<td class="entradaMateria"><s:property value="registroEntrada"/></td>
													<td id="cantidadMP_<s:property value="registroEntrada" />"><s:property value="disponible" /></td>
												</tr>
												<tr>
													<td><b>UBICACIONES</b></td>
													<td>Referencia del hueco</td>
													<td>Zona</td>
													<td>Linea</td>
													<td style="text-align: right;">Almacenado</td>
													<td style="text-align: right;">Cantidad a sacar</td>
												</tr>
												<s:iterator id="ubicacionesMp" value="%{#infomp.ubicaciones}" status="status">
													<tr style="font-size:14px;" id="<s:property value="registroEntrada" />_<s:property value="%{#status.count}" />" class="<s:property value="registroEntrada" />">
														<td style="background: transparent;"></td>
														<td style="width: 160px;"><s:property value="nombreHueco" /></td>
														<td style="width: 180px;"><s:property value="nombreZona" /></td>
														<td style="width: 200px;"><s:property value="nombreLinea" /></td>
														<td id="enHueco_<s:property value="registroEntrada" />" class="hueco" style="text-align: right; width: 90px;"><s:property value="cantidad" /></td>
														<td id="sacar_<s:property value="registroEntrada" />_<s:property value="%{#status.count}" />" style="text-align: right;">
															<s:textfield id="aSacar_%{#infomp.registroEntrada}" cssClass="sacar" key="sacar_%{#ubicacionesMp.idUbicacion}_%{#infomp.registroEntrada}" name="sacar_%{#ubicacionesMp.idUbicacion}_%{#infomp.registroEntrada}" value="0" cssStyle="width: 90px; text-align: right;"/>
														</td>
													</tr>
												</s:iterator>
											</s:iterator>
										</tbody>
									</table>
									<!-- <button class="prev">Anterior</button><button class="next">Siguiente</button> -->
								</p>
							</div>
							<div>
								<h3>Resultados</h3>
								<p>
									Seleccione las categorias de salida del proceso y las cantidades de cada categoria
									<table class="tabla" border="1">
										<thead>
											<tr>
												<th>Escoja</th>
												<th>Categor&iacute;a de salida</th>
												<th>Cantidad</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="cates" value="%{#session.listacategoriasseleccion}">
												<tr>
													<td>
														<input id="<s:property value="idCategoria" />" class="check" type="checkbox" name="listCateSele" value="<s:property value="idCategoria" />" />
													</td>
													<td><s:property value="nombreCategoria" /></td>
													<td>
														<s:textfield id="cantidad_%{#cates.idCategoria}" cssClass="cantidadSeleccionada" name="cantidad" key="cantidad" size="10" value="0" />
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
									<!-- <button class="prev">Anterior</button> -->
								</p>
							</div>
						</div>
					</div>
				</fieldset>
			</s:form>
		</s:iterator>
	</div><!-- end #divNecesarioWidget -->	
	<p>&nbsp;</p>
</s:i18n>
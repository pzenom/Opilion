<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rcrib.paso2" /><span class="screenCode">RCRIB_2</span></h3>
	<div id="divNecesarioWidget">
		<s:iterator id="entrada" value="%{#session.listaregistrosupd}">
			<div id="info" style="display: none;">
				<s:form name="formulario" action="InseRECrib" validate="true">
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
				</s:form>
			</div> <!-- end info -->
			<fieldset>
				<label>Configuraci&oacute;n del cribado</label>
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
								Introduzca la cantidad que se va a cribadoar
								<fieldset>
									<section>
										<label for="cantidadCribar">Cantidad</label>
										<div>
											<div>
												<div id="sliderCantidad" class="slider" data-connect="cantidadCribar" data-value="0" data-range="min" data-max="<s:property value="saldo"/>">
												</div>
												<input type="number" class="integer" id="cantidadCribar">
											</div>
										</div>
									</section>
								</fieldset>
								<!-- <button class="next">Siguiente</button> -->
							</p>
						</div>
						<div>
							<h3>Ubicaci&oacute;n</h3>
							<p>
								Cribadoe la ubicaci&oacute;n de la que se va a sacar el registro de entrada
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
												<!-- <td>Zona</td> -->
												<td>Linea</td>
												<td style="text-align: right;">Almacenado</td>
												<td style="text-align: right;">Cantidad a sacar</td>
											</tr>
											<s:iterator id="ubicacionesMp" value="%{#infomp.ubicaciones}" status="status">
												<tr style="font-size:14px;" id="<s:property value="registroEntrada" />_<s:property value="%{#status.count}" />" class="<s:property value="registroEntrada" />">
													<td style="background: transparent;"></td>
													<td style="width: 160px;"><s:property value="nombreHueco" /></td>
													<!-- <td style="width: 180px;"><s:property value="nombreZona" /></td> -->
													<td style="width: 200px;"><s:property value="nombreLinea" /></td>
													<td id="enHueco_<s:property value="registroEntrada" />" class="hueco" style="text-align: right; width: 90px;"><s:property value="cantidad" /></td>
													<td id="sacar_<s:property value="registroEntrada" />_<s:property value="%{#status.count}" />" style="text-align: right;">
														<section>
															<div>
																<div>
																	<div style="position: relative; float:left; width: 70%;" class="slider" data-connect="aSacar_<s:property value="registroEntrada" />" data-orientation="horizontal" data-range="min" data-max="<s:property value="cantidad" />">
																	</div>
																	<input id="aSacar_<s:property value="registroEntrada" />" class="sacar integer" key="sacar_<s:property value="idUbicacion" />_<s:property value="registroEntrada" />" name="sacar_<s:property value="idUbicacion" />_<s:property value="registroEntrada" />" value="0" style="width: 20%; text-align: right;" type="number" />
																</div>
															</div>
														</section>
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
								Cribadoe las categorias de salida del proceso y las cantidades de cada categoria
								<table id="tablaResultados" class="tabla" border="1" style="width: 66% !important;">
									<thead>
										<tr>
											<th style="width: 30%;">Categor&iacute;a de salida</th>
											<th>Cantidad</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator id="cates" value="%{#session.listacategoriascribado}">
											<tr>
												<td>
													<div style="position: relative; float: left;">
														<s:property value="nombreCategoria" />
													</div>
													<input id="<s:property value="idCategoria" />" class="check" type="checkbox" name="listCateCrib" value="<s:property value="idCategoria" />"/>
												</td>
												<td>
													<section style="width: 137%; ">
														<div>
															<div>
																<div class="slider" data-connect="cantidad_<s:property value="idCategoria" />" data-orientation="horizontal" data-range="min" data-max="<s:property value="saldo"/>" style="position: relative; float:left; width: 50%;">
																</div>
																<input id="cantidad_<s:property value="idCategoria" />" class="cantidadSeleccionada integer" key="cantidad" name="cantidad" value="0" style="position: relative; width: 90px; text-align: right; float:left;" type="number" />
															</div>
														</div>
													</section>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
								<!-- <button class="prev">Anterior</button> -->
							</p>
						</div>
					</div>
				</div>	<!-- end widget -->
			</fieldset>
		</s:iterator>
	</div><!-- end #divNecesarioWidget -->
</s:i18n>
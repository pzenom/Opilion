<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">&Oacute;rden cargada<span class="screenCode">EDIFACT_CARGADO</span></h3>
	<s:iterator id="pedido" value="%{#session.pedido}" >
					<s:form id="formulario" name="formulario" action="InseDetaAlba" validate="true">
						<fieldset>
							<table width="100%" cellpadding="2" cellspacing="2" border="0">
								<tr>
									<td class="label"><s:label name="%{getText('exit.pedido')}" value="%{getText('exit.pedido')}" cssStyle="text-align:right;"/></td>
									<td nowrap="nowrap">
										<s:textfield key="bgmNum" label="%{getText('exit.pedido')}" value="%{#pedido.bgmNum}" disabled="true"/>
									</td>

									<s:iterator id="dtm" value="%{dtm}" >
										<s:if test="%{dtmFunc == 137}">
										<th class="label"><s:label name="Fecha/Hora del Mensaje" value="Fecha/Hora del Mensaje" cssStyle="text-align:right;" /></th></s:if>
										<s:elseif test="%{dtmFunc == 2}"><td class="label"><s:label name="Fecha/Hora de Entrega Requerida" value="Fecha/Hora de Entrega Requerida" cssStyle="text-align:right;" /></td></s:elseif>
										<td nowrap="nowrap">
											<s:textfield key="fechaPedido" label="%{getText('exit.fechaPedido')}" value="%{#dtm.fech}" disabled="true"/>
										</td>
									</s:iterator>
								</tr>
								<tr>
									<td class="label"><s:label name="%{getText('exit.cliente')}" value="%{getText('exit.cliente')}" cssStyle="text-align:right;"/></td>
									<td>
										<s:textfield key="nadBy" label="%{getText('exit.cliente')}" value="%{nadBy}" disabled="true"/>
									</td>
								</tr>
								<tr>
									<td class="label"><s:label name="%{getText('exit.destino')}" value="%{getText('exit.destino')}" cssStyle="text-align:right;"/></td>
									<td nowrap="nowrap">
										<s:textfield key="nadBy" label="%{getText('exit.cliente')}" value="%{nadBy}" disabled="true"/>
									</td>

								</tr>
								<tr>
									<td nowrap="nowrap">
									</td>
								</tr>
							</table>
						</fieldset>
						<fieldset>
							<legend><span><s:text name="pedido.composicion" /></span></legend>
							<p>&nbsp;</p>
							<!-- Listado productos aniadidos -->
							<div id="demo">
									<table id="tablaProductos" cellpadding="0" cellspacing="0" border="0" class="display" >
										<thead>
											<tr>
												<th># Linea</th>
												<th>Producto</th>
												<th>Identificacion</th>
												<th>Cantidades solicitadas</th>
												<th>Precios</th>
												<th>Observaciones</th>
											</tr>
										</thead>
										<tbody id="tablaProductosBody">
											<s:iterator id="lin" value="%{lin}" >
												<tr>
													<!-- Id linea -->
													<td width="10%"><s:property value="%{linNum}" /></td>

													<!-- Producto -->
													<s:iterator id="imd" value="%{imd}" >
														<td>
															<s:property value="%{#imd.imdDesc}" />
														</td>
													</s:iterator>

													<!-- Identificador -->
													<td width="22%">
														EAN: <s:property value="%{idLin}" />
													</td>

													<!-- Cantidades -->
													<td width="15%">
														<p style="background: transparent !important;">Cantidad Pedida: <s:property value="%{qty21Cant}" /></p>
														<s:hidden cssClass="cantidades" key="cantidadPedida_%{linNum}" value="%{qty21Cant}"/>
														<p id="uExpedicion_<s:property value="%{linNum}" />" style="background: transparent !important;">U. De Expedici&oacute;n: <s:property value="%{qty59Cant}" /></p>
														<s:hidden cssClass="expediciones" key="uExpedicion_%{linNum}" value="%{qty59Cant}"/>
													</td>

													<!-- Precios -->
													<td width="16%">
														<p style="background: transparent !important;">Neto Unitario: <s:property value="%{priAaa}" /></p>
														<s:hidden key="netoUnitario_%{linNum}" value="%{priAaa}"/>
														<p style="background: transparent !important;">PVP: <s:property value="%{priInf}" /></p>
														<s:hidden key="pvp_%{linNum}" value="%{priInf}"/>
														<p style="background: transparent !important;">Neto Linea: <s:property value="%{moa203}" /></p>
														<s:hidden cssClass="netoLineas" key="netoLinea_%{linNum}" value="%{moa203}"/>
													</td>

													<!-- Observaciones -->
													<td>
														Observaciones:
														<textarea id="observaciones_<s:property value="%{linNum}" />" style="width:96%;"></textarea>
													</td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
							</div> <!-- end demo: Listado MP disponible-->
						<div id="totales">
							<table id="tablaTotales" cellpadding="0" cellspacing="0" border="0" class="tabla" >
								<thead>
									<tr>
										<th>Peso neto total</th>
										<th>Numero de bultos total</th>
										<th>Cantidad total</th>
										<th>Importe total</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td id="pesoNetoTotal"><s:property value="0.0" /></td>
										<td id="numBultosTotal"><s:property value="0" /></td>
										<td id="cantidadTotal"><s:property value="0" /></td>
										<td id="importeTotal"><s:property value="0.0" /></td>
									</tr>
								</tbody>
							</table>

						</div> <!-- end demo -->
						</fieldset>
					</s:form>
					</s:iterator>
					<div style="text-align:right;">
						<a id="insertarPedido" class="botonInserta" href="javascript:aceptar()" title="%{getText('pedido.aceptar')}">
							<img src="img/j_button_pill.png" alt="%{getText('pedido.aceptar')}" title="%{getText('pedido.aceptar')}"/><s:text name="pedido.aceptar" />
						</a>&nbsp;
					</div>
	</s:i18n>
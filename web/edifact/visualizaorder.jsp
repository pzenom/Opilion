<%@ page contentType="text/html; charset=iso-8859-15"
	pageEncoding="iso-8859-15" language="java"
	import="es.induserco.edifact.data.*,es.induserco.opilion.presentacion.*,java.util.Vector,java.sql.*,es.induserco.opilion.negocio.gestionentidades.*,es.induserco.opilion.data.entidades.*"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

	<s:i18n name="ApplicationResources">
		<h3 class="handle">&Oacute;rden cargada<span class="screenCode">EDIFACT_CARGADO</span></h3>

		  	<s:iterator id="pedido" value="%{#session.pedido}" >
				<s:form action="pagEscritorio.jsp" validate="true">
				
					<fieldset>
						<legend><span><s:text name="registro.edifact.orders.cabecera" /></span></legend>
						<table class="tabla" border=1 cellpadding="2" cellspacing="2" width="100%" >
							<tr>
								<th class="label"><s:label name="Numero de Orden" value="Numero de Orden" cssStyle="text-align:right;" /></th>
								<td nowrap="nowrap">
									<s:property value="bgmNum" />
								</td>
								<th class="label"><s:label name="Tipo Pedido" value="Tipo Pedido" cssStyle="text-align:right;" /></th>
								<td nowrap="nowrap">
									<s:property value="bgmTipo" />-
									<s:if test="%{bgmTipo == 220}">Pedido</s:if>
									<s:elseif test="%{bgmTipo == '22E'}">Propuesta de Pedido</s:elseif>
									<s:elseif test="%{bgmTipo == 227}">Pedido en Consigna</s:elseif>
									<s:elseif test="%{bgmTipo == 'YB1'}">Pedido de Cross Docking</s:elseif>
									<s:else>No Reconocido el Tipo</s:else>
									<s:if test="%{aliCond == '81E'}">Facturar pero no Enviar</s:if>
									<s:elseif test="%{aliCond == '82E'}">Enviar pero No Facturar</s:elseif>
								</td>
								<s:iterator id="dtm" value="%{dtm}" >
										<s:if test="%{dtmFunc == 137}">
										<th class="label"><s:label name="Fecha/Hora del Mensaje" value="Fecha/Hora del Mensaje" cssStyle="text-align:right;" /></th></s:if>
										<s:elseif test="%{dtmFunc == 2}"><td class="label"><s:label name="Fecha/Hora de Entrega Requerida" value="Fecha/Hora de Entrega Requerida" cssStyle="text-align:right;" /></td></s:elseif>
										<s:elseif test="%{dtmFunc == 63}"><td class="label"><s:label name="&Uacute;ltima Fecha / Hora de Entrega" value="&Uacute;ltima Fecha / Hora de Entrega" cssStyle="text-align:right;" /></td></s:elseif>
										<s:elseif test="%{dtmFunc == 64}"><td class="label"><s:label name="Primera Fecha / Hora de Entrega" value="Primera Fecha / Hora de Entrega" cssStyle="text-align:right;" /></td></s:elseif>
										<s:else><td>No Reconocido el Tipo</td></s:else>
										<td><s:property value="%{fech}" /></td>
								</s:iterator>
								
							</tr>
						</table>
						<hr />
						<table border=0 cellpadding="2" cellspacing="2" width="100%">
							<s:if test="%{nadMs != null}">
							<tr>
								<td <s:if test="%{rffMs != null}">rowspan="2"</s:if>>
									<s:label name="Emisor del Documento" value="Emisor del Documento" cssStyle="text-align:right;" />
									<td><s:label name="P.O." value="P.O." cssStyle="text-align:right;" /></td>
									<td>&nbsp;</td><td><s:property value="%{nadMs}" /></td>
								</td>
									<s:if test="%{rffMs != null}"><td><s:label name="Departamento" value="Departamento" cssStyle="text-align:right;" /></td></s:if>
							</tr>
							</s:if>
							<s:if test="%{nadMr != null}">
							<tr>
								<td <s:if test="%{rffMr != null}">rowspan="2"</s:if>>
									<s:label name="Receptor del Mensaje" value="Receptor del Mensaje" cssStyle="text-align:right;" />
									<td><s:label name="P.O." value="P.O." cssStyle="text-align:right;" /></td>
									<td>&nbsp;</td><td><s:property value="%{nadMr}" /></td>
								</td>
								<s:if test="%{rffMr != null}"><td><s:label name="Departamento" value="Departamento" cssStyle="text-align:right;" /></td></s:if>
							</tr>
							</s:if>
							<s:if test="%{nadSu != null}">
							<tr>
								<td <s:if test="%{rffSu != null}">rowspan="2"</s:if>>
									<s:label name="Proveedor" value="Proveedor" cssStyle="text-align:right;" />
									<td><s:label name="P.O." value="P.O." cssStyle="text-align:right;" /></td>
									<td>&nbsp;</td><td><s:property value="%{nadSu}" /></td>
								</td>
								<s:if test="%{rffSu != null}">
									<tr>
										<td><s:label name="Departamento" value="Departamento" cssStyle="text-align:right;" /></td>
										<td></td>
										<td><s:property value="%{rffSu}" /></td>
									</tr>
								</s:if>
							</tr>
							</s:if>
							<s:if test="%{nadBy != null}">
							<tr>
								<td <s:if test="%{rffBy != null}">rowspan="2"</s:if>>
									<s:label name="Comprador" value="Comprador" cssStyle="text-align:right;" />
								</td>
								<td><s:label name="P.O." value="P.O." cssStyle="text-align:right;" /></td>
								<td>&nbsp;</td><td><s:property value="%{nadBy}" /></td>
								<s:if test="%{rffBy != null}">
									<tr>
										<td><s:label name="Departamento" value="Departamento" cssStyle="text-align:right;" /></td>
										<td></td>
										<td><s:property value="%{rffBy}" /></td>
									</tr>
								</s:if>
							</tr>
							</s:if>
							<s:if test="%{nadDp != null}">
							<tr>
								<td <s:if test="%{rffDp != null}">rowspan="2"</s:if>>
									<s:label name="Receptor de Mercancías" value="Receptor de Mercancías" cssStyle="text-align:right;" />
									<td><s:label name="P.O." value="P.O." cssStyle="text-align:right;" /></td>
									<td>&nbsp;</td><td><s:property value="%{nadDp}" /></td>
								</td>
								<s:if test="%{rffDp != null}">
									<tr>
										<td><s:label name="Departamento" value="Departamento" cssStyle="text-align:right;" /></td>
										<td></td>
										<td><s:property value="%{rffDp}" /></td>
									</tr>
								</s:if>
							</tr>
							</s:if>
							<s:if test="%{nadIv != null}">
							<tr>
								<td <s:if test="%{rffIv != null}">rowspan="2"</s:if>>
									<s:label name="Receptor de la Factura" value="Receptor de la Factura" cssStyle="text-align:right;" />
									<td><s:label name="P.O." value="P.O." cssStyle="text-align:right;" /></td>
									<td>&nbsp;</td><td><s:property value="%{nadIv}" /></td>
								</td>
								<s:if test="%{rffIv != null}">
									<tr>
										<td><s:label name="Departamento" value="Departamento" cssStyle="text-align:right;" /></td>
										<td></td>
										<td><s:property value="%{rffIv}" /></td>
									</tr>
								</s:if>
							</tr>
							</s:if>
							<s:if test="%{nadPr != null}">
							<tr>
								<td <s:if test="%{rffPr != null}">rowspan="2"</s:if>>
									<s:label name="Emisor del Pago" value="Emisor del Pago" cssStyle="text-align:right;" />
									<td><s:label name="P.O." value="P.O." cssStyle="text-align:right;" /></td>
									<td>&nbsp;</td><td><s:property value="%{nadPr}" /></td>
								</td>
								<s:if test="%{rffPr != null}">
									<tr>
										<td><s:label name="Departamento" value="Departamento" cssStyle="text-align:right;" /></td>
										<td></td>
										<td><s:property value="%{rffPr}" /></td>
									</tr>
								</s:if>
							</tr>
							</s:if>
						</table>
						<s:if test="%{cux != null}">
							<hr />
							<table class="tabla" width="100%">
								<tr>
									<td><s:label name="Divisa de Referencia" value="Divisa de Referencia" cssStyle="text-align:right;" /></td>
									<td>:</td>
									<td><s:property value="%{cux}" /></td>
								</tr>
							</table>
						</s:if>
					</fieldset>	
					
					
					<fieldset>
						<legend><span><s:text name="registro.edifact.orders.detallePedido" /></span></legend>
						<s:iterator id="lin" value="%{lin}" >
						<fieldset>
						<table class="tabla" border="1" width="100%" style="border solid 1px">
							<tr>
									<th>Numero de Linea</th>
									<th>Identificaci&oacute;n</th>
									<th>Descripci&oacute;n</th>
									<th>Cantidades</th>
									<th>Precios</th>
									<th>Escoger producto</th>
							</tr>
							<tr>
									<td width="10%"><s:property value="%{linNum}" /></td>
									<td width="22%">
										<table class="tabla" border="0" width="95%">
											<tr>
												<td>C&oacute;digo EAN:</td>
												<td><s:property value="%{idLin}" /></td>
											</tr>
											<s:if test="%{piaNumSa != null}">
											<tr>
												<td>Ref. del Proveedor:</td>
												<td><s:property value="%{piaNumSa}" /></td>
											</tr>
											</s:if>
											<s:if test="%{piaNumIn != null}">
											<tr>
												<td>Ref. del Comprador:</td>
												<td><s:property value="%{piaNumIn}" /></td>
											</tr>
											</s:if>
										</table>
									</td>
									<td>
										<table class="tabla" border="0" width="95%">
										<s:iterator id="imd" value="%{imd}" >
											<tr>
												<td><s:property value="%{imdTipo}" /></td>
												<td>&nbsp;</td>
												<td><s:property value="%{imdCara}" /></td>
												<td>&nbsp;</td>
												<td><s:property value="%{imdDesc}" /></td>
												<td>&nbsp;</td>
												<td><s:property value="%{imdCodi}" /></td>
											</tr>
										</s:iterator>
										</table>
									</td>
									<td width="15%">
										<table class="tabla" border="0" width="95%">
											<tr>
												<td>Cantidad Pedida</td>
												<td><s:property value="%{qty21Cant}" /></td>
											</tr>
											<tr>
												<td>U. De Expedici&oacute;n</td>
												<td><s:property value="%{qty59Cant}" /></td>
											</tr>
										</table>
									</td>
									<td width="16%">
										<table class="tabla" border="0" width="95%">
											<tr>
												<td>Neto Unitario</td>
												<td><s:property value="%{priAaa}" /></td>
											</tr>
											<tr>
												<td>Bruto Unitario</td>
												<td><s:property value="%{priAab}" /></td>
											</tr>
											<tr>
												<td>PVP</td>
												<td><s:property value="%{priInf}" /></td>
											</tr>
											<tr>
												<td>Neto Linea</td>
												<td><s:property value="%{moa203}" /></td>
											</tr>
										</table>
									</td>
									<td width="16%">
										<a href="#" onClick="javascript:parent.get_ventana_emergente('regProv','ConsRegiENEan.action?codigoEan=<s:property value="%{idLin}" />','no','no','590','420','','');"><img src="img/note_add.png" alt="Enlazar producto" title="Enlazar producto" /></a>
									</td>
									
							</tr>
						</table>
						<fieldset>
							<legend><span>Notas espec&iacute;ficas para el art&iacute;culo</span></legend>
							<table border="0">
								<s:iterator id="loc" value="%{loc}" >
								<tr>
									<td>Entregar en</td>
									<td>&nbsp;</td>
									<td><s:property value="%{loc}" /></td>
									<td>&nbsp;&nbsp;&nbsp;-&nbsp;&nbsp;&nbsp;</td>
									<td>Cantidad</td>
									<td>&nbsp;</td>
									<td><s:property value="%{qty}" /></td>
								</tr>
								</s:iterator>
							</table>
						</fieldset>
						</fieldset>
						
						</s:iterator>
					</fieldset>
					
					<fieldset>
						<legend><span><s:text name="registro.edifact.orders.detallePie" /></span></legend>
						<table style="float:right">
							<tr>
								<td>
									<s:label name="Productos Pedidos" value="Productos Pedidos" cssStyle="text-align:right;" />
								</td>
								<td>
									<s:textfield key="cnt" label="cnt" />
								</td>
								<td>&nbsp;</td>
								<td>
									<s:label name="Importe Neto" value="Importe Neto" cssStyle="text-align:right;" />
								</td>
								<td>
									<s:textfield key="moa79" label="moa79" />
								</td>
							</tr>
						</table>
					</fieldset>
					
					<s:submit value="Generar Orden de Pedido"/>
				</s:form>	
			</s:iterator>
	</s:i18n>	

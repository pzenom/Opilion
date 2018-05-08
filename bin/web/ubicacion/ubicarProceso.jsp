<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Ubicacion de los resultados del proceso - UBICA_RESULT_PROCE</h3>
	<div id="divNecesarioWidget">
		<s:hidden id="procesoSeleccion" name="procesoSeleccion" value="%{#session.procesoSeleccion}"/>
		<fieldset>
			<table class="tabla">
				<tbody id="body_<s:property value="%{#status.count}"/>">
					<s:iterator id="ubica" value="%{#session.listaUbicar}" status="status">
						<s:hidden id="ubica_%{#ubica.grupo}_%{#ubica.lineaGrupo}" name="ubica_%{#ubica.grupo}_%{#ubica.lineaGrupo}" value="%{#ubica.idUbicacion}"/>
						<s:hidden id="cuanto_%{#ubica.grupo}" name="cuanto_%{#ubica.grupo}" value="%{#ubica.cantidadProducto}"/>
						<s:if test="%{#ubica.orden != #ubica.ordenAnterior}">
							<tr>
								<th style="font-size: 17px;" >Orden</th>
								<th style="font-size: 17px;" >Cantidad (Total orden: <s:property value="%{#ubica.cantidadProducto}"/>)</th>
								<th style="font-size: 17px;" >Ubicacion</th>
								<th style="font-size: 16px; background: none;" ></th>
							</tr>
							<tr id="fila_<s:property value="%{#ubica.grupo}"/>_<s:property value="%{#ubica.lineaGrupo}"/>" class="grupo_<s:property value="%{#ubica.grupo}"/>" style="height: 28px;" ubicado="NO">
								<td style="font-size: 20px;">
									<s:textfield cssStyle="text-align:right; font-size:20px;" value="%{#ubica.orden}" cssClass="orden" disabled="true" name="orden_%{#ubica.grupo}_%{#ubica.lineaGrupo}" id="orden_%{#ubica.grupo}_%{#ubica.lineaGrupo}"/>
								</td>
								<td style="font-size: 20px;">
									<s:textfield onkeyup="checkCantidad(%{#ubica.grupo}, %{#ubica.lineaGrupo});" cssStyle="text-align:right; font-size:20px; width:60px;" value="%{#ubica.cantidadUbicada}" cssClass="cantidadesUbicar_%{#ubica.grupo}" name="%{#ubica.grupo}_%{#ubica.lineaGrupo}" id="%{#ubica.grupo}_%{#ubica.lineaGrupo}"/>
								</td>
								<td style="font-size: 20px;">
									<s:textfield name="ubicacion_%{#ubica.grupo}_%{#ubica.lineaGrupo}" id="ubicacion_%{#ubica.grupo}_%{#ubica.lineaGrupo}" cssStyle="text-align:right; font-size:20px;" onclick = "javascript:ubicar('%{#ubica.orden}', %{#ubica.grupo}, %{#ubica.lineaGrupo});" value="%{#ubica.nombreHueco}" cssClass="ubicacionFinal"/>
								</td>
							</tr>
						</s:if>
						<s:else>
							<tr id="fila_<s:property value="%{#ubica.grupo}"/>_<s:property value="%{#ubica.lineaGrupo}"/>" style="height: 28px;" ubicado="NO" class="grupo_<s:property value="%{#ubica.grupo}"/>">
								<td style="font-size: 20px;">
									<s:textfield cssStyle="text-align:right; font-size:20px;" value="%{#ubica.orden}" cssClass="orden" disabled="true" name="orden_%{#ubica.grupo}_%{#ubica.lineaGrupo}" id="orden_%{#ubica.grupo}_%{#ubica.lineaGrupo}"/>
								</td>
								<td style="font-size: 20px;">
									<s:textfield onkeyup="checkCantidad(%{#ubica.grupo},%{#ubica.lineaGrupo});" cssStyle="text-align:right; font-size:20px; width:60px;" value="%{#ubica.cantidadUbicada}" cssClass="cantidadesUbicar_%{#ubica.grupo}" name="%{#ubica.grupo}_%{#ubica.lineaGrupo}" id="%{#ubica.grupo}_%{#ubica.lineaGrupo}"/>
								</td>
								<td style="font-size: 20px;">
									<s:textfield name="ubicacion_%{#ubica.grupo}_%{#ubica.lineaGrupo}" id="ubicacion_%{#ubica.grupo}_%{#ubica.lineaGrupo}" cssStyle="text-align:right; font-size:20px;" onclick = "javascript:ubicar('%{#ubica.orden}', %{#ubica.grupo}, %{#ubica.lineaGrupo});" value="%{#ubica.nombreHueco}" cssClass="ubicacionFinal"/>
								</td>
							</tr>
						</s:else>
						<s:if test="%{#ubica.ultimo == 1}">
							<tr>
								<td style="background-color: transparent;">
									<!-- <button class="i_plus icon yellow" onclick="javascript:addUbicacion('<s:property value="%{#ubica.orden}"/>', <s:property value="%{#ubica.grupo}"/>);">A&ntilde;adir direcci&oacute;n</button> -->
									<p>&nbsp;</p>
								</td>
							</tr>
						</s:if>
					</s:iterator>
				</tbody>
			</table>
		</fieldset>
	</div><!-- end #divNecesarioWidget -->
</s:i18n>
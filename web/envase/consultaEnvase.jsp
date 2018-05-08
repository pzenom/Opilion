<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Consultar envases<span class="screenCode">CONS_ENVA</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
			<s:form id="formulario" action="ConsultaEnvase" validate="true">
				<s:hidden id="idTipo" name="idTipo" key="idTipo" value="0" />
				<s:hidden id="idEnvase" name="idEnvase" value="0"/>
				<fieldset><!-- Informacion Basica-->
					<section>
						<label for="dropdown_materiales">Tipo de material</label>
						<select name="dropdown_materiales" id="dropdown_materiales" onchange="javascript:filtraEnvases();">
							<option value="0">Todos</option>
							<optgroup label="Tipo de material">
								<s:iterator id="tipos" value="%{#session.listamateriales}">
									<option value="<s:property value="idMaterial" />">
										<s:property value="nombre" />
									</option>
								</s:iterator>
							</optgroup>
						</select>
					</section>
				</fieldset><!--end Informacion Basica-->
			</s:form>
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="envases">
			<table id="tablaEnvases" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th><s:text name="envase.id"/></th>
						<th><s:text name="envase.nombre"/></th>
						<th><s:text name="envase.peso"/></th>
						<th><s:text name="envase.stock"/></th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="entry" value="%{#session.listaenvases}">
						<tr>
							<td style="width: 4%;"><s:property value="idEnvase" /></td>
							<td style="width: 70%;"><s:property value="nombre" /></td>
							<td style="width: 8%;"><s:property value="peso" /></td>
							<td style="width: 8%;">
								<span id="stock_<s:property value="idEnvase" />" class="numeroMilesDecimal"><s:property value="stock" /></span>
							</td>
							<td id="celdaCargarEnvase_<s:property value="idEnvase" />" class="celdaCargarEnvase" style="width: 4%; background: none repeat scroll 0 0 #FFF; vertical-align: middle; display: none;">
								<a href="javascript:cargarEnvase('<s:property value="idEnvase" />');"><img src="img/edit.png" alt="Editar" title="Editar"/></a>
							</td>
							<td id="celdaVerLotesEnvase_<s:property value="idEnvase" />" class="celdaVerLotesEnvase" style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 3%;">
								<a href="javascript:verLotesEnvase('<s:property value="idEnvase" />');"><img src="img/zoom.png" alt="Ver lotes" title="Ver lotes"/></a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div> <!-- DEMO -->
	</div> <!-- end divNecesarioWidget -->
</s:i18n>
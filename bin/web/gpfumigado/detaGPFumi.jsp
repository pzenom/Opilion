<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="rfum.paso2" /> - <s:text name="insenv.envasesdos" /></h3>
	<div id="divNecesarioWidget">
		<s:form action="InseGPFumi" validate="true">
			<fieldset><!-- Información Básica-->
				<label>Informaci&oacute;n basica</label>
				<section class="sectionMitad">
					<label for="orden">Orden</label>
					<div>
						<s:textfield key="orden" disabled="true"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="Fecha">Fecha</label>
					<div>
						<s:textfield key="fecha" value="%{#session.fecharegistro}" disabled="true"/>
					</div>
				</section>
				<section class="sectionMitad">
					<label for="Hora de inicio">Hora de inicio</label>
					<div>
						<s:textfield key="horaInicioProceso" value="%{#session.horaInicioProceso}" disabled="true"/>
					</div>
				</section>
				<div id="demo">
					<table id="tablaDetaFumi" cellpadding="0" cellspacing="0" border="0" class="display">
						<thead>
							<tr>
								<th style="display: none;">Seleccione</th>
								<th>C&oacute;digo de entrada</th>
								<th>Descripci&oacute;n</th>
								<th>Cantidad total (Kg.)</th>
								<th>Cantidad a fumigar (Kg.)</th>
								<th>Mermas</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="ingred" value="%{#session.subentradasproceso}">
								<tr>
									<td style="display: none;"><input type="checkbox" checked="true" name="listProcSele" value=<s:property value="proceso" />/></td>
									<td><s:property value="proceso" /></td>
									<td><s:property value="descEnvase" /></td>
									<td><s:property value="cantidadProducto" /></td>
									<td><s:property value="saldoProducto" /></td>
									<td>
										<s:textfield id="%{#ingred.proceso}" cssClass="mermas" key="cantidad" size="10" value="0"/>
									</td>
									<!-- <td>
										<a href="CargarSubRegistro.action?codigoEntrada=<s:property value="codigoEntrada" />"><img src="img/note_edit.png" alt="Ver Registro" title="Ver Registro"/></a>
									</td> -->
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div> <!-- end demo -->
			</fieldset><!--end product info-->
		</s:form>
		</div> <!-- end divNecesarioWidget -->
	<p>&nbsp;</p>
</s:i18n>
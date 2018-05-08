<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Backup</h3>
	<div id="divNecesarioWidget">
		<div id="historicoCopias">
			<s:form>
				<fieldset>
					<label>Hist&oacute;rico de backups</label>
					<table class="tabla">
						<thead>
							<tr>
								<th style="font-size: 17px;">Nombre backup</th>
								<th style="font-size: 17px;">Fecha</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="historico" value="%{#session.historico}">
								<tr class="copiaSeguridad">
									<td style="font-size: 15px; padding: 8px;"><s:property value="nombre" /></td>
									<td style="font-size: 15px; padding: 8px;"><s:property value="fecha" /></td>
									<td style="padding: 10px;">
										<a href="./backups/<s:property value="nombre" />" target="_blank"><img src="img/disk.png" alt="Ver fichero SQL" title="Ver fichero SQL"/></a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</fieldset>
			</s:form>
		</div> <!-- end historicoCopias -->
		<button class="i_arrow_left icon verdeOpilion" onclick="javascript:volverAlEscritorio();">Volver</button>
		<button class="i_plus icon verdeOpilion" onclick="javascript:hacerBackup();">Hacer copia de seguridad</button>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>
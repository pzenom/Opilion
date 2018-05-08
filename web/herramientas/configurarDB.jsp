<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Configurar DB</h3>
	<div id="divNecesarioWidget">
		<s:form name="formu" action="SalvarCambiosDB">
			<fieldset>
				<label>Campos configurables</label>
				<table width="100%" cellpadding="2" cellspacing="2" border="0">
					<s:iterator id="config" value="%{#session.config}">
						<tr>
							<th><s:label name="Host" value="Host" cssStyle="text-align:right;"/></th>
							<td>
								<s:textfield id="host" key="host" cssStyle="width: 300px;" />
							</td>
						</tr>
						<tr>
							<th><s:label name="Puerto" value="Puerto" cssStyle="text-align:right;"/></th>
							<td>
								<s:textfield id="puerto" key="puerto" cssStyle="width: 300px;" />
							</td>
						</tr>
						<tr>
							<th><s:label name="Nombre BD" value="Nombre BD" cssStyle="text-align:right;"/></th>
							<td>
								<s:textfield id="db" key="db" cssStyle="width: 300px;" />
							</td>
						</tr>
						<tr>
							<th><s:label name="Ruta de la aplicacion" value="Ruta de la aplicacion" cssStyle="text-align:right;"/></th>
							<td>
								<s:textfield id="filePath" key="filePath" cssStyle="width: 300px;" />
							</td>
						</tr>
						<tr>
							<th><s:label name="Ruta del proceso (mysqldump)" value="Ruta del proceso (mysqldump)" cssStyle="text-align:right;"/></th>
							<td>
								<s:textfield id="rutaProceso" key="rutaProceso" cssStyle="width: 300px;" />
							</td>
						</tr>
						<tr>
							<th><s:label name="Directorio de almacenamiento de las copias" value="Directorio de almacenamiento de las copias" cssStyle="text-align:right;"/></th>
							<td>
								<s:textfield id="rutaBackups" key="rutaBackups" cssStyle="width: 300px;" />
							</td>
						</tr>
						<tr>
							<th><s:label name="Usuario" value="Usuario" cssStyle="text-align:right;"/></th>
							<td>
								<s:textfield id="user" key="user" cssStyle="width: 300px;" />
							</td>
						</tr>
						<tr>
							<th><s:label name="Contraseña" value="Contraseña" cssStyle="text-align:right;"/></th>
							<td>
								<s:password id="pass" key="pass" cssStyle="width: 300px;"/>
							</td>
						</tr>
					</s:iterator>
				</table>
			</fieldset>
		</s:form>
		<button class="i_arrow_left icon verdeOpilion" onclick="javascript:volver();">Volver</button>
		<button class="i_plus icon verdeOpilion" onclick="javascript:procederCambios();">Proceder</button>
		<button class="i_plus icon verdeOpilion" onclick="javascript:valoresPredeterminados();">Valores predeterminados</button>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>
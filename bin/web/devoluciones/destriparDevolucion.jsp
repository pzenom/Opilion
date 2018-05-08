<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle">Reaprovechamiento de devoluci&oacute;n<span class="screenCode">DEV02</span></h3>
	<div id="divNecesarioWidget">
		<div id="consulta" style="display: none;">
		</div> <!-- end consulta -->
		<p>&nbsp;</p>
		<div id="demo">
			<table id="tablaDestripado" cellpadding="0" cellspacing="0" border="0" class="display" style="display: none;">
				<thead>
					<tr>
						<th style="width: 60px;">Producto</th>
						<th style="width: 60px;">Lote</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator id="productos" value="%{#session.productos}">
						<tr>
							<td><s:property value="nombre" /></td>
							<td><s:property value="lote" /></td>
							<td style="background: none repeat scroll 0 0 #FFF; vertical-align: middle; width: 30px;">
								<a id="actualiza" href="javascript:reaprovechar('<s:property value="lote" />')" title="Reaprovechar">
									<img src="img/pill_go.png" alt="Pildora con flecha para reaprovechar" title="Reaprovechar"/>
								</a>
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<p>&nbsp;</p>
		</div>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>
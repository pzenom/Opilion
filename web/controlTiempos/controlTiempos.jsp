<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<h3 class="handle"><s:text name="controlTiempos.bienvenida" /><span class="screenCode">CONTROL_TIEMPOS</span></h3>
	<div id="divNecesarioWidget">
		<section class="sectionCuarto">
			<label for="text_fechaCaducidad">Fecha de caducidad</label>
			<div>
			<div>
				<input id="text_fechaCaducidad" name="text_fechaCaducidad" type="text" value="<s:property value="%{#session.fechaCaducidad}"/>">
				<input id="text_proceso" style="display: none;" type="text" value="<s:property value="%{#session.proceso}"/>">
			</div>
		</section>
		<s:form id="listado" name="listado" action="#" validate="true" method="get">
			<fieldset><!-- Informacion Basica -->
				<label><s:text name='tiempos.caption.tiemposTotales' /></label>
				<section class="sectionMitad">
					<label for="text_horasDedicadas">Horas dedicadas</label>
					<div>
						<input id="text_horasDedicadas" readonly="true" name="text_horasDedicadas" type="text" value="<s:property value="%{#session.horas}"/>">
					</div>
				</section>
				<section class="sectionMitad">
					<label for="text_lote">Minutos dedicados</label>
					<div>
					<div>
						<input id="text_minutosDedicados" readonly="true" name="text_minutosDedicados" type="text" value="<s:property value="%{#session.minutos}"/>">
					</div>
				</section>
			</fieldset><!--end Informacion Basica-->
		</s:form>
		<s:iterator id="registros" value="%{#session.registros}" status="status">
			<s:form>
				<fieldset><!-- Informaciones varias -->
					<label><span style="font-size: 15px;"><b><s:property value="#status.count" /></b></span> - <s:text name='tiempos.caption.registros' /></label>
					<section class="sectionMitad">
						<label for="text_horaInicio">Hora inicio</label>
						<div>
							<s:textfield id="text_horaInicio" readonly="true" key="nombre" label="%{getText('tiempos.hora')}" value="%{#registros.horaInicio}"/>
						</div>
					</section>
					<section class="sectionMitad">
						<label for="text_responsable">Usuario responsable</label>
						<div>
							<s:textfield id="text_responsable" readonly="true" key="nombre" label="%{getText('tiempos.responsable')}" value="%{#registros.responsable}"/>
						</div>
					</section>
					<section>
						<label for="text_observaciones">Observaciones</label>
						<div>
							<s:textfield id="text_observaciones" readonly="true" key="nombre" label="%{getText('tiempos.observacion')}" value="%{#registros.observaciones}"/>
						</div>
					</section>
				</fieldset><!-- end Informaciones varias -->
			</s:form>
		</s:iterator>
	</div> <!-- end divNecesarioWidget -->
</s:i18n>
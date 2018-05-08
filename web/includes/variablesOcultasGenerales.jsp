<%@ taglib uri="/struts-tags" prefix="s"%>
<s:i18n name="ApplicationResources">
	<s:iterator id="rolesUsuario" value="%{#session.rolesUsuario}" >
		<s:hidden id="rolUsuario_%{#rolesUsuario.idRol}" cssClass="rolUsuario" />
	</s:iterator>
	<s:hidden id="rolUsuario" name="rolUsuario" key="rolUsuario" value="%{#session.rolUsuario}"/>
	<!-- VARIABLES OCULTAS PARA SABER A DONDE IR CON EL BOTON VOLVER -->
	<s:hidden id="vuelveEscritorio" value="true" />
	<!-- VARIABLES OCULTAS PARA GESTION DE ALMACENES, UBICACIONES... -->
	<s:hidden name="productoSeleccionado" key="productoSeleccionado" value="%{#session.productoSeleccionado}"/>
	<s:hidden id="sacar" key="sacar" name="sacar" value="false"/>
	<s:hidden id="incidencia" key="incidencia" name="incidencia" value="false"/>
	<s:hidden id="meter" key="meter" name="meter" value="false"/>
	<s:hidden id="mover" key="mover" name="mover" value="false"/>
	<s:hidden id="devolucion" key="devolucion" name="devolucion" value="false"/>
	<s:hidden id="idPalet" name="idPalet" key="idPalet" value="%{#session.idPalet}"/>
	<s:hidden id="idLineaZona" key="idLineaZona" name="idLineaZona"/>
	<s:hidden id="idAlmacen" key="idAlmacen" name="idAlmacen" value="%{#session.idAlmacen}"/>
	<s:hidden id="cuantosPalets" key="cuantosPalets" name="cuantosPalets" value="%{#session.numeroPalets}"/>
	<s:hidden id="reubicar" key="reubicar" name="reubicar" value="false"/>
	<s:hidden id="seleccion" key="seleccion" name="seleccion" value="%{#session.seleccion}"/>
	<s:hidden id="ubicar" key="ubicar" name="ubicar" value="false"/>
	<s:hidden id="idEnvasado" key="idEnvasado" name="idEnvasado" value="%{#session.idEnvasado}"/>
	<s:hidden id="orden" key="orden" name="orden" value="%{#session.orden}"/>
	<s:hidden id="procesoSeleccion" key="procesoSeleccion" name="procesoSeleccion" value="%{#session.procesoSeleccion}"/>
	<s:hidden id="tipoProceso" key="tipoProceso" name="tipoProceso" value="%{#session.tipoProceso}"/>
	<s:hidden id="estado" key="estado" name="estado" value="%{#session.estado}"/>
	<s:hidden id="lote" key="lote" name="lote" value="%{#session.lote}"/>
	<s:hidden id="idUbicacion" key="idUbicacion" name="idUbicacion" value="%{#session.idUbicacion}"/>
	<s:hidden id="seleccionado" key="seleccionado"/>
	<s:hidden id="ubicacionBigBag" key="ubicacionBigBag"/>
	<s:hidden id="idHueco" name="idHueco" key="idHueco" cssClass="idHueco" value="%{#session.idHueco}"/>
	<s:hidden id="nombreHueco" name="nombreHueco" key="nombreHueco" cssClass="nombreHueco"/>
	<s:hidden id="cantidad" name="cantidad" key="cantidad" value="%{#session.cantidad}"/>
	<s:hidden id="numeroBultos" key="numeroBultos" name="numeroBultos" value="%{#session.numeroBultos}"/>
	<s:hidden id="gestionBultos" name="gestionBultos" key="gestionBultos" value="%{#session.gestionBultos}"/>
	<s:hidden id="idZona" key="idZona" name="idZona" value="%{#session.idZona}"/>
	<s:hidden id="idLinea" key="idLinea" name="idLinea" value="%{#session.idLinea}"/>
	<s:hidden id="idEstanteria" key="idEstanteria" name="idEstanteria" value="%{#session.idEstanteria}"/>
	<s:hidden id="idPiso" key="idPiso" name="idPiso" value="%{#session.idPiso}"/>
	<s:hidden id="registro" key="registro" name="registro" value="%{#session.registro}"/>
	<s:hidden id="idTipoRegistro" key="idTipoRegistro" name="idTipoRegistro" value=""/>
</s:i18n>
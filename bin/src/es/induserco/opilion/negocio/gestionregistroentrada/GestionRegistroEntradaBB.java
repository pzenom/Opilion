package es.induserco.opilion.negocio.gestionregistroentrada;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import es.induserco.opilion.data.entidades.Bulto;
import es.induserco.opilion.data.entidades.Ciclo;
import es.induserco.opilion.data.entidades.Mantenimiento;
import es.induserco.opilion.data.entidades.Maquina;
import es.induserco.opilion.data.entidades.RegistroCalidad;
import es.induserco.opilion.data.entidades.RegistroEntrada;
import es.induserco.opilion.data.entidades.RegistroOrden;
import es.induserco.opilion.data.entidades.MovimientoVehiculo;
import es.induserco.opilion.data.entidades.TipoMantenimiento;
import es.induserco.opilion.data.entidades.TipoMaquina;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.negocio.RegistroEntradaDataHelper;
import es.induserco.opilion.negocio.UbicacionDataHelper;

public class GestionRegistroEntradaBB implements IGestionRegistroEntradaService{

	//@Override
	public String getCodigoOrden() throws Exception {
		return new RegistroEntradaDataHelper().getCodigoOrden();
	}		

	//@Override
	public Boolean addROMovimientoVehiculo(MovimientoVehiculo entry) throws Exception {
		return new RegistroEntradaDataHelper().addROMovimientoVehiculo(entry);
	}	
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#addRegistroOrden(es.induserco.opilion.data.entidades.RegistroOrden)
	 */
	//@Override
	public Boolean addRegistroOrden(RegistroOrden entry) throws Exception {
		return new RegistroEntradaDataHelper().addRegistroOrden(entry);
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#addRegistroEntrada(es.induserco.opilion.data.entidades.RegistroEntrada, java.util.List, java.util.List)
	 */
	//@Override
	public Boolean addRegistroEntrada(RegistroEntrada entry,List listindic,List listestados) throws Exception {
		return new RegistroEntradaDataHelper().addRegistroEntrada(entry,listindic,listestados);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#updateRegistroEntrada(es.induserco.opilion.data.entidades.RegistroEntrada, es.induserco.opilion.data.entidades.RegistroEntrada, java.util.List, java.util.List)
	 */
	//@Override
	public Boolean updateRegistroEntrada(RegistroEntrada entryf,RegistroEntrada entryu,List listindic,List listestados) throws Exception {
		return new RegistroEntradaDataHelper().updateRegistroEntrada(entryf,entryu,listindic,listestados);
	}

	//@Override
	public Boolean deleteRegistroEntrada(RegistroEntrada entryd) throws Exception {
		return new RegistroEntradaDataHelper().deleteRegistroEntrada(entryd);
	}

	//@Override
	public Vector getOrdenes() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector ordenes = edh.getOrdenes();
		return ordenes;
	}	
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getOrdenes(es.induserco.opilion.data.entidades.RegistroOrden)
	 */
	//@Override
	public Vector <RegistroOrden> getOrdenes(RegistroOrden orden, int filtro) throws Exception {	
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector <RegistroOrden>ordenes = edh.getOrdenes(orden, filtro);
		return ordenes;
	}		

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getRegistrosEntradaOrden(java.lang.String)
	 */
	//@Override
	public Vector getRegistrosEntradaOrden(String codigoOrden) throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector ordenes = edh.getRegistrosEntradaOrden(codigoOrden);
		return ordenes;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#loadRegistroEntrada(es.induserco.opilion.data.entidades.RegistroEntrada)
	 */
	//@Override
	public RegistroEntrada loadRegistroEntrada(RegistroEntrada regEntrada) throws Exception{
		return new RegistroEntradaDataHelper().loadRegistroEntrada(regEntrada);
	}	
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getProveedores()
	 */
	//@Override
	public Vector getProveedores() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector proveedores = edh.getProveedores();
		return proveedores;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getTransportistas()
	 */
	//@Override
	public Vector getTransportistas() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector proveedores = edh.getTransportistas();
		return proveedores;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getProductos(java.lang.String)
	 */
	//@Override
	public Vector getProductos(String filtro) throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector producto = edh.getProductos(filtro);
		return producto;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getProductos()
	 */
	//@Override
	public Vector getProductos() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector producto = edh.getProductos();
		return producto;
	}	

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getEnvases()
	 */
	//@Override
	public Vector getEnvases() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector producto = edh.getEnvases();
		return producto;
	}	
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getCategorias()
	 */
	//@Override
	public Vector getCategorias(String codigoEntrada) throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector categoria = edh.getCategorias(codigoEntrada);
		return categoria;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getVehiculos()
	 */
	//@Override
	public Vector getVehiculos() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector vehiculos = edh.getVehiculos();
		return vehiculos;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getIncidencias()
	 */
	//@Override
	public Vector getIncidencias() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector incidencias = edh.getIncidencias();
		return incidencias;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getFechaRegistro()
	 */
	//@Override
	public String getFechaRegistro() throws Exception {
		return new RegistroEntradaDataHelper().getFechaRegistro();
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getFechaCaducidad()
	 */
	//@Override
	public String getFechaCaducidad() throws Exception {
		return new RegistroEntradaDataHelper().getFechaCaducidad();
	}
	
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getRegistroEntradas(java.lang.String, java.util.Date, java.lang.Long)
	 */
	//@Override
	public Vector getRegistroEntradas(String codigoEntrada, Date fecha,
			Long idProducto) throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector entradas = edh.getRegistroEntradas(codigoEntrada, fecha, idProducto);
		return entradas;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getFormatos()
	 */
	//@Override
	public Vector getFormatos() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector formatos = edh.getFormatos();
		return formatos;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getOperarios()
	 */
	//@Override
	public Vector getOperarios() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector operarios = edh.getOperarios();
		return operarios;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getEstadosFabas()
	 */
	//@Override
	public Vector getEstadosFabas() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector estados = edh.getEstadosFabas();
		return estados;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#addAnalisisCalidadRegistro(es.induserco.opilion.data.entidades.RegistroEntrada, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long, double, java.lang.String)
	 */
	//@Override
	public boolean addAnalisisCalidadRegistro(RegistroEntrada entry,
			Long varIGL, Long varSPL, Long varDPL, Long varDAL, Long varML,
			double calidad, String baremoCalidad) throws Exception {
		return new RegistroEntradaDataHelper().addAnalisisCalidadRegistro(entry,
				varIGL, varSPL, varDPL, varDAL, varML,
				calidad, baremoCalidad);
	}	

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getCosechas()
	 */
	//@Override
	public Vector getCosechas() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector cosechas = edh.getCosechas();
		return cosechas;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#addRegistroSubEntrada(es.induserco.opilion.data.entidades.RegistroEntrada, es.induserco.opilion.data.entidades.RegistroEntrada, java.util.List, java.util.List)
	 */
	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind, RegistroEntrada entry,List listindic,List listestados) throws Exception {
		return new RegistroEntradaDataHelper().addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados);
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getTipoUbicaciones()
	 */
	//@Override
	public Vector getTipoUbicaciones() throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector tipoUbicaciones = edh.getTipoUbicaciones();
		return tipoUbicaciones;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getIncidenciasFiltradas(int)
	 */
	//@Override
	public Vector getIncidenciasFiltradas(int filtro) throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector incidencias = edh.getIncidenciasFiltradas(filtro);
		return incidencias;
	}

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getCategoriasFiltradas(int)
	 */
	//@Override
	public Vector getCategoriasFiltradas(int filtro) throws Exception {
		RegistroEntradaDataHelper edh = new RegistroEntradaDataHelper();
		Vector incidencias = edh.getCategoriasFiltradas(filtro);
		return incidencias;
	}

	//@Override
	public Boolean addRegistroSubEntrada(RegistroEntrada regEntradaFind, RegistroEntrada entry,List listindic,List listestados,String tipoProceso) throws Exception {
		return new RegistroEntradaDataHelper().addRegistroSubEntrada(regEntradaFind,entry,listindic,listestados,tipoProceso);
	}

	//@Override
	public String addRegistrosSubEntradaProceso(RegistroEntrada regEntradaFind, Map mapaCategorias,String proceso,Double cantidadProceso) throws Exception {	
		return new RegistroEntradaDataHelper().addRegistrosSubEntradaProceso(regEntradaFind, mapaCategorias,proceso,cantidadProceso);
	}		

	//@Override
	public Double getSaldoRegistro(String codigoEntrada) throws Exception {	
		return new RegistroEntradaDataHelper().getSaldoRegistro(codigoEntrada);
	}	

	//@Override
	public Vector getREENProducto(Long idProducto) throws Exception{	
		return new RegistroEntradaDataHelper().getREENProducto(idProducto);
	}	

	//@Override
	public Vector getREENProducto(String codigoEan) throws Exception{	
		return new RegistroEntradaDataHelper().getREENProducto(codigoEan);
	}	

	//@Override
	public Vector getREENDetalle(String codigoEntrada) throws Exception{	
		return new RegistroEntradaDataHelper().getREENDetalle(codigoEntrada);
	}		

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#getEtiquetaRE(java.lang.String)
	 */
	//@Override
	public RegistroEntrada getEtiquetaRE(String codigoEntrada) throws Exception{	
		return new RegistroEntradaDataHelper().getEtiquetaRE(codigoEntrada);
	}	
	
	//Maquinaria
	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#addMQ(es.induserco.opilion.data.entidades.Maquina)
	 */
	//@Override
	public Boolean addMQ(Maquina maquina) throws Exception{
		return new RegistroEntradaDataHelper().addMQ(maquina);
	}		

	/* (non-Javadoc)
	 * @see es.induserco.opilion.negocio.gestionregistroentrada.IGestionRegistroEntradaService#addMT(es.induserco.opilion.data.entidades.Mantenimiento)
	 */
	//@Override
	public Boolean addMT(Mantenimiento mant) throws Exception{
		return new RegistroEntradaDataHelper().addMT(mant);
	}	

	//@Override
	public Vector<TipoMantenimiento> getTipoMant() throws Exception{	
		return new RegistroEntradaDataHelper().getTipoMant();
	}		

	//@Override
	public Vector<Maquina> getMaquinas(long idTipo, long idMaquina, String fecha) throws Exception{	
		return new RegistroEntradaDataHelper().getMaquinas(idTipo,idMaquina, fecha);
	}

	//@Override
	public Vector<Mantenimiento> getRegistrosMT(long idTipoMant,long idMaquina,String fechaConsulta)throws Exception{	
		return new RegistroEntradaDataHelper().getRegistrosMT(idTipoMant,idMaquina,fechaConsulta);
    }

	//@Override
	public Boolean getOrdenEntradaTemporal(RegistroEntrada registroEntrada) throws Exception {
		return new RegistroEntradaDataHelper().getOrdenEntradaTemporal(registroEntrada);
	}

	//@Override
	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada, List listindic, List listestados) throws Exception {
		return new RegistroEntradaDataHelper().addRegistroEntradaTmp(registroEntrada,listindic,listestados);
	}

	public Boolean addRegistroEntradaTmp(RegistroEntrada registroEntrada) throws Exception {
		return new RegistroEntradaDataHelper().addRegistroEntradaTmp(registroEntrada);
	}

	//@Override
	public Vector<RegistroEntrada> getRegistrosEntradaTmp(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getRegistrosEntradaTemp(codigoOrden);
	}

	//@Override
	public RegistroEntrada getOrdenRegistroTmp() throws Exception {
		return new RegistroEntradaDataHelper().getOrdenRegistroTmp();
	}

	//@Override
	public Boolean deleteTemporales(String idOperario) throws Exception {
		return new RegistroEntradaDataHelper().deleteTemporales(idOperario);
	}

	//@Override
	public Boolean addRegistrosTemporales(String idOperario) throws Exception {
		System.out.println("Pasando los temporales a definitivos");
		Boolean bol= new UbicacionDataHelper().addRegistrosTemporales();
		System.out.println("Pasados los temporales de ubicacion");
		return new RegistroEntradaDataHelper().addRegistrosTemporales(idOperario);
	}

	//@Override
	public RegistroEntrada loadRegistroEntradaTmp(RegistroEntrada regEntrada) throws Exception{
		return new RegistroEntradaDataHelper().loadRegistroEntradaTmp(regEntrada);
	}

	//@Override
	public Vector loadEstadoFabasTmp(RegistroEntrada regEntrada) throws Exception {
		return new RegistroEntradaDataHelper().loadEstadoFabasTmp(regEntrada);
	}
	
	//@Override
	public Vector loadIncidenciasTmp(RegistroEntrada regEntrada) throws Exception {
		return new RegistroEntradaDataHelper().loadIncidenciasTmp(regEntrada);
	}
	
	//@Override
	public Vector loadEstadoFabas(RegistroEntrada regEntrada) throws Exception {
		return new RegistroEntradaDataHelper().loadEstadoFabas(regEntrada);
	}
	
	//@Override
	public Vector loadIncidencias(RegistroEntrada regEntrada) throws Exception {
		return new RegistroEntradaDataHelper().loadIncidencias(regEntrada);
	}

	//@Override
	public Boolean updateRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaActualiza, List listindic, List listestados) throws Exception {
		return new RegistroEntradaDataHelper().updateRegistroEntradaTmp(regEntradaFind, regEntradaActualiza, listindic, listestados);
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(RegistroEntrada regEntradaFind,RegistroEntrada regEntradaElimina) throws Exception {
		return new RegistroEntradaDataHelper().deleteRegistroEntradaTmp(regEntradaFind, regEntradaElimina);
	}

	//@Override
	public Boolean deleteRegistroEntradaTmp(String idOperario) throws Exception {
		return new RegistroEntradaDataHelper().deleteRegistroEntradaTmp(idOperario);
	}

	//@Override
	public Boolean deleteOrdenEntrada(String codigoEntrada) throws Exception {
		return new RegistroEntradaDataHelper().deleteOrdenEntrada(codigoEntrada);
	}

	//@Override
	public RegistroEntrada getOrdenRegistroTmp(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getOrdenRegistroTmp(codigoOrden);
	}

	//@Override
	public String getFechaCaducidadTmp(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getFechaCaducidadTmp(codigoOrden);
	}

	//@Override
	public String getFechaRegistroTmp(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getFechaRegistroTmp(codigoOrden);
	}
	
	//@Override
	public String getFechaCaducidad(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getFechaCaducidad(codigoOrden);
	}
	
	//@Override
	public String getFechaRegistro(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getFechaRegistro(codigoOrden);
	}

	//@Override
	public RegistroOrden getOrdenEntrada(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getOrdenEntrada(codigoOrden);
	}
	
	//@Override
	public RegistroOrden getOrdenEntradaTmp(String codigoOrden) throws Exception {
		return new RegistroEntradaDataHelper().getOrdenEntradaTmp(codigoOrden);
	}

	//@Override
	public Vector getRegistroEntradas(RegistroEntrada entrada, int filtro) throws Exception {
		return new RegistroEntradaDataHelper().getRegistroEntradas(entrada, filtro);
	}

	//@Override
	public RegistroEntrada getRegistroEntrada(String codigoEntrada) throws Exception {
		return new RegistroEntradaDataHelper().getRegistroEntrada (codigoEntrada);
	}

	//@Override
	public Vector<RegistroCalidad> getRegistrosCalidad(String codigoEntrada) throws Exception {
		//return new RegistroCalidadDataHelper().getRegistrosCalidad (codigoEntrada);
		return new RegistroEntradaDataHelper().getRegistrosCalidad (codigoEntrada);
	}

	//@Override
	public Double addAnalisisCalidadRegistro(RegistroCalidad calidad) throws Exception {
		//aqui es donde tengo que hacer el c�lculo
		System.out.println(" IG es:"+calidad.getVarIG());
		System.out.println(" SP:"+calidad.getVarSP());
		System.out.println(" DP es:"+calidad.getVarDP());
		System.out.println(" DA:"+calidad.getVarDA());	
		System.out.println(" M:"+calidad.getVarM());	
		System.out.println(" Codigo:"+calidad.getCodigoEntrada());
		
		Long varIGL= Long.parseLong(calidad.getVarIG().trim());
		Long varSPL= Long.parseLong(calidad.getVarSP().trim());
		Long varDPL= Long.parseLong(calidad.getVarDP().trim());
		Long varDAL= Long.parseLong(calidad.getVarDA().trim());
		Long varML= Long.parseLong(calidad.getVarM().trim());
		calidad.setCalidad((2.6*varIGL)+(2.2*varSPL)+(2.6*varDPL)+(2.8*varDAL)-(2.4*varML));
		
		System.out.println(" LA CALIDAD :"+calidad.getCalidad());	
		
		/*if(resultadoCalidad <= 23.5 && resultadoCalidad >= 13.5)
			calidad.setBaremoCalidad("Mala calidad");	
		else if(resultadoCalidad <= 12.5 && resultadoCalidad >= 3.5)
			calidad.setBaremoCalidad("Calidad aceptable");
		else if(resultadoCalidad <= 2.5 && resultadoCalidad >= -6.5)
			calidad.setBaremoCalidad("Muy buena calidad");
		else if(resultadoCalidad <= -7.5 && resultadoCalidad >= -16.5)
			calidad.setBaremoCalidad("Excelente calidad");	
		*/
		if(new RegistroEntradaDataHelper().addAnalisisCalidadRegistro (calidad)) {
			System.out.println("Insertado correctamente");
			return calidad.getCalidad();
		} else return null;
		//return new RegistroEntradaDataHelper().addAnalisisCalidadRegistro (calidad);
	}

	//@Override
	public Vector getBultosRegistroEntrada(RegistroEntrada entrada)	throws Exception {
		entrada = new RegistroEntradaDataHelper().getRegistroEntrada(entrada.getCodigoEntrada());
		Vector respuesta = new RegistroEntradaDataHelper().getBultosRegistroEntrada(entrada);
		//si no hay bultos
		if (respuesta.size() == 0){
			if(entrada.getNumeroPallets()==0) {
				System.out.println("No hay palet definido");
			} else {
				for (int i=1; i<=entrada.getNumeroPallets(); i++){
					Bulto bulto = new Bulto();
					bulto.setCodigoEntrada(entrada.getCodigoEntrada());
					bulto.setNumBulto(i);
					//bulto.setPeso(entrada.getCantidad()/entrada.getNumeroBultos());
					bulto.setPeso(entrada.getCantidad()/entrada.getNumeroPallets());
					bulto.setPBruto(bulto.getPeso());
					bulto.setPNeto(bulto.getPeso());
					bulto.setNumBultosPalet((int) (entrada.getNumeroBultos()/entrada.getNumeroPallets()));
					//int pezo=(int)(entrada.getNumeroBultos()/entrada.getNumeroPallets());
					//bulto.setNumBultosPalet(pezo);
					new RegistroEntradaDataHelper().inicializaBultosRE(bulto, entrada.getCantidad());
				}
				System.out.println("Bultos inicializados");
			}
			respuesta = new RegistroEntradaDataHelper().getBultosRegistroEntrada (entrada);
		}
		return respuesta;
		//return new RegistroEntradaDataHelper().getBultosRegistroEntrada (entrada);
	}

	//@Override
	public Boolean updateBultoRE(Bulto bulto) throws Exception { 
		// entonces se debe actualizar el stock, porque se est� produciendo una merma
		return new RegistroEntradaDataHelper().updateBultoRE (bulto);
	}

	//@Override
	public Boolean addBultoRE(String idEntrada, int numBulto, double peso) throws Exception {
		return new RegistroEntradaDataHelper().addBultoRE(idEntrada,numBulto,peso);
	}

	//@Override
	public Boolean addBultoRE(Bulto bulto) throws Exception {
		return new RegistroEntradaDataHelper().addBultoRE(bulto);
	}

	//@Override
	public Boolean inicializaBultosRE(Bulto bulto, double total) throws Exception {
		return new RegistroEntradaDataHelper().inicializaBultosRE(bulto, total);
	}

	//@Override
	public Vector<TipoMaquina> getTiposMaquinas() throws Exception {
		return new RegistroEntradaDataHelper().getTiposMaquinas();
	}

	//@Override
	public Vector<Mantenimiento> getMantenimientos(long idMantenimientoProgramacion, long idTipo, long idMaquina) throws Exception {
		return new RegistroEntradaDataHelper().getMantenimientos(idMantenimientoProgramacion, idTipo, idMaquina);
	}

	//@Override
	public Vector<Ciclo> getCiclos() throws Exception {
		return new RegistroEntradaDataHelper().getCiclos();
	}

	//@Override
	public boolean addMTProgramado(Mantenimiento entry) throws Exception {
		return new RegistroEntradaDataHelper().addMTProgramado(entry);
	}

	//@Override
	public void inseRegMT(Mantenimiento entry) throws Exception {
		new RegistroEntradaDataHelper().inseRegMT(entry);
	}

	//@Override
	public void devolucion(RegistroEntrada entry)
			throws Exception {
		new RegistroEntradaDataHelper().devolucion(entry);
	}

	//@Override
	public void checkMT() throws Exception {
		new RegistroEntradaDataHelper().checkMT();
	}

	//@Override
	public Vector<RegistroEntrada> getDevoluciones(String fecha) throws Exception {
		return new RegistroEntradaDataHelper().getDevoluciones(fecha);
	}

	//@Override
	public Vector<Mantenimiento> getMantenimientosProgramados(long tipo,
			long idMaquina, String fechaConsulta) throws Exception {
		return new RegistroEntradaDataHelper().
			getMantenimientosProgramados(tipo, idMaquina, fechaConsulta);
	}

	//@Override
	public Boolean updateRegistroOrden(RegistroOrden entry) throws Exception {
		return new RegistroEntradaDataHelper().updateRegistroOrden(entry);
	}

	//@Override
	public String generarCodigoEntrada() throws Exception {
		return new RegistroEntradaDataHelper().generarCodigoEntrada();
	}

	//@Override
	public void reaprovecharDevolucion(LineaProducto linea) throws Exception {
		new RegistroEntradaDataHelper().reaprovecharDevolucion(linea);
	}

	//@Override
	public Vector<RegistroEntrada> getDevolucionesLote(String lote) throws Exception {
		return new RegistroEntradaDataHelper().getDevolucionesLote(lote);
	}
}
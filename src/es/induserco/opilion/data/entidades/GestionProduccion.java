package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public class GestionProduccion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String ubicado;
	private String estaUbicado = "S";
	private String noUbicado = "N";
	private long idGestion;
	private String proceso;
	private String orden = "";
	private String fecha;
	private String horaProceso;
	private String fechaCaducidad;
	private String loteAsignado;
	private String habilitado;
	private String horaInicioProceso;
	private String horaFinProceso;
	private long idProducto;
	private double cantidadProducto, cantidadElaborada, cantidadElaboradaAgrupacion;
	private Double saldoProducto;
	private long cantidadUnidadesEnvases;
	private long idIngredientes;
	private long cantidadIngredientesIni;
	private String origen;
	private String descripcionCategoria;
	private long cantidadIngredientesFin;
	private Double mermaIngredientes;
	private String loteIngredientes;
	private long idEnvase;
	private long cantidadEnvasesIni;
	private long cantidadEnvasesFin;
	private long mermaEnvases;
	private long temperatura;
	private String idOperario;
	private String loteEnvases;
	private String notasIncidencias;
	private String notasInstrucciones;
	private String descProducto;
	private String descIngrediente;
	private String descEnvase;
    private String observaciones;
    private String estadoProceso;
    private String usuarioResponsable;
    private List<RegistroEntrada> regEntradas;
    private long idCategoria;
    private String nombreHueco;
    private double cantidadUbicada;
    private String ordenAnterior;
    private int grupo;
    private int lineaGrupo;
    private int ultimo;
    private int tipoEan;
    private String fechaProgramada, registroSanitario;
    private long idHueco;
    private long idUbicacion;
	private double destrio, peso;
	private List<LineaProducto> lineasMP, lineasEN;
	private boolean agrupar;
	private String resultado;

    public String getUsuarioResponsable() { return usuarioResponsable; }
	public void setUsuarioResponsable(String usuarioResponsable) { this.usuarioResponsable = usuarioResponsable; }

	public long getIdGestion() { return idGestion; }
	public void setIdGestion(long idGestion) { this.idGestion = idGestion; }

	public String getProceso() { return proceso; }
	public void setProceso(String proceso) { this.proceso = proceso; }
	public String getOrden() { return orden; }
	public void setOrden(String orden) { this.orden = orden; }
	public String getFecha() { return fecha; }
	public void setFecha(String fecha) { this.fecha = fecha; }
	public String getLoteAsignado() { return loteAsignado; }
	public void setLoteAsignado(String loteAsignado) { this.loteAsignado = loteAsignado; }
	
	/**
	 * Gets the id producto.
	 *
	 * @return the id producto
	 */
	public long getIdProducto() { return idProducto;}
	
	/**
	 * Sets the id producto.
	 *
	 * @param idProducto the new id producto
	 */
	public void setIdProducto(long idProducto) { this.idProducto = idProducto; }
	
	/**
	 * Gets the cantidad producto.
	 *
	 * @return the cantidad producto
	 */
	public Double getCantidadProducto() { return cantidadProducto; }
	
	/**
	 * Sets the cantidad producto.
	 *
	 * @param cantidadProducto the new cantidad producto
	 */
	public void setCantidadProducto(Double cantidadProducto) { this.cantidadProducto = cantidadProducto; }
	
	/**
	 * Gets the id ingredientes.
	 *
	 * @return the id ingredientes
	 */
	public long getIdIngredientes() { return idIngredientes; }
	
	/**
	 * Sets the id ingredientes.
	 *
	 * @param idIngredientes the new id ingredientes
	 */
	public void setIdIngredientes(long idIngredientes) {
		this.idIngredientes = idIngredientes;
	}
	
	/**
	 * Gets the cantidad ingredientes ini.
	 *
	 * @return the cantidad ingredientes ini
	 */
	public long getCantidadIngredientesIni() {
		return cantidadIngredientesIni;
	}

	public void setCantidadIngredientesIni(long cantidadIngredientesIni) {
		this.cantidadIngredientesIni = cantidadIngredientesIni;
	}

	public long getCantidadIngredientesFin() {
		return cantidadIngredientesFin;
	}

	public void setCantidadIngredientesFin(long cantidadIngredientesFin) {
		this.cantidadIngredientesFin = cantidadIngredientesFin;
	}

	public String getLoteIngredientes() {
		return loteIngredientes;
	}

	public void setLoteIngredientes(String loteIngredientes) {
		this.loteIngredientes = loteIngredientes;
	}

	public long getIdEnvase() {
		return idEnvase;
	}

	public void setIdEnvase(long idEnvase) {
		this.idEnvase = idEnvase;
	}

	public long getCantidadEnvasesIni() {
		return cantidadEnvasesIni;
	}

	public void setCantidadEnvasesIni(long cantidadEnvasesIni) {
		this.cantidadEnvasesIni = cantidadEnvasesIni;
	}

	public long getCantidadEnvasesFin() {
		return cantidadEnvasesFin;
	}

	public void setCantidadEnvasesFin(long cantidadEnvasesFin) {
		this.cantidadEnvasesFin = cantidadEnvasesFin;
	}
	
	/**
	 * Gets the lote envases.
	 *
	 * @return the lote envases
	 */
	public String getLoteEnvases() {
		return loteEnvases;
	}
	
	/**
	 * Sets the lote envases.
	 *
	 * @param loteEnvases the new lote envases
	 */
	public void setLoteEnvases(String loteEnvases) {
		this.loteEnvases = loteEnvases;
	}
	
	/**
	 * Gets the notas incidencias.
	 *
	 * @return the notas incidencias
	 */
	public String getNotasIncidencias() {
		return notasIncidencias;
	}

	public void setNotasIncidencias(String notasIncidencias) {
		this.notasIncidencias = notasIncidencias;
	}

	public String getNotasInstrucciones() {
		return notasInstrucciones;
	}
	
	/**
	 * Sets the notas instrucciones.
	 *
	 * @param notasInstrucciones the new notas instrucciones
	 */
	public void setNotasInstrucciones(String notasInstrucciones) {
		this.notasInstrucciones = notasInstrucciones;
	}
	
	/**
	 * Gets the desc producto.
	 *
	 * @return the desc producto
	 */
	public String getDescProducto() {
		return descProducto;
	}
	
	/**
	 * Sets the desc producto.
	 *
	 * @param descProducto the new desc producto
	 */
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}
	
	/**
	 * Gets the desc ingrediente.
	 *
	 * @return the desc ingrediente
	 */
	public String getDescIngrediente() {
		return descIngrediente;
	}
	
	/**
	 * Sets the desc ingrediente.
	 *
	 * @param descIngrediente the new desc ingrediente
	 */
	public void setDescIngrediente(String descIngrediente) {
		this.descIngrediente = descIngrediente;
	}
	
	/**
	 * Gets the desc envase.
	 *
	 * @return the desc envase
	 */
	public String getDescEnvase() {
		return descEnvase;
	}
	
	/**
	 * Sets the desc envase.
	 *
	 * @param descEnvase the new desc envase
	 */
	public void setDescEnvase(String descEnvase) {
		this.descEnvase = descEnvase;
	}
	
	/**
	 * Gets the observaciones.
	 *
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	
	/**
	 * Sets the observaciones.
	 *
	 * @param observaciones the new observaciones
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	/**
	 * Sets the merma ingredientes.
	 *
	 * @param mermaIngredientes the new merma ingredientes
	 */
	public void setMermaIngredientes(Double mermaIngredientes) {
		this.mermaIngredientes = mermaIngredientes;
	}
	
	/**
	 * Gets the merma ingredientes.
	 *
	 * @return the merma ingredientes
	 */
	public Double getMermaIngredientes() {
		return mermaIngredientes;
	}
	
	/**
	 * Sets the merma envases.
	 *
	 * @param mermaEnvases the new merma envases
	 */
	public void setMermaEnvases(long mermaEnvases) {
		this.mermaEnvases = mermaEnvases;
	}
	
	/**
	 * Gets the merma envases.
	 *
	 * @return the merma envases
	 */
	public long getMermaEnvases() {
		return mermaEnvases;
	}
	
	/**
	 * Sets the habilitado.
	 *
	 * @param habilitado the new habilitado
	 */
	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}
	
	/**
	 * Gets the habilitado.
	 *
	 * @return the habilitado
	 */
	public String getHabilitado() {
		return habilitado;
	}
	public String getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(String fechaCaducidad2) {
		this.fechaCaducidad = fechaCaducidad2;
	}
	
	/**
	 * Gets the temperatura.
	 *
	 * @return the temperatura
	 */
	public long getTemperatura() {
		return temperatura;
	}
	
	/**
	 * Sets the temperatura.
	 *
	 * @param temperatura the new temperatura
	 */
	public void setTemperatura(long temperatura) {
		this.temperatura = temperatura;
	}
	
	/**
	 * Gets the id operario.
	 *
	 * @return the id operario
	 */
	public String getIdOperario() {
		return idOperario;
	}
	
	/**
	 * Sets the id operario.
	 *
	 * @param idOperario the new id operario
	 */
	public void setIdOperario(String idOperario) {
		this.idOperario = idOperario;
	}
	
	/**
	 * Sets the hora proceso.
	 *
	 * @param horaEnvasado the new hora proceso
	 */
	public void setHoraProceso(String horaEnvasado) {
		this.horaProceso = horaEnvasado;
	}
	
	/**
	 * Gets the hora proceso.
	 *
	 * @return the hora proceso
	 */
	public String getHoraProceso() {
		return horaProceso;
	}
	
	/**
	 * Sets the hora inicio proceso.
	 *
	 * @param horaInicioProceso the new hora inicio proceso
	 */
	public void setHoraInicioProceso(String horaInicioProceso) {
		this.horaInicioProceso = horaInicioProceso;
	}
	
	/**
	 * Gets the hora inicio proceso.
	 *
	 * @return the hora inicio proceso
	 */
	public String getHoraInicioProceso() {
		return horaInicioProceso;
	}
	
	/**
	 * Sets the hora fin proceso.
	 *
	 * @param horaFinProceso the new hora fin proceso
	 */
	public void setHoraFinProceso(String horaFinProceso) {
		this.horaFinProceso = horaFinProceso;
	}
	
	/**
	 * Gets the hora fin proceso.
	 *
	 * @return the hora fin proceso
	 */
	public String getHoraFinProceso() {
		return horaFinProceso;
	}

	public void setCantidadUnidadesEnvases(long cantidadUnidadesEnvases) {
		this.cantidadUnidadesEnvases = cantidadUnidadesEnvases;
	}

	public long getCantidadUnidadesEnvases() {
		return cantidadUnidadesEnvases;
	}

	public void setSaldoProducto(Double saldoProducto) {
		this.saldoProducto = saldoProducto;
	}
	
	/**
	 * Gets the saldo producto.
	 *
	 * @return the saldoProducto
	 */
	public Double getSaldoProducto() {
		return saldoProducto;
	}

	public void setRegEntradas(List<RegistroEntrada> regEntradas) {
		this.regEntradas = regEntradas;
	}

	public List<RegistroEntrada> getRegEntradas() {
		return regEntradas;
	}

	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
	}

	public String getEstadoProceso() {
		return estadoProceso;
	}

	public void setUbicado(String ubicado) {
		this.ubicado = ubicado;
	}

	public String getUbicado() {
		return ubicado;
	}

	public void setEstaUbicado(String estaUbicado) {
		this.estaUbicado = estaUbicado;
	}

	public String getEstaUbicado() {
		return estaUbicado;
	}

	public void setNoUbicado(String noUbicado) {
		this.noUbicado = noUbicado;
	}

	public String getNoUbicado() {
		return noUbicado;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setNombreHueco(String nombreHueco) {
		this.nombreHueco = nombreHueco;
	}

	public String getNombreHueco() {
		return nombreHueco;
	}

	public void setCantidadUbicada(double cantidadUbicada) {
		this.cantidadUbicada = cantidadUbicada;
	}

	public double getCantidadUbicada() {
		return cantidadUbicada;
	}

	public void setOrdenAnterior(String ordenAnterior) {
		this.ordenAnterior = ordenAnterior;
	}

	public String getOrdenAnterior() {
		return ordenAnterior;
	}

	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}

	public int getGrupo() {
		return grupo;
	}

	public void setLineaGrupo(int lineaGrupo) {
		this.lineaGrupo = lineaGrupo;
	}

	public int getLineaGrupo() {
		return lineaGrupo;
	}

	public void setUltimo(int ultimo) {
		this.ultimo = ultimo;
	}

	public int getUltimo() {
		return ultimo;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getOrigen() {
		return origen;
	}
	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}
	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}
	public void setTipoEan(int tipoEan) {
		this.tipoEan = tipoEan;
	}
	public int getTipoEan() {
		return tipoEan;
	}
	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}
	public String getFechaProgramada() {
		return fechaProgramada;
	}
	public void setIdHueco(long idHueco) {
		this.idHueco = idHueco;
	}
	public long getIdHueco() {
		return idHueco;
	}
	public void setIdUbicacion(long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}
	public long getIdUbicacion() {
		return idUbicacion;
	}
	public void setDestrio(double destrio) {
		this.destrio = destrio;
	}
	public double getDestrio(){
		return this.destrio;
	}
  
    public JRDataSource getLineasMPDS(){
        return new JRBeanCollectionDataSource(lineasMP);
    }
    
    public JRDataSource getLineasENDS(){
        return new JRBeanCollectionDataSource(lineasEN);
    }
    
	public void setLineasMP(List<LineaProducto> lineasMP) {
		this.lineasMP = lineasMP;
	}
	public List<LineaProducto> getLineasMP() {
		return lineasMP;
	}
	public void setLineasEN(List<LineaProducto> lineasEN) {
		this.lineasEN = lineasEN;
	}
	public List<LineaProducto> getLineasEN() {
		return lineasEN;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	public double getPeso() {
		return peso;
	}
	public void setRegistroSanitario(String registroSanitario) {
		this.registroSanitario = registroSanitario;
	}
	public String getRegistroSanitario() {
		return registroSanitario;
	}
	public void setCantidadElaborada(double cantidadElaborada) {
		this.cantidadElaborada = cantidadElaborada;
	}
	public double getCantidadElaborada() {
		return cantidadElaborada;
	}
	public void setCantidadElaboradaAgrupacion(double cantidadElaboradaAgrupacion) {
		this.cantidadElaboradaAgrupacion = cantidadElaboradaAgrupacion;
	}
	public double getCantidadElaboradaAgrupacion() {
		return cantidadElaboradaAgrupacion;
	}
	public void setAgrupar(boolean agrupar) {
		this.agrupar = agrupar;
	}
	public boolean isAgrupar() {
		return agrupar;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getResultado() {
		return resultado;
	}
}
package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Producto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idProducto;
	private String codigoProducto;
	private Long idCategoria;
	private Date fecha;//fecha elaboraci�n o fecha de recepcion
	private Formato formato;//Formatos hacer clase
	private String nombre;
	private String descripcion;
	private String lote;
	private double stock, stockAgrupado, stockMinimo;
	private int caducidad;//meses
	private int humedad;
	private Long idEstado; // 1. Materia prima, 2.Semi elaborado, 3. Acabado
	private Long idFamilia;
	private Long idTipo;
	private Long idGrupo;	
	private Long idBolsa;
	private Long idSaco;
	private Long idCarton;
	private Long idSaquete;
	private Long idMadera;
	private Long idOtro;
	private Long idMateriaPrima;
	private Long idMateriaCategoria;
	private String habilitado;
	private String codigoEan;
	private List<Integer> materiasPrimas;
	private List<Integer> envases;
	private double cantidad;
	private Long idImpuesto;
	private String foto;
	public double precioCoste;
	private double precio;
	private double peso;
	private Familia familia;
	private List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
	private List<IncidenciaProducto> incidencias = new ArrayList<IncidenciaProducto>();
	private String item;
	private Date fechaCaducidad;
	private int mostrar, EANs13;

	public Producto() {
		super();
	}	

	public Producto(Long idProducto, String codigoProducto, String nombre, Long idImpuesto) {
		this.idProducto = idProducto;
		this.codigoProducto = codigoProducto;
		this.nombre = nombre;
		this.idImpuesto = idImpuesto;
	}

	public Producto(Long idProducto,Long idCategoria, Date fecha,
			Formato formato, String nombre, String descripcion, String lote,
			double stock, int humedad, double stockMinimo, List<Ubicacion> ubicaciones,
			Long idEstado, List<IncidenciaProducto> incidencias, List<Producto> composicion, Long idImpuesto, double precioCoste) {
		super();
		this.idProducto = idProducto;
		this.idCategoria = idCategoria;
		this.fecha = fecha;
		this.formato = formato;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.lote = lote;
		this.stock = stock;
		this.humedad = humedad;
		this.stockMinimo = stockMinimo;
		this.ubicaciones = ubicaciones;
		this.idEstado = idEstado;
		this.incidencias = incidencias;
		this.idImpuesto = idImpuesto;
		this.precioCoste = precioCoste;
	}

	public double getPrecioCoste()						{ return this.precioCoste; }
	public Long getIdProducto() 						{ return idProducto; }
	public Long getIdCategoria() 						{ return idCategoria; }
	public Formato getFormato()							{ return formato; }
	public String getNombre() 							{ return nombre; }
	public double getStock() 							{ return stock; }
	public double getStockMinimo() 						{ return stockMinimo; }
	public Date getFecha() 								{ return fecha; }
	public String getDescripcion() 						{ return descripcion; }
	public String getLote() 							{ return lote; }
	public List<Ubicacion> getUbicacion() 				{ return Collections.unmodifiableList(ubicaciones); }
	public Long getIdEstado() 							{ return idEstado; }
	public List<IncidenciaProducto> getIncidencias() 			{ return incidencias; }
	public List<Integer> getMateriasPrimas()			{ return materiasPrimas; }
	public List<Integer> getEnvases()					{ return envases; }
	public double getCantidad()							{ return cantidad; }
	public Long getIdImpuesto()							{ return this.idImpuesto; }
	public String getFoto()								{ return foto; }
	
	public void setIdProducto(Long idProducto) 			{ this.idProducto = idProducto; }
	public void setIdCategoria(Long idCategoria) 		{ this.idCategoria = idCategoria; }
	public void setFormato(Formato formato)				{ this.formato = formato; }
	public void setNombre(String nombre) 				{ this.nombre = nombre; }
	public void setStock(double stock) 					{ this.stock = stock; }
	public void setPrecioCoste(double precioCoste)		{ this.precioCoste = precioCoste; }
	public void setStockMinimo(double stockMinimo) 		{ this.stockMinimo = stockMinimo; }
	public void setFecha(Date fecha) 					{ this.fecha = fecha; }
	public void setDescripcion(String descripcion) 		{ this.descripcion = descripcion; }
	public void setLote(String lote) 					{ this.lote = lote; }
	public void setUbicacion(Ubicacion ubicacion) 		{ this.ubicaciones.add(ubicacion); }
	public void setIdEstado(Long idEstado) 				{ this.idEstado = idEstado; }
	public void setIncidencias(List<IncidenciaProducto> incidencias) { this.incidencias = incidencias; }
	public void setMateriasPrimas(List<Integer> materias)	{ this.materiasPrimas = materias; }
	public void setEnvases(List<Integer> envases)		{ this.envases = envases; }
	public void setCantidad (double cantidad)			{ this.cantidad = cantidad; }
	public void setHumedad(int humedad) 				{ this.humedad = humedad; }
	public void setIdImpuesto(Long id)					{ this.idImpuesto = id; }
	public void setFoto(String i)						{ this.foto = i; }
	public int getHumedad() {
		return humedad;
	}
	public void setFamilia(Familia familia) {
		this.familia = familia;
	}

	public Familia getFamilia() {
		return familia;
	}

	public void setIdFamilia(Long idFamilia) {
		this.idFamilia = idFamilia;
	}

	public Long getIdFamilia() {
		return idFamilia;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setIdGrupo(Long idGrupo) {
		this.idGrupo = idGrupo;
	}

	public Long getIdGrupo() {
		return idGrupo;
	}

	public void setHabilitado(String habilitado) {
		this.habilitado = habilitado;
	}

	public String getHabilitado() {
		return habilitado;
	}

	public void setIdMateriaPrima(Long idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public Long getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public void setIdBolsa(Long idBolsa) {
		this.idBolsa = idBolsa;
	}

	public Long getIdBolsa() {
		return idBolsa;
	}

	public void setIdSaco(Long idSaco) {
		this.idSaco = idSaco;
	}

	public Long getIdSaco() {
		return idSaco;
	}

	public void setIdCarton(Long idCarton) {
		this.idCarton = idCarton;
	}

	public Long getIdCarton() {
		return idCarton;
	}

	public void setIdSaquete(Long idSaquete) {
		this.idSaquete = idSaquete;
	}

	public Long getIdSaquete() {
		return idSaquete;
	}

	public void setIdMadera(Long idMadera) {
		this.idMadera = idMadera;
	}
	
	public Long getIdMadera() {
		return idMadera;
	}

	public void setIdOtro(Long idOtro) {
		this.idOtro = idOtro;
	}

	public Long getIdOtro() {
		return idOtro;
	}

	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoEan(String codigoEan) {
		this.codigoEan = codigoEan;
	}

	public String getCodigoEan() {
		return codigoEan;
	}

	public void setIdMateriaCategoria(Long idMateriaCategoria) {
		this.idMateriaCategoria = idMateriaCategoria;
	}

	public Long getIdMateriaCategoria() {
		return idMateriaCategoria;
	}
	
	public double getPeso(){
		return this.peso;
	}
	public void setPeso(double peso){
		this.peso = peso;
	}
	public double getPrecio(){
		return precio;
	}
	public void setPrecio(double precio){
		this.precio = precio;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	public String getItem(){
		return item;
	}

	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	
	public Date getFechaCaducidad(){
		return this.fechaCaducidad;
	}

	public void setStockAgrupado(double stockAgrupado) {
		this.stockAgrupado = stockAgrupado;
	}

	public double getStockAgrupado() {
		return stockAgrupado;
	}

	public void setMostrar(int mostrar) {
		this.mostrar = mostrar;
	}

	public int getMostrar() {
		return mostrar;
	}

	public void setEANs13(int eANs13) {
		EANs13 = eANs13;
	}

	public int getEANs13() {
		return EANs13;
	}

	public void setCaducidad(int caducidad) {
		this.caducidad = caducidad;
	}

	public int getCaducidad() {
		return caducidad;
	}
}
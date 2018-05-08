package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.induserco.opilion.data.comun.contacto.Direccion;

public class Ubicacion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idUbicacion;
	private Direccion Direccion;
	private Long idTipoUbicacion;//Almacen
	private Long idZona;
	private String estanteria;
	private String pasillo;
	private String altura;
	private String nombre;
	private String descripcion;
	private String dimensiones;
	private long idLinea;
	private long idEstanteria;
	private long idPiso;
	private long idHueco;
	private long idAlmacen;
	private String nombreAlmacen;
	private String nombreZona;
	private String nombreLinea;
	private char idTipoRegistro;
	private String urlPlanoLinea;
	private String urlPlanoAlmacen;
	private long idLineaZona;
	private long idHuecoPiso;
	private String nombreHueco;
	private String nombrePiso;
	private String nombreEstanteria;
	private String registro;
	private long idPalet;
	private double cantidad;
	private long numeroBultos;
	private String congelado;
	private String orden;
	private boolean estaUbicado;
	private boolean esVehiculo;
	private String fecha;
	private String proveedor;
	private boolean salidaEnvasado;
	private String nombreProducto, usuarioResponsable;
	private char movido;
	private char cancelado;
	
	private List<Producto> productosUbicacion = new ArrayList<Producto>();

	public void setProductosUbicacion(List<Producto> productosUbicacion) {
		this.productosUbicacion = productosUbicacion;
	}

	public List<Producto> getProductosUbicacion() {
		return productosUbicacion;
	}

	public void setDireccion(Direccion Direccion) {
		this.Direccion = Direccion;
	}

	public Direccion getDireccion() {
		return Direccion;
	}

	public String getEstanteria() {
		return estanteria;
	}

	public void setEstanteria(String estanteria) {
		this.estanteria = estanteria;
	}

	public String getPasillo() {
		return pasillo;
	}

	public void setPasillo(String pasillo) {
		this.pasillo = pasillo;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public void setIdUbicacion(Long idUbicacion) {
		this.idUbicacion = idUbicacion;
	}

	public Long getIdUbicacion() {
		return idUbicacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setIdZona(Long idZona) {
		this.idZona = idZona;
	}

	public Long getIdZona() {
		return idZona;
	}

	public void setIdTipoUbicacion(Long idTipoUbicacion) {
		this.idTipoUbicacion = idTipoUbicacion;
	}

	public Long getIdTipoUbicacion() {
		return idTipoUbicacion;
	}

	public void setIdLinea(long idLinea) {
		this.idLinea = idLinea;
	}

	public long getIdLinea() {
		return idLinea;
	}

	public void setIdEstanteria(long idEstanteria) {
		this.idEstanteria = idEstanteria;
	}

	public long getIdEstanteria() {
		return idEstanteria;
	}

	public void setIdPiso(long idPiso) {
		this.idPiso = idPiso;
	}

	public long getIdPiso() {
		return idPiso;
	}

	public void setIdHueco(long idHueco) {
		this.idHueco = idHueco;
	}

	public long getIdHueco() {
		return idHueco;
	}

	public void setIdAlmacen(long idAlmacen) {
		this.idAlmacen = idAlmacen;
	}

	public long getIdAlmacen() {
		return idAlmacen;
	}

	public void setNombreAlmacen(String nombreAlmacen) {
		this.nombreAlmacen = nombreAlmacen;
	}

	public String getNombreAlmacen() {
		return nombreAlmacen;
	}

	public void setNombreZona(String nombreZona) {
		this.nombreZona = nombreZona;
	}

	public String getNombreZona() {
		return nombreZona;
	}

	public void setIdTipoRegistro(char idTipoRegistro) {
		this.idTipoRegistro = idTipoRegistro;
	}

	public char getIdTipoRegistro() {
		return idTipoRegistro;
	}

	public void setNombreLinea(String nombreLinea) {
		this.nombreLinea = nombreLinea;
	}

	public String getNombreLinea() {
		return nombreLinea;
	}

	public void setUrlPlanoLinea(String urlPlanoLinea) {
		this.urlPlanoLinea = urlPlanoLinea;
	}

	public String getUrlPlanoLinea() {
		return urlPlanoLinea;
	}

	public void setUrlPlanoAlmacen(String urlPlanoAlmacen) {
		this.urlPlanoAlmacen = urlPlanoAlmacen;
	}

	public String getUrlPlanoAlmacen() {
		return urlPlanoAlmacen;
	}

	public void setIdLineaZona(long idLineaZona) {
		this.idLineaZona = idLineaZona;
	}

	public long getIdLineaZona() {
		return idLineaZona;
	}

	public void setIdHuecoPiso(long idHuecoPiso) {
		this.idHuecoPiso = idHuecoPiso;
	}

	public long getIdHuecoPiso() {
		return idHuecoPiso;
	}

	public void setNombreHueco(String nombreHueco) {
		this.nombreHueco = nombreHueco;
	}

	public String getNombreHueco() {
		return nombreHueco;
	}

	public void setNombrePiso(String nombrePiso) {
		this.nombrePiso = nombrePiso;
	}

	public String getNombrePiso() {
		return nombrePiso;
	}

	public void setNombreEstanteria(String nombreEstanteria) {
		this.nombreEstanteria = nombreEstanteria;
	}

	public String getNombreEstanteria() {
		return nombreEstanteria;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public String getRegistro() {
		return registro;
	}

	public void setIdPalet(long idPalet) {
		this.idPalet = idPalet;
	}

	public long getIdPalet() {
		return idPalet;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCongelado(String congelado) {
		this.congelado = congelado;
	}

	public String getCongelado() {
		return congelado;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getOrden() {
		return orden;
	}

	public void setEstaUbicado(boolean estaUbicado) {
		this.estaUbicado = estaUbicado;
	}

	public boolean getEstaUbicado() {
		return estaUbicado;
	}

	public void setEsVehiculo(boolean esVehiculo) {
		this.esVehiculo = esVehiculo;
	}

	public boolean isEsVehiculo() {
		return esVehiculo;
	}

	public void setNumeroBultos(long numeroBultos) {
		this.numeroBultos = numeroBultos;
	}

	public long getNumeroBultos() {
		return numeroBultos;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getFecha() {
		return fecha;
	}
	public void setSalidaEnvasado(boolean salidaEnvasado) {
		this.salidaEnvasado = salidaEnvasado;
	}
	public boolean isSalidaEnvasado() {
		return salidaEnvasado;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setMovido(char movido) {
		this.movido = movido;
	}

	public char getMovido() {
		return movido;
	}

	public void setCancelado(char cancelado) {
		this.cancelado = cancelado;
	}

	public char getCancelado() {
		return cancelado;
	}

	public void setUsuarioResponsable(String usuarioResponsable) {
		this.usuarioResponsable = usuarioResponsable;
	}

	public String getUsuarioResponsable() {
		return usuarioResponsable;
	}
}
package es.induserco.opilion.data.entidades;

import java.util.Date;

public class Mantenimiento {
	
	private Long idMantenimiento;
	private Long idMaquina;
	private Long idTipo;
	private String fecha;
	private String descripcion;
	private String descMaquina;
	private String descMant;
	private String nombre;
	private String tipoMaquina;
	private String observaciones;
	private String fechaProgramada;
	private String fechaRealizada;
	private String responsable;
	private char estado;
	private long idCiclo;
	private long idCalibrado;
	private String calibrado;
	private long idMantenimientoProgramacion;
	private String ciclo;
	private String patron;
	private String medidaPatron;
	private String medidaEquipo;
	private String desviacion;
	private String verificado;
	private String descripcionTipo;
	private String nombreMaquina;
	private String descripcionMantenimiento;
	private String clasificacion;
	
	public Long getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(Long idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setDescMaquina(String descMaquina) {
		this.descMaquina = descMaquina;
	}

	public String getDescMaquina() {
		return descMaquina;
	}

	public void setDescMant(String descMant) {
		this.descMant = descMant;
	}

	public String getDescMant() {
		return descMant;
	}

	public void setIdTipo(Long idTipo) {
		this.idTipo = idTipo;
	}

	public Long getIdTipo() {
		return idTipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setIdMantenimiento(Long idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}

	public Long getIdMantenimiento() {
		return idMantenimiento;
	}

	public void setTipoMaquina(String tipoMaquina) {
		this.tipoMaquina = tipoMaquina;
	}

	public String getTipoMaquina() {
		return tipoMaquina;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setFechaRealizada(String fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}

	public String getFechaRealizada() {
		return fechaRealizada;
	}

	public void setFechaProgramada(String fechaProgramada) {
		this.fechaProgramada = fechaProgramada;
	}

	public String getFechaProgramada() {
		return fechaProgramada;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public char getEstado() {
		return estado;
	}

	public void setIdCiclo(long idCiclo) {
		this.idCiclo = idCiclo;
	}

	public long getIdCiclo() {
		return idCiclo;
	}

	public void setIdCalibrado(long idCalibrado) {
		this.idCalibrado = idCalibrado;
	}

	public long getIdCalibrado() {
		return idCalibrado;
	}

	public void setCalibrado(String calibrado) {
		this.calibrado = calibrado;
	}

	public String getCalibrado() {
		return calibrado;
	}

	public void setIdMantenimientoProgramacion(long idMantenimientoProgramacion) {
		this.idMantenimientoProgramacion = idMantenimientoProgramacion;
	}

	public long getIdMantenimientoProgramacion() {
		return idMantenimientoProgramacion;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setPatron(String patron) {
		this.patron = patron;
	}

	public String getPatron() {
		return patron;
	}

	public void setMedidaPatron(String medidaPatron) {
		this.medidaPatron = medidaPatron;
	}

	public String getMedidaPatron() {
		return medidaPatron;
	}

	public void setMedidaEquipo(String medidaEquipo) {
		this.medidaEquipo = medidaEquipo;
	}

	public String getMedidaEquipo() {
		return medidaEquipo;
	}

	public void setDesviacion(String desviacion) {
		this.desviacion = desviacion;
	}

	public String getDesviacion() {
		return desviacion;
	}

	public void setVerificado(String verificado) {
		this.verificado = verificado;
	}

	public String getVerificado() {
		return verificado;
	}

	public void setDescripcionMantenimiento(String descripcionMantenimiento) {
		this.descripcionMantenimiento = descripcionMantenimiento;
	}

	public String getDescripcionMantenimiento() {
		return descripcionMantenimiento;
	}

	public void setNombreMaquina(String nombreMaquina) {
		this.nombreMaquina = nombreMaquina;
	}

	public String getNombreMaquina() {
		return nombreMaquina;
	}

	public void setDescripcionTipo(String descripcionTipo) {
		this.descripcionTipo = descripcionTipo;
	}

	public String getDescripcionTipo() {
		return descripcionTipo;
	}

	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}

	public String getClasificacion() {
		return clasificacion;
	}
}
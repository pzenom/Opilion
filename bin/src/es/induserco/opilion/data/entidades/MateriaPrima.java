package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class MateriaPrima implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idMateriaPrima;
	private Long idMateriaCategoria;
	private String nombre;
	private double cantidad;
	private long idGrupo;
	private double stock;
	private long idCategoria;
	private String nombreCategoria;
	private ArrayList<Long> categorias;
	
	public MateriaPrima() {
	}

	public void setIdMateriaPrima(Long idMateriaPrima) {
		this.idMateriaPrima = idMateriaPrima;
	}

	public Long getIdMateriaPrima() {
		return idMateriaPrima;
	}

	public double getCantidad(){
		return this.cantidad;
	}

	public void setCantidad(double c){
		this.cantidad = c;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	
	public long getIdGrupo(){
		return this.idGrupo;
	}
	
	public void setIdGrupo(long idGrupo){
		this.idGrupo = idGrupo;
	}
	
	public double getStock(){
		return this.stock;
	}
	
	public void setStock(double stock){
		this.stock = stock;
	}

	public void setIdCategoria(long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public long getIdCategoria() {
		return idCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setIdMateriaCategoria(Long idMateriaCategoria) {
		this.idMateriaCategoria = idMateriaCategoria;
	}

	public Long getIdMateriaCategoria() {
		return idMateriaCategoria;
	}

	public void setCategorias(ArrayList<Long> categorias) {
		this.categorias = categorias;
	}

	public ArrayList<Long> getCategorias() {
		return categorias;
	}
}
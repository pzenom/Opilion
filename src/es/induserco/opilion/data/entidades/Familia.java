package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Familia implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idFamilia= null;
	private String descripcionFamilia;
	private Long stock;
	private Long stockMinimo;

	//1) relacion familia tiene lista de productos
	private List<Producto> listaProductos= new ArrayList<Producto>();	
	
	//2) relacion de familia tiene familia padre
	private Familia familiaPadre;	
	
	//3) relacion familia tiene familia padre
	private List<Familia> listaFamilias = new ArrayList<Familia>();

	public Familia() {
		super();
		this.idFamilia = null;
		this.listaProductos = null;
		this.descripcionFamilia = null;
		this.listaFamilias = null;
		this.stockMinimo = null;
	}	

	public Familia(Long idFamilia, List<Producto> listaProductos,
			String descripcionFamilia, Long stock, Long stockMinimo) {
		super();
		this.idFamilia = idFamilia;
		this.listaProductos = listaProductos;
		this.descripcionFamilia = descripcionFamilia;
		this.stock = stock;
		this.stockMinimo = stockMinimo;
	}

	public Long getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(Long idFamilia) {
		this.idFamilia = idFamilia;
	}

	public List<Producto> getListaProductos() {
		return Collections.unmodifiableList(listaProductos);
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public String getDescripcionFamilia() {
		return descripcionFamilia;
	}

	public void setDescripcionFamilia(String descripcionFamilia) {
		this.descripcionFamilia = descripcionFamilia;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public List<Familia> getListaFamilias() {
		return Collections.unmodifiableList(listaFamilias);
	}

	public void setListaFamilias(List<Familia> listaFamilias) {
		this.listaFamilias = listaFamilias;
	}

	public Long getStockMinimo() {
		return stockMinimo;
	}

	public void setStockMinimo(Long stockMinimo) {
		this.stockMinimo = stockMinimo;
	}

	public void setFamiliaPadre(Familia familiaPadre) {
		this.familiaPadre = familiaPadre;
	}

	public Familia getFamiliaPadre() {
		return familiaPadre;
	}	
}
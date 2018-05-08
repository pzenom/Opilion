package es.induserco.opilion.data.entidades;

import java.io.Serializable;

public class Categoria  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long idCategoria;
	private String nombreCategoria;
	private double stock;

	public Categoria() {
		super();
	}	

	public Categoria(Long idCategoria,String nombreCategoria, double stock) {
		super();
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.stock = stock;
	}

	public Long getIdCategoria() 						{ return idCategoria; }
	public String getNombreCategoria() 							{ return nombreCategoria; }
	public void setIdCategoria(Long idCategoria) 		{ this.idCategoria = idCategoria; }
	public void setNombreCategoria(String nombreCategoria) 			{ this.nombreCategoria = nombreCategoria; }

	public void setStock(double stock) {
		this.stock = stock;
	}

	public double getStock() {
		return stock;
	}
}
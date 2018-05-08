package es.induserco.edifact.data.x12;

public class IMD {
	
	private Long idOrders;   //identificador de orden de referencia
	private long idProducto;
	private Long idLinea;
	private String imdTipo;	//F=forma libre, C=Codigo
	private String imdCara;	//DSC= descripci�n, 35=color, 98=talla 
	private String imdCodi;	//Descripci�n del art�culo codificado CU=Unidad de consumo, DU=Unidad de envio
	private String imdDesc;	//Descripci�n del art�culo
	
	public IMD() {}

	public IMD(Long idOrders, Long idLinea, String imdForm, String imdCara,
			String imdCodi, String imdDesc) {
		this.idOrders = idOrders;
		this.idLinea = idLinea;
		this.imdTipo = imdForm;  //F � C
		this.imdCara = imdCara;  //DSC � 35, 98...
		this.imdCodi = imdCodi;  // CU, DU
		this.imdDesc = imdDesc;  // descripcion o escritura
	}

	public Long getIdOrders() 				{ return idOrders; }
	
	/**
	 * Gets the id linea.
	 *
	 * @return the id linea
	 */
	public Long getIdLinea() 				{ return idLinea; }
	
	/**
	 * Gets the imd tipo.
	 *
	 * @return the imd tipo
	 */
	public String getImdTipo() 				{ return imdTipo; }
	
	/**
	 * Gets the imd cara.
	 *
	 * @return the imd cara
	 */
	public String getImdCara() 				{ return imdCara; }
	
	/**
	 * Gets the imd codi.
	 *
	 * @return the imd codi
	 */
	public String getImdCodi() 				{ return imdCodi; }
	
	/**
	 * Gets the imd desc.
	 *
	 * @return the imd desc
	 */
	public String getImdDesc() 				{ return imdDesc; }

	/**
	 * Sets the id orders.
	 *
	 * @param idOrders the new id orders
	 */
	public void setIdOrders(Long idOrders) 	{ this.idOrders = idOrders; }
	
	/**
	 * Sets the id linea.
	 *
	 * @param idLinea the new id linea
	 */
	public void setIdLinea(Long idLinea) 	{ this.idLinea = idLinea; }
	
	/**
	 * Sets the imd tipo.
	 *
	 * @param imdForm the new imd tipo
	 */
	public void setImdTipo(String imdForm) 	{ this.imdTipo = imdForm; }

	public void setImdCara(String imdCara) 	{ this.imdCara = imdCara; }

	public void setImdCodi(String imdCodi) 	{ this.imdCodi = imdCodi; }

	public void setImdDesc(String imdDesc) 	{ this.imdDesc = imdDesc; }

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public long getIdProducto() {
		return idProducto;
	}
}
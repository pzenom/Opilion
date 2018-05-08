package es.induserco.opilion.data.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transporte implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int idTransporte;
	private int idTransportista;
	private String matricula;
	private int temperatura;
	
	
	//1) relacion muchos a muchos Registros de Entrada
	//@ManyToMany
	/** The regs entrada transporte. */
	private List<RegistroEntrada> regsEntradaTransporte = new ArrayList<RegistroEntrada>();
	
	//2) relacion muchos a uno
	//@ManyToOne
	/** The transportista. */
	private Entidad transportista;
	
}
package es.induserco.opilion.data.comun;

public class GrupoProducto {
	
	private long idGrupo, idPadre;
	private String nombreGrupo;

	public GrupoProducto() {}

	public long getIdGrupo() 						{ return idGrupo; }
	public long getIdPadre() 						{ return idPadre; }
	public String getNombreGrupo() 					{ return nombreGrupo; }

	public void setIdGrupo(long idGrupo) 			{ this.idGrupo = idGrupo; }
	public void setIdPadre(long idPadre) 			{ this.idPadre = idPadre; }
	public void setNombreGrupo(String nombreGrupo) 	{ this.nombreGrupo = nombreGrupo; }
}
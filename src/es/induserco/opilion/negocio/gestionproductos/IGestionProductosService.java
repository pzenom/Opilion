package es.induserco.opilion.negocio.gestionproductos;

import java.util.Vector;

import es.induserco.opilion.data.comun.GrupoProducto;
import es.induserco.opilion.data.entidades.Categoria;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Familia;
import es.induserco.opilion.data.entidades.Impuesto;
import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public interface IGestionProductosService {

	public Boolean addProducto (Producto proveedor, boolean especifica, long id) throws Exception;
	public Vector getProductos(Producto entry, boolean categorizacion) throws Exception;
	public Vector loadProducto (Producto entry ) throws Exception;
	public Vector getFamilias() throws Exception;	
	public Vector getEstados() throws Exception;
	public Vector getCategorias()throws Exception;
	public Vector getTipoProducto()throws Exception;	
	public Vector getGrupoProducto()throws Exception;
	public Boolean addProductoMerma(ProductoMerma entry)throws Exception;
	public Boolean deshabilitaProducto(Producto producto)throws Exception;
	public Vector getMateriasPrimas(long idGrupo) throws Exception;
	public long addProductoGetId(Producto entry) throws Exception;
	public boolean setEnvasesProducto(long idProducto, Vector<double[]> materias) throws Exception;
	public boolean setMateriasPrimasProducto(long idProducto,Vector<double[]> materias) throws Exception;
	public boolean setProductoCompuesto(long idProducto,Vector<double[]> compuesto) throws Exception;
	public Vector getProductosComponen(long idProducto, boolean cantidades) throws Exception;
	public Vector getMateriasPrimasProducto(long idProducto, String ean) throws Exception;
	public Vector getEnvasesProducto(long idProducto, String ean) throws Exception;
	public Producto getProducto(long idProducto)throws Exception;
	public boolean eliminarProducto(long idProducto) throws Exception;
	public Vector getImpuestos() throws Exception;	
	public boolean setCampoProducto(long idProducto, String columna, String valor) throws Exception;
	public Vector<MateriaPrima> getMateriasPrimasCategorias(long idMateriaPrima) throws Exception;
	public long addMateriaPrima(MateriaPrima materiaPrima) throws Exception;
	public Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry)throws Exception;
	public boolean updateMateriaPrima(MateriaPrima materiaFind,	MateriaPrima mpActualiza) throws Exception;
	public Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo) throws Exception;
	public Vector<Categoria> getCategoriasMateria(Long idMateriaPrima) throws Exception;
	public void addStockProducto(String lote, double cantidad) throws Exception;
	public void registrarGrupoProducto(GrupoProducto grupo) throws Exception;
	public void registrarFamilia(Familia familia) throws Exception;
	public void registrarCategoria(Categoria categoria) throws Exception;
	public void registrarImpuesto(Impuesto impuesto) throws Exception;
	public int devolver(String lote, double cantidad, String destino, Entidad e) throws Exception;
	public Vector<Producto> getProductosMalDefinidos() throws Exception;
	public Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro) throws Exception;
	public Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception;
	public void updateStockEnvases() throws Exception;
	public void updateStockProductos() throws Exception;
	public void updateStockMateriasPrimas() throws Exception;
	public boolean setEANs13Producto(long idProducto, Vector<double[]> EANs13) throws Exception;
	public Vector<Producto> getEANs13Producto(long idProducto, String ean) throws Exception;
	public long getIdProductoLote(String lote) throws Exception;
	public Vector<LineaProducto> getEANs13Producto(long idProducto) throws Exception;
	public void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causas) throws Exception;
	public String validarLote(String lote) throws Exception;
	public Producto listaProductoLote(String lote) throws Exception;
}
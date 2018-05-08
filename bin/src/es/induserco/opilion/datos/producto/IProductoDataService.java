package es.induserco.opilion.datos.producto;

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

public interface IProductoDataService {

	Boolean addProducto(Producto producto, boolean especifica, long id) throws Exception;
	Vector getProductos(Producto producto, boolean categorizacion)throws Exception;
	Vector loadProducto(Producto entry)throws Exception;
	String getFechaRegistro()throws Exception;
	Vector getFamilias() throws Exception;
	Vector getEstados()throws Exception;
	Vector getCategorias()throws Exception;
	Vector getTipoProducto()throws Exception;
	Vector getGrupoProducto()throws Exception;
	Boolean addProductoMerma(ProductoMerma entry)throws Exception;
	Boolean deshabilitaProducto(Producto producto)throws Exception;
	Vector getMateriasPrimas(long idGrupo)throws Exception;
	long addProductoGetId(Producto producto) throws Exception;
	boolean setMateriasPrimasProducto(long idProducto, Vector<double[]> materias) throws Exception;
	boolean setEnvasesProducto(long idProducto, Vector<double[]> envases) throws Exception;
	boolean setProductoCompuesto(long idProducto, Vector<double[]> componentes)	throws Exception;
	Vector getEnvasesProducto(long idProducto, String ean) throws Exception;
	Vector getMateriasPrimasProducto(long idProducto, String ean) throws Exception;
	Vector getProductosComponen(long idProducto, boolean cantidades) throws Exception;
	Producto getProducto(long id) throws Exception;
	boolean eliminarProducto(long id) throws Exception;
	Vector getImpuestos() throws Exception;
	boolean setCampoProducto(long id, String columna, String valor) throws Exception;
	Vector getMateriasPrimasCategorias(long idMateriaPrima) throws Exception;
	long addMateriaPrima(MateriaPrima materiaPrima) throws Exception;
	Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry) throws Exception;
	boolean updateMateriaPrima(MateriaPrima materiaFind, MateriaPrima mpActualiza) throws Exception;
	Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo) throws Exception;
	Vector<Categoria> getCategoriasMateria(Long idMateriaPrima)throws Exception;
	void addStockProducto(String lote, double cantidad) throws Exception;
	void registrarGrupoProducto(GrupoProducto grupo) throws Exception;
	void registrarFamilia(Familia familia) throws Exception;
	void registrarCategoria(Categoria categoria) throws Exception;
	void registrarImpuesto(Impuesto impuesto) throws Exception;
	int devolver(String lote, double cantidad, String destino, Entidad e) throws Exception;
	Vector<Producto> getProductosMalDefinidos() throws Exception;
	Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro) throws Exception;
	Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception;
	void updateStockMateriasPrimas() throws Exception;
	void updateStockEnvases() throws Exception;
	void updateStockProductos() throws Exception;
	boolean setEANs13Producto(long idProducto, Vector<double[]> EANs13)	throws Exception;
	Vector<Producto> getEANs13Producto(long idProducto, String ean) throws Exception;
	long getIdProductoLote(String lote) throws Exception;
	Vector<LineaProducto> getEANs13Producto(long id) throws Exception;
	void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causas) throws Exception;
	String validarLote(String lote) throws Exception;
	Producto listaProductoLote(String lote) throws Exception;
}
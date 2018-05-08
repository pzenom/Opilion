package es.induserco.opilion.negocio;

import java.util.Vector;

import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.data.comun.GrupoProducto;
import es.induserco.opilion.data.entidades.Categoria;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Familia;
import es.induserco.opilion.data.entidades.Impuesto;
import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;
import es.induserco.opilion.datos.producto.IProductoDataService;

public class ProductoDataHelper {
	
	public Boolean addProducto (Producto producto, boolean especifica, long id) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).addProducto(producto, especifica, id);
	}

	public Vector getProductos(Producto producto, boolean categorizacion)throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getProductos(producto, categorizacion);
	}

	public Vector loadProducto(Producto entry)  throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).loadProducto(entry);
	}

	public String getFechaRegistro() throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getFechaRegistro();
	}

	public Vector getEstados()throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getEstados();
	}

	public Vector getFamilias()throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getFamilias();
	}

	public Vector getCategorias()throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getCategorias();
	}

	public Vector getGrupoProducto()throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getGrupoProducto();
	}

	public Vector getTipoProducto()throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getTipoProducto();
	
	}

	public Boolean addProductoMerma(ProductoMerma entry) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).addProductoMerma(entry);
	}

	public Boolean deshabilitaProducto(Producto producto) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).deshabilitaProducto(producto);
	}

	public Vector getMateriasPrimas(long idGrupo)throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getMateriasPrimas(idGrupo);
	}

	public long addProductoGetId(Producto producto) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).addProductoGetId(producto);
	}

	public boolean setMateriasPrimasProducto(long id, Vector<double[]> materias) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).setMateriasPrimasProducto(id, materias);
	}
	
	public boolean setEANs13Producto(long id, Vector<double[]> EANs13) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).setEANs13Producto(id, EANs13);
	}
	
	public boolean setEnvasesProducto(long id, Vector<double[]> envases) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).setEnvasesProducto(id, envases);
	}

	public boolean setProductoCompuesto(long id, Vector<double[]> compuesto) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).setProductoCompuesto(id, compuesto);
	}

	public Vector getEnvasesProducto(long idProducto, String ean) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getEnvasesProducto(idProducto, ean);
	}

	public Vector getMateriasPrimasProducto(long idProducto, String ean) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).
				getService("IProductoDataService")).getMateriasPrimasProducto(idProducto, ean);
	}

	public Vector getProductosComponen(long idProducto, boolean cantidades) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).
				getService("IProductoDataService")).getProductosComponen(idProducto, cantidades);
	}

	public Producto getProducto(long idProducto) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getProducto(idProducto);
	}

	public boolean eliminarProducto(long idProducto) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).eliminarProducto(idProducto);
	}

	public Vector getImpuestos() throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getImpuestos();
	}

	public boolean setCampoProducto(long id, String columna, String valor) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).setCampoProducto(id, columna, valor);
	}

	public Vector<MateriaPrima> getMateriasPrimasCategorias(long idMateriaPrima) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getMateriasPrimasCategorias(idMateriaPrima);
	}

	public long addMateriaPrima(MateriaPrima materiaPrima) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).addMateriaPrima(materiaPrima);
	}

	public Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).loadMateriaPrima(entry);
	}

	public boolean updateMateriaPrima(MateriaPrima materiaFind,
			MateriaPrima mpActualiza) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).updateMateriaPrima(materiaFind, mpActualiza);
	}

	public Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getMateriasPrimasBasicas(idGrupo);
	}

	public Vector<Categoria> getCategoriasMateria(Long idMateriaPrima) throws Exception {
		return((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getCategoriasMateria(idMateriaPrima);
	}

	public void addStockProducto(String lote, double cantidad) throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).addStockProducto(lote, cantidad);
	}

	public void registrarCategoria(Categoria categoria) throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).registrarCategoria(categoria);
	}

	public void registrarGrupoProducto(GrupoProducto grupo) throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).registrarGrupoProducto(grupo);
	}

	public void registrarFamilia(Familia familia) throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).registrarFamilia(familia);
	}

	public void registrarImpuesto(Impuesto impuesto) throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).registrarImpuesto(impuesto);
	}

	public int devolver(String lote, double cantidad, String destino, Entidad e) throws Exception {
		return ((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).devolver(lote, cantidad, destino, e);
	}

	public Vector<Producto> getProductosMalDefinidos() throws Exception{
		return ((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getProductosMalDefinidos();
	}

	public Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro) throws Exception {
		return ((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getEnvasesAgruparProducto(idProducto, filtro);
	}

	public Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception {
		return ((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getAgrupacionesEAN13(idProducto);
	}

	public void updateStockEnvases() throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).updateStockEnvases();
	}

	public void updateStockProductos() throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).updateStockProductos();
	}
	
	public void updateStockMateriasPrimas() throws Exception {
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).updateStockMateriasPrimas();
	}

	public Vector<Producto> getEANs13Producto(long idProducto, String ean) throws Exception{
		return((IProductoDataService)(new ServiceLocator()).
				getService("IProductoDataService")).getEANs13Producto(idProducto, ean);
	}

	public long getIdProductoLote(String lote) throws Exception{
		return ((IProductoDataService)(new ServiceLocator()).
				getService("IProductoDataService")).getIdProductoLote(lote);
	}

	public Vector<LineaProducto> getEANs13Producto(long idProducto) throws Exception {
		return ((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).getEANs13Producto(idProducto);
	}

	public void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causas) throws Exception{
		((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).modificarStockRegistro(lote, cantidad, idHueco, usuarioResponsable, causas);
	}

	public String validarLote(String lote) throws Exception{
		return ((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).validarLote(lote);
	}

	public Producto listaProductoLote(String lote) throws Exception{
		return ((IProductoDataService)(new ServiceLocator()).getService("IProductoDataService")).listaProductoLote(lote);
	}
}
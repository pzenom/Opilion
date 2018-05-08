package es.induserco.opilion.presentacion;

import java.util.Vector;
import es.induserco.opilion.infraestructura.ServiceLocator;
import es.induserco.opilion.negocio.gestionproductos.IGestionProductosService;

import es.induserco.opilion.data.comun.GrupoProducto;
import es.induserco.opilion.data.entidades.Categoria;
import es.induserco.opilion.data.entidades.Entidad;
import es.induserco.opilion.data.entidades.Familia;
import es.induserco.opilion.data.entidades.Impuesto;
import es.induserco.opilion.data.entidades.MateriaPrima;
import es.induserco.opilion.data.entidades.Producto;
import es.induserco.opilion.data.entidades.ProductoMerma;
import es.induserco.opilion.data.entidades.envasado.LineaProducto;

public class GestionProductosHelper {

	public Boolean addProducto(Producto producto, boolean especifica, long id) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).addProducto(producto, especifica, id);
	}

	public Vector getProductos(Producto entry, boolean categorizacion) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getProductos(entry, categorizacion);
	}

	public Vector loadProducto(Producto entry) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).loadProducto(entry);
	}

	public Vector getEstados() throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getEstados();
	}

	public Vector getFamilias() throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getFamilias();
	}

	public Vector getCategorias() throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getCategorias();
	}

	public Vector getTipoProducto() throws Exception {
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getTipoProducto();	
	}

	public Vector getGrupoProducto() throws Exception {
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getGrupoProducto();
	}	

	public Boolean addProductoMerma(ProductoMerma entry) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).addProductoMerma(entry);
	}

	public Boolean deshabilitaProducto(Producto producto) throws Exception {
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).deshabilitaProducto(producto);
	}

	public Vector getMateriasPrimas(long idGrupo) throws Exception {
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getMateriasPrimas(idGrupo);
	}

	public long addProductoGetId(Producto entry) throws Exception {
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).addProductoGetId(entry);
	}

	public boolean setEnvasesProducto(long idProducto, Vector<double[]> envases) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).setEnvasesProducto(idProducto, envases);
	}

	public boolean setMateriasPrimasProducto(long idProducto, Vector<double[]> materias) throws Exception{
		return ((IGestionProductosService) (new ServiceLocator()).getService("IGestionProductosService")).setMateriasPrimasProducto(idProducto, materias);
	}

	public boolean setProductoCompuesto(long idProducto,
			Vector<double[]> compuesto) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).setProductoCompuesto(idProducto, compuesto);
	}

	public Vector getProductosComponen(long idProducto, boolean cantidades) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getProductosComponen(idProducto, cantidades);
	}

	public Vector getMateriasPrimasProducto(long idProducto, String ean) throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getMateriasPrimasProducto(idProducto, ean);
	}

	public Vector getEnvasesProducto(long idProducto, String ean) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getEnvasesProducto(idProducto, ean);
	}

	public Producto getProducto(long idProducto) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getProducto(idProducto);
	}

	public boolean eliminarProducto(long idProducto) throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).eliminarProducto(idProducto);
	}

	public Vector getImpuestos() throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getImpuestos();
	}

	public boolean setCampoProducto(long idProducto, String columna, String valor) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).setCampoProducto(idProducto, columna, valor);
	}
	
	public Vector<MateriaPrima> getMateriasPrimasCategorias(long idMateriaPrima) throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getMateriasPrimasCategorias(idMateriaPrima);
	}

	public long addMateriaPrima(MateriaPrima materiaPrima) throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).addMateriaPrima(materiaPrima);
	}

	public Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry) throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).loadMateriaPrima(entry);
	}

	public boolean updateMateriaPrima(MateriaPrima materiaFind, MateriaPrima mpActualiza) throws Exception{
		return ((IGestionProductosService)
			(new ServiceLocator()).getService("IGestionProductosService")).updateMateriaPrima( materiaFind,	mpActualiza);
	}

	public Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getMateriasPrimasBasicas(idGrupo);
	}

	public Vector<Categoria> getCategoriasMateria(Long idMateriaPrima) throws Exception{
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getCategoriasMateria(idMateriaPrima);
	}

	public void addStockProducto(String lote, double cantidad) throws Exception{
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).addStockProducto(lote, cantidad);
	}

	public void registrarImpuesto(Impuesto impuesto) throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).registrarImpuesto(impuesto);
	}

	public void registrarCategoria(Categoria categoria) throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).registrarCategoria(categoria);
	}

	public void registrarFamilia(Familia familia) throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).registrarFamilia(familia);
	}

	public void registrarGrupoProducto(GrupoProducto grupo) throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).registrarGrupoProducto(grupo);
	}

	public int devolver(String lote, double cantidad, String destino, Entidad e) throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).devolver(lote, cantidad, destino, e);
	}

	public Vector<Producto> getProductosMalDefinidos() throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getProductosMalDefinidos();
	}

	public Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro) throws Exception {
		return ((IGestionProductosService) (new ServiceLocator()).getService("IGestionProductosService")).getEnvasesAgruparProducto(idProducto, filtro);
	}

	public Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception {
		return ((IGestionProductosService)
				(new ServiceLocator()).getService("IGestionProductosService")).getAgrupacionesEAN13(idProducto);
	}

	public void updateStockEnvases() throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).updateStockEnvases();
	}
	public void updateStockMateriasPrimas() throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).updateStockMateriasPrimas();
	}
	public void updateStockProductos() throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).updateStockProductos();
	}

	public boolean setEANs13Producto(long idProducto, Vector<double[]> EANs13) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).setEANs13Producto(idProducto, EANs13);
	}
	public Vector<Producto> getEANs13Producto(long idProducto, String ean) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getEANs13Producto(idProducto, ean);
	}
	public long getIdProductoLote(String lote) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getIdProductoLote(lote);
	}
	public Vector<LineaProducto> getEANs13Producto(long idProducto) throws Exception {
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).getEANs13Producto(idProducto);
	}

	public void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causas) throws Exception {
		((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).modificarStockRegistro(lote, cantidad, idHueco, usuarioResponsable, causas);
	}

	public String validarLote(String lote) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).validarLote(lote);
	}

	public Producto listaProductoLote(String lote) throws Exception{
		return ((IGestionProductosService)(new ServiceLocator()).getService("IGestionProductosService")).listaProductoLote(lote);
	}
}
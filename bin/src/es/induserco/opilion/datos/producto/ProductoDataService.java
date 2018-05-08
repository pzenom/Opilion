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
import es.induserco.opilion.datos.entidad.EntidadDAO;

public class ProductoDataService implements IProductoDataService {

	public Boolean addProducto(Producto producto, boolean especifica, long id)throws Exception{
		return (new ProductoDAO()).addProducto(producto, especifica, id);
	}

	public Vector getProductos(Producto producto, boolean categorizacion)throws Exception{
		return (new ProductoDAO()).getProductos(producto, categorizacion);
	}

	//@Override
	public Vector loadProducto(Producto entry) throws Exception {
		return (new ProductoDAO()).loadProducto(entry);
	}

	//@Override
	public String getFechaRegistro() throws Exception {
		return (new EntidadDAO()).getFechaRegistro();
	}

	//@Override
	public Vector getFamilias()throws Exception{
		return (new ProductoDAO()).getFamilias();
	}

	//@Override
	public Vector getEstados()throws Exception{
		return (new ProductoDAO()).getEstados();
	}
	
	//@Override
	public Vector getCategorias() throws Exception {
		return (new ProductoDAO()).getCategorias();
	}

	//@Override
	public Vector getTipoProducto() throws Exception {
		return (new ProductoDAO()).getTipoProducto();
	}

	//@Override
	public Vector getGrupoProducto() throws Exception {
		return (new ProductoDAO()).getGrupoProducto();
	}

	//@Override
	public Boolean addProductoMerma(ProductoMerma entry) throws Exception {
		return (new ProductoDAO()).addProductoMerma(entry);
	}

	//@Override
	public Boolean deshabilitaProducto(Producto producto) throws Exception {
		return (new ProductoDAO()).deshabilitaProducto(producto);
	}

	public Vector getMateriasPrimas(long idGrupo)throws Exception{
		return (new ProductoDAO()).getMateriasPrimas(idGrupo);
	}

	public long addProductoGetId(Producto entry)throws Exception{
		return (new ProductoDAO()).addProductoGetId(entry);
	}

	public boolean setMateriasPrimasProducto(long id, Vector<double[]> materias) throws Exception{
		return (new ProductoDAO()).setMateriasPrimasProducto(id, materias);
	}

	public boolean setEnvasesProducto(long id, Vector<double[]> envases) throws Exception{
		return (new ProductoDAO()).setEnvasesProducto(id, envases);
	}

	//@Override
	public boolean setProductoCompuesto(long idProducto, Vector<double[]> componentes) throws Exception {
		return (new ProductoDAO()).setProductoCompuesto(idProducto, componentes);
	}

	//@Override
	public Vector getEnvasesProducto(long idProducto, String ean) throws Exception {
		return (new ProductoDAO()).getEnvasesProducto(idProducto, ean);
	}

	//@Override
	public Vector getMateriasPrimasProducto(long idProducto, String ean) throws Exception {
		return (new ProductoDAO()).getMateriasPrimasProducto(idProducto, ean);
	}

	//@Override
	public Vector getProductosComponen(long idProducto, boolean cantidades) throws Exception {
		return (new ProductoDAO()).getProductosComponen(idProducto, cantidades);
	}
	
	public Producto getProducto(long id) throws Exception {
		return (new ProductoDAO()).getProducto(id);
	}

	public boolean eliminarProducto(long id) throws Exception {
		return (new ProductoDAO()).eliminarProducto(id);
	}

	public Vector getImpuestos() throws Exception{
		return (new ProductoDAO()).getImpuestos();
	}

	public boolean setCampoProducto(long id, String columna, String val) throws Exception{
		return (new ProductoDAO()).setCampoProducto(id, columna, val);
	}

	//@Override
	public Vector<MateriaPrima> getMateriasPrimasCategorias(long idMateriaPrima) throws Exception {
		return (new ProductoDAO()).getMateriasPrimasCategorias(idMateriaPrima);
	}

	//@Override
	public long addMateriaPrima(MateriaPrima materiaPrima) throws Exception {
		return (new ProductoDAO()).addMateriaPrima(materiaPrima);
	}

	//@Override
	public Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry) throws Exception {
		return (new ProductoDAO()).loadMateriaPrima(entry);
	}

	//@Override
	public boolean updateMateriaPrima(MateriaPrima materiaFind, MateriaPrima mpActualiza) throws Exception {
		return (new ProductoDAO()).updateMateriaPrima(materiaFind, mpActualiza);
	}

	//@Override
	public Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo)
			throws Exception {
		return (new ProductoDAO()).getMateriasPrimasBasicas(idGrupo);
	}

	//@Override
	public Vector<Categoria> getCategoriasMateria(Long idMateriaPrima) throws Exception {
		return (new ProductoDAO()).getCategoriasMateria(idMateriaPrima);
	}

	//@Override
	public void addStockProducto(String lote, double cantidad) throws Exception {
		(new ProductoDAO()).addStockProducto(lote, cantidad);
	}

	//@Override
	public void registrarCategoria(Categoria categoria) throws Exception {
		(new ProductoDAO()).registrarCategoria(categoria);
	}
	
	//@Override
	public void registrarFamilia(Familia familia) throws Exception {
		(new ProductoDAO()).registrarFamilia(familia);
	}

	//@Override
	public void registrarGrupoProducto(GrupoProducto grupo) throws Exception {
		(new ProductoDAO()).registrarGrupoProducto(grupo);
	}

	//@Override
	public void registrarImpuesto(Impuesto impuesto) throws Exception {
		(new ProductoDAO()).registrarImpuesto(impuesto);
	}

	//@Override
	public int devolver(String lote, double cantidad, String destino, Entidad e) throws Exception {
		return (new ProductoDAO()).devolver(lote, cantidad, destino, e);
	}

	//@Override
	public Vector<Producto> getProductosMalDefinidos() throws Exception {
		return (new ProductoDAO()).getProductosMalDefinidos();
	}

	//@Override
	public Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro) throws Exception {
		return (new ProductoDAO()).getEnvasesAgruparProducto(idProducto, filtro);
	}

	//@Override
	public Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception {
		return (new ProductoDAO()).getAgrupacionesEAN13(idProducto);
	}

	//@Override
	public void updateStockEnvases() throws Exception {
		(new ProductoDAO()).updateStockEnvases();
	}

	//@Override
	public void updateStockMateriasPrimas() throws Exception {
		(new ProductoDAO()).updateStockMateriasPrimas();
	}

	//@Override
	public void updateStockProductos() throws Exception {
		(new ProductoDAO()).updateStockProductos();
	}

	//@Override
	public boolean setEANs13Producto(long idProducto, Vector<double[]> EANs13) throws Exception {
		return (new ProductoDAO()).setEANs13Producto(idProducto, EANs13);
	}
	
	//@Override
	public Vector<Producto> getEANs13Producto(long idProducto, String ean) throws Exception {
		return (new ProductoDAO()).getEANs13Producto(idProducto, ean);
	}

	//@Override
	public long getIdProductoLote(String lote) throws Exception {
		return (new ProductoDAO()).getIdProductoLote(lote);
	}

	//@Override
	public Vector<LineaProducto> getEANs13Producto(long id) throws Exception {
		return (new ProductoDAO()).getEANs13Producto(id);
	}

	public void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causas) throws Exception {
		new ProductoDAO().modificarStockRegistro(lote, cantidad, idHueco, usuarioResponsable, causas);
	}

	public String validarLote(String lote) throws Exception {
		return (new ProductoDAO()).validarLote(lote);
	}

	public Producto listaProductoLote(String lote) throws Exception {
		return (new ProductoDAO()).listaProductoLote(lote);
	}
}
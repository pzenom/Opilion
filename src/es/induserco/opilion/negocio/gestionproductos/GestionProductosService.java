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

public class GestionProductosService implements IGestionProductosService{

	//@Override
	public Boolean addProducto(Producto producto, boolean especifica, long id) throws Exception{
		return new GestionProductosBB().addProducto(producto, especifica, id);
	}

	//@Override
	public long addProductoGetId(Producto producto) throws Exception{
		return new GestionProductosBB().addProductoGetId(producto);
	}

	//@Override
	public Vector getProductos(Producto producto, boolean categorizacion) throws Exception{
		return new GestionProductosBB().getProductos(producto, categorizacion);
	}

	//@Override
	public Vector loadProducto(Producto entry) throws Exception {
		return new GestionProductosBB().loadProducto(entry);		
	}

	//@Override
	public Vector getFamilias() throws Exception{
		return new GestionProductosBB().getFamilias();    
	}

	//@Override
	public Vector getEstados() throws Exception{
		return new GestionProductosBB().getEstados();    
	}

	//@Override
	public Vector getCategorias() throws Exception{
		return new GestionProductosBB().getCategorias();    
	}

	//@Override
	public Vector getGrupoProducto() throws Exception{
		return new GestionProductosBB().getGrupoProducto();    
	}

	//@Override
	public Vector getTipoProducto() throws Exception{
		return new GestionProductosBB().getTipoProducto();    
	}

	//@Override
	public Boolean addProductoMerma(ProductoMerma entry) throws Exception{
		return new GestionProductosBB().addProductoMerma(entry);
	}

	//@Override
	public Boolean deshabilitaProducto(Producto producto) throws Exception {
		return new GestionProductosBB().deshabilitaProducto(producto);
	}

	//@Override
	public Vector getMateriasPrimas(long idGrupo) throws Exception{
		return new GestionProductosBB().getMateriasPrimas(idGrupo);
	}

	//@Override
	public boolean setMateriasPrimasProducto(long id, Vector<double[]> materias) throws Exception{
		return new GestionProductosBB().setMateriasPrimasProducto(id, materias);    
	}

	//@Override
	public boolean setEnvasesProducto(long id, Vector<double[]> envases) throws Exception{
		return new GestionProductosBB().setEnvasesProducto(id, envases);    
	}

	//@Override
	public boolean setProductoCompuesto(long id, Vector<double[]> c) throws Exception{
		return new GestionProductosBB().setProductoCompuesto(id, c);    
	}

	//@Override
	public Vector getEnvasesProducto(long idProducto, String ean) throws Exception {
		return new GestionProductosBB().getEnvasesProducto(idProducto, ean);    
	}

	//@Override
	public Vector getMateriasPrimasProducto(long idProducto, String ean) throws Exception {
		return new GestionProductosBB().getMateriasPrimasProducto(idProducto, ean);  
	}

	//@Override
	public Vector getProductosComponen(long idProducto, boolean cantidades) throws Exception {
		return new GestionProductosBB().getProductosComponen(idProducto, cantidades);  
	}

	//@Override
	public Producto getProducto(long idProducto) throws Exception {
		return new GestionProductosBB().getProducto(idProducto);  
	}

	public boolean eliminarProducto(long idProducto) throws Exception {
		return new GestionProductosBB().eliminarProducto(idProducto);  
	}

	public Vector getImpuestos() throws Exception {
		return new GestionProductosBB().getImpuestos();
	}

	public boolean setCampoProducto(long idProducto, String columna, String valor) throws Exception{
		return new GestionProductosBB().setCampoProducto(idProducto, columna, valor);
	}

	//@Override
	public Vector<MateriaPrima> getMateriasPrimasCategorias(long idMateriaPrima)throws Exception {
		return new GestionProductosBB().getMateriasPrimasCategorias(idMateriaPrima);
	}

	//@Override
	public long addMateriaPrima(MateriaPrima materiaPrima) throws Exception {
		return new GestionProductosBB().addMateriaPrima(materiaPrima);
	}

	//@Override
	public Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry) throws Exception {
		return new GestionProductosBB().loadMateriaPrima(entry);
	}

	//@Override
	public boolean updateMateriaPrima(MateriaPrima materiaFind,MateriaPrima mpActualiza) throws Exception {
		return new GestionProductosBB().updateMateriaPrima(materiaFind, mpActualiza);
	}

	//@Override
	public Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo)throws Exception {
		return new GestionProductosBB().getMateriasPrimasBasicas(idGrupo);
	}

	//@Override
	public Vector<Categoria> getCategoriasMateria(Long idMateriaPrima)throws Exception {
		return new GestionProductosBB().getCategoriasMateria(idMateriaPrima);
	}

	//@Override
	public void addStockProducto(String lote, double cantidad) throws Exception {
		new GestionProductosBB().addStockProducto(lote, cantidad);
	}

	//@Override
	public void registrarCategoria(Categoria categoria) throws Exception {
		new GestionProductosBB().registrarCategoria(categoria);
	}

	//@Override
	public void registrarFamilia(Familia familia) throws Exception {
		new GestionProductosBB().registrarFamilia(familia);
	}

	//@Override
	public void registrarGrupoProducto(GrupoProducto grupo) throws Exception {
		new GestionProductosBB().registrarGrupoProducto(grupo);
	}

	//@Override
	public void registrarImpuesto(Impuesto impuesto) throws Exception {
		new GestionProductosBB().registrarImpuesto(impuesto);
	}

	//@Override
	public int devolver(String lote, double cantidad, String destino, Entidad e)throws Exception {
		return new GestionProductosBB().devolver(lote, cantidad, destino, e);
	}

	//@Override
	public Vector<Producto> getProductosMalDefinidos() throws Exception {
		return new GestionProductosBB().getProductosMalDefinidos();
	}

	//@Override
	public Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro)throws Exception {
		return new GestionProductosBB().getEnvasesAgruparProducto(idProducto, filtro);
	}

	//@Override
	public Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception {
		return new GestionProductosBB().getAgrupacionesEAN13(idProducto);
	}

	//@Override
	public void updateStockEnvases() throws Exception {
		new GestionProductosBB().updateStockEnvases();
	}

	//@Override
	public void updateStockMateriasPrimas() throws Exception {
		new GestionProductosBB().updateStockMateriasPrimas();
	}

	//@Override
	public void updateStockProductos() throws Exception {
		new GestionProductosBB().updateStockProductos();
	}

	//@Override
	public boolean setEANs13Producto(long idProducto, Vector<double[]> EANs13) throws Exception {
		return new GestionProductosBB().setEANs13Producto(idProducto, EANs13);
	}

	//@Override
	public Vector<Producto> getEANs13Producto(long idProducto, String ean) throws Exception {
		return new GestionProductosBB().getEANs13Producto(idProducto, ean);
	}

	//@Override
	public long getIdProductoLote(String lote) throws Exception {
		return new GestionProductosBB().getIdProductoLote(lote);
	}

	//@Override
	public Vector<LineaProducto> getEANs13Producto(long idProducto) throws Exception {
		return new GestionProductosBB().getEANs13Producto(idProducto);
	}

	public void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causas) throws Exception {
		new GestionProductosBB().modificarStockRegistro(lote, cantidad, idHueco, usuarioResponsable, causas);
	}

	public String validarLote(String lote) throws Exception {
		return new GestionProductosBB().validarLote(lote);
	}

	public Producto listaProductoLote(String lote) throws Exception {
		return new GestionProductosBB().listaProductoLote(lote);
	}
}
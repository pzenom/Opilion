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
import es.induserco.opilion.negocio.ProductoDataHelper;

public class GestionProductosBB implements IGestionProductosService{

	//@Override
	public Boolean addProducto(Producto producto, boolean especifica, long id) throws Exception {
		return new ProductoDataHelper().addProducto(producto, especifica, id);		
	}

	//@Override
	public long addProductoGetId(Producto producto) throws Exception {
		return new ProductoDataHelper().addProductoGetId(producto);		
	}

	//@Override
	public Vector getProductos(Producto producto, boolean categorizacion) throws Exception{	
		//Recuperamos la lista de entidades
		ProductoDataHelper edh = new ProductoDataHelper();
		Vector productos = edh.getProductos(producto, categorizacion);
		return productos;
	}

	//@Override
	public Vector loadProducto(Producto entry) throws Exception {
		return new ProductoDataHelper().loadProducto(entry);		
	}

	//@Override
	public Vector getEstados() throws Exception{	
		//Recuperamos la lista de entidades
		ProductoDataHelper edh = new ProductoDataHelper();
		Vector estados = edh.getEstados();
		//si hay que aplicar alguna regla del negocio aqui!!!
		return estados;
	}	

	//@Override
	public Vector getFamilias() throws Exception{	
		//Recuperamos la lista de entidades
		ProductoDataHelper edh = new ProductoDataHelper();
		Vector familias = edh.getFamilias();
		//si hay que aplicar alguna regla del negocio aqui!!!
		return familias;
	}

	//@Override
	public Vector getCategorias() throws Exception {
		ProductoDataHelper edh = new ProductoDataHelper();
		Vector categoria = edh.getCategorias();
		return categoria;
	}

	//@Override
	public Vector getGrupoProducto() throws Exception {
		//Recuperamos la lista de entidades
		ProductoDataHelper edh = new ProductoDataHelper();
		Vector grupos = edh.getGrupoProducto();
		//si hay que aplicar alguna regla del negocio aqui!!!
		return grupos;
	}

	//@Override
	public Vector getTipoProducto() throws Exception {
		ProductoDataHelper edh = new ProductoDataHelper();
		Vector tipos = edh.getTipoProducto();
		return tipos;
	}

	//@Override
	public Boolean addProductoMerma(ProductoMerma entry) throws Exception {
		return new ProductoDataHelper().addProductoMerma(entry);
	}

	//@Override
	public Boolean deshabilitaProducto(Producto producto) throws Exception {
		return new ProductoDataHelper().deshabilitaProducto(producto);
	}	

	//@Override
	public Vector getMateriasPrimas(long idGrupo) throws Exception{	
		ProductoDataHelper edh = new ProductoDataHelper();
		Vector materiasprimas = edh.getMateriasPrimas(idGrupo);
		return materiasprimas;
	}

	//@Override
	public boolean setMateriasPrimasProducto(long id, Vector<double[]> materias) throws Exception {
		return new ProductoDataHelper().setMateriasPrimasProducto(id, materias);
	}

	//@Override
	public boolean setEnvasesProducto(long id, Vector<double[]> envases) throws Exception {
		return new ProductoDataHelper().setEnvasesProducto(id, envases);
	}

	//@Override
	public boolean setProductoCompuesto(long id, Vector<double[]> compuesto) throws Exception {
		return new ProductoDataHelper().setProductoCompuesto(id, compuesto);
	}

	//@Override
	public Vector getEnvasesProducto(long idProducto, String ean) throws Exception {
		return new ProductoDataHelper().getEnvasesProducto(idProducto, ean);
	}

	//@Override
	public Vector getMateriasPrimasProducto(long idProducto, String ean) throws Exception {
		return new ProductoDataHelper().getMateriasPrimasProducto(idProducto, ean);
	}

	//@Override
	public Vector getProductosComponen(long idProducto, boolean cantidades) throws Exception {
		return new ProductoDataHelper().getProductosComponen(idProducto, cantidades);
	}

	public Producto getProducto(long idProducto) throws Exception {
		return new ProductoDataHelper().getProducto(idProducto);
	}

	public boolean eliminarProducto(long idProducto) throws Exception {
		return new ProductoDataHelper().eliminarProducto(idProducto);
	}

	public Vector getImpuestos() throws Exception{
		return new ProductoDataHelper().getImpuestos();
	}

	public boolean setCampoProducto(long id, String columna, String valor) throws Exception{
		return new ProductoDataHelper().setCampoProducto(id, columna, valor);
	}

	//@Override
	public Vector<MateriaPrima> getMateriasPrimasCategorias(long idMateriaPrima)
			throws Exception {
		return new ProductoDataHelper().getMateriasPrimasCategorias(idMateriaPrima);
	}

	//@Override
	public long addMateriaPrima(MateriaPrima materiaPrima) throws Exception {
		return new ProductoDataHelper().addMateriaPrima(materiaPrima);
	}

	//@Override
	public Vector<MateriaPrima> loadMateriaPrima(MateriaPrima entry) throws Exception {
		return new ProductoDataHelper().loadMateriaPrima(entry);
	}

	//@Override
	public boolean updateMateriaPrima(MateriaPrima materiaFind,
			MateriaPrima mpActualiza) throws Exception {
		return new ProductoDataHelper().updateMateriaPrima(materiaFind, mpActualiza);
	}

	//@Override
	public Vector<MateriaPrima> getMateriasPrimasBasicas(long idGrupo)
			throws Exception {
		return new ProductoDataHelper().getMateriasPrimasBasicas(idGrupo);
	}

	//@Override
	public Vector<Categoria> getCategoriasMateria(Long idMateriaPrima)
			throws Exception {
		return new ProductoDataHelper().getCategoriasMateria(idMateriaPrima);
	}

	//@Override
	public void addStockProducto(String lote, double cantidad) throws Exception {
		new ProductoDataHelper().addStockProducto(lote, cantidad);
	}

	//@Override
	public void registrarCategoria(Categoria categoria) throws Exception {
		new ProductoDataHelper().registrarCategoria(categoria);
	}

	//@Override
	public void registrarFamilia(Familia familia) throws Exception {
		new ProductoDataHelper().registrarFamilia(familia);
	}

	//@Override
	public void registrarGrupoProducto(GrupoProducto grupo) throws Exception {
		new ProductoDataHelper().registrarGrupoProducto(grupo);
	}

	//@Override
	public void registrarImpuesto(Impuesto impuesto) throws Exception {
		new ProductoDataHelper().registrarImpuesto(impuesto);
	}

	//@Override
	public int devolver(String lote, double cantidad, String destino, Entidad e)
			throws Exception {
		return new ProductoDataHelper().devolver(lote, cantidad, destino, e);
	}

	//@Override
	public Vector<Producto> getProductosMalDefinidos() throws Exception {
		return new ProductoDataHelper().getProductosMalDefinidos();
	}

	//@Override
	public Vector<LineaProducto> getEnvasesAgruparProducto(long idProducto, String filtro) throws Exception {
		return new ProductoDataHelper().getEnvasesAgruparProducto(idProducto, filtro);
	}

	//@Override
	public Vector<LineaProducto> getAgrupacionesEAN13(long idProducto) throws Exception {
		return new ProductoDataHelper().getAgrupacionesEAN13(idProducto);
	}

	//@Override
	public void updateStockEnvases() throws Exception {
		new ProductoDataHelper().updateStockEnvases();
	}

	//@Override
	public void updateStockMateriasPrimas() throws Exception {
		new ProductoDataHelper().updateStockMateriasPrimas();
	}

	//@Override
	public void updateStockProductos() throws Exception {
		new ProductoDataHelper().updateStockProductos();
	}

	//@Override
	public boolean setEANs13Producto(long idProducto, Vector<double[]> EANs13) throws Exception {
		return new ProductoDataHelper().setEANs13Producto(idProducto, EANs13);
	}

	//@Override
	public Vector<Producto> getEANs13Producto(long idProducto, String ean) throws Exception {
		return new ProductoDataHelper().getEANs13Producto(idProducto, ean);
	}

	//@Override
	public long getIdProductoLote(String lote) throws Exception {
		return new ProductoDataHelper().getIdProductoLote(lote);
	}

	//@Override
	public Vector<LineaProducto> getEANs13Producto(long idProducto) throws Exception {
		return new ProductoDataHelper().getEANs13Producto(idProducto);
	}

	public void modificarStockRegistro(String lote, double cantidad, long idHueco, String usuarioResponsable, String causas) throws Exception {
		new ProductoDataHelper().modificarStockRegistro(lote, cantidad, idHueco, usuarioResponsable, causas);
	}

	public String validarLote(String lote) throws Exception {
		return new ProductoDataHelper().validarLote(lote);
	}

	public Producto listaProductoLote(String lote) throws Exception {
		return new ProductoDataHelper().listaProductoLote(lote);
	}
}
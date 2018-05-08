package es.induserco.edifact.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import es.induserco.edifact.data.x12.DTM;

public class Order implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long idOrders;
	private String unh;
	private String bgmTipo;
	private String bgmNum;
	private String bgmFunc;
	private List <DTM> dtm;
	private String aliCond;
	private String ftxCali;
	private String nadMs;
	private String rffMs;
	private String nadMr;
	private String rffMr;
	private String nadSu;
	private String rffSu;
	private String nadBy;
	private String rffBy;
	private String nadDp;
	private String rffDp;
	private String nadIv;
	private String rffIv;
	private String nadPr;
	private String rffPr;
	private String cux;
	private List <OrdersLin> lin;
	private String moa79;
	private String cnt;
	private String unt;
	private char estado;
	private String descripcionEstado, descripcionFormaPago;
	private long idFormaPago, numeroDireccionesDistintas;
	private String responsable, nombreResponsable, observacionesPedido, nombreCliente;

	public Order() { super(); 
		dtm = new ArrayList<DTM>();
		lin = new ArrayList<OrdersLin>();
	}

	public Order(Long idOrders, String unh, String bgmTipo, String bgmNum,
			String bgmFunc, List<DTM> dtm, String aliCond, String ftxCali,
			String nadMs, String rffMs, String nadMr, String rffMr,
			String nadSu, String rffSu, String nadBy, String rffBy,
			String nadDp, String rffDp, String nadIv, String rffIv,
			String nadPr, String rffPr, String cux, List<OrdersLin> lin,
			String moa79, String cnt, String unt, char estado, long idFormaPago) {
		this.idOrders = idOrders;
		this.unh = unh;
		this.bgmTipo = bgmTipo;
		this.bgmNum = bgmNum;
		this.bgmFunc = bgmFunc;
		this.dtm = dtm;
		this.aliCond = aliCond;
		this.ftxCali = ftxCali;
		this.nadMs = nadMs;
		this.rffMs = rffMs;
		this.nadMr = nadMr;
		this.rffMr = rffMr;
		this.nadSu = nadSu;
		this.rffSu = rffSu;
		this.nadBy = nadBy;
		this.rffBy = rffBy;
		this.nadDp = nadDp;
		this.rffDp = rffDp;
		this.nadIv = nadIv;
		this.rffIv = rffIv;
		this.nadPr = nadPr;
		this.rffPr = rffPr;
		this.cux = cux;
		this.lin = lin;
		this.moa79 = moa79;
		this.cnt = cnt;
		this.unt = unt;
		this.setEstado(estado);
		this.idFormaPago = idFormaPago;
	}

	public Long getIdOrders() { return idOrders; }
	public String getUnh() { return unh; }
	public String getBgmTipo() { return bgmTipo; }
	public String getBgmNum() { return bgmNum; }
	public String getBgmFunc() { return bgmFunc; }
	public List<DTM> getDtm() { return dtm; }
	public String getAliCond() { return aliCond; }
	public String getFtxCali() { return ftxCali; }
	public String getNadMs() { return nadMs; }
	public String getRffMs() { return rffMs; }
	public String getNadMr() { return nadMr; }
	public String getRffMr() { return rffMr; }
	public String getNadSu() { return nadSu; }
	public String getRffSu() { return rffSu; }
	public String getNadBy() { return nadBy; }
	public String getRffBy() { return rffBy; }
	public String getNadDp() { return nadDp; }
	public String getRffDp() { return rffDp; }
	public String getNadIv() { return nadIv; }
	public String getRffIv() { return rffIv; }
	public String getNadPr() { return nadPr; }
	public String getRffPr() { return rffPr; }
	public String getCux() { return cux; }
	public List<OrdersLin> getLin() { return lin; }
	public String getMoa79() { return moa79; }
	public String getCnt() { return cnt; }
	public String getUnt() {  return unt; }
	public char getEstado() { return estado; }
	public long getIdFormaPago() { return idFormaPago; }
	public String getNombreResponsable() { return nombreResponsable; }
	public JRDataSource getProductosDS() { return new JRBeanCollectionDataSource(this.lin); }

	public void setIdOrders(Long idOrders) { this.idOrders = idOrders; }
	public void setUnh(String unh) { this.unh = unh; }
	public void setBgmTipo(String bgmTipo) { this.bgmTipo = bgmTipo; }
	public void setBgmNum(String bgmNum) { this.bgmNum = bgmNum; }
	public void setBgmFunc(String bgmFunc) { this.bgmFunc = bgmFunc; }
	public void setDtm(List<DTM> dtm) 	   { this.dtm = dtm; }
	public void setDtm(DTM dtm) 		   { this.dtm.add(dtm); }
	public void setAliCond(String aliCond) { this.aliCond = aliCond; }
	public void setFtxCali(String ftxCali) { this.ftxCali = ftxCali; }
	public void setNadMs(String nadMs) { this.nadMs = nadMs; }
	public void setRffMs(String rffMs) { this.rffMs = rffMs; }
	public void setNadMr(String nadMr) { this.nadMr = nadMr; }
	public void setRffMr(String rffMr) { this.rffMr = rffMr; }
	public void setNadSu(String nadSu) { this.nadSu = nadSu; }
	public void setRffSu(String rffSu) { this.rffSu = rffSu; }
	public void setNadBy(String nadBy) { this.nadBy = nadBy; }
	public void setRffBy(String rffBy) { this.rffBy = rffBy; }
	public void setNadDp(String nadDp) { this.nadDp = nadDp; }
	public void setRffDp(String rffDp) { this.rffDp = rffDp; }
	public void setNadIv(String nadIv) { this.nadIv = nadIv; }
	public void setRffIv(String rffIv) { this.rffIv = rffIv; }
	public void setNadPr(String nadPr) { this.nadPr = nadPr; }
	public void setRffPr(String rffPr) { this.rffPr = rffPr; }
	public void setCux(String cux) { this.cux = cux; }
	public void setLin(List<OrdersLin> lin) { this.lin = lin; }
	public void setLin(OrdersLin lin) { this.lin.add(lin); }
	public void setMoa79(String moa79) { this.moa79 = moa79; }
	public void setCnt(String cnt) { this.cnt = cnt; }
	public void setUnt(String unt) { this.unt = unt; }
	public void setEstado(char estado) { this.estado = estado; }
	public void setIdFormaPago(long idFormaPago) { this.idFormaPago = idFormaPago; }
	public void setNombreResponsable(String nombreResponsable) { this.nombreResponsable = nombreResponsable; }

	public void setDescripcionEstado(String descripcionEstado) { this.descripcionEstado = descripcionEstado; }
	public String getDescripcionEstado() { return descripcionEstado; }

	public void setDescripcionFormaPago(String descripcionFormaPago) { this.descripcionFormaPago = descripcionFormaPago; }
	public String getDescripcionFormaPago() { return descripcionFormaPago; }

	public void setNumeroDireccionesDistintas(long numeroDireccionesDistintas) {
		this.numeroDireccionesDistintas = numeroDireccionesDistintas;
	}

	public long getNumeroDireccionesDistintas() {
		return numeroDireccionesDistintas;
	}

	public void setObservacionesPedido(String observacionesPedido) {
		this.observacionesPedido = observacionesPedido;
	}

	public String getObservacionesPedido() {
		return observacionesPedido;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public String getResponsable() {
		return responsable;
	}
}
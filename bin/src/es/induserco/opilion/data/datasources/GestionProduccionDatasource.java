package es.induserco.opilion.data.datasources;

import java.util.ArrayList;
import java.util.List;

import es.induserco.opilion.data.entidades.GestionProduccion;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class GestionProduccionDatasource implements JRDataSource {

	private List<GestionProduccion> listaGestionProduccion = new ArrayList<GestionProduccion>();
	private int indiceGestionProduccionActual = -1;
	
	//@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
        if ("orden".equals(jrf.getName())){
            valor = listaGestionProduccion.get(indiceGestionProduccionActual).getOrden();
        }
        return valor;
	}

	//@Override
	public boolean next() throws JRException {
		 return ++indiceGestionProduccionActual < listaGestionProduccion.size();
    }

    public void addParticipante(GestionProduccion gestionProduccion){
        this.listaGestionProduccion.add(gestionProduccion);
    }
}
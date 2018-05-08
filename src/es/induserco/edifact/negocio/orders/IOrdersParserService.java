package es.induserco.edifact.negocio.orders;

import java.util.Vector;

public interface IOrdersParserService {

	public Vector orderParse (String fichero) throws Exception;
}
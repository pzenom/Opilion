package es.induserco.edifact.negocio.orders;

import java.util.Vector;

public class OrdersParserService implements IOrdersParserService {

	public Vector orderParse(String fichero) throws Exception {
		System.out.println("OrdersParsersService: orderParse");
		return new OrdersParserBB().orderParse(fichero);
	}
}
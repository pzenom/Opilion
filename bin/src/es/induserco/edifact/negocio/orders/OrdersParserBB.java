package es.induserco.edifact.negocio.orders;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

import es.induserco.edifact.data.Order;
import es.induserco.edifact.data.OrdersLin;
import es.induserco.edifact.data.OrdersLoc;
import es.induserco.edifact.data.x12.DTM;
import es.induserco.edifact.data.x12.IMD;
import es.induserco.edifact.negocio.EdifactParserDataHelper;

public class OrdersParserBB implements IOrdersParserService {

	public ArrayList <String> fichero = new ArrayList <String>();
	public ArrayList <String> fichero2 = new ArrayList <String>();
	public Order pedido = new Order();
	private String mensaje;

	public Vector orderParse(String fichero) throws Exception {
		//nos viene un fichero, lo parseamos y creamos una estructura
		System.out.println("OrdersParseBB: Where the magic happens");
		System.out.println("\n------------------------------");
		System.out.println("-------- FASE I - OBJ --------");
		System.out.println("------------------------------");
		Vector resultado = new Vector();
		//Creamos la estructura pedido con el parser
		if(parseador(filtraErrores(fichero))) {
			System.out.println("*******ERROR********");
			System.out.println(mensaje);
			resultado.add(mensaje);
			return resultado;
		}
		//La estructura est� creada, pero ya est� en el sistema?
		//new EdifactParserDataHelper().orderExists(pedido);
		if(new EdifactParserDataHelper().orderExists(pedido)) {
			System.out.println("*******ERROR********");
			mensaje="[ERROR] El fichero ya ha sido subido previamente, o hay un fichero en conflicto";
			resultado.add(mensaje);
			return resultado;
		}
		System.out.println("\n------------------------------");
		System.out.println("-------- FASE II - BD --------");
		System.out.println("------------------------------");
		new EdifactParserDataHelper().orderParse(pedido);
		resultado.add(pedido);
		return resultado;
		//return pedido;
	}

	public String filtraErrores (String fichero) {
		String ficheroCompleto = new String();
		try{
			FileInputStream fstream = new FileInputStream(fichero);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				//Meto todo lo que leo del fichero en un string
				ficheroCompleto=ficheroCompleto+strLine;
				// Print the content on the console
				System.out.println (strLine);
				//Si la linea no esta vacia
				if(strLine.length()!=0) {
					//si el ultimo caracter es una comilla simple -> '
					//entonces me la quedo
					this.fichero.add(strLine);
				}
			}
			//	Close the input stream
			in.close();
			System.out.println("-----------FICHERO UNIFICADO-------------------");
			System.out.println(ficheroCompleto.toString());
	    }catch (Exception e){//Catch exception if any
	    		System.err.println("Error: " + e.getMessage());
	   	}
		return ficheroCompleto.toString();
	}

	//He aqu� nuestro parser de l�neas
	public Boolean parseador(String ficheroCompleto) {
		String [] temporal;
		String [] tokens;
		String [] validador;
		String nodoPadre=new String(); //para saber el ultimo nodo
		Long numeroDeLinea = 0L; //para llevar el registro de las l�neas de productos
		OrdersLin lin = new OrdersLin();
		OrdersLoc loc = new OrdersLoc();
		temporal=ficheroCompleto.split("'");
		System.out.println("Hemos reconocido "+temporal.length+" lineas");
		System.out.println("-----------IMPRIME PARSEADOR--------------------");
		for (int i=0; i<temporal.length;i++) {
			System.out.println("["+(i+1)+"] "+temporal[i].toString()+"'");
			//fichero2.add(temporal[i]);
			//tipo(temporal[i]);
			tokens = temporal[i].split("[+]");
			tokens[0]=tokens[0].trim();
			if(tokens[0].equalsIgnoreCase("UNH")){
				System.out.println("-----------UNH--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
				pedido.setUnt(tokens[1].trim());
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"]");
				validador=tokens[2].trim().split(":");
				if (validador[0].equalsIgnoreCase("ORDERS")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Bien");
					if(validador[1].equalsIgnoreCase("D")){
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Bien");
						if(validador[2].equalsIgnoreCase("96A")){
							System.out.println(">>>>>>>>>>>>>>>>["+validador[2]+"] Bien");
							if(validador[3].equalsIgnoreCase("UN")){
								System.out.println(">>>>>>>>>>>>>>>>["+validador[3]+"] Bien");
								if(validador[4].equalsIgnoreCase("EAN008")){
									System.out.println(">>>>>>>>>>>>>>>>["+validador[4]+"] Bien");
									pedido.setUnh(tokens[1]);
								}else { mensaje="[ERROR][UNH] No es un D";
									System.out.println("[ERROR][UNH] No es un D"); return true; }
							}else { mensaje="[ERROR][UNH] No es un UN";
								System.out.println("[ERROR][UNH] No es un UN");return true; }
						}else { mensaje="[ERROR][UNH] No es un D";
							System.out.println("[ERROR][UNH] No es un D"); return true; }
					}else { mensaje = "[ERROR][UNH] No es un D"; 
						System.out.println("[ERROR][UNH] No es un D"); return true; }
				} else { mensaje ="[ERROR][UNH] No es un ORDER";
					System.out.println("[ERROR][UNH] No es un ORDER"); return true; }
			}else if(tokens[0].equalsIgnoreCase("BGM")){
				System.out.println("-----------BGM--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[1].trim().split(":");
				if (validador[0].equalsIgnoreCase("220")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Pedido");
				} else if (validador[0].equalsIgnoreCase("22E")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Propuesta de Pedido");
				} else if (validador[0].equalsIgnoreCase("227")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Pedido en Consigna");
				} else if (validador[0].equalsIgnoreCase("YB1")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Propuesta de Cross Docking");
				} else { mensaje="[ERROR][BGM] Codificacion de pedido no reconocido";
					System.out.println("[ERROR][BGM] Codificacion de pedido no reconocido"); return true; }				
				pedido.setBgmTipo(validador[0]);
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"]");
				pedido.setBgmNum(tokens[2].trim());
				if (tokens[3].equalsIgnoreCase("9")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[3]+"] Original");
					pedido.setBgmFunc(tokens[3].trim());
				}else if(tokens[3].equalsIgnoreCase("16")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[3]+"] Propuesta");
					pedido.setBgmFunc(tokens[3].trim());
				}else { mensaje="[ERROR][BMG] No es una funci�n del Mensaje reconocida ";
					System.out.println("[ERROR][BMG] No es una funci�n del Mensaje reconocida "); return true; }
			}else if(tokens[0].equalsIgnoreCase("DTM")){
				System.out.println("-----------DTM--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
				validador=tokens[1].trim().split(":");
				//TODO: Faltar�a meter el valor idOrden en la clase DTM
				DTM dtm = new DTM(null, validador[1], validador[2], validador[0]);
				pedido.setDtm(dtm);
				if (validador[0].equalsIgnoreCase("137")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Fecha/Hora del Mensaje");
				}else if(validador[0].equalsIgnoreCase("2")){
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Fecha/Hora de Entrega");
				}else if(validador[0].equalsIgnoreCase("63")){
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] �ltima Fecha/Hora de Entrega");
				}else if(validador[0].equalsIgnoreCase("64")){
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Primera Fecha/Hora de Entrega");
				}else { mensaje="[ERROR][DTM] No es una Calificador de Fecha Reconocido ";
					System.out.println("[ERROR][DTM] No es una Calificador de Fecha Reconocido "); return true; }
				System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Fecha/Hora Introducida");
				if (validador[2].equalsIgnoreCase("101")) {
					if (validador[1].length()==6)
						System.out.println(">>>>>>>>>>>>>>>>["+validador[2]+"] Fecha Correcta");
					else { mensaje="[ERROR][DTM] El formato de fecha no corresponde a su logitud esperada (6)"+validador[1].length();
						System.out.println("[ERROR][DTM] El formato de fecha no corresponde a su logitud esperada (6)"+validador[1].length()); }
				}else if (validador[2].equalsIgnoreCase("102")) {
					if (validador[1].length()==8)
						System.out.println(">>>>>>>>>>>>>>>>["+validador[2]+"] Fecha Correcta");
					else {
						mensaje="[ERROR][DTM] El formato de fecha no corresponde a su logitud esperada (8)"+validador[1].length();
						System.out.println("[ERROR][DTM] El formato de fecha no corresponde a su logitud esperada (8)"+validador[1].length()); return true; }
				}else if (validador[2].equalsIgnoreCase("203")) {
					if (validador[1].length()==12)
						System.out.println(">>>>>>>>>>>>>>>>["+validador[2]+"] Fecha Correcta");
					else {
						mensaje="[ERROR][DTM] El formato de fecha no corresponde a su logitud esperada (12)"+validador[1].length();
						System.out.println("[ERROR][DTM] El formato de fecha no corresponde a su logitud esperada (12)"+validador[1].length()); return true; }
				}else {
					mensaje="[ERROR][DTM] No es un Formato de Fecha Reconocido ("+validador[2]+")";
					System.out.println("[ERROR][DTM] No es un Formato de Fecha Reconocido ("+validador[2]+")"); return true; }
			}else if(tokens[0].equalsIgnoreCase("ALI")){
				System.out.println("-----------ALI--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				if (!tokens[1].equalsIgnoreCase("")) { 
					mensaje="[ERROR][ALI] Par�metro 1 no esperado";
					System.out.println("[ERROR][ALI] Par�metro 1 no esperado"); 
					return true; 
				} else if (!tokens[2].equalsIgnoreCase("")) { 
					mensaje="[ERROR][ALI] Par�metro 2 no esperado";
					System.out.println("[ERROR][ALI] Par�metro 2 no esperado"); 
					return true; 
				} else if (tokens[3].equalsIgnoreCase("81E")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[3]+"] Facturar pero no Enviar");
				}else if (tokens[3].equalsIgnoreCase("82E")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[3]+"] Enviar pero no Facturar");
				}else { 
					mensaje="[ERROR][ALI] No es una Condici�n reconocida";
					System.out.println("[ERROR][ALI] No es una Condici�n reconocida"); return true; }
				pedido.setAliCond(tokens[3]);
			}else if(tokens[0].equalsIgnoreCase("FTX")){
				System.out.println("-----------FTX--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				if (tokens[1].equalsIgnoreCase("DEL")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Informaci�n de entrega");
				} else { 
					mensaje="[ERROR][ALI] No es un tipo de informaci�n reconocida";
					System.out.println("[ERROR][ALI] No es un tipo de informaci�n reconocida"); return true; }
				if (!tokens[2].equalsIgnoreCase("")) { 
					mensaje="[ERROR][FTX] Par�metro 2 no esperado";
					System.out.println("[ERROR][FTX] Par�metro 2 no esperado"); 
					return true; 
				} else if (!tokens[3].equalsIgnoreCase("")) { 
					mensaje="[ERROR][ALI] Par�metro 3 no esperado";
					System.out.println("[ERROR][ALI] Par�metro 3 no esperado"); 
					return true; 
				} else if (tokens[4].equalsIgnoreCase(""))  {
					mensaje="[ERROR][ALI] Par�metro 4 Vac�o";
					System.out.println("[ERROR][ALI] Par�metro 4 Vac�o"); 
					return true; 
				} else {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[4]+"] Bien");
					pedido.setAliCond(tokens[4]);
				}
			}else if(tokens[0].equalsIgnoreCase("NAD")){
				System.out.println("-----------NAD--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[2].trim().split(":");
				nodoPadre=tokens[1];
				if (tokens[1].equalsIgnoreCase("MS")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Emisor del Mensaje");
					pedido.setNadMs(validador[0].trim());
				} else if (tokens[1].equalsIgnoreCase("MR")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Receptor del Mensaje");
					pedido.setNadMr(validador[0].trim());
				} else if (tokens[1].equalsIgnoreCase("SU")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Proveedor");
					pedido.setNadSu(validador[0].trim());
				} else if (tokens[1].equalsIgnoreCase("BY")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Comprador");
					pedido.setNadBy(validador[0].trim());
				} else if (tokens[1].equalsIgnoreCase("DP")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Receptor de las Mercanc�as");
					pedido.setNadDp(validador[0].trim());
				} else if (tokens[1].equalsIgnoreCase("IV")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Receptor de la Factura");
					pedido.setNadIv(validador[0].trim());
				} else if (tokens[1].equalsIgnoreCase("PR")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Emisor del Pago");
					pedido.setNadPr(validador[0].trim());
				}else {
					mensaje="[ERROR][NAD] No es un Formato de interlocutor v�lido ("+tokens[1]+")";
					System.out.println("[ERROR][NAD] No es un Formato de interlocutor v�lido ("+tokens[1]+")"); 
					return true;
				}
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"]");				
				System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] C�digo de la empresa / Punto operacional EAN");
				if (!validador[1].equalsIgnoreCase("")) {
					mensaje="[ERROR][NAD] Par�metro 1 no esperado";
					System.out.println("[ERROR][NAD] Par�metro 1 no esperado");
					return true;
				}
				else if (validador[2].equalsIgnoreCase("9")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[2]+"] EAN");
				}else {
					mensaje="[ERROR][NAD] No es un Formato reconocido, se esperaba un 9 ("+validador[2]+")";
					System.out.println("[ERROR][NAD] No es un Formato reconocido, se esperaba un 9 ("+validador[2]+")"); 
					return true; 
				}
			}else if(tokens[0].equalsIgnoreCase("RFF")){
				System.out.println("-----------RFF--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[1].trim().split(":");
				if (validador[0].equalsIgnoreCase("API")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Identificacion adicional de la parte");
					if (nodoPadre.equalsIgnoreCase("MS")) {
						System.out.println(">>>>>>>>>>>>>>>>["+nodoPadre+"] Emisor del Mensaje: "+validador[1]);
						pedido.setRffMs(validador[1]);
					} else if (nodoPadre.equalsIgnoreCase("MR")) {
						System.out.println(">>>>>>>>>>>>>>>>["+nodoPadre+"] Receptor del Mensaje: "+validador[1]);
						pedido.setRffMr(validador[1]);
					} else if (nodoPadre.equalsIgnoreCase("SU")) {
						System.out.println(">>>>>>>>>>>>>>>>["+nodoPadre+"] Proveedor: "+validador[1]);
						pedido.setRffSu(validador[1]);
					} else if (nodoPadre.equalsIgnoreCase("BY")) {
						System.out.println(">>>>>>>>>>>>>>>>["+nodoPadre+"] Comprador: "+validador[1]);
						pedido.setRffBy(validador[1]);
					} else if (nodoPadre.equalsIgnoreCase("DP")) {
						System.out.println(">>>>>>>>>>>>>>>>["+nodoPadre+"] Receptor de las Mercanc�as: "+validador[1]);
						pedido.setRffDp(validador[1]);
					} else if (nodoPadre.equalsIgnoreCase("IV")) {
						System.out.println(">>>>>>>>>>>>>>>>["+nodoPadre+"] Receptor de la Factura: "+validador[1]);
						pedido.setRffIv(validador[1]);
					} else if (nodoPadre.equalsIgnoreCase("PR")) {
						System.out.println(">>>>>>>>>>>>>>>>["+nodoPadre+"] Emisor del Pago: "+validador[1]);
						pedido.setRffPr(validador[1]);
					}else { System.out.println("[ERROR][RFF] No es un Formato de interlocutor Padre v�lido ("+tokens[1]+")"); return true; }
				} else { System.out.println("[ERROR][RFF] No es un Identificacion adicional de la parte reconocido ("+validador[0]+")"); return true; }
			}else if(tokens[0].equalsIgnoreCase("CUX")){
				System.out.println("-----------CUX--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[1].trim().split(":");
				if (validador[0].equalsIgnoreCase("2")) {
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Divisa de Referencia");
					if (validador[2].equalsIgnoreCase("9")) {
						pedido.setCux(validador[1].trim());
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Divisa");
					}else { 
						mensaje="[ERROR][CUX] No es una divisa de pedido v�lida ("+validador[2]+")";
						System.out.println("[ERROR][CUX] No es una divisa de pedido v�lida ("+validador[2]+")"); 
						return true; 
					}
				}else { 
					mensaje="[ERROR][CUX] No es una divisa de referencia v�lida ("+validador[0]+")";
					System.out.println("[ERROR][CUX] No es una divisa de referencia v�lida ("+validador[0]+")"); 
					return true; 
				}
			}else if(tokens[0].equalsIgnoreCase("LIN")){
				System.out.println("-----------LIN--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				numeroDeLinea++;
				//lin = new OrdersLin();
				if(tokens[1].equalsIgnoreCase(String.valueOf(numeroDeLinea))&&(!tokens[1].equalsIgnoreCase(""))) {
					//si hay mas l�neas, inserta la anterior al array, sino, esperamos a que lo haga el resumen
					if(numeroDeLinea>1) {
						pedido.setLin(lin); // me guardas el que viene
						//lin = new OrdersLin(); //me creas uno limpio
						nodoPadre = "";
					}
					lin = new OrdersLin();
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Numero de l�nea correcto");
					//numeroDeLinea++;
					lin.setLinNum(tokens[1]);
					if(tokens[2].equalsIgnoreCase("")){
						validador=tokens[3].trim().split(":");
						if (validador[1].equalsIgnoreCase("EN")) {
							lin.setIdLin(validador[0].trim());
							System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] Articulo solicitado N�mero");
							System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Formato EAN");
						}else { 
							mensaje="[ERROR][LIN] Formato del art�culo pedido diferente del n�mero EAN13 � EAN14 ("+validador[1]+")";
							System.out.println("[ERROR][LIN] Formato del art�culo pedido diferente del n�mero EAN13 � EAN14 ("+validador[1]+")"); 
							return true; }
					}else { 
						mensaje="[ERROR][LIN] Par�metro 2 no esperado ("+tokens[2]+")";
						System.out.println("[ERROR][LIN] Par�metro 2 no esperado ("+tokens[2]+")"); 
						return true; }
				}else { 
					mensaje="[ERROR][LIN] N�mero de l�nea incorrecto/no secuencial ("+tokens[1]+")";
					System.out.println("[ERROR][LIN] N�mero de l�nea incorrecto/no secuencial ("+tokens[1]+")"); 
					return true; }
			}else if(tokens[0].equalsIgnoreCase("PIA")){
				System.out.println("-----------PIA--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				if(!tokens[1].equalsIgnoreCase("")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Validador de identificaci�n adicional ");
					validador=tokens[2].trim().split(":");
					System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] N�mero de Articulo ");
					if (validador[1].equalsIgnoreCase("IN")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Referencia Interna del Comprador ");
						lin.setPiaNumIn(validador[0]);
					} else if (validador[1].equalsIgnoreCase("SA")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Referencia Interna del Vendedor ");
						lin.setPiaNumSa(validador[0]);
					}else { 
						mensaje="[ERROR][PIA] No hemos encontrado una referencia Interna o Externa. Esperado IN/SA ("+validador[1]+")";
						System.out.println("[ERROR][PIA] No hemos encontrado una referencia Interna o Externa. Esperado IN/SA ("+validador[1]+")"); 
						return true; }
					//lin.setPiaTipo(validador[1]);
				}else { 
					mensaje="[ERROR][PIA] No hemos encontrado la funci�n de identificaci�n de producto. Esperado 1 ("+tokens[1]+")";
					System.out.println("[ERROR][PIA] No hemos encontrado la funci�n de identificaci�n de producto. Esperado 1 ("+tokens[1]+")"); 
					return true; }
			}else if(tokens[0].equalsIgnoreCase("IMD")){
				//TODO: Revisar este punto, puesto que debe necesitar cambios por los aspectos de Descripci�n color, talla, que muy seguramente Tierrina no se ve afectado
				System.out.println("-----------IMD--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				IMD imd = new IMD();
				imd.setIdLinea(numeroDeLinea);
				// Primer parametro
				if (tokens[1].equalsIgnoreCase("F")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Descripci�n del art�culo en Forma Libre");
					//destripamos la tercera parte
					validador=tokens[3].split(":");
					if(validador[0].equalsIgnoreCase("")&&validador[1].equalsIgnoreCase("")&&validador[2].equalsIgnoreCase("")
							&&!validador[3].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[3]+"] Descripci�n de producto");
						//lin.setImdDesc(validador[2]);
						imd.setImdDesc(validador[3]);
					}else { 
						mensaje="[ERROR][IMD] Descripci�n Libre no reconocida. ("+tokens[3]+")";
						System.out.println("[ERROR][IMD] Descripci�n Libre no reconocida. ("+tokens[3]+")"); 
						return true; }					
				} else if (tokens[1].equalsIgnoreCase("C")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Descripci�n del art�culo en C�digo");
					//no necesitamos destripar la tercera parte
					//imd.setImdDesc(tokens[3]);
					imd.setImdCodi(tokens[3]);
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[3]+"] El art�culo es una unidad de consumo");
				} else { 
					mensaje="[ERROR][IMD] Descripci�n del art�culo no conocida el formato. Esperado F/C ("+tokens[1]+")";
					System.out.println("[ERROR][IMD] Descripci�n del art�culo no conocida el formato. Esperado F/C ("+tokens[1]+")"); 
					return true; }
				imd.setImdTipo(tokens[1]);
				//segundo par�metro				
				if(tokens[2].equalsIgnoreCase("")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"] Es un articulos sin DSC, Color o Talla");
				} else if(tokens[2].equalsIgnoreCase("DSC")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"] Es una descripcion DSC");
				} else if(tokens[2].equalsIgnoreCase("35")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"] Es una descripci�n de color ");
				} else if(tokens[2].equalsIgnoreCase("98")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"] Es una descripci�n de Talla ");
				} else { 
					mensaje="[ERROR][IMD] Caracteristica no reconocida. ("+tokens[2]+")";
					System.out.println("[ERROR][IMD] Caracteristica no reconocida. ("+tokens[2]+")"); 
					return true; }
				imd.setImdCara(tokens[2]);
				lin.setImd(imd);
			}else if(tokens[0].equalsIgnoreCase("QTY")){
				System.out.println("-----------QTY--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[1].trim().split(":");
				if (validador[0].equalsIgnoreCase("21")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
					//lin.setQty21Cali(validador[0].trim());
					if (!validador[1].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Cantidad Pedida");
						if(nodoPadre.equalsIgnoreCase("LOC")) {
							loc.setQty(validador[1]);
							System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Cantidad Pedida Asociada a una localizaci�n");
							nodoPadre = "";
							lin.setLoc(loc);
						} else lin.setQty21Cant(validador[1]);
					} else { 
						mensaje="[ERROR][QTY] No hay cantidad Pedida ("+validador[1]+")";
						System.out.println("[ERROR][QTY] No hay cantidad Pedida ("+validador[1]+")"); 
						return true; }
				} else if (validador[0].equalsIgnoreCase("59")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
					//lin.setQty59Cali(validador[0].trim());
					if (!validador[1].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Numero de unidades de Consumo en la unidad de expedici�n");
						lin.setQty59Cant(validador[1]);
					} else { 
						mensaje="[ERROR][QTY] No hay cantidad en Unidades de Consumo ("+validador[1]+")";
						System.out.println("[ERROR][QTY] No hay cantidad en Unidades de Consumo ("+validador[1]+")"); 
						return true; }
				} else {
					mensaje="[ERROR][QTY] Error en el tipo de calificador de cantidad ("+tokens[1]+")";
					System.out.println("[ERROR][QTY] Error en el tipo de calificador de cantidad ("+tokens[1]+")"); 
					return true; 
				}
				//como una localizacion va acompa�ada de una qty...
			}else if(tokens[0].equalsIgnoreCase("MOA")){
				System.out.println("-----------MOA--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[1].trim().split(":");
				if (validador[0].equalsIgnoreCase("203")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
					if (!validador[1].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Importe Total Neto de la L�nea de Art�culo (incluye descuentos y cargos, no impuestos)");
						lin.setMoa203(validador[1]);
					} else { 
						mensaje="[ERROR][MOA] 203: Importe Total neto de la linea ("+validador[1]+")";
						System.out.println("[ERROR][MOA] 203: Importe Total neto de la linea ("+validador[1]+")"); 
						return true; }
				} else if (validador[0].equalsIgnoreCase("79")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
					if (!validador[1].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Importe Total Neto Final ");
						pedido.setMoa79(validador[1]);
					} else { 
						mensaje="[ERROR][MOA] 79: Error en el Importe Total Neto Final ("+validador[1]+")";
						System.out.println("[ERROR][MOA] 79: Error en el Importe Total Neto Final ("+validador[1]+")"); 
						return true; }
				} else {
					mensaje="[ERROR][MOA] Identificador Monetario no reconocido ("+tokens[1]+")";
					System.out.println("[ERROR][MOA] Identificador Monetario no reconocido ("+tokens[1]+")"); 
					return true; }
			}else if(tokens[0].equalsIgnoreCase("PRI")){
				System.out.println("-----------PRI--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[1].trim().split(":");
				if (validador[0].equalsIgnoreCase("AAA")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
					if (!validador[1].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Precio Bruto Unitario ");
						lin.setPriAaa(validador[1]);
					} else { 
						mensaje="[ERROR][PRI] AAA: Error en el Precio Bruto Unitario ("+validador[1]+")";
						System.out.println("[ERROR][PRI] AAA: Error en el Precio Bruto Unitario ("+validador[1]+")"); 
						return true; }
				} else if (validador[0].equalsIgnoreCase("AAB")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
					if (!validador[1].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Precio Neto Unitario ");
						lin.setPriAab(validador[1]);
					} else { 
						mensaje="[ERROR][PRI] AAB: Error en el Precio Neto Unitario ("+validador[1]+")";
						System.out.println("[ERROR][PRI] AAB: Error en el Precio Neto Unitario ("+validador[1]+")"); 
						return true; }
				} else if (validador[0].equalsIgnoreCase("INF")) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"]");
					if (!validador[1].equalsIgnoreCase("")) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Precio a t�tulo informativo (PVP) ");
						lin.setPriInf(validador[1]);
					} else { 
						mensaje="[ERROR][PRI] INF: Error en el Precio a t�tulo informativo (PVP) ("+validador[1]+")";
						System.out.println("[ERROR][PRI] INF: Error en el Precio a t�tulo informativo (PVP) ("+validador[1]+")"); 
						return true; }
				} else { 
					mensaje="[ERROR][PRI] Identificador Monetario no reconocido ("+tokens[1]+")";
					System.out.println("[ERROR][PRI] Identificador Monetario no reconocido ("+tokens[1]+")"); 
					return true; }
			}else if(tokens[0].equalsIgnoreCase("LOC")){
				System.out.println("-----------LOC--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				loc = new OrdersLoc();
				nodoPadre = tokens[0];
				loc.setIdLin(String.valueOf(numeroDeLinea));
				System.out.println(">>>>>>>>>>>>>>>>["+String.valueOf(numeroDeLinea)+"] Para este n�mero de l�nea");
				if(tokens[1].equalsIgnoreCase("7")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Lugar de Entrega ");
					validador=tokens[2].trim().split(":");
					if (validador[2].equalsIgnoreCase("9")) {
						System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Es en formato EAN");
						if (validador[1].equalsIgnoreCase("")&&!validador[0].equalsIgnoreCase("")) {
							System.out.println(">>>>>>>>>>>>>>>>["+validador[0]+"] C�digo de localizaci�n EAN formato n13 ");
							loc.setLoc(validador[0]);
						} else { 
							mensaje="[ERROR][LOC] Parametro excesivo o EAN vaci� ("+validador[1]+"|"+validador[0]+")";
							System.out.println("[ERROR][LOC] Parametro excesivo o EAN vaci� ("+validador[1]+"|"+validador[0]+")"); 
							return true; }
					} else { 
						mensaje="[ERROR][LOC] Formato no reconocido. Esperado 9 -> EAN ("+validador[2]+")";
						System.out.println("[ERROR][LOC] Formato no reconocido. Esperado 9 -> EAN ("+validador[2]+")"); 
						return true; }
				} else { 
					mensaje="[ERROR][LOC] Identificaci�n de localizaci�n no reconocido ("+tokens[1]+")";
					System.out.println("[ERROR][LOC] Identificaci�n de localizaci�n no reconocido ("+tokens[1]+")"); 
					return true; }
				// el loc lo guardamos en QTY
			}else if(tokens[0].equalsIgnoreCase("UNS")){
				System.out.println("-----------UNS--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				//metemos la �ltima l�nea de art�culo (o la �nica) en el array
				pedido.setLin(lin);
				if(tokens[1].equalsIgnoreCase("S")){
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Summarize");
				} else { 
					mensaje="[ERROR][UNS0] No es el sumatorio";
					System.out.println("[ERROR][UNS0] No es el sumatorio"); 
					return true; }		
			}else if(tokens[0].equalsIgnoreCase("CNT")){
				System.out.println("-----------CNT--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				validador=tokens[1].trim().split(":");
				if(validador[0].trim().equalsIgnoreCase("2")) {
					if(validador[1].equalsIgnoreCase(String.valueOf(numeroDeLinea))) {
						System.out.println(">>>>>>>>>>>>>>>>["+validador[1]+"] Numero de lineas correcto");
						pedido.setCnt(validador[1]);
					} else { 
						mensaje="[ERROR][CNT] El numero de lineas recibido es "+validador[1]+" Mientras que el esperado es "+numeroDeLinea;
						System.out.println("[ERROR][CNT] El numero de lineas recibido es "+validador[1]+" Mientras que el esperado es "+numeroDeLinea); 
						return true; }
				} else { 
					mensaje="[ERROR][CNT] No se ha reconocido el valor entregado. Esperado 2, recibido ("+validador[0]+")";
					System.out.println("[ERROR][CNT] No se ha reconocido el valor entregado. Esperado 2, recibido ("+validador[0]+")"); 
					return true; }
			}else if(tokens[0].equalsIgnoreCase("UNT")){
				System.out.println("-----------UNT--------------------");
				System.out.println(">>>>>>>>>>>>>>>>["+tokens[0]+"]");
				if(temporal.length == Integer.parseInt(tokens[1])) {
					System.out.println(">>>>>>>>>>>>>>>>["+tokens[1]+"] Las l�neas de mensaje coinciden ["+temporal.length+"]");
					if(tokens[2].equalsIgnoreCase(pedido.getUnh())){
						//Guardamos el n�mero de l�neas, esto podr�amos haberlo hecho al principio, hehehe
						pedido.setUnt(String.valueOf(temporal.length));
						System.out.println(">>>>>>>>>>>>>>>>["+tokens[2]+"] Numero de codigo coincide");
					} else { 
						mensaje="[ERROR][UNT] El numero Codigo de mensaje recibido es "+tokens[2]+" Mientras que el esperado es "+pedido.getUnh();
						System.out.println("[ERROR][UNT] El numero Codigo de mensaje recibido es "+tokens[2]+" Mientras que el esperado es "+pedido.getUnh()); 
						return true; }						
				} else { 
					mensaje="[ERROR][UNT] El numero de lineas recibido es "+tokens[1]+" Mientras que el esperado es "+temporal.length;
					System.out.println("[ERROR][UNT] El numero de lineas recibido es "+tokens[1]+" Mientras que el esperado es "+temporal.length);	 
					return true; }
			} else { 
				mensaje="[ERROR][GENERAL] Token no reconocido :_(";
				System.out.println("[ERROR][GENERAL] Token no reconocido :_("); 
				return true; }
		}
		return false;
	}
}
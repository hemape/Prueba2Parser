import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Accion> operacion = null;

	public Parser() {
		operacion = new ArrayList<Accion>();
	}

	public void parseFicheroXml(String fichero) {
		// creamos una factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// creamos un documentbuilder
			DocumentBuilder db = dbf.newDocumentBuilder();

			// parseamos el XML y obtenemos una representación DOM
			dom = db.parse(fichero);
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	public void parseDocument() {
		// obtenemos el elemento raíz
		Element docEle = dom.getDocumentElement();

		// obtenemos el nodelist de elementos
		NodeList nl = docEle.getElementsByTagName("accion");
		if (nl != null && nl.getLength() > 0) {
			for (int i = 0; i < nl.getLength(); i++) {

				// obtenemos un elemento de la lista (accion)
				Element el = (Element) nl.item(i);

				// obtenemos una accion
				Accion p = getNombreaccion(el);

				// lo añadimos al array
				operacion.add(p);
			}
		}
	}

	private Accion getNombreaccion(Element accionEle) {

		String nombreaccion = getTextValue(accionEle, "nombreaccion");
		ArrayList<String> operacion = getNombres(accionEle, "operaciones");
		
		Accion a = new Accion(nombreaccion, operacion);

		return a;

	}

//	private String getAttribute(Element ele, String tag, String attr) {
//
//		String textVal = "";
//		NodeList nl = ele.getElementsByTagName(tag);
//		if (nl != null && nl.getLength() > 0) {
//			Element el = (Element) nl.item(0);
//			textVal = el.getAttribute(attr);
//		}
//		return textVal;
//
//	}

	private ArrayList<String> getNombres(Element ele, String tag) {
		ArrayList<String> lista_operaciones = new ArrayList<String>();

		NodeList nl = ele.getElementsByTagName(tag);

		if (nl != null && nl.getLength() > 0) {
			Element el_autor = (Element) nl.item(0);

			NodeList nodelist_operaciones = el_autor.getElementsByTagName("operacion");

			if (nodelist_operaciones != null && nodelist_operaciones.getLength() > 0) {
				for (int i = 0; i < nodelist_operaciones.getLength(); i++) {
					Element el_nombre = (Element) nodelist_operaciones.item(i);
					String texto = el_nombre.getFirstChild().getNodeValue();
					lista_operaciones.add(texto);
				}
			}

		}
		return lista_operaciones;
	}

	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if (nl != null && nl.getLength() > 0) {
			Element el = (Element) nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

	public void print() {

		Iterator it = operacion.iterator();
		while (it.hasNext()) {
			Accion l = (Accion) it.next();
			l.print();
			System.out.println("-----------------------------\n");
		}
	}
}

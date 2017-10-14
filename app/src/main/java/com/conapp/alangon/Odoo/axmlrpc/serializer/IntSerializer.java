package com.conapp.alangon.Odoo.axmlrpc.serializer;

import com.conapp.alangon.Odoo.axmlrpc.XMLRPCException;
import com.conapp.alangon.Odoo.axmlrpc.XMLUtil;
import com.conapp.alangon.Odoo.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/**
 *
 * @author timroes
 */
public class IntSerializer implements Serializer {

	public Object deserialize(Element content) throws XMLRPCException {
		return Integer.parseInt(XMLUtil.getOnlyTextContent(content.getChildNodes()));
	}

	public XmlElement serialize(Object object) {
		return XMLUtil.makeXmlTag(SerializerHandler.TYPE_INT,
				object.toString());
	}

}

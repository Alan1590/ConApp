package com.conapp.alangon.Odoo.axmlrpc.serializer;

import com.conapp.alangon.Odoo.axmlrpc.XMLRPCException;
import com.conapp.alangon.Odoo.axmlrpc.XMLUtil;
import com.conapp.alangon.Odoo.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/**
 *
 * @author Tim Roes
 */
public class BooleanSerializer implements Serializer {

	public Object deserialize(Element content) throws XMLRPCException {
		return XMLUtil.getOnlyTextContent(content.getChildNodes()).equals("1")
				? Boolean.TRUE : Boolean.FALSE;
	}

	public XmlElement serialize(Object object) {
		return XMLUtil.makeXmlTag(SerializerHandler.TYPE_BOOLEAN,
				(Boolean)object ? "1" : "0");
	}

}
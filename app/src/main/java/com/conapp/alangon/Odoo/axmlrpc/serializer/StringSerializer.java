package com.conapp.alangon.Odoo.axmlrpc.serializer;

import com.conapp.alangon.Odoo.axmlrpc.XMLRPCException;
import com.conapp.alangon.Odoo.axmlrpc.XMLUtil;
import com.conapp.alangon.Odoo.axmlrpc.xmlcreator.XmlElement;
import org.w3c.dom.Element;

/**
 *
 * @author Tim Roes
 */
public class StringSerializer implements Serializer {

	private boolean decodeStrings;
	private boolean encodeStrings;

	public StringSerializer(boolean encodeStrings, boolean decodeStrings) {
		this.decodeStrings = decodeStrings;
		this.encodeStrings = encodeStrings;
	}

	public Object deserialize(Element content) throws XMLRPCException {
		String text = XMLUtil.getOnlyTextContent(content.getChildNodes());
		if(decodeStrings) {
			text = text.replaceAll("&lt;", "<").replaceAll("&amp;", "&");
		}
		return text;
	}

	public XmlElement serialize(Object object) {
		String content = object.toString();
		if(encodeStrings) {
			content = content
					.replaceAll("&", "&amp;")
					.replaceAll("<", "&lt;")
					.replaceAll("]]>", "]]&gt;");
		}
		return XMLUtil.makeXmlTag(SerializerHandler.TYPE_STRING, content);
	}

}
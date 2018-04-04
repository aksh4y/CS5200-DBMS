package edu.northeastern.cs5200.xml.assignment;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.*;

public class transformer {

	public static void main(String[] args) throws Exception {

		String XSLFILE = args[0];
		String INFILE = args[1];
		String OUTFILE = args[2];

		StreamSource xslcode = new StreamSource(new File(XSLFILE));
		StreamSource input = new StreamSource(new File(INFILE));
		StreamResult output = new StreamResult(new File(OUTFILE));

		TransformerFactory tf =	TransformerFactory.newInstance();
		Transformer trans =	tf.newTransformer(xslcode);
		
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
		trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		
		trans.transform(input, output);

		System.out.println("Transformation completed...");
		
	}

}

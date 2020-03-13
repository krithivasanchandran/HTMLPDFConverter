package com.generic.pdf;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.IElement;

public class MergePdfFunc {

	public static final String[] HTML = { 
			"C:/Users/krichandran/Desktop/oct.html" };
	
	public static String mergeDest = "C:/Users/krichandran/Documents/generic.pdf";

	public static void main(String args[]) {

		try {
			
			createPdf("", HTML, mergeDest);
			
		} catch (IOException e) {

			System.out.println(" " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void createPdf(String baseUri, String[] src, String dest) throws IOException {

		ConverterProperties properties = new ConverterProperties();
		properties.setBaseUri(baseUri);
		properties.setCharset("UTF-8");
		properties.setTagWorkerFactory(null);

		PdfWriter writer = new PdfWriter(dest);
		writer.setHighPrecision(true);
		writer.setSmartMode(true);
		
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf);

		for (String html : src) {

			List<IElement> elements = HtmlConverter.convertToElements(new FileInputStream(html), properties);

			for (IElement element : elements) {
				document.add((IBlockElement) element);
			}
		}
		
		document.close();
	}

}

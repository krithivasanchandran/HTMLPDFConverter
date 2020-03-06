package com.billing.paypal.pdf;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;

public class PDFExporter extends Exporter {

	String data = null;
	int size = 0;
	InputType dataType = InputType.HTML;
	private static final String fileExtension = "pdf";
	
	public PDFExporter(String data, int size) {
		this.data = data;
		this.size = size;
	}

	public PDFExporter(byte[] data, int size, String characterSet)
			throws UnsupportedEncodingException {
		this.data = new String(data, characterSet);
		this.size = size;
	}

	public PDFExporter(String data, int size, InputType type) {
		this.data = data;
		this.size = size;
		this.dataType = type;
	}

	public PDFExporter(byte[] data, int size, String characterSet,
			InputType type) throws UnsupportedEncodingException {
		this.data = new String(data, characterSet);
		this.size = size;
		this.dataType = type;
	}

	@Override
	public String getExtension() {
		return fileExtension;
	}
	
    public static Map<String,Object> exportGAPInvoice(byte[] data) throws IOException, DocumentException {
		
		Map<String,Object> pdfFileCarrier = Collections.synchronizedMap(new LinkedHashMap());
		
		// Create a buffer to hold the cleaned up HTML
		
		ByteArrayOutputStream bOutStream = new ByteArrayOutputStream();

		PdfDocument pdfDocument = new PdfDocument(new PdfWriter(bOutStream));
		// System.out.println(data);
		
		// Clean up the HTML to be well formed
		//HtmlCleaner cleaner = new HtmlCleaner();
		
		ConverterProperties converterProps = new ConverterProperties();
		converterProps.setCharset("utf-8");
		
		PageSize pageSize = PageSize.A4;
	
	//	pageSize.applyMargins(0f, 0f, 0f, 0f, true);
		//pageSize.moveRight(200f);
	
		pdfDocument.setDefaultPageSize(pageSize);
		
		
		HtmlConverter.convertToPdf(new String(data, "UTF-8"), pdfDocument, converterProps);
		
		pdfDocument = new PdfDocument(
	                new PdfReader(new ByteArrayInputStream(bOutStream.toByteArray())));
		
		pdfFileCarrier.put("PdfDocument", pdfDocument);
		pdfFileCarrier.put("ByteArrayStream", bOutStream.toByteArray());
		
		return pdfFileCarrier;
		
	/*
//		 TagNode node = cleaner.clean(URLDecoder.decode(data,"UTF-8"));
		 
		TagNode node = cleaner.clean(data);

		// Instead of writing to System.out we now write to the ByteArray buffer
		new PrettyXmlSerializer(props).writeToStream(node, out); // HtmlSerializer

		// System.out.println(out.toString());

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(new String(out.toByteArray()));
		renderer.layout();
		

		ByteArrayOutputStream pdf = new ByteArrayOutputStream();
		renderer.createPDF(pdf);
		renderer.finishPDF();
		// out.flush(); // no need to flush since no-ops
		// out.close(); // no need to close since no-ops
	 */
		
	}

	@Override
	public ByteArrayOutputStream export() throws IOException, DocumentException, com.itextpdf.text.DocumentException {
		// Create a buffer to hold the cleaned up HTML
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// System.out.println(data);

		// Clean up the HTML to be well formed
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		// TagNode node = cleaner.clean(URLDecoder.decode(this.preProcessData(),
		// "UTF-8"));
		TagNode node = cleaner.clean(this.preProcessData());

		// Instead of writing to System.out we now write to the ByteArray buffer
		new PrettyXmlSerializer(props).writeToStream(node, out); // HtmlSerializer

		// System.out.println(out.toString());

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(new String(out.toByteArray()));
		renderer.layout();

		ByteArrayOutputStream pdf = new ByteArrayOutputStream();
		renderer.createPDF(pdf);
		renderer.finishPDF();
		// out.flush(); // no need to flush since no-ops
		// out.close(); // no need to close since no-ops

		return pdf;
	}

	public static ByteArrayOutputStream export(String data) throws IOException, DocumentException, com.itextpdf.text.DocumentException {
		// Create a buffer to hold the cleaned up HTML
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		// System.out.println(data);

		// Clean up the HTML to be well formed
		HtmlCleaner cleaner = new HtmlCleaner();
		CleanerProperties props = cleaner.getProperties();
		// TagNode node = cleaner.clean(URLDecoder.decode(this.preProcessData(),
		// "UTF-8"));
		TagNode node = cleaner.clean(data);

		// Instead of writing to System.out we now write to the ByteArray buffer
		new PrettyXmlSerializer(props).writeToStream(node, out); // HtmlSerializer

		// System.out.println(out.toString());

		ITextRenderer renderer = new ITextRenderer();
		renderer.setDocumentFromString(new String(out.toByteArray()));
		renderer.layout();

		ByteArrayOutputStream pdf = new ByteArrayOutputStream();
		renderer.createPDF(pdf);
		renderer.finishPDF();
		// out.flush(); // no need to flush since no-ops
		// out.close(); // no need to close since no-ops

		return pdf;
	}

	private String preProcessData() throws IOException {
		// String htmlReport = "<!DOCTYPE html><html>" +
		// "<head>" +
		// URLEncoder.encode("<style> @page { size: " + size +
		// "in 8.5in; margin: 30px;} " +
		// "#repData.report { margin-left: -25px; } " +
		// "</style>", "UTF-8") +
		// "</head>" +
		// "<body>" + data + "</body>" +
		// "</html>";
		// return htmlReport;
		return data;
	}
}

package com.billing.paypal.pdf;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.utils.PdfMerger;

public class MergePDF {
	
	static List<String> files = new ArrayList<String>();
	
	public static String mergeDest = "C:/Users/krichandran/Documents/hw.pdf";
	
	public static final String[] HTML = {
			"C:/Users/krichandran/Desktop/invoices/hwoct.html"};


    public static void main(String[] args) throws IOException {
    	
    	ConverterProperties properties = new ConverterProperties();
        properties.setBaseUri("");
        properties.setCharset("utf-8");

        PdfWriter writer = new PdfWriter(mergeDest);
        writer.setHighPrecision(true);
		writer.setSmartMode(true);
		
        PdfDocument pdf = new PdfDocument(writer);
        PdfMerger merger = new PdfMerger(pdf);
        
        
        for (String html : HTML) {
        	
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            PdfDocument temp = new PdfDocument(new PdfWriter(baos));
            PageSize pageSize = PageSize.A4;
          
            temp.setDefaultPageSize(pageSize);
            
            HtmlConverter.convertToPdf(new FileInputStream(html), temp, properties);
            
            temp = new PdfDocument(
                new PdfReader(new ByteArrayInputStream(baos.toByteArray())));
           
            merger.merge(temp, 1, temp.getNumberOfPages());
          
        }
        
        merger.close();
        pdf.close();
        writer.setCloseStream(true);
        writer.close();
    }
}

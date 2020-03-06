package com.billing.paypal.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.attach.impl.OutlineHandler;
import com.itextpdf.io.IOException;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.styledxmlparser.css.media.MediaDeviceDescription;
import com.itextpdf.styledxmlparser.css.media.MediaType;
import com.itextpdf.styledxmlparser.css.util.CssUtils;

public class PdfConversion {
	
//	public static final PageSize[] A4Sizes = {
//			    PageSize.A4.rotate(),
//			    new PageSize(720,PageSize.A4.getHeight()),
//			    new PageSize(PageSize.A4.getWidth(), PageSize.A4.getHeight())
//	};
//	
	 public static final PageSize[] pageSizes = {
	            PageSize.A4.rotate(),
	            new PageSize(PageSize.A4.getWidth() , PageSize.A4.getHeight()),
	            new PageSize(PageSize.A4.getWidth(), PageSize.A4.getHeight())
	    };
	
	public static void main(String args[]){
		
		String SRC = "C:/Users/krichandran/Desktop/html69888677_prof_Other_charges.html";
		String DEST = "C:/Users/krichandran/Documents/hyperwallet.pdf";
		
		try {
			File file = new File(DEST);
	    	file.getParentFile().mkdirs();
	        int count = 1;
	    	PdfConversion app = new PdfConversion();
	        
			   for (PageSize size : pageSizes) {
		        	app.createPdf(SRC, String.format(DEST, count++), size);
		        }
			
			  
		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());

		} catch (Exception exception) {

			System.out.println(exception.getMessage());
		}
	}
	
	 public static void createPdf(String src, String dest, PageSize size) throws IOException, java.io.IOException { 
		 
	    	PdfWriter writer = new PdfWriter(dest);
	    	writer.setHighPrecision(true);
	    	writer.setSmartMode(true);
	    	
	    	PdfDocument pdf = new PdfDocument(writer);
	    	pdf.setTagged();
	    	pdf.setDefaultPageSize(size);
	    	
	    	
	        System.out.println(" Total pdf page numbers  ----> " );
	    	
	    	ConverterProperties properties = new ConverterProperties();
	    	properties.setBaseUri(new File(src).getParentFile().getAbsolutePath());
	     
	    	MediaDeviceDescription mediaDescription = new MediaDeviceDescription(MediaType.SCREEN);
	    	mediaDescription.setWidth(CssUtils.parseAbsoluteLength(String.valueOf(size.getWidth())));
	    	properties.setMediaDeviceDescription(mediaDescription);
	    	properties.setCreateAcroForm(true);
	    	
	    	
	    	try (FileInputStream fileInputStream = new FileInputStream(src)) {
				HtmlConverter.convertToPdf(fileInputStream, pdf, properties);
			}
	    }
	
}

package com.filely.pdf.core;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.text.PDFTextStripper;

/*
 *  Apache PDFBox® - A Java PDF Library
 * 	https://pdfbox.apache.org/
 */
public class PDFCore {
	
	static{
		
		/* 
		 * https://pdfbox.apache.org/2.0/getting-started.html#pdfbox-and-java-8
		 * Important notice when using PDFBox with Java 8 before 1.8.0_191 or
		 * Java 9 before 9.0.4
		 * 
		 * Due to the change of the java color management module towards
		 * “LittleCMS”, users can experience slow performance in color
		 * operations. A solution is to disable LittleCMS in favor of the old
		 * KCMS (Kodak Color Management System) by:
		 * 
		 * Starting with
		 * -Dsun.java2d.cmm=sun.java2d.cmm.kcms.KcmsServiceProvider or Calling
		 * System.setProperty("sun.java2d.cmm",
		 * "sun.java2d.cmm.kcms.KcmsServiceProvider")
		 */
		
		 System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
		 
		 /*
		 * PDFBox 2.0.4 introduced a new command line setting
		 * 
		 * -Dorg.apache.pdfbox.rendering.UsePureJavaCMYKConversion=true which
		 * may improve the performance of rendering PDFs on some systems
		 * especially if there are a lot of images on a page.
		 */
		 
		 System.setProperty("org.apache.pdfbox.rendering.UsePureJavaCMYKConversion", "true");
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("This is the start of the PDF converter Initial repository");
		
		 try (PDDocument document = PDDocument.load(new File("C:/Users/krichandran/Documents/key-value-memory-networks-for-directly-reading-documents.pdf"))){
			 
			 AccessPermission ap = document.getCurrentAccessPermission();
	            if (!ap.canExtractContent())
	            {
	                throw new IOException("You do not have permission to extract text");
	            }

	            PDFTextStripper stripper = new PDFTextStripper();

	            // This example uses sorting, but in some cases it is more useful to switch it off,
	            // e.g. in some files with columns where the PDF content stream respects the
	            // column order.
	            stripper.setSortByPosition(true);

	            for (int p = 1; p <= document.getNumberOfPages() - 2; ++p)
	            {
	                // Set the page interval to extract. If you don't, then all pages would be extracted.
	                stripper.setStartPage(p);
	                stripper.setEndPage(p);

	                // let the magic happen
	                String text = stripper.getText(document);

	                // do some nice output with a header
	                String pageStr = String.format("page %d:", p);
	                System.out.println(pageStr);
	                for (int i = 0; i < pageStr.length(); ++i)
	                {
	                    System.out.print("-");
	                }
	                System.out.println();
	                System.out.println(text.trim());
	                System.out.println();
	            }
	        }
		 }
		 
	}
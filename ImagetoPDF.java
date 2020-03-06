package com.filely.pdf.core;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class ImagetoPDF {
	
	 public static void main(String[] args) throws IOException
	    {
	        String imagePath =  "C:/Users/krichandran/Desktop/personalimage.jpg";
	        String pdfPath = "C:/Users/krichandran/Desktop/datacrunklogo.pdf";
	        
	        if (!pdfPath.endsWith(".pdf"))
	        {
	            System.err.println("Last argument must be the destination .pdf file");
	            System.exit(1);
	        }

	        try (PDDocument doc = new PDDocument())
	        {
	            PDPage page = new PDPage();
	            doc.addPage(page);
	            
	            // createFromFile is the easiest way with an image file
	            // if you already have the image in a BufferedImage, 
	            // call LosslessFactory.createFromImage() instead
	            PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, doc);
	            
	            // draw the image at full size at (x=20, y=20)
	            try (PDPageContentStream contents = new PDPageContentStream(doc, page))
	            {
	                // draw the image at full size at (x=20, y=20)
	              //  contents.drawImage(pdImage,20 ,20 , pdImage.getHeight() / 2, pdImage.getWidth() / 2);
	                
	                contents.drawImage(pdImage , 0 , 0 , pdImage.getWidth(), pdImage.getHeight());
	                
	                // to draw the image at half size at (x=20, y=20) use
	                // contents.drawImage(pdImage, 20, 20, pdImage.getWidth() / 2, pdImage.getHeight() / 2); 
	            }
	            doc.save(pdfPath);
	        }
	    }

}

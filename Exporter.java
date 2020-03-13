package com.generic.pdf;

import java.io.OutputStream;

public abstract class Exporter {
	
	public static enum InputType {
		HTML, TEXT, JSON
	};
	
	protected InputType dataType;
	
	protected String filename;
	protected String fileExtension;
	
	public abstract OutputStream export() throws Exception;
	public abstract String getExtension();


	
	public static class check{
		
		public static void main(String args[])
		{
			String str = "temp_.pdf";
		
			String destintfile = str.split("_temp_")[0];

			System.out.println("Destination file --> " + destintfile);
			
		}
		
	}
}

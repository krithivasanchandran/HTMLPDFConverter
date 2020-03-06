package com.billing.paypal.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

	public static void main(String args[]) throws Exception {
		
	     final int autoRowIncrement = 1;
	     final int autoColumnIncrement = 1;
	  //   final Set<String> rulesPattern = new LinkedHashSet<String>();
		
		try {
			Iterator<Row> iterator = excelRead("C:/Users/krichandran/Desktop/Corporate_Billing.xlsx", 0);

			if (iterator == null)
				System.exit(0);

			int rownos = 0;
			int cols = 0;

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();

					if (currentCell.getCellType() == Cell.CELL_TYPE_STRING) {
						// System.out.println(" Excel Cell values ===> " +
						// currentCell.getRichStringCellValue().getString());

						String str = currentCell.getRichStringCellValue().getString();

						if (str.contains("Date:")) {
							String date = currentCell.getStringCellValue();
							System.out.println("Date :: ====> " + date);
						}

						if (str.contains("Currency:")) {
							String currency = currentCell.getStringCellValue();
							System.out.println("Currency :: ====> " + currency);
						}

						if (str.contains("Account:")) {
							String account = currentCell.getStringCellValue();
							System.out.println("Account :: ====> " + account);
						}

						if ("Code: 195".equalsIgnoreCase(str)) {
							System.out.println("Match found");
							
							rownos = currentCell.getRowIndex() + autoRowIncrement;
							cols = currentCell.getColumnIndex() + autoColumnIncrement;

							StringBuilder builder = new StringBuilder();
							builder.append("Row no :: --->  " + rownos + "\n");
							builder.append("Column no :: ---> " + cols);

							System.out.println(builder.toString());
							
							
							Iterator<Cell> shallowCellIterator = null;
							
							for(int i=0;iterator.hasNext() && i < 1;i++){
								shallowCellIterator = iterator.next().cellIterator();
							}

							while (shallowCellIterator.hasNext()) {
								Cell itc = shallowCellIterator.next();

								System.out.println("Inside Internal Column Iterator for the banking ones");
								
								System.out.println("itc.getRowIndex() ::----> " + itc.getRowIndex());
								System.out.println("itc.getColumnIndex() : ---> " + itc.getColumnIndex());
								
								System.out.println("rownos ::----> " + rownos);
								System.out.println("cols : ---> " + cols);
								
								if ((itc.getColumnIndex() == cols) || (itc.getRowIndex() == rownos)) {
									
									System.out.println("=========> Inner Loop <=============");
									
									System.out.println("itc.getRowIndex() ::----> " + itc.getRowIndex());
									System.out.println("itc.getColumnIndex() : ---> " + itc.getColumnIndex());
									
									String embeddedInvoiceId = itc.getStringCellValue();
									System.out.println(
											" Embedded Invoice ID Details :: -----------> " + embeddedInvoiceId);
									
									String[] v = embeddedInvoiceId.split(";");

									for(String s : v){
									    String k = s.split(":=")[0];
									    String val = s.split(":=")[1];
									    
									    System.out.println(k + " --> " + val);
									    
									    if(val.contains("INVOICES") || 
									    		 val.contains("REF:") ||
									    		 val.contains("HW") || 
									    		 val.contains("INVOICE")){
									    	System.out.println(" Required Invoice Details here *********************> " + val);
									    }
									}
									rownos = 0;
									cols = 0;
									break;
								}
							}
							
							Cell c = cellIterator.hasNext() ? cellIterator.next() : null;

							//if (c == null)
								//throw new Exception("Next Cell Iterator is null");

							if (c.getCellType() == 0) {
								double amt = c.getNumericCellValue();
								System.out.println("Amount (Cell Type Numeric Cell Value):: ==> " + amt);
							}

							if (c.getCellType() == 1) {
								String amount = c.getStringCellValue();
								System.out.println("Amount :: (Cell Type String Cell Value) ==> " + amount);
							}
						}
						
						
					}
				}
			}
		} catch (EncryptedDocumentException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (InvalidFormatException invalidFormatException) {
			System.out.println(invalidFormatException.getMessage());
			invalidFormatException.printStackTrace();
		}
	}

	public static Iterator<Row> excelRead(String FILE_NAME, int sheetNumber)
			throws EncryptedDocumentException, InvalidFormatException {

		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook wb = WorkbookFactory.create(excelFile);
			Sheet datatypeSheet = wb.getSheetAt(sheetNumber);
			Iterator<Row> iterator = datatypeSheet.iterator();

			return iterator;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
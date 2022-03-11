package utility;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public  class readCredentials {
//method for reading targeted cell
	public String ReadCellData(int vRow, int vColumn) {
		String value = null;	//variable for storing the cell value
		Workbook Wbook = null;	//initialize Workbook null;
		try {
		// src\\main\\java\\testData\\sample.xlsx
		FileInputStream fis = new FileInputStream("src\\main\\java\\testData\\sampleD9.xlsx");
		Wbook = new XSSFWorkbook(fis);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e1) {
			e1.printStackTrace();
		}
		Sheet sheet = Wbook.getSheet("login");
		Row row = sheet.getRow(vRow);	// returns Logical row
		Cell cell = row.getCell(vColumn);	//representing the given column
		value = cell.getStringCellValue();	//getting the cell value
		//sheet.close;
		//Wbook.close;
		//fis.Close;
		return value;	//returns the cell value
		
	}
	public static void main(String[] args) {
		readCredentials rc = new readCredentials();
		String username1 = rc.ReadCellData(1,0);
		String password1 = rc.ReadCellData(1,1);
		System.out.println(username1);
		System.out.println(password1);
	}
}

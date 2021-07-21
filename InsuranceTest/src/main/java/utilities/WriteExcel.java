package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * WriteExcel class is used to write the pass or fail condition for the testcases in the Excel file
 */
public class WriteExcel {
	public static XSSFWorkbook workbook;

	/*
	 * This method is to write testcase validation results on excel sheet
	 */
	public void writeTestCaseResult(String comments, String testStatus, String FileName, String RowNum) {
		try {
			int rowNum = Integer.parseInt(RowNum);
			rowNum = rowNum + 1;
			Workbook wb = null;
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\ExcelFiles\\" + FileName);
			String fileExtensionName = FileName.substring(FileName.indexOf("."));
			if (fileExtensionName.equalsIgnoreCase(".xlsx")) {
				wb = new XSSFWorkbook(fis);
			} else if (fileExtensionName.equalsIgnoreCase(".xls")) {
				wb = new HSSFWorkbook(fis);
			}
			Sheet sheet = wb.getSheetAt(0);
			CellStyle style = wb.createCellStyle();
			// Setting Background color
			if (testStatus.equalsIgnoreCase("pass"))
				style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
			else
				style.setFillForegroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			int testResultColumn = 0; // below method to get Test Result column
			while (!sheet.getRow(1).getCell(testResultColumn).getStringCellValue().equalsIgnoreCase("Test Ststus")) {
				testResultColumn++;
			}
			int commentsColumn = 0;
			while (!sheet.getRow(1).getCell(commentsColumn).getStringCellValue().equalsIgnoreCase("Comments")) {
				commentsColumn++;
			}
			sheet.getRow(rowNum).createCell(testResultColumn).setCellStyle(style); // creating cell
			sheet.getRow(rowNum).createCell(commentsColumn);
			sheet.getRow(rowNum).getCell(testResultColumn).setCellValue(testStatus); // setting Test result values after
																						// validation
			sheet.getRow(rowNum).getCell(commentsColumn).setCellValue(comments);// setting comments values after
																				// validation
			FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "\\ExcelFiles\\" + FileName);
			wb.write(out);
			TimeUnit.SECONDS.sleep(1);
			wb.close();
		} catch (Exception e) {
			System.out.println("Error : " + e.getMessage());
		}
	}

}

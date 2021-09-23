import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.EncryptedDocumentException;

public class XLSXHandler {
	
	private String filePath;
	
	public XLSXHandler(String path) {
		this.filePath = new String(path);
	}
	
	public byte highlightNotChosenLanguages (LanguageManager languageManager) {
		
		try {
			
			FileInputStream inputStream = new FileInputStream(filePath);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			int columns = 0;
			CellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			for (int i = 0; i <= rows; i++) {
				
				if (i < sheet.getRow(i).getLastCellNum()) {
					columns = sheet.getRow(i).getLastCellNum();
				}
			}
			
			for (int i = 0; i <= rows; i++) {
				
				XSSFRow row = sheet.getRow(i);
				
				for (int k = 0; k < columns; k++) {
					
					XSSFCell cell = row.getCell(k);

					if (Objects.nonNull(cell)) {
					
						switch (cell.getCellType()) {
						
							case STRING:
								String text = cell.getStringCellValue();
								if (!(languageManager.verifyIfChosenLanguage(text))) {
									cell.setCellStyle(style);
								}
								break;
								
							default:
								break;
						}
					}	
				}
			}
			
			inputStream.close();
			FileOutputStream outputStream = new FileOutputStream(filePath);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
			
			return 0;
		} catch (IOException | EncryptedDocumentException e) {
			System.out.println(e);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 2;
		}
	}
}

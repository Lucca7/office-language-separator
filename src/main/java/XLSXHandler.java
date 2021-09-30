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
	
	private LanguageManager languageManager;
	private String filePath;
	private FileInputStream inputStream;
	private FileOutputStream outputStream;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private int rows;
	private int columns;
	private XSSFRow row;
	private XSSFCell cell;
	private String text;
	private CellStyle style;
	
	public XLSXHandler(LanguageManager lm, String path) {
		this.languageManager = lm;
		this.filePath = new String(path);
	}
	
	public byte highlightNotChosenLanguages () {
		
		try {
			
			inputStream = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(inputStream);
			
			sheet = workbook.getSheetAt(0);
			rows = sheet.getLastRowNum();
			columns = 0;
			style = workbook.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			for (int i = 0; i <= rows; i++) {
				
				if (i < sheet.getRow(i).getLastCellNum()) {
					columns = sheet.getRow(i).getLastCellNum();
				}
			}
			
			for (int i = 0; i <= rows; i++) {
				
				row = sheet.getRow(i);
				
				for (int k = 0; k < columns; k++) {
					
					cell = row.getCell(k);

					if (Objects.nonNull(cell)) {
					
						switch (cell.getCellType()) {
						
							case STRING:
								text = cell.getStringCellValue();
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
			outputStream = new FileOutputStream(filePath);
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

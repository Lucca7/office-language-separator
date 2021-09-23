import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xwpf.usermodel.*;

public class DOCXHandler {

	LanguageManager languageManager;
	private String filePath;
	private boolean hideOtherLanguages;
	private boolean highlightOtherLanguages;
	List<XWPFParagraph> paragraphs;
	Iterator<IBodyElement> docElementsIterator;
	List<XWPFTable> xwpfTableList;
	List<XWPFRun> runs;
	XWPFTableCell cell;
	String text;
	
	public DOCXHandler(LanguageManager lm, String path, boolean hideOtherLangs, boolean highlightOtherLangs) {
		this.languageManager = lm;
		this.filePath = new String(path);
		this.hideOtherLanguages = hideOtherLangs;
		this.highlightOtherLanguages = highlightOtherLangs;
	}
	
	public byte highlightNotChosenLanguages () {
		
		try {
			
			FileInputStream inputStream;
			FileOutputStream outputStream;
			XWPFDocument document;
			inputStream = new FileInputStream(filePath);
			document = new XWPFDocument(inputStream);
			
			// Checking paragraphs on the body of the document
			paragraphs = document.getParagraphs();
			this.verifyEachParagraph();
			
			// Checking paragraphs inside tables
			docElementsIterator = document.getBodyElementsIterator();
            while (docElementsIterator.hasNext()) {
                IBodyElement docElement = docElementsIterator.next();
                if ("TABLE".equalsIgnoreCase(docElement.getElementType().name())) {
                    xwpfTableList = docElement.getBody().getTables();
                    for (XWPFTable xwpfTable : xwpfTableList) {
                        for (int i = 0; i < xwpfTable.getRows().size(); i++) {
                            for (int j = 0; j < xwpfTable.getRow(i).getTableCells().size(); j++) {
                            	cell = xwpfTable.getRow(i).getCell(j);
                            	paragraphs = cell.getParagraphs();
                            	this.verifyEachParagraph();
                            }
                        }
                    }
                }
            }
			
			inputStream.close();
			outputStream = new FileOutputStream(filePath);
			document.write(outputStream);
			outputStream.close();
			document.close();
			
			return 0;
		} catch (IOException | EncryptedDocumentException e) {
			System.out.println(e);
			return 1;
		} catch (Exception e) {
			System.out.println(e);
			return 2;
		}
	}
	
	private void verifyEachParagraph() {
		for (XWPFParagraph paragraph : paragraphs) {
			if (!(paragraph.isEmpty())) {
				text = paragraph.getParagraphText();
				if (!(languageManager.verifyIfChosenLanguage(text))) {
					runs = paragraph.getRuns();
					for (XWPFRun run : runs) {
						if (hideOtherLanguages) {
							run.setVanish(true);
						}
						if (highlightOtherLanguages) {
							run.setTextHighlightColor("green");
						}
					}
				}
			}
		}
	}
}

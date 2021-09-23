public class Main {
	public static void main(String args[]) {
		//String path = "C:\\teste\\teste.xlsx";
		String path = "C:\\teste\\teste.docx";
		String chosenLang = "enus";
		String[] langs = {"enus", "dede"};
		boolean hideOtherLanguages = true;
		boolean highlightOtherLanguages = true;
		LanguageManager lm = new LanguageManager(chosenLang, langs);
		//XLSXHandler handler = new XLSXHandler(path);
		DOCXHandler handler = new DOCXHandler(lm, path, hideOtherLanguages, highlightOtherLanguages);
		byte returnType = handler.highlightNotChosenLanguages();
		if (returnType == 0) {
			System.out.println("Sucesso");
		} else if (returnType == 1) {
			System.out.println("Erro de manipulação de arquivo");
		} else {
			System.out.println("Erro não especificado");
		}
	}
}

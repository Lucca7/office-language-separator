import com.github.pemistahl.lingua.api.*;
import static com.github.pemistahl.lingua.api.Language.*;
import java.util.ArrayList;	

public class LanguageManager {
	
	final private LanguageDetector detector;
	final private Language chosenLanguage;
	
	public LanguageManager (String language, String[] languages) {
		
		ArrayList<Language> langs = new ArrayList<Language>();
		Language[] langsArr;
		
		switch (language) {
			case "enus":
				chosenLanguage = ENGLISH;
				break;
			case "ptbr":
				chosenLanguage = PORTUGUESE;
				break;
			case "spla":
				chosenLanguage = SPANISH;
				break;
			case "frfr":
				chosenLanguage = FRENCH;
				break;
			case "dede":
				chosenLanguage = GERMAN;
				break;
			case "itit":
				chosenLanguage = ITALIAN;
				break;
			case "arar":
				chosenLanguage = ARABIC;
				break;
			case "jpjp":
				chosenLanguage = JAPANESE;
				break;
			case "zhcn":
				chosenLanguage = CHINESE;
				break;
			default:
				chosenLanguage = ENGLISH;
				break;
		}
		
		for (String str : languages) {
			switch (str) {
				case "enus":
					langs.add(ENGLISH);
					break;
				case "ptbr":
					langs.add(PORTUGUESE);
					break;
				case "spla":
					langs.add(SPANISH);
					break;
				case "frfr":
					langs.add(FRENCH);
					break;
				case "dede":
					langs.add(GERMAN);
					break;
				case "itit":
					langs.add(ITALIAN);
					break;
				case "arar":
					langs.add(ARABIC);
					break;
				case "jpjp":
					langs.add(JAPANESE);
					break;
				case "zhcn":
					langs.add(CHINESE);
					break;
			}
		}
		
		langsArr = new Language[langs.size()];
		langsArr = langs.toArray(langsArr);
		detector = LanguageDetectorBuilder.fromLanguages(langsArr).build();
	}
	
	public boolean verifyIfChosenLanguage (String text) {
		Language detectedLanguage = detector.detectLanguageOf(text);
		System.out.println(detectedLanguage);
		return detectedLanguage.equals(chosenLanguage);
	}
}

package home.test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import home.utility.BundleLanguageManager;
import home.utility.LocalFolder;
//package-protected
final class InitializeLanguage {
    private static final String FILE = LocalFolder.CONFIG_FOLDER.toString() + LocalFolder.SEPARATOR + "test-language";
    public static void initialize() {
        BundleLanguageManager.get().setLocaleFile(new File(FILE));
        try {
            BundleLanguageManager.get().setLocale(Locale.ITALIAN);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private InitializeLanguage() { }
}

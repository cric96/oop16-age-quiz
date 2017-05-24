package home.test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import home.utility.BundleLanguageManager;
import home.utility.LocalFolder;
//package-protected
final class InitializeLanguage {
    private static final String FILE = LocalFolder.CONFIG_FOLDER + LocalFolder.SEPARATOR + "test-language";
    public static void initialize() {
        BundleLanguageManager.get().setLocaleFile(new File(FILE));
        try {
            BundleLanguageManager.get().setLocale(Locale.ITALIAN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private InitializeLanguage() { }
}

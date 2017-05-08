package home.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * a class used to manage the language during the game.
 */
public class BundleLanguageManager {
    private static final List<Locale> SUPPORTED_LANGUAGE = Arrays.asList(Locale.ITALIAN, Locale.ENGLISH);
    private static final BundleLanguageManager SINGLETON = new BundleLanguageManager();
    private static final String PACKAGE = "language.";
    private File locale;
    private Locale language;
    /**
     * 
     * @return
     *  a bundle manager
     */
    public static BundleLanguageManager get() {
        return BundleLanguageManager.SINGLETON;
    }
    /**
     * 
     * @return
     *  a list of locale supported
     */
    public List<Locale> getSupportedLanguage() {
        return BundleLanguageManager.SUPPORTED_LANGUAGE;
    }
    /**
     * 
     * @return
     *  the current locale of a session in game
     */
    public Locale getCurrentLocale() {
        return Optional.of(this.language).orElseThrow(() -> new IllegalStateException());
    }
    /**
     * set a file where put the locale.
     * @param file
     *  the file where is store a locale
     * @throws FileNotFoundException
     *  if the file is not legal
     * @throws IOException
     *  if the file is not legal
     * @throws ClassNotFoundException
     *  if the file don't contains the locale
     */
    public void setLocaleFile(final File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        try (ObjectInput input = new ObjectInputStream(new FileInputStream(file))) {
            this.language = (Locale) input.readObject();
            this.locale = file; 
        }
    }
    /**
     * set the locale of a current session of game.
     * @param locale
     *  the locale that you want to set
     * @throws FileNotFoundException
     *  if there is some error in the file
     * @throws IOException
     *  if there is some error in the file
     */
    public void setLocale(final Locale locale) throws FileNotFoundException, IOException {
        if (!this.getSupportedLanguage().contains(locale)) {
            throw new IllegalArgumentException();
        }
        this.language = locale;
        try (ObjectOutput output = new ObjectOutputStream(new FileOutputStream(this.locale))) {
            output.writeObject(this.language);
        }
    }
    /** 
     * get a resource bundle associated with the key.
     * @param bundleName
     *  the name of bundle 
     * @return
     *  the bundle associated 
     */
    public ResourceBundle getBundle(final String bundleName) {
        return ResourceBundle.getBundle(PACKAGE + bundleName, this.language);
    }
}

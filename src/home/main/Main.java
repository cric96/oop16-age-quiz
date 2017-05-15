package home.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Locale;

import home.controller.profile.ProfileBox;
import home.utility.BundleLanguageManager;
import home.utility.LocalFolder;
import home.view.App;
import home.view.debug.DebugApplication;
import javafx.application.Application;

/**
 * This class is used to start the video game
 * and to set some values.
 * 
 */
public final class Main {
    private static final boolean DEBUG = false;
    private static final File BOX_PROFILES = new File(LocalFolder.CONFIG_FOLDER + LocalFolder.SEPARATOR.toString() + "profile-box.obj");
    private static final File LANGUAGE = new File(LocalFolder.CONFIG_FOLDER + LocalFolder.SEPARATOR.toString() + "language.obj");
    private Main() { }
    /** 
     * @param args
     *  not used in this contest 
     * @throws IOException 
     *  the error due to load a file
     * @throws ClassNotFoundException
     *  the error due to load a file 
     */
    public static void main(final String[] args) throws IOException, ClassNotFoundException {
        if (!new File(LocalFolder.CONFIG_FOLDER.toString()).exists()) {
            new Installer().install();
        }
        ProfileBox.getProfileBox().setFile(BOX_PROFILES);
        ProfileBox.getProfileBox().load();
        BundleLanguageManager.get().setLocaleFile(LANGUAGE);
        BundleLanguageManager.get().setLocale(Locale.ITALIAN);
        if (DEBUG) {
            DebugApplication.launch();
        } else {
            Application.launch(App.class);
        }
    }
    //a little object used for the first launch of application
    private static class  Installer {
        private void install() throws IOException {
            new File(LocalFolder.CONFIG_FOLDER.toString()).mkdir();
            ProfileBox.getProfileBox().setFile(BOX_PROFILES);
            ProfileBox.getProfileBox().save();
            try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(LANGUAGE))) {
                out.writeObject(Locale.getDefault());
            }
        }
    }
}

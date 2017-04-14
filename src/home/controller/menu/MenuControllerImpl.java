package home.controller.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import home.controller.AbstractController;
import home.controller.Controller;
import home.model.Game;
import home.model.Kingdom;
import home.utility.LocalFolder;
import home.utility.Utility;
import home.view.Container;
import home.view.View;
import home.view.menu.MenuView;
//package-protected
class MenuControllerImpl extends AbstractController<MenuView> implements MenuController {
    private static final String BOX_PROFILES = "profile-box.obj";
    private final BoxProfile profiles;
    MenuControllerImpl(final MenuView ... views) {
        super(views);
        final File file = new File(LocalFolder.CONFIG_FOLDER + BOX_PROFILES);
        this.profiles = new BoxProfile(file);
        if (file.exists()) {
            try {
                this.profiles.load();
            } catch (ClassNotFoundException | IOException e) {
                //TODO metti errore nelle view
            }
        }
    }
    @Override
    public void checkUpdate() {

    }

    @Override
    public void newGamePressed() {
        this.getInternalView().forEach(x -> x.showNewGame(this.profiles.getProfile()));
    }

    @Override
    public void createGame(final String name, final Profile profile) {
        profile.setEnabled(true);
        profile.setName(name);
        try {
            this.profiles.save();
        } catch (IOException e) {
            //TODO MANDA ERRORE SU TUTTI I MESSAGGI
        }
        Game.getGame().newGame();
        //TODO METTI IL METODO PER CAMBIARE LA SCENA
    }

    @Override
    public void loadGamePressed() {
        this.getInternalView().forEach(x -> x.showSavedGames(this.profiles.getProfile()));
    }

    @Override
    public void loadGame(final Profile profile) {
        Game.getGame().load(profile.getSaveGame());
        //TODO METTI IL METODO PER CAMBIARE LA SCENA
    }

    @Override
    public void exitPressed() {
        System.out.println("EXIT PRESSED");
    }

    @Override
    protected void attachViews() {
        this.getInternalView().forEach(x -> x.attachOn((this)));
    }
    //a class that contain and restore a set of profile
    private static class BoxProfile {
        private static final int NUM_PROFILES = 3;
        private Set<Profile> profiles;
        private final File saveFile;
        BoxProfile(final File file) {
            this.saveFile = file;
            this.profiles = IntStream.range(0, NUM_PROFILES)
                                     .mapToObj(x -> Profile.createProfile(new File(LocalFolder.SAVE_FOLDER.getInfo() + LocalFolder.SEPARATOR.getInfo() + x)))
                                     .collect(Collectors.toSet());
        }
        public void save() throws IOException {
            final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(this.saveFile));
            out.writeObject(profiles);
            out.close();
        }
        @SuppressWarnings("unchecked")
        public void load() throws IOException, ClassNotFoundException {
            final ObjectInput in = new ObjectInputStream(new FileInputStream(this.saveFile));
            this.profiles = (Set<Profile>) in.readObject();
            in.close();
        }
        public Set<Profile> getProfile() {
            return Collections.unmodifiableSet(profiles);
        }
    }

}

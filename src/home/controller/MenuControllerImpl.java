package home.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import home.controller.profile.Profile;
import home.model.Game;
import home.utility.LocalFolder;
import home.view.Container;
import home.view.MessageType;
import home.view.ViewType;
import home.view.menu.MenuView;
//package-protected
final class MenuControllerImpl extends AbstractController<MenuView> implements MenuController {
    private static final String BOX_PROFILES = "profile-box.obj";
    private static final String EXIT_MESSAGE = "Are you sure to do this?";
    private static final String FILE_ERROR = "there was ar error caused by loading file!";
    private static final String PROFILE_ERROR = "error to try to load a profile";
    private final BoxProfile profiles;
    MenuControllerImpl(final MenuView ... views) {
        super(views);
        final File file = new File(LocalFolder.CONFIG_FOLDER.getInfo() + LocalFolder.SEPARATOR.getInfo() + BOX_PROFILES);
        //TODO PENSA SE FARE UN PICCOLO INSTALLER
        this.profiles = new BoxProfile(file);
        if (file.exists()) {
            try {
                this.profiles.load();
            } catch (ClassNotFoundException | IOException e) {
                super.showErrors(FILE_ERROR);
            }
        } else {
            try {
                new File(LocalFolder.CONFIG_FOLDER.getInfo()).mkdir();
                this.profiles.save();
            } catch (IOException e) {
                super.showErrors(FILE_ERROR);
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
            super.showErrors(FILE_ERROR);
        }
        Game.getGame().newGame();
        try {
            Game.getGame().save(profile.getSaveGame());
        } catch (IOException e) {
            super.showErrors(FILE_ERROR);
        }
        Container.getContainer().changeDisplay(ViewType.WORLD);
    }

    @Override
    public void loadGamePressed() {
        this.getInternalView().forEach(x -> x.showSavedGames(this.profiles.getProfile()));
    }

    @Override
    public void loadGame(final Profile profile) {
        if (!profile.isEnabled()) {
            super.showErrors(PROFILE_ERROR);
        }
        try {
            Game.getGame().load(profile.getSaveGame());
        } catch (ClassNotFoundException | IOException e) {
            super.showErrors(FILE_ERROR);
        }
        Container.getContainer().changeDisplay(ViewType.WORLD);
    }

    @Override
    public void exitPressed() {
        this.getInternalView().forEach(x -> x.showMessage(EXIT_MESSAGE, MessageType.EXIT));
    }
    public void exitConfirmed() {
        System.exit(0);
    }
    @Override
    protected void attachViews() {
        this.getInternalView().forEach(x -> x.attachOn((this)));
    }
    //a class that contain and restore a set of profile
    //TODO VEDI SE FARLA ESTERNA
    private static class BoxProfile {
        private static final int NUM_PROFILES = 3;
        private Set<Profile> profiles;
        private final File saveFile;
        BoxProfile(final File file) {
            this.saveFile = file;
            this.profiles = IntStream.range(0, NUM_PROFILES)
                                     .mapToObj(x -> Profile.createProfile(new File(LocalFolder.CONFIG_FOLDER.getInfo() + LocalFolder.SEPARATOR.getInfo() + x)))
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

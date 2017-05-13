package home.controller;

import java.io.IOException;
import java.util.Set;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.controller.profile.ProfileBox;
import home.model.Game;
import home.view.Container;
import home.view.MessageType;
import home.view.View;
import home.view.ViewType;
import home.view.menu.MenuView;

final class MenuObserverImpl extends AbstractObserver implements MenuObserver {
    private static final String EXIT_MESSAGE = "Are you sure to do this?";
    private static final String FILE_ERROR = "there was ar error caused by loading file!";
    private static final String PROFILE_ERROR = "error to try to load a profile";
    private final ProfileBox profiles;
    private final Set<MenuView> views;
    MenuObserverImpl(final Set<MenuView> views) {
        this.views = views;
        this.views.forEach(x -> x.attachOn(this));
        this.profiles = ProfileBox.getProfileBox();
        //foreach
    }

    @Override
    public void newGamePressed() {
        this.views.forEach(x -> x.showNewGame(this.profiles.getProfile()));
    }

    @Override
    public void createGame(final String name, final Profile profile) {
        profile.setEnabled(true);
        profile.setName(name);
        this.profiles.select(profile);
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
        ProfileBox.getProfileBox().select(profile);
        Container.getContainer().changeDisplay(ViewType.WORLD);
    }

    @Override
    public void loadGamePressed() {
        this.views.forEach(x -> x.showSavedGames(this.profiles.getProfile()));
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
        ProfileBox.getProfileBox().select(profile);
        Container.getContainer().changeDisplay(ViewType.WORLD);
    }

    @Override
    public void exitPressed() {
        this.views.forEach(x -> x.showMessage(EXIT_MESSAGE, MessageType.EXIT));
    }
    public void exitConfirmed() {
        System.exit(0);
    }

    @Override
    protected Set<? extends View<?>> getAttached() {
        return this.views;
    }

    @Override
    protected void defineUpdateView(final View<?> view) { }
}

package home.controller;

import java.io.IOException;
import java.util.Set;

import home.controller.observer.MenuObserver;
import home.controller.profile.Profile;
import home.controller.profile.ProfileBox;
import home.model.Game;
import home.utility.BundleLanguageManager;
import home.view.Container;
import home.view.MessageType;
import home.view.View;
import home.view.ViewType;
import home.view.menu.MenuView;

final class MenuObserverImpl extends AbstractObserver implements MenuObserver {
    private static final String EXIT_MESSAGE = "EXIT";
    private static final String FILE_ERROR = "FILE_ERROR";
    private static final String PROFILE_ERROR = "PROFILE_ERROR";
    private static final String BUNDLE = "ErrorBundle";
    private final ProfileBox profiles;
    private final Set<MenuView> views;
    MenuObserverImpl(final Set<MenuView> views) {
        this.views = views;
        this.views.forEach(x -> x.attachOn(this));
        this.profiles = ProfileBox.getProfileBox();
    }

    @Override
    public void newGamePressed() {
        this.views.forEach(x -> x.showNewGame(this.profiles.getProfile()));
    }

    @Override
    public void createGame(final String name, final Profile profile) {
        final String fileError = BundleLanguageManager.get().getBundle(BUNDLE).getString(PROFILE_ERROR);
        profile.setEnabled(true);
        profile.setName(name);
        this.profiles.select(profile);
        try {
            this.profiles.save();
        } catch (IOException e) {
            super.showErrors(fileError);
        }
        Game.getGame().newGame();
        try {
            Game.getGame().save(profile.getSaveGame());
        } catch (IOException e) {
            super.showErrors(fileError);
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
        final String fileError = BundleLanguageManager.get().getBundle(BUNDLE).getString(PROFILE_ERROR);
        final String profileError = BundleLanguageManager.get().getBundle(BUNDLE).getString(FILE_ERROR);
        if (!profile.isEnabled()) {
            super.showErrors(profileError);
        }
        try {
            Game.getGame().load(profile.getSaveGame());
        } catch (ClassNotFoundException | IOException e) {
            super.showErrors(fileError);
        }
        ProfileBox.getProfileBox().select(profile);
        Container.getContainer().changeDisplay(ViewType.WORLD);
    }

    @Override
    public void exitPressed() {
        final String exit = BundleLanguageManager.get().getBundle(BUNDLE).getString(EXIT_MESSAGE);
        this.views.forEach(x -> x.showMessage(exit, MessageType.EXIT));
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

package home.view.debug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import home.controller.MenuController;
import home.controller.profile.Profile;
import home.view.menu.MenuView;

class ConsoleMenuViewImpl extends AbstractConsoleView<MenuController> implements MenuView {

    private final BufferedReader read;
    ConsoleMenuViewImpl() {
        this.read = new BufferedReader(new InputStreamReader(System.in));
    }
    public void show() {

        System.out.println("Welcome in the main screen, what want to do??");
        System.out.println("1 : create new game");
        System.out.println("2: laod game");
        System.out.println(": exit..");
        try {
            final int scelta = Integer.parseInt(read.readLine());
            switch(scelta) {
            case 1:
                this.getCurrentController().newGamePressed();
                break;
            case 2:
                this.getCurrentController().loadGamePressed();
                break;
            default:
                this.getCurrentController().exitConfirmed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void showSavedGames(final Set<Profile> profiles) {
        final Profile selected = this.selectedProfile(profiles).get();
        this.getCurrentController().loadGame(selected);
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        final Profile selected = this.selectedProfile(profiles).get();
        System.out.println("Scegli un nome..");
        try {
            final String name = read.readLine();
            this.getCurrentController().createGame(name, selected);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private Optional<Profile> selectedProfile(final Set<Profile> profiles) {
        final List<Profile> listProfiles = new ArrayList<>(profiles);
        System.out.println("Wich profile you choose??");
        IntStream.range(0, listProfiles.size()).forEach(x -> System.out.println(x + listProfiles.get(x).getName().orElse("EMPTY")));
        try {
            final int scelta = Integer.parseInt(this.read.readLine());
            return Optional.of(listProfiles.get(scelta));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    private MenuController getCurrentController() {
        return this.getController().orElseThrow(() -> new IllegalStateException());
    }
}

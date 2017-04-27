package home.view.debug;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

import home.controller.MenuController;
import home.controller.profile.Profile;
import home.view.menu.MenuView;

class ConsoleMenuViewImpl extends AbstractConsoleView<MenuController> implements MenuView {
    private final Scanner scan;
    ConsoleMenuViewImpl() {
        scan = new Scanner(System.in);
    }
    public void show() {
        System.out.println("Welcome in the main screen, what want to do??");
        System.out.println("1 : create new game");
        System.out.println("2: laod game");
        System.out.println(": exit..");
        final int scelta = this.scan.nextInt();
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
    }
    @Override
    public void showSavedGames(final Set<Profile> profiles) {
        final Profile selected = this.selectedProfile(profiles);
        this.getCurrentController().loadGame(selected);
    }

    @Override
    public void showNewGame(final Set<Profile> profiles) {
        final Profile selected = this.selectedProfile(profiles);
        System.out.println("Scegli un nome..");
        final String name = this.scan.nextLine();
        this.getCurrentController().createGame(name, selected);

    }
    private Profile selectedProfile(final Set<Profile> profiles) {
        final List<Profile> listProfiles = new ArrayList<>(profiles);
        System.out.println("Wich profile you choose??");
        IntStream.range(0, listProfiles.size()).forEach(x -> System.out.println(x + listProfiles.get(x).getName().orElse("EMPTY")));
        final int scelta = this.scan.nextInt();
        //TODO AGGIUNGERE CONTROLLO A SCELTA
        return listProfiles.get(scelta);
    }
    private MenuController getCurrentController() {
        return this.getController().orElseThrow(() -> new IllegalStateException());
    }
}

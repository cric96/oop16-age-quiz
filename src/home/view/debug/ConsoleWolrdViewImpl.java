package home.view.debug;

import java.util.Map;
import java.util.Scanner;

import home.controller.MenuController;
import home.controller.WorldController;
import home.controller.dialog.Dialog;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;
import home.view.world.WorldView;

class ConsoleWolrdViewImpl extends AbstractConsoleView<WorldController> implements WorldView{
    private static final String LEVELUP = "LVUP";
    private static final String START = "START";
    private String era;
    private int exp;
    private Map<String, Integer> statusScore;
    private Map<BuildingType, Pair<ImageInfo, Boolean>> buildings;
    private ImageInfo kingdom;
    private final Scanner scan;
    ConsoleWolrdViewImpl() {
        scan = new Scanner(System.in);
    }
    @Override
    public void show() {
        System.out.println("Welcome to the wolrd!");
        System.out.println("era = " + this.era);
        System.out.println("exp = " + this.exp);
        System.out.println("Status..");
        this.statusScore.forEach((x, y) -> System.out.println(x + " = " + y));
        System.out.println("---");
        System.out.println("buildings..");
        this.buildings.forEach((x, y) -> {
            if (y.getY()) {
                System.out.println("name = " + x.toString() + " image name = " + y.getX().getPath()); 
            }
        });
        System.out.println("kingdom : image info = " + kingdom.getPath());
        System.out.println("other to select kingdom");
        final String value = this.scan.nextLine();
        boolean isBuilding = true;
        try {
            System.out.println(BuildingType.valueOf(value));
        } catch (Exception e) {
            isBuilding = false;
        }
        if (isBuilding) {
            this.getCurrentController().pressOnBuilding(BuildingType.valueOf(value));
        } else {
            this.getCurrentController().pressOnKingdom();
        }
    }

    @Override
    public void updateEra(final Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, final ImageInfo kingdom) {
        this.buildings = buildings;
        this.kingdom = kingdom;
    }

    @Override
    public void changeEra(final String era) {
        this.era = era;
    }

    @Override
    public void changeExp(final int exp) {
        this.exp = exp;
    }

    @Override
    public void changeStatus(final Map<String, Integer> statusScose) {
        this.statusScore = statusScose;
    }

    @Override
    public void showBuildingDialog(final BuildingType building, final Dialog dialog) {
        boolean canLevelUp = true;
        System.out.println("BUILDING = " + dialog.getName());
        System.out.println("level = " + dialog.getLevel());
        if (dialog.isLevelBlocked() && dialog.levelUpEnabled()) {
            System.out.println(dialog.getExperience());
            System.out.println("to level up write " + LEVELUP);
            canLevelUp = false;
        }
        System.out.println("To start a quiz write " + START);
        System.out.println("to turn back write somethig..");
        final String value = this.scan.nextLine();
        if (value.equals(START)) {
            this.getCurrentController().createQuiz(building);
        } else if (value.equals(LEVELUP) && canLevelUp) {
            this.getCurrentController().nextLevel(building);
            this.show();
        } else {
            this.show();
        }
    }

    @Override
    public void showKingdomDialog(final Dialog dialog) {
        System.out.println("KINGDOM");
        if (dialog.isLevelBlocked() && dialog.levelUpEnabled()) {
            System.out.println(dialog.getExperience());
            System.out.println("to level up write " + LEVELUP);
        }
    }
    private WorldController getCurrentController() {
        return this.getController().orElseThrow(() -> new IllegalStateException());
    }

}
package home.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import home.controller.dialog.Dialog;
import home.model.Game;
import home.model.Kingdom;
import home.model.building.BuildingType;
import home.model.building.ImmutableAgeBuilding;
import home.model.image.Image;
import home.utility.Pair;
import home.view.Container;
import home.view.ViewType;
import home.view.world.WorldView;

class WorldControllerImpl extends AbstractController<WorldView> implements WorldController {
    private static final String CASTLE_NAME = "CASTLE";
    WorldControllerImpl(final WorldView ... views) {
        super(views);
    }
    @Override
    public void checkUpdate() {
        final Kingdom current = Game.getGame().getCurrentKingdom();
        // update the main view
        this.getInternalView().forEach(x -> {
            x.changeStatus(current.getStatusStatistic().entrySet().stream()
                    .collect(Collectors.toMap(y -> y.getKey().toString(), y -> y.getValue())));
            this.onAgeChange();

        });
    }

    @Override
    public void nextEra() {
        Game.getGame().getCurrentKingdom().nextAge();
        //update all thing in the view
        this.onAgeChange();
    }

    @Override
    public void nextLevel(final BuildingType building) {
        final ImmutableAgeBuilding build = this.getBuilding(building);
        build.levelUp();
        //update the experience amount in the views
        this.getInternalView().forEach(x -> x.changeExp(Game.getGame().getCurrentKingdom().getExperienceAmount()));
    }

    @Override
    public void createQuiz(final BuildingType building) {
        Game.getGame().createQuiz(building);
        Container.getContainer().changeDisplay(ViewType.QUIZ);
    }

    @Override
    public void goOnMenu() {
        Container.getContainer().changeDisplay(ViewType.MENU);
    }

    @Override
    public void pressOnBuilding(final BuildingType building) {
        this.getInternalView().forEach(x -> x.showBuildingDialog(building, this.createBuildingDialog(building)));
    }

    @Override
    public void pressOnKingdom() {
        this.getInternalView().forEach(x -> x.showKingdomDialog(this.createKingdomDialog()));
    }

    @Override
    protected void attachViews() {
        this.getInternalView().forEach(x -> x.attachOn(this));
    }
    //create a dialog by a kingdom status
    private Dialog createKingdomDialog() {
        final Kingdom current = Game.getGame().getCurrentKingdom();
        return Dialog.Builder.createBuilder().setName(CASTLE_NAME).setExperience(current.getExperienceAmount())
                                             .setLevel(current.getAge().getIncrementalLevel())
                                             .setLevelBlocked(current.getAge().isUpgradable())
                                             .setLevelEnable(current.canUpgradeAge()).build();
    }
    /*return a building attach on the kingdom*/
    private ImmutableAgeBuilding getBuilding(final BuildingType building) {
        return Game.getGame().getCurrentKingdom().getComponents(ImmutableAgeBuilding.Container.class).stream()
                .filter(x -> x.getY()).filter(x -> x.getX().getName() == building).map(x -> x.getX()).findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
    //crea un dialogo dato un building type
    private Dialog createBuildingDialog(final BuildingType type) {
        final ImmutableAgeBuilding building = this.getBuilding(type);
        return Dialog.Builder.createBuilder().setName(building.getName().toString())
                                             .setExperience(building.getLevel().getExperienceAmount())
                                             .setLevel(building.getLevel().getIncrementalLevel()).setLevelBlocked(building.getLevel().isUpgradable())
                                             .setLevelEnable(building.canLevelUp()).build();
    }
    //TODO PENSA SE FARE UN OPTIONAL DI IMAGE AL POSTO DI UN IMAGE
    /*what to do when the age change*/
    private void onAgeChange() {
        final Kingdom current = Game.getGame().getCurrentKingdom();
        final Map<BuildingType, Pair<Image, Boolean>> buildings = createMap(current.getComponents(ImmutableAgeBuilding.Container.class));
        final Image kingdomImage = current.getComponents(Image.class).stream().findFirst().get().getX();
        this.getInternalView().stream().forEach(x -> {
            x.updateEra(buildings, kingdomImage);
            x.changeEra(current.getAgeName());
            x.changeExp(current.getExperienceAmount());
        });
    }
    /*Create a map to give a view by the internal state on kingdom*/
    private Map<BuildingType, Pair<Image, Boolean>> createMap(final Set<Pair<ImmutableAgeBuilding.Container, Boolean>> buildings) {
        final Map<BuildingType, Pair<Image, Boolean>> returnBuilding = new HashMap<>();
        for (final Pair<ImmutableAgeBuilding.Container, Boolean> building : buildings) {
            final Image image = building.getX().getComponents(Image.class).stream().findFirst().get().getX();
            returnBuilding.put(building.getX().getName(), Pair.createPair(image, building.getY()));
        }
        return returnBuilding;
    }
}
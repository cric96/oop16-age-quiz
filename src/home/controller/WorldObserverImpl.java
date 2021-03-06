package home.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.controller.profile.Profile;
import home.controller.profile.ProfileBox;
import home.model.Game;
import home.model.building.Building;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.model.kingdom.Kingdom;
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.Pair;
import home.view.MessageType;
import home.view.View;
import home.view.ViewType;
import home.view.container.Container;
import home.view.world.WorldView;

final class WorldObserverImpl extends AbstractObserver implements WorldObserver {

    private static final String CASTLE_NAME = "CASTLE";
    private static final String END_QUESTION = "END_QUESTION"; 
    private static final String EXPERIENCE_ERROR = "EXPERIENCE_ERROR";
    private static final String BUILDING_ERROR = "BUILDING_ERROR";
    private static final String PROFILE_ERROR = "PROFILE_ERROR"; 
    private final Set<WorldView> views;
    WorldObserverImpl(final Set<WorldView>  views) {
        this.views = views;
        this.views.forEach(x -> x.attachOn(this));
    }
    @Override
    public void nextEra() {
        final String experienceError = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(EXPERIENCE_ERROR);
        try {
            Game.getGame().getCurrentKingdom().nextAge();
            //update all thing in the view
            this.onAgeChange();
        } catch (IllegalStateException exc) {
            this.showMessageInViews(experienceError, MessageType.ERROR);
        }
    }

    @Override
    public void nextLevel(final BuildingType building) {
        final String experienceError = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(EXPERIENCE_ERROR);
        try {
            final Building build = this.getBuilding(building);
            build.levelUp();
            //update the experience amount in the views
            this.views.forEach(x -> x.changeExp(Game.getGame().getCurrentKingdom().getExperienceAmount()));
        } catch (IllegalArgumentException | IllegalStateException e) {
            this.showMessageInViews(experienceError, MessageType.ERROR);
        }
    }

    @Override
    public void createQuiz(final BuildingType building) {
        final String endQuestion = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(END_QUESTION);
        try {
            Game.getGame().createQuiz(building);
            Container.getContainer().changeDisplay(ViewType.QUIZ);
        } catch (Exception e) {
            this.showMessageInViews(endQuestion, MessageType.ERROR);
        }
    }

    @Override
    public void goOnMenu() {
        final String error = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(PROFILE_ERROR);
        if (!ProfileBox.getProfileBox().getSelected().isPresent()) {
            this.showMessageInViews(error, MessageType.ERROR);
        } else {
            final Profile selected = ProfileBox.getProfileBox().getSelected().get();
            try {
                Game.getGame().save(selected.getSaveGame());
                Container.getContainer().changeDisplay(ViewType.MENU);
            } catch (IOException e) {
                this.showMessageInViews(error, MessageType.ERROR);
            }
        }
    }

    @Override
    public void pressOnBuilding(final BuildingType building) {
        this.views.forEach(x -> x.showBuildingDialog(building, this.createBuildingDialog(building)));
    }

    @Override
    public void pressOnKingdom() {
        this.views.forEach(x -> x.showKingdomDialog(this.createKingdomDialog()));
    }

    //create a dialog by a kingdom status
    private Dialog createKingdomDialog() {
        final ResourceBundle bundle = BundleLanguageManager.get().getBundle(Bundles.BUILDING);
        final Kingdom current = Game.getGame().getCurrentKingdom();
        return Dialog.Builder.createBuilder().setName(bundle.getString(CASTLE_NAME))
                                             .setExperience(current.getAge().getExperienceAmount())
                                             .setLevel(current.getAge().getIncrementalLevel())
                                             .setLevelBlocked(!current.getAge().isUpgradable())
                                             .setLevelEnable(current.canUpgradeAge()).build();
    }
    /*return a building attach on the kingdom*/
    private Building getBuilding(final BuildingType building) {
        return Game.getGame().getCurrentKingdom().getComponents(Building.Container.class).stream()
                .filter(x -> x.getY()).filter(x -> x.getX().getName() == building).map(x -> x.getX()).findFirst()
                .orElseThrow(() -> new IllegalArgumentException());
    }
    //crea un dialogo dato un building type
    private Optional<Dialog> createBuildingDialog(final BuildingType type) {
        final String buildingError = BundleLanguageManager.get().getBundle(Bundles.ERROR).getString(BUILDING_ERROR);
        try {
            final Building building = this.getBuilding(type);
            return Optional.of(Dialog.Builder.createBuilder().setName(building.getName().toString())
                    .setExperience(building.getLevel().getExperienceAmount())
                    .setLevel(building.getLevel().getIncrementalLevel()).setLevelBlocked(building.getLevel().isUpgradable())
                    .setLevelEnable(building.canLevelUp()).build());
        } catch (IllegalArgumentException exc) {
            this.showMessageInViews(buildingError, MessageType.ERROR);
        }
        return Optional.empty();
    }
    /*what to do when the age change*/
    private void onAgeChange() {
        final Kingdom current = Game.getGame().getCurrentKingdom();
        final String age = BundleLanguageManager.get().getBundle(Bundles.AGE).getString(current.getAge().getName());
        final Map<BuildingType, Pair<ImageInfo, Boolean>> buildings = createMap(current.getComponents(Building.Container.class));
        final ImageInfo kingdomImage = current.getComponents(ImageInfo.class).stream().findFirst().get().getX();
        this.views.stream().forEach(x -> {
            x.updateEra(buildings, kingdomImage);
            x.changeEra(age);
            x.changeExp(current.getExperienceAmount());
        });
    }
    /*Create a map to give a view by the internal state on kingdom*/
    private Map<BuildingType, Pair<ImageInfo, Boolean>> createMap(final Set<Pair<Building.Container, Boolean>> buildings) {
        final Map<BuildingType, Pair<ImageInfo, Boolean>> returnBuilding = new TreeMap<>();
        for (final Pair<Building.Container, Boolean> building : buildings) {
            final ImageInfo image = building.getX().getComponents(ImageInfo.class).stream().findFirst().get().getX();
            returnBuilding.put(building.getX().getName(), Pair.createPair(image, building.getY()));
        }
        return returnBuilding;
    }
    @Override
    protected Set<? extends View<?>> getAttached() {
        return this.views;
    }
    @Override
    protected void defineUpdateView(final View<?> view) {
        final Kingdom current = Game.getGame().getCurrentKingdom();
        final WorldView world = (WorldView) view;
        world.changeStatus(current.getStatusStatistic().entrySet().stream()
             .collect(Collectors.toMap(y -> y.getKey().toString(), y -> y.getValue())));
    }
    @Override
    protected void update() {
        this.onAgeChange();
        super.update();
    }
}

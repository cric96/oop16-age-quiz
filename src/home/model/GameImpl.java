package home.model;

import java.io.File;
import java.util.Optional;

import home.model.building.BuildingFactory;
import home.model.level.Level;
import home.model.status.*;

final class GameImpl implements Game {
    private static final Game SINGLETON = new GameImpl();
    private Optional<Kingdom> currentKingdom;
    //package protected
    static Game get() {
        return GameImpl.SINGLETON;
    }
    private GameImpl() {
        this.currentKingdom = Optional.empty();
    }
    @Override
    public void save(final File save) {
        // TODO Auto-generated method stub
    }

    @Override
    public void load(final File load) {
        // TODO Auto-generated method stub

    }

    @Override
    public Kingdom getCurrentKingdom() {
        return currentKingdom.orElseThrow(() -> new IllegalStateException());
    }

    @Override
    public void newGame() {
        final Kingdom current = new KingdomImpl(Status.createStatuses(), Level.Age.createAgeLevel());
        BuildingFactory.get().createAllBuilding().forEach(x -> {
            x.attachOn(current);
            current.addComponent(x);
        });
        this.currentKingdom = Optional.of(current);
    }

}

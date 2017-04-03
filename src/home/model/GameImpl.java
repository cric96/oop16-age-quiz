package home.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Optional;


import home.model.building.BuildingFactory;
import home.model.level.Level;
import home.model.status.Status;

final class GameImpl implements Game {
    private static final Game SINGLETON = new GameImpl();
    private Optional<Kingdom> currentKingdom;
    //package protected
    public static Game get() {
        return GameImpl.SINGLETON;
    }
    private GameImpl() {
        this.currentKingdom = Optional.empty();
    }
    @Override
    public void save(final File save) {
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(save))) {
            out.writeObject(this.currentKingdom.orElseThrow(() -> new IllegalStateException()));
        //DA VEDERE COME FARE    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void load(final File load) {
        try (ObjectInput in = new ObjectInputStream(new FileInputStream(load))) {
            this.currentKingdom = Optional.of((Kingdom) in.readObject());
        //DA VEDERE COME FARE    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

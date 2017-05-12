package home.model.kingdom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonStreamParser;

import home.model.building.BuildingComposite;
import home.model.building.BuildingFactory;
import home.model.building.BuildingType;
import home.model.building.ImmutableAgeBuilding;
import home.model.composite.Component;
import home.model.composite.Composite;
import home.model.image.ImageComponent;
import home.model.level.Level;
import home.model.serializator.Serializator;
import home.model.status.Status;
import home.model.status.StatusName;
import home.utility.LocalFolder;
import home.utility.Pair;
import netscape.javascript.JSObject;
//package-protected
class KingdomSerializator implements Serializator<Kingdom> {
    private static final String AGE = "age";
    private static final String EXP = "exp";
    private static final String BUILDINGS = "buildings";
    private static final String LV = "level";
    private static final String STATUS = "statues";
    private static final String NAME = "name";
    private static final String VALUE = "value";
    private static final String MAX_LV = "maxLv";
    private static final String EXP_AMOUNT = "exp-amount";
    private static final String KINGDOM = "KINGDOM";
    private final File file;
  //package-protected
    KingdomSerializator(final File file) {
        this.file = file;
    }
    @Override
    public void save(final Kingdom object) throws IOException {
        final Set<Pair<ImmutableAgeBuilding, Boolean>> buildings = object.getComponents(ImmutableAgeBuilding.class);
        final JsonObject serializer = new JsonObject();
        //create an array of status
        final JsonArray arrayStatus = new JsonArray();
        object.getStatusStatistic().forEach((x, y) -> {
            final JsonObject status = new JsonObject();
            status.addProperty(NAME, x.name());
            status.addProperty(VALUE, y);
            arrayStatus.add(status);
        });
        //create an array of building
        final JsonArray arrayBuilding = new JsonArray();
        buildings.forEach(x -> {
            final JsonObject building = new JsonObject();
            building.addProperty(NAME, x.getX().getName().name());
            building.addProperty(LV, x.getX().getLevel().getIncrementalLevel());
            building.addProperty(EXP_AMOUNT, x.getX().getLevel().getExperienceAmount());
            arrayBuilding.add(building);
        });
        //create the object to serialize
        serializer.addProperty("age", object.getAge().getIncrementalLevel());
        serializer.addProperty("exp", object.getExperienceAmount());
        serializer.add(STATUS, arrayStatus);
        serializer.add(BUILDINGS, arrayBuilding);
        final String out = new Gson().toJson(serializer);
        final PrintStream print = new PrintStream(new FileOutputStream(this.file));
        print.print(out);
        print.close();
    }

    @Override
    public Kingdom load() throws FileNotFoundException, IOException, ClassNotFoundException {
        final String in = Files.readAllLines(Paths.get(this.file.toURI())).get(0);
        final JsonObject inObj = new JsonStreamParser(in).next().getAsJsonObject();
        final JsonArray buildingsJson = inObj.getAsJsonArray(BUILDINGS);
        //Create the building used to add on kingdom
        final Set<BuildingComposite> buildings = new HashSet<>();
        buildingsJson.forEach(x -> {
            final JsonObject building = x.getAsJsonObject();
            final BuildingType name = BuildingType.valueOf(building.get(NAME).getAsString());
            final int expAmount = building.get(EXP_AMOUNT).getAsInt();
            final int currentLv = building.get(LV).getAsInt();
            final Level.Building level = Level.Building.restoreBuildingLevel(currentLv, Level.Building.INITIAL_MAX_LEVEL, expAmount, Level.Building.LEVEL_ADVANCE);
            buildings.add(BuildingFactory.get().createAdvanceBuilding(name, level));
        });
        //create the set of status used to add on kingdom
        final JsonArray statusesJson = inObj.getAsJsonArray(STATUS);
        final Set<Status> statuses = new HashSet<>();
        statusesJson.forEach(x -> {
            final JsonObject status = x.getAsJsonObject();
            final StatusName name = StatusName.valueOf(status.get(NAME).getAsString());
            final int value = status.get(VALUE).getAsInt();
            statuses.add(Status.createStatusWithValue(name, value));
        });
        //build the kingdom
        final KingdomBuilder builder = KingdomBuilder.createBuilder();
        buildings.forEach(x -> builder.addComponent(x));
        statuses.forEach(x -> builder.addStatus(x));
        builder.setAge(Level.Age.restoreAgeLevel(inObj.get(AGE).getAsInt()));
        builder.setExperience(inObj.get(EXP).getAsInt());
        builder.addComponent(ImageComponent.createComponent(KINGDOM));
        return builder.build();
    }
}

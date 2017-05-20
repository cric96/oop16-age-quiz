package home.model.kingdom;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import home.model.building.BuildingOfKingdom;
import home.model.building.BuildingFactory;
import home.model.building.BuildingType;
import home.model.building.Building;
import home.model.image.ImageComponent;
import home.model.level.Level;
import home.model.status.Status;
import home.model.status.StatusName;
import home.utility.Pair;

final class KingdomAdapter extends TypeAdapter<Kingdom> {

    @Override
    public Kingdom read(final JsonReader reader) throws IOException {
        final KingdomBuilder builder = KingdomBuilder.createBuilder();
        reader.beginArray();
        reader.beginObject();
        //BUILDING
        reader.nextName();
        int buildingCount = reader.nextInt();
        reader.endObject();
        final Set<BuildingOfKingdom> buildings = new HashSet<>();
        reader.beginArray();
        while (buildingCount > 0) {
            reader.beginObject();
            reader.nextName();
            final BuildingType building = BuildingType.valueOf(reader.nextString());
            reader.nextName();
            final int lv = reader.nextInt();
            reader.nextName();
            final int exp = reader.nextInt();
            final Level.Building level = Level.Building.restoreBuildingLevel(lv, Level.Building.INITIAL_MAX_LEVEL, exp, Level.Building.LEVEL_ADVANCE);
            buildings.add(BuildingFactory.get().createAdvanceBuilding(building, level));
            reader.endObject();
            buildingCount--;
        }
        reader.endArray();
        //STATUS
        reader.beginObject();
        reader.nextName();
        int statusCount = reader.nextInt();
        reader.endObject();
        reader.beginArray();
        final Set<Status> statuses = new HashSet<>();
        while (statusCount > 0) {
            reader.beginObject();
            reader.nextName();
            final StatusName status = StatusName.valueOf(reader.nextString());
            reader.nextName();
            final int amount = reader.nextInt();
            statuses.add(Status.createStatusWithValue(status, amount));
            reader.endObject();
            statusCount--;
        }
        reader.endArray();
        //AGE
        reader.beginObject();
        reader.nextName();
        final int age = reader.nextInt();
        reader.endObject();
        //EXP
        reader.beginObject();
        reader.nextName();
        final int exp = reader.nextInt();
        reader.endObject();
        reader.endArray();
        builder.setAge(Level.Age.restoreAgeLevel(age));
        builder.setExperience(exp);
        builder.addComponent(ImageComponent.createComponent("KINGDOM"));
        buildings.forEach(x -> builder.addComponent(x));
        statuses.forEach(x -> builder.addStatus(x));
        builder.addStrategy(AgeUpStrategy.createSimple());
        return builder.build();
    }

    @Override
    public void write(final JsonWriter writer, final Kingdom king) throws IOException {
        final Set<Pair<Building, Boolean>> buildings = king.getComponents(Building.class);
        //the node of the json file
        writer.beginArray();
        //the number of building
        writer.beginObject();
        writer.name("BUILDING-COUNT").value(buildings.size());
        writer.endObject();
        //save all building in the array
        writer.beginArray();
        for (final Pair<Building, Boolean> building : buildings) {
            writer.beginObject();
            writer.name("NAME").value(building.getX().getName().name());
            writer.name("LV").value(building.getX().getLevel().getIncrementalLevel());
            writer.name("EXP_AMOUNT").value(building.getX().getLevel().getExperienceAmount());
            writer.endObject();
        }
        writer.endArray();
        writer.beginObject();
        //the count of status on this kingdom
        writer.name("STATUS-COUNT").value(king.getStatusStatistic().size());
        writer.endObject();
        //save all the status 
        writer.beginArray();
        for (final Map.Entry<StatusName, Integer> status : king.getStatusStatistic().entrySet()) {
            writer.beginObject();
            writer.name("STATUS").value(status.getKey().name());
            writer.name("VALUE").value(status.getValue());
            writer.endObject();
        }
        writer.endArray();
        //another information about age and so on
        writer.beginObject();
        writer.name("AGE").value(king.getAge().getIncrementalLevel());
        writer.endObject();
        writer.beginObject();
        writer.name("EXP").value(king.getExperienceAmount());
        writer.endObject();
        writer.endArray();
    }
}

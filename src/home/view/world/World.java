package home.view.world;

import java.util.Map;
import java.util.Set;

import home.model.building.BuildingType;
import home.model.image.Image;
import home.utility.Pair;

public interface World {
    void updateEra(Map<BuildingType,Pair<Image,Boolean>> building,Image kingdom);
    void changeEra(String era);
    void changeExp(int exp);
    void changeStatus(Set<Pair<String,Integer>> statusScose);
    void showBuildingDialog(BuildingType building/*, Dialog dialog*/);
    void showKingdomDialog(/*Dialog dialog*/);
}

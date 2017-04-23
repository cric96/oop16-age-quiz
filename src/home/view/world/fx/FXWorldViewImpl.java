package home.view.world.fx;

import java.util.Map;
import java.util.Set;

import home.controller.WorldController;
import home.model.building.BuildingType;
import home.model.image.Image;
import home.utility.Pair;
import home.view.fx.AbstractFXView;
import home.view.world.WorldWiew;

/**
 * Implementation of World view in javafx.
 */
public class FXWorldViewImpl extends AbstractFXView implements WorldWiew {

    @Override
    public void attachOn(final WorldController controller) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateEra(final Map<BuildingType, Pair<Image, Boolean>> buildings, final Image kingdom) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeEra(final String era) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeExp(final int exp) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeStatus(final Set<Pair<String, Integer>> statusScose) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showBuildingDialog(final BuildingType building) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showKingdomDialog() {
        // TODO Auto-generated method stub

    }

}

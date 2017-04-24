package home.view.world.fx;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import home.controller.WorldController;
import home.controller.dialog.Dialog;
import home.model.building.BuildingType;
import home.model.image.Image;
import home.utility.Pair;
import home.view.fx.AbstractFXView;

import home.view.world.WorldView;
import javafx.scene.Scene;


/**
 * Implementation of World view in javafx.
 */
public class FXWorldViewImpl extends AbstractFXView implements WorldView {
    private Optional<WorldController> controller;

    /**
     * create new FXWorldView.
     */
    public FXWorldViewImpl() {
        super();
        this.controller = Optional.empty();
    }

    @Override
    public void attachOn(final WorldController controller) {
        this.controller = Optional.of(controller);
        this.setScene(new Scene(new ParentWorld(controller)));
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
    public void changeStatus(Map<String, Integer> statusScose) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showBuildingDialog(BuildingType building, Dialog dialog) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void showKingdomDialog(Dialog dialog) {
        // TODO Auto-generated method stub
        
    }

}

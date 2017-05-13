package home.view.world.fx;

import java.util.Map;
import java.util.Optional;
import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;
import home.view.MessageType;
import home.view.fx.AbstractFXView;
import home.view.world.WorldView;
import javafx.scene.control.ButtonType;



/**
 * Implementation of World view in javafx.
 */
public class FXWorldViewImpl extends AbstractFXView<ParentWorld> implements WorldView {
    private FXMLControllerWorld fxmlController;

    @Override
    public void attachOn(final WorldObserver controller) {
        this.setParent(new ParentWorld(controller));
        fxmlController = this.getParent().getFxmlControllerWorld();
    }

    @Override
    public void show() {
        super.show();
        this.getParent().addFocus();
    }

    @Override
    public void updateEra(final Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, final ImageInfo kingdom) {
        this.fxmlController.setBuildingPane(buildings, kingdom);
    }

    @Override
    public void changeEra(final String era) {
        this.fxmlController.setEraLabel(era);
    }

    @Override
    public void changeExp(final int exp) {
        this.fxmlController.setExperienceLabel(exp);
    }

    @Override
    public void changeStatus(final Map<String, Integer> statusScose) {
        this.fxmlController.setStatsPane(statusScose);
    }

    @Override
    public void showBuildingDialog(final BuildingType building, final Dialog dialog) {
        this.fxmlController.showBuildingDialog(building, dialog);
    }

    @Override
    public void showKingdomDialog(final Dialog dialog) {
       this.fxmlController.showBuildingDialog(dialog);
    }

    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) {
        // TODO Auto-generated method stub

    }
}

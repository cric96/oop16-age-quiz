package home.view.world.fx;

import java.util.Map;
import java.util.Optional;
import home.controller.WorldController;
import home.controller.dialog.Dialog;
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
public class FXWorldViewImpl extends AbstractFXView implements WorldView {
    private Optional<WorldController> controller;
    private FXMLControllerWorld fxmlController;

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
        final ParentWorld parent = new ParentWorld(controller);
        this.setParent(parent);
        fxmlController = parent.getFxmlControllerWorld();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
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
       System.out.println("building Over");
    }

    @Override
    public void showKingdomDialog(final Dialog dialog) {
        System.out.println("kingdom Over");
    }

    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) {
        // TODO Auto-generated method stub

    }
}

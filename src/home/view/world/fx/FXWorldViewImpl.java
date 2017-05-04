package home.view.world.fx;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.swing.plaf.ProgressBarUI;

import home.controller.WorldController;
import home.controller.dialog.Dialog;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;
import home.view.MessageType;
import home.view.fx.AbstractFXView;
import home.view.world.WorldView;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

/**
 * Implementation of World view in javafx.
 */
public class FXWorldViewImpl extends AbstractFXView implements WorldView {
    private Optional<WorldController> controller;
    private Stage principalStage;
    private FXMLControllerWorld fxmlController;
    private int i = 0;

    /**
     * create new FXWorldView.
     * @param principalStage 
     */
    public FXWorldViewImpl(final Stage principalStage) {
        super();
        this.principalStage = principalStage;
        this.controller = Optional.empty();
    }

    @Override
    public void attachOn(final WorldController controller) {
        this.controller = Optional.of(controller);
        final ParentWorld parent = new ParentWorld(controller, this.principalStage);
        this.setParent(parent);
        fxmlController = parent.getFxmlControllerWorld();
    }

    @Override
    public void show() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateEra(final Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, final ImageInfo kingdom) {
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
    public void changeStatus(final Map<String, Integer> statusScose) {
        
        this.fxmlController.setStatsPane(statusScose);
    }

    @Override
    public void showBuildingDialog(final BuildingType building, final Dialog dialog) {
        // TODO Auto-generated method stub

    }

    @Override
    public void showKingdomDialog(final Dialog dialog) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void onClickMessage(final MessageType type, final Optional<ButtonType> button) {
        // TODO Auto-generated method stub

    }
}

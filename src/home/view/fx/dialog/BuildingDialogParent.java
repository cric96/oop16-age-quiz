package home.view.fx.dialog;

import java.util.Arrays;
import java.util.List;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import home.utility.view.FontManager;
import home.view.fx.CSSManager;
import home.view.fx.StyleSheet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.stage.Popup;

/**
 *sub class to create a general building pop up parent.
 */
class BuildingDialogParent extends DialogParentWorldImpl {

    /**
     * 
     * @param controller 
     *          observer of this view
     * @param building 
     *          building representer by this parent
     * @param dialog 
     *          information showed in this dialog
     * @param pop 
     *          container of this parent
     */
    BuildingDialogParent(final WorldObserver controller, final BuildingType building, final Dialog dialog,
            final Popup pop) {
        super(dialog, pop);
        final Button start = new Button(this.getBundle().getString("STRQUIZ"));
        start.setOnMouseClicked(e -> {
            pop.hide();
            controller.createQuiz(building);
        });
        start.setAlignment(Pos.CENTER_RIGHT);
        final Button upgrade = new Button(this.getBundle().getString("UPGRADE"));
        upgrade.setOnMouseClicked(e -> {
            pop.hide();
            controller.nextLevel(building);
        });


        final List<Button> list = Arrays.asList(upgrade, start);
        list.forEach(b -> {
            CSSManager.addStyleSheet(StyleSheet.GAME_BUTTONS, b);
            CSSManager.addStyleClass("button-game", b);
            b.setFont(FontManager.getGeneralFont());
        });
        upgrade.setAlignment(Pos.CENTER_LEFT);
        upgrade.setDisable(!dialog.levelUpEnabled());
        this.getController().setButtonBox(list);
    }

}

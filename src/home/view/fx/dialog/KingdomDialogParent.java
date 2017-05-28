package home.view.fx.dialog;

import java.util.Arrays;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.utility.view.FontManager;
import home.view.fx.CSSManager;
import home.view.fx.StyleSheet;
import javafx.scene.control.Button;
import javafx.stage.Popup;
/**
 * sub class to create a kingdom parent dialog. 
 */
class KingdomDialogParent extends DialogParentWorldImpl {
    /**
     * 
     * @param controller 
     *          observer of this view
     * @param dialog 
     *          information showed in this dialog
     * @param pop 
     *          container of this parent
     */
    KingdomDialogParent(final WorldObserver controller, final Dialog dialog, final Popup pop) {
        super(dialog, pop);
        final Button upgrade = new Button(this.getBundle().getString("UPGRADE"));
        CSSManager.addStyleSheet(StyleSheet.GAME_BUTTONS, upgrade);
        CSSManager.addStyleClass("button-game", upgrade);
        upgrade.setFont(FontManager.getGeneralFont());
        upgrade.setOnMouseClicked(e -> {
            pop.hide();
            controller.nextEra();
        });
        upgrade.setDisable(!dialog.levelUpEnabled());
        this.getController().setButtonBox(Arrays.asList(upgrade));
    }

}

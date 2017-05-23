package home.view.fx.dialogs;

import java.util.Arrays;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
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
        upgrade.setOnMouseClicked(e -> {
            pop.hide();
            controller.nextEra();
        });
        upgrade.setDisable(!dialog.levelUpEnabled());
        this.getController().setButtonBox(Arrays.asList(upgrade));
    }

}

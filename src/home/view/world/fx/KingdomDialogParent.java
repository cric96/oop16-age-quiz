package home.view.world.fx;

import java.util.Arrays;
import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import javafx.scene.control.Button;
import javafx.stage.Popup;
/**
 * sub class to create a kingdom parent dialog. 
 */
public class KingdomDialogParent extends DialogParent {
    /**
     * 
     * @param controller 
     *          observer of this view
     * @param building 
     *          buildig representer by this parent
     * @param dialog 
     *          information showed in this dialog
     * @param pop 
     *          container of this parent
     */
    public KingdomDialogParent(final WorldObserver controller, final Dialog dialog, final Popup pop) {
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

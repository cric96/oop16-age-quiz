package home.view.world.fx;

import java.util.Arrays;
import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import javafx.scene.control.Button;
import javafx.stage.Popup;
/**
 * 
 *
 */
public class KingdomDialogParent extends DialogParent {
    /**
     * 
     * @param controller 
     * @param dialog 
     * @param pop 
     */
    public KingdomDialogParent(final WorldObserver controller, final Dialog dialog, final Popup pop) {
        super(dialog, pop);
        final Button upgrade = new Button(this.getBundle().getString("UPGRADE"));
        upgrade.setOnMouseClicked(e -> {
            pop.hide();
            controller.nextEra();
        });
        upgrade.setDisable(dialog.isLevelBlocked());
        this.getController().setButtonBox(Arrays.asList(upgrade));
    }

}

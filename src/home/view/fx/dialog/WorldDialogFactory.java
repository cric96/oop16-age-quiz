package home.view.fx.dialog;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import javafx.stage.Popup;

/**
 * factory for building dialogs.
 */
public final class WorldDialogFactory {
    private WorldDialogFactory() { }

    /**
     * create a new parent dialog for kingdom.
     * @param controller controller of world
     * @param dialog information of parent
     * @param pop popuP owner of this parent
     * @return the parent
     */
    public static DialogParentWorld createKingdomDialogParent(final WorldObserver controller, final Dialog dialog, final Popup pop) {
        return new KingdomDialogParent(controller, dialog, pop);
    }

    /**
     * create a new parent dialog for a building.
     * @param controller 
     * @param building 
     * @param dialog 
     * @param pop 
     * @return the parent
     */
    public static DialogParentWorld createBuildingParent(final WorldObserver controller, final BuildingType building, final Dialog dialog,
            final Popup pop) {
        return new BuildingDialogParent(controller, building, dialog, pop);
    }
}

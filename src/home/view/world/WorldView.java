package home.view.world;

import java.util.Map;
import java.util.Set;

import home.controller.WorldController;
import home.controller.dialog.Dialog;
import home.model.building.BuildingType;
import home.model.image.Image;
import home.utility.Pair;
import home.view.View;

/**
 * Specification of View used to implement a generic WordView.
 */
public interface WorldView extends View<WorldController> {

    /**
     * update the actual Era.
     * 
     * @param buildings Images. 
     * @param kingdom Image.
     */
    void updateEra(Map<BuildingType, Pair<Image, Boolean>> buildings, Image kingdom);

    /**
     * change the grafic Era of the word.
     * 
     * @param era era.
     */
    void changeEra(String era);

    /**
     * change the expirience.
     * 
     * @param exp expirience
     */
    void changeExp(int exp);

    /**
     * change the value of rappresentation of status.
     * 
     * @param statusScose set of name and value of status.
     */
    void changeStatus(Map<String, Integer> statusScose);

    /**
     * show dialog from building to start a quiz.
     * 
     * @param building type.
     * @param dialog
     */
    void showBuildingDialog(BuildingType building, Dialog dialog);

    /**
     * show dialog from the castle.
     * @param dialog
     *  the dialog of the castle
     */
    void showKingdomDialog(Dialog dialog );
}

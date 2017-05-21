package home.view.world;

import java.util.Map;
import java.util.Optional;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;
import home.view.View;

/**
 * Specification of View used to implement a generic WordView.
 */
public interface WorldView extends View<WorldObserver> {

    /**
     * update the actual Era.
     * 
     * @param buildings Images. 
     * @param kingdom Image.
     */
    void updateEra(Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, ImageInfo kingdom);

    /**
     * change the graphics Era of the word.
     * 
     * @param era era.
     */
    void changeEra(String era);

    /**
     * change the experience.
     * 
     * @param exp experience
     */
    void changeExp(int exp);

    /**
     * change the value of representation of status.
     * 
     * @param statusScose set of name and value of status.
     */
    void changeStatus(Map<String, Integer> statusScose);

    /**
     * show dialog from building to start a quiz.
     * 
     * @param building type.
     * @param dialog
     *  the dialog of building selected empty if the building is not present
     */
    void showBuildingDialog(BuildingType building, Optional<Dialog> dialog);

    /**
     * show dialog from the castle.
     * @param dialog
     *  the dialog of the castle
     */
    void showKingdomDialog(Dialog dialog);
}

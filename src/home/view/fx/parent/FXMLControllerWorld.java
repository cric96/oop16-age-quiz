package home.view.fx.parent;

import java.util.Map;

import home.controller.dialog.Dialog;
import home.controller.observer.WorldObserver;
import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;

/**
 * model of a fxmlController for world view.
 *
 */
public interface FXMLControllerWorld extends FXMLController {

    /**
     * used to insert in the word the castle and other buildings.
     * @param buildings 
     *              map used to create button for any building inside the world.
     * @param kingdom 
     *              the path of kingdom image.
     */
    void setBuildingPane(Map<BuildingType, Pair<ImageInfo, Boolean>> buildings, ImageInfo kingdom);

    /**
     * @param experienceLabel
     *            the experienceLabel to set
     */
    void setExperienceLabel(int experienceLabel);

    /**
     * @param eraLabel
     *            the name of actual era.
     */
    void setEraLabel(String eraLabel);

    /**
     * used to create the tooltip of statsImage.
     * @param statusScose
     *            Map of status <name of status, value>
     */
    void setStatsPane(Map<String, Integer> statusScose);

    /**
     *  used to set the controller of world view and call its methods. 
     * @param controller 
     *          controller of MVC.
     */
    void setController(WorldObserver controller);

    /**
     * show a popUp from a specific building.
     * @param building 
     *              type of building owner of popUp
     * @param dialog 
     *              information to display in the popUp
     */
    void showBuildingDialog(BuildingType building, Dialog dialog);

    /**
     * show a popUp from kingdom.
     * @param dialog 
     *          information to display in the popUp
     */
    void showBuildingDialog(Dialog dialog);

    /**
     * method used to refresh label if the player change language game.
     */
    void refresh();

}
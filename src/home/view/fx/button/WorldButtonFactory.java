package home.view.fx.button;

import java.util.Map.Entry;

import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;

/**
 * simple factory for buttons inside world.
 *
 */
public final class WorldButtonFactory {
    private WorldButtonFactory() { }

    /**
     * create a new button to represent the kingdom.
     * @param graphicPath
     *              path of image.
     * @return a kingdom button
     */
    public static BuildingButton createKingdomButton(final String graphicPath) {
        return new BuildingButtonKingdom(graphicPath);
    }

    /**
     * create a new button to represent a specific building.
     * @param building
     *             to represent
     * @return
     *      new instance of button
     */
    public static BuildingButton createBuildingButton(final Entry<BuildingType, Pair<ImageInfo, Boolean>> building) {
        return new BuildingButtonGeneral(building);
    }
}

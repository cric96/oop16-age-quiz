package home.view.fx.buttons;

import java.util.Map.Entry;

import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;

/**
 * sub class to model a butto that represent a general building
 */
class BuildingButtonGeneral extends BuildingButtonImpl {
    /**
     * @param building 
     *          information of building represented by this button
     */
    BuildingButtonGeneral(final Entry<BuildingType, Pair<ImageInfo, Boolean>> building) {
        super(building.getValue().getX().getPath());
        this.setDisable(!building.getValue().getY());
        if (!building.getValue().getY()) {
            this.setOpacity(0.5);
        }
    }
}

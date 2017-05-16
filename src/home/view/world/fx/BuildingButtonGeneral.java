package home.view.world.fx;

import java.util.Map.Entry;

import home.model.building.BuildingType;
import home.model.image.ImageInfo;
import home.utility.Pair;

/**
 * 
 *
 */
class BuildingButtonGeneral extends BuildingButton {
    /**
     * @param building 
     */
    BuildingButtonGeneral(final Entry<BuildingType, Pair<ImageInfo, Boolean>> building) {
        super(building.getValue().getX().getPath());
        this.setDisable(!building.getValue().getY());
        if (!building.getValue().getY()) {
            this.setOpacity(0.5);
        }
    }
}

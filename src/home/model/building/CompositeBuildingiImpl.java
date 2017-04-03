package home.model.building;

import home.model.level.Level;
import home.model.query.Category;

final class CompositeBuildingiImpl extends AbstractBuilding implements BuildingComposite {

    CompositeBuildingiImpl(final String name, final Level.Building level, final Category category) {
        super(name, level, category);
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onAgeChange() {
        /*TODO DO */
    }
}

package home.model.building;

import home.model.level.Level;
import home.model.quiz.Category;

final class CompositeBuildingiImpl extends AbstractBuilding implements BuildingComposite {
    private static final long serialVersionUID = -8506796377291602901L;
    CompositeBuildingiImpl(final String name, final Level.Building level, final Category category) {
        super(name, level, category);
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void onAgeChange() {
        /*TODO DO */
    }
}

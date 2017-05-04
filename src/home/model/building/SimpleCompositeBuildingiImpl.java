package home.model.building;

import home.model.composite.Event;
import home.model.level.AgeEnum;
import home.model.level.Level;
final class SimpleCompositeBuildingiImpl extends AbstractBuilding implements BuildingComposite {
    private static final long serialVersionUID = -8506796377291602901L;
    private static final AgeEnum INITIAL_AGE = AgeEnum.values()[0];
    private boolean enable;
    SimpleCompositeBuildingiImpl(final Level.Building level, final BuildingType type) {
        super(level, type);
        this.enable = type.getAgeEnable() == INITIAL_AGE;
    }
    @Override
    protected void onAgeChange(final Event.Age<?> event) {
        if (this.getBuildingType().getAgeEnable() == event.currentAge()) {
            this.enable = true;
        }
        /*TODO A SIMPLE VERSION REMEMBER THAT YOU CHANGE THIS*/
        this.getBuildingLevel().setMaxiumLevel(this.getLevel().getIncrementalLevel() + 1);
    }
    @Override
    public boolean isEnable() {
        return enable;
    }
}

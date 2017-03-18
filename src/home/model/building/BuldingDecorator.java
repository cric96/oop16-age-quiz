package home.model.building;

import home.model.quiz.Category;

//package-protected
abstract class BuldingDecorator implements Building.Age {
    private Building.Age building;
    public String getName() {
        return building.getName();
    }
    public int getLevel() {
        return building.getLevel();
    }
    public void startQuiz() {
        building.startQuiz();
    }
    public Category getInfluecedCategory() {
        return building.getInfluecedCategory();
    }
    public boolean levelUp(final int experience) {
        return building.levelUp(experience);
    }
    public boolean isUpgradable() {
        return building.isUpgradable();
    }
    protected Building getBuilding() {
        return this.building;
    }
    public boolean nextAge(final int currentAge){
        return this.building.nextAge(currentAge);
    }
}

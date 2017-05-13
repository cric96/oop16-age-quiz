package home.controller.observer;

import home.model.building.BuildingType;

public interface WorldObserver extends Observer{
    /**
     * tell to the controller to go on the next era.
     *  @throws IllegalStateExcption if is called with less experience required
     */
    void nextEra();
    /**
     * tell to the controller to increase a level of a specific building.
     * @param building
     *  the building selected
     * @throws IllegalStateExcption if is called with less experience required
     */
    void nextLevel(BuildingType building);
    /**
     * create a quiz associated with some building.
     * @param building
     *  the building selected
     * @throws IllegalStateExcption if is called with less experience required
     */
    void createQuiz(BuildingType building);
    /**
     * turn on menu.
     */
    void goOnMenu();
    /**
     * tell to the controller that someone want information of specific building.
     * @param building
     *  the building selected
     */
    void pressOnBuilding(BuildingType building);
    /**
     * tell to the controller that someone want information of Kingdom.
     */
    void pressOnKingdom();
}

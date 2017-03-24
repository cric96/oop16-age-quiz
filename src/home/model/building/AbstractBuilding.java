package home.model.building;

import home.model.level.ImmutableLevel;
import home.model.level.Level;
import home.model.quiz.Category;
import home.model.quiz.QuizGame;
import home.model.utility.Utility;
//package-protected
abstract class AbstractBuilding implements Building {
    /*TODO ricorda che devi trovare un modo per salire di livello in base all'era*/
    private final String name;
    private final Level.Building level;
    private final Category category;
    private int currentAge;
    AbstractBuilding(final String name, final Level.Building level, final Category category, final int currentAge) {
        if (Utility.checkNullOb(category, level, name)) {
            throw new IllegalArgumentException();
        }
        if (currentAge < 0) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.level = level;
        this.category = category;
        this.currentAge = currentAge;
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final ImmutableLevel getLevel() {
        return this.level;
    }
    /*TODO aspettare l'interfaccia di creazione*/
    @Override
    public QuizGame startQuiz() {
        return null;
    }

    @Override
    public final Category getInfluecedCategory() {
        return this.category;
    }

    @Override
    public boolean levelUp(final int experience) {
        return this.level.nextLevel(experience);
    }

    @Override
    public void nextAge() {
        this.currentAge++;
        /* TODO VERSIONE NON ACCETTABILE RICORDA CHE DEVI MODIFICARLA */
        this.level.setMaxiumLevel(this.level.getIncrementalLevel() + 1);
        this.advanceOnAge();
    }
    public int getExperienceNecesary() {
        return this.level.getExperienceAmount();
    }
    @Override
    public String toString() {
        return "AbstractBuilding [name=" + name + ", level=" + level + ", category=" + category + ", currentAge="
                + currentAge + "]";
    }
    /*TEMPLATE METHOD what to do when the age change */
    protected abstract void advanceOnAge();
}

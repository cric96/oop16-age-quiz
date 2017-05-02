package home.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Optional;
import java.util.Set;

import home.model.building.BuildingFactory;
import home.model.building.BuildingType;
import home.model.building.ImmutableAgeBuilding;
import home.model.composite.Component;
import home.model.image.ImageComponent;
import home.model.level.Level;
import home.model.quiz.QuizGame;
import home.model.quiz.QuizGameFactory;
import home.model.status.Status;
import home.utility.Pair;

final class GameImpl implements Game {
    private static final String KINGDOM_NAME = "KINGDOM";
    private static final Game SINGLETON = new GameImpl();
    private Optional<Kingdom> currentKingdom;
    private Optional<QuizGame> currentQuiz;
    //package protected
    public static Game get() {
        return GameImpl.SINGLETON;
    }
    private GameImpl() {
        this.currentKingdom = Optional.empty();
        this.currentQuiz = Optional.empty();
    }
    @Override
    public void save(final File save) throws FileNotFoundException, IOException {
        final ObjectOutput out = new ObjectOutputStream(new FileOutputStream(save));
        out.writeObject(this.currentKingdom.orElseThrow(() -> new IllegalStateException()));
        out.close();
    }

    @Override
    public void load(final File load) throws FileNotFoundException, IOException, ClassNotFoundException {
        final ObjectInput in = new ObjectInputStream(new FileInputStream(load));
        this.currentKingdom = Optional.of((Kingdom) in.readObject());
        in.close();
    }

    @Override
    public Kingdom getCurrentKingdom() {
        return currentKingdom.orElseThrow(() -> new IllegalStateException());
    }

    @Override
    public void newGame() {
        final Kingdom current = new KingdomImpl(Status.createStatuses(), Level.Age.createAgeLevel(), AgeUpStrategy.createSimple());
        BuildingFactory.get().createAllBuilding().forEach(x -> {
            Component.compositeAttach(current, x);
        });
        Component.compositeAttach(current, ImageComponent.createComponent(KINGDOM_NAME));
        this.currentKingdom = Optional.of(current);
    }
    @Override
    public Optional<QuizGame> getCurrentQuiz() {
        return this.currentQuiz;
    }
    @Override
    public void createQuiz(final BuildingType building) {
        final Kingdom current = this.getCurrentKingdom();
        final Set<Pair<ImmutableAgeBuilding.Container, Boolean>> buildings = current.getComponents(ImmutableAgeBuilding.Container.class);
        final ImmutableAgeBuilding selectedBuilding = buildings.stream().filter(x -> x.getX().getName() == building)
                                                                        .filter(x -> x.getY())
                                                                        .findFirst().orElseThrow(() -> new IllegalStateException())
                                                                        .getX();
        this.currentQuiz = Optional.of(QuizGameFactory.createQuizGameAdvanced(selectedBuilding.getInfluecedCategory(), selectedBuilding.getLevel()));
    }
    @Override
    public void endCurrentQuiz() {
        final QuizGame quiz = this.currentQuiz.orElseThrow(() -> new IllegalStateException());
        if (!quiz.isFinished()) {
            throw new IllegalStateException();
        }
        this.getCurrentKingdom().addExperience(quiz.getXP());
        quiz.getStatusScore().forEach((x, y) -> this.getCurrentKingdom().changeStatus(x, y));
    }

}

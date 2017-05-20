package home.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import home.model.building.Building;
import home.model.building.BuildingFactory;
import home.model.building.BuildingOfKingdom;
import home.model.building.BuildingType;
import home.model.composite.Component;
import home.model.composite.Composite;
import home.model.image.ImageComponent;
import home.model.kingdom.AgeUpStrategy;
import home.model.kingdom.Kingdom;
import home.model.kingdom.KingdomBuilder;
import home.model.level.Level;
import home.model.quiz.QuizGame;
import home.model.quiz.QuizGameFactory;
import home.model.status.Status;
import home.utility.Pair;
//package-protected
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
        //Serializer.createSimple(save).save(this.getCurrentKingdom());
        Kingdom.createSerializer(save).save(this.getCurrentKingdom());
    }

    @Override
    public void load(final File load) throws FileNotFoundException, IOException, ClassNotFoundException {
        //this.currentKingdom = Optional.of(Serializer.<Kingdom>createSimple(load).load());
        this.currentKingdom = Optional.of(Kingdom.createSerializer(load).load());
    }

    @Override
    public Kingdom getCurrentKingdom() {
        return currentKingdom.orElseThrow(() -> new IllegalStateException());
    }

    @Override
    public void newGame() {
        final Set<BuildingOfKingdom> buildings = BuildingFactory.get().createAllBuilding();
        final Set<Status> statuses = Status.createStatuses();
        final Component<Composite> image = ImageComponent.createComponent(KINGDOM_NAME);
        final KingdomBuilder builder = KingdomBuilder.createBuilder();
        statuses.forEach(x -> builder.addStatus(x));
        buildings.forEach(x -> builder.addComponent(x));
        builder.addComponent(image);
        builder.setExperience(0);
        builder.setAge(Level.Age.createAgeLevel());
        builder.addStrategy(AgeUpStrategy.createSimple());
        this.currentKingdom = Optional.of(builder.build());
    }
    @Override
    public Optional<QuizGame> getCurrentQuiz() {
        return this.currentQuiz;
    }
    @Override
    public void createQuiz(final BuildingType building) {
        final Kingdom current = this.getCurrentKingdom();
        final Set<Pair<Building.Container, Boolean>> buildings = current.getComponents(Building.Container.class);
        final Building selectedBuilding = buildings.stream().filter(x -> x.getX().getName() == building)
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

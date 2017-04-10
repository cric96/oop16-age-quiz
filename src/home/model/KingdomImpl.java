package home.model;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import home.model.composite.AbstractComposite;
import home.model.composite.Event;
import home.model.level.AgeEnum;
import home.model.level.ImmutableLevel;
import home.model.level.Level;
import home.model.status.Status;
import home.model.status.StatusName;
import home.utility.Utility;
 
//package protected 
final class KingdomImpl extends AbstractComposite implements Kingdom {
    private static final long serialVersionUID = 1L;
    private final Set<Status> statuses; //the status of this kingdom
    private final Level.Age age;
    private int experience;
    //package protected
    KingdomImpl(final Set<Status> statuses, final Level.Age age) {
        if (Utility.checkNullOb(age, statuses)) {
            throw new IllegalArgumentException();
        }
        this.statuses = statuses;
        this.age = age;
    }

    @Override
    public String getAgeName() {
        return this.age.getLevelName();
    }

    @Override
    public ImmutableLevel getAge() {
        return this.age;
    }

    @Override
    public int getExperienceAmount() {
        return this.experience;
    }

    @Override
    public void addExperience(final int amount) {
        if (this.isExperienceInvalid(amount)) {
            throw new IllegalArgumentException();
        }
        this.experience += amount;
    }

    @Override
    public void decExperiene(final int amount) {
        if (isExperienceInvalid(amount) || this.experience - amount < 0) {
            throw new IllegalArgumentException();
        }
        this.experience -= amount;
    }
    @Override
    public Map<StatusName, Integer> getStatusStatistic() {
        return this.statuses.stream()
                            .collect(Collectors.toMap(x -> x.getName(), x -> x.getValue()));
    }

    @Override
    public boolean changeStatus(final StatusName name, final int amount) {
        final Optional<Status> status = this.statuses.stream().filter(x -> x.getName() == name)
                                                              .findFirst();
        if (status.isPresent()) {
             final Status stat = status.get();
             if (amount < 0) {
                 stat.decValue(-amount);
             } else {
                 stat.addValue(amount);
             }
             return true;
         }
         return false;
    }

    @Override
    public boolean nextAge() {
        final int currentAmount = this.age.getExperienceAmount();
        if (this.age.nextLevel(this.experience)) {
            this.decExperiene(currentAmount);
            this.getComponents().forEach(x -> x.update(Event.Age.createEvent(this, AgeEnum.valueOf(this.age.getLevelName()))));
            return true;
        }
        return false;
    }
    private boolean isExperienceInvalid(final int value) {
        return value < 0;
    }
    @Override
    public String toString() {
        return "KingdomImpl [statuses=" + statuses + ", age=" + age + ", experience=" + experience + "]";
    }
}

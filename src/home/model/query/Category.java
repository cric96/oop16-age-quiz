package home.model.query;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import home.model.status.StatusName;

/**
 * Models what Category means in a videogame and what status it influences.
 */
public enum Category {
    /**
     * Every category influences some status.
     */
    SCIENCE(StatusName.KNOWLEDGE), 
    /**
     * 
     */
    LIBERAL_ARTS(StatusName.KNOWLEDGE, StatusName.HAPPINESS),
    /**
     * 
     */
    MANUFACTURING(StatusName.TECHNICAL),
    /**
     * 
     */
    MEDICINE(StatusName.KNOWLEDGE, StatusName.HEALTH),
    /**
     * 
     */
    SPORT(StatusName.HAPPINESS, StatusName.HEALTH);
    private final Set<StatusName> influencingStatus;
    Category(final StatusName ...statusName) {
        this.influencingStatus = new HashSet<>(Arrays.asList(statusName));
    }
    /**
     * @return the Set of StatusName that each Category influences
     */
    public Set<StatusName> getStatusNames() {
        return Collections.unmodifiableSet(this.influencingStatus);
    }

}

package home.controller.profile;

import java.io.File;
import java.io.Serializable;
import java.util.Optional;

/** 
  Define a profile of game.
*/
public interface Profile extends Serializable {

    /**
     * get the name associated with this profile.
     * @return
     *  optional.Empty if the profile isn't enable Optional.of(profileName) otherwise
     */
    Optional<String> getName();
    /**
     * set the name of a profile.
     * @param name
     *  the name associated with this profile
     */
    void setName(String name);
    /**
     * 
     * @return
     *  true if the profile is associated with some save game
     */
    boolean isEnabled();
    /**
     * change the state of a profile.
     * @param enabled
     *  true if you want to enable a game false otherwise.
     */
    void setEnabled(boolean enabled);
    /**
     * 
     * @return
     *  the file associated with this profile
     */
    File getSaveGame();
    /**
     * @return the date of the file
     */
    String getSaveDate();
}

package home.controller.profile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
/**
 * a box that contains all type of profiles.
 * a user can select one and it can play with it
 */
public interface ProfileBox {
    /**
     * @return
     *  a single instance of the profile box
     */
    static ProfileBox getProfileBox() {
        return ProfileBoxImpl.get();
    }
    /**
     * save the box that contains the profiles.
     * @throws IOException
     *  if the file name is illegal
     * @throws IllegalStateException
     *  if the file is not set
     */
    void save() throws IOException;
    /**
     * load the box contains the profiles.
     * @throws IOException
     *  if the file name is illegal
     * @throws IllegalStateException
     *  if the file is not set
     * @throws ClassNotFoundException
     *  if something goes wrong in the cast
     */
    void load() throws IOException, ClassNotFoundException;
    /**
     * 
     * @return
     *  all profile associated with this box
     */
    List<Profile> getProfile();
    /**
     * 
     * @param file
     *  the file where the box load and store the profile
     */
    void setFile(File file);
    /**
     * select a profile to start the game.
     * @param profile
     *  the profile selected
     * @throws IllegalArgumentException 
     *  if the profile selected is not present in the box
     */
    void select(Profile profile);
    /**
     * @return
     *  Optional.of profile if is selected someone, empty otherwise
     */
    Optional<Profile> getSelected();
}
package home.controller.profile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
/**
 * a box that contains all type of profile.
 * a user can select one and it can played with it
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
     * save the box contains the profile.
     * @throws IOException
     *  if the file name is illegal
     * @throws IllegalStateException
     *  if the file is not set
     */
    void save() throws IOException;
    /**
     * load the box contains the profile.
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
     * Select a profile to start the game.
     * @param profile
     *  the profile selected
     * @throws IllegalArgumentException 
     *  if the profile selected is not present in the box
     */
    void select(Profile profile);
    /**
     * @return
     *  Optional.of profile if is select someone empty otherwise
     */
    Optional<Profile> getSelected();
}
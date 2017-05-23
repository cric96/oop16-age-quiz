package home.view.fx.button;

import home.controller.profile.Profile;
import home.utility.ResourceManager;
import home.view.fx.Images;

/**
 * Represent the button of a profile in NewGameDialog.
 */
// package-protected
class ButtonProfileNewGame extends AbstractButtonProfile {

    /**
     * @param profile
     *            represented by button.
     */
    ButtonProfileNewGame(final Profile profile) {
        super(profile);
    }

    @Override
    protected String getLockedPath() {
        return ResourceManager.load(Images.PROFILE_IMAGE_EMPTY.getPath()).toExternalForm();

    }

}

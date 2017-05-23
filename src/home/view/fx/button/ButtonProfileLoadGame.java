package home.view.fx.button;

import home.controller.profile.Profile;
import home.utility.ResourceManager;
import home.view.fx.Images;

/**
 * Represent the button of a profile in LoadGameDialog.
 */
//package-protected
class ButtonProfileLoadGame extends AbstractButtonProfile {

    /**
     * @param profile represented by button.
     */
    ButtonProfileLoadGame(final Profile profile) {
        super(profile);
        this.setDisable(!profile.isEnabled());
    }

    @Override
    protected String getLockedPath() {
        return ResourceManager.load(Images.PROFILE_IMAGE_LOCK.getPath()).toExternalForm();

    }

}

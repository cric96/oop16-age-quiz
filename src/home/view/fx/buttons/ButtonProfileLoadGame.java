package home.view.fx.buttons;

import home.controller.profile.Profile;
import home.utility.ResourceManager;
import home.view.fx.Images;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represent the button of a profile in LoadGameDialog.
 */
//package-protected
class ButtonProfileLoadGame extends ProfileButton {

    /**
     * @param profile represented by button.
     */
    ButtonProfileLoadGame(final Profile profile) {
        super(profile);
        String fileName;
        if (profile.isEnabled()) {
            fileName = ResourceManager.load(Images.PROFILE_IMAGE_UNLOCK.getPath()).toExternalForm();
        } else {
            fileName = ResourceManager.load(Images.PROFILE_IMAGE_LOCK.getPath()).toExternalForm();
        }
        this.setDisable(!profile.isEnabled());
        super.setGraphic(new ImageView(new Image(fileName)));
    }

}

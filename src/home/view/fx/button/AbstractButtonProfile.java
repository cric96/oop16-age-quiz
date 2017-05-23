package home.view.fx.button;

import home.controller.profile.Profile;
import home.utility.ResourceManager;
import home.utility.view.FontManager;
import home.view.fx.Images;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** 
 *      Create a custom button used in Load/New Game dialog.
 */
//package-protected
abstract class AbstractButtonProfile extends Button {
    private static final int BOX_DIMENSION = 30;

    /**
     * @param profile the profile represented by the button.
     */
    AbstractButtonProfile(final Profile profile) {
        super(profile.getName().orElse("Empty slot"));
        String fileName;
        if (profile.isEnabled()) {
            fileName = ResourceManager.load(Images.PROFILE_IMAGE_UNLOCK.getPath()).toExternalForm();
        } else {
            fileName = this.getLockedPath();
        }
        this.setGraphic(new ImageView(new Image(fileName)));
        this.setFont(FontManager.getGeneralFont());
    }

    /**
     * used by class who extends this to set the graphic image.
     * @param img the image icon of button.
     */
    protected void setGraphic(final ImageView img) {
        img.setFitHeight(BOX_DIMENSION);
        img.setFitWidth(BOX_DIMENSION);
        super.setGraphic(img);
    }

    protected abstract String getLockedPath();
}

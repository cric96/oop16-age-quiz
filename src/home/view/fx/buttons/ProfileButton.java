package home.view.fx.buttons;

import home.controller.profile.Profile;
import home.utility.view.FontManager;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

/** 
 *      Create a custom button used in Load/New Game dialog.
 */
//package-protected
class ProfileButton extends Button {
    private static final int BOX_DIMENSION = 30;

    /**
     * @param profile the profile represented by the button.
     */
    ProfileButton(final Profile profile) {
        super(profile.getName().orElse("Empty slot"));
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
}

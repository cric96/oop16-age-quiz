package home.view.fx.button;

import home.controller.profile.Profile;
import home.utility.BundleLanguageManager;
import home.utility.Bundles;
import home.utility.ResourceManager;
import home.utility.view.FontManager;
import home.view.fx.Images;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/** 
 *      Create a custom button used in Load/New Game dialog.
 */
//package-protected
abstract class AbstractButtonProfile extends Button implements ButtonProfile {
    private static final int BOX_DIMENSION = 30;
    /**
     * @param profile the profile represented by the button.
     */
    AbstractButtonProfile(final Profile profile) {
        final String empty = BundleLanguageManager.get().getBundle(Bundles.BUTTON).getString("EMPTY_SLOT");
        this.setText(profile.getName().orElse(empty));
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

    @Override
    public void deselect() {
        this.setEffect(null);
    }

    @Override
    public void select() {
        final DropShadow dropS = new DropShadow(40, Color.CORNFLOWERBLUE);
        this.setEffect(dropS);
    }

    protected abstract String getLockedPath();
}

package home.view.menu.fx;

import java.io.IOException;
import home.controller.profile.Profile;
import home.utility.ResourceManager;
import home.view.fx.Images;
import home.view.menu.Buttons;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

/** 
 *      Create a custom button used in Load/New Game dialog.
 */
public class ProfileButton extends Button {
    private static final int BOX_DIMENSION = 30;
    private static final int BOX_DROP_SHADOW = 10;

    /**
     * @param profile the profile represented by the button.
     * @param mode to select a different locked image.
     * @throws IOException if loading of button icon gone wrong.
     */
    public ProfileButton(final Profile profile, final Buttons mode) throws IOException {
        super();
         String fileName;
        if (profile.isEnabled()) {
            fileName = ResourceManager.load(Images.PROFILE_IMAGE_UNLOCK.getPath()).toExternalForm();
        } else {
            if (mode.equals(Buttons.LOAD_GAME)) {
                fileName = ResourceManager.load(Images.PROFILE_IMAGE_LOCK.getPath()).toExternalForm();
            } else {
                fileName = ResourceManager.load(Images.PROFILE_IMAGE_EMPTY.getPath()).toExternalForm();
            }
        }
        final Image img = new Image(fileName);
        final ImageView profileImage = new ImageView(img);
        this.setText(profile.getName().orElse("Empty slot"));
        profileImage.setFitWidth(BOX_DIMENSION);
        profileImage.setFitHeight(BOX_DIMENSION);
        this.setGraphic(profileImage);

        final DropShadow dropS = new DropShadow(BOX_DROP_SHADOW, Color.WHITE);
        dropS.setInput(new Glow());

        setOnMouseEntered(e -> {
            if (profile.isEnabled()) {
                setEffect(dropS);
            }
        });

        setOnMouseExited(e -> {
           setEffect(null);
        });

    }
}

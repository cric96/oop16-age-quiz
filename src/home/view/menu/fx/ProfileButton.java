package home.view.menu.fx;

import home.controller.profile.Profile;
import home.utility.Utility;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
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
     */
    public ProfileButton(final Profile profile) {
        super(profile.getName().orElse("Empty slot"));
        final DropShadow dropS = new DropShadow(BOX_DROP_SHADOW, Color.WHITE);
        dropS.setInput(new Glow());
        this.setFont(Utility.getGeneralFont());
        setOnMouseEntered(e -> {
            if (profile.isEnabled()) {
                setEffect(dropS);
            }
        });

        setOnMouseExited(e -> {
           setEffect(null);
        });

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

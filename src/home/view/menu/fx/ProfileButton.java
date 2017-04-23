package home.view.menu.fx;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import home.controller.menu.Profile;
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
     * @param profile the profile represented by the button
     * @throws IOException if loading of button icon gone wrong.
     */
    public ProfileButton(final Profile profile) throws IOException {
        super();
        InputStream is;
        if (profile.isEnabled()) {
             is = Files.newInputStream(Paths.get("res/images/unlockedProfile.png"));
        } else {
            is = Files.newInputStream(Paths.get("res/images/emptyProfile.png"));
        }
        final Image img = new Image(is);
        is.close();
        //this.setShape(new Circle(4));

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
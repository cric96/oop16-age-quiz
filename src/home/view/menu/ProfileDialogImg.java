package home.view.menu;

import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;

public class ProfileDialogImg extends Button{
    private static final int BOX_WIDTH = 40;
    private static final int BOX_HEIGHT = 40;
    private static final int BOX_DROP_SHADOW = 10;


    public ProfileDialogImg(boolean enabled) throws IOException {
        super();
        InputStream is;
        if (enabled) {
             is = Files.newInputStream(Paths.get("res/images/unlockedProfile.png"));
        } else {
            is = Files.newInputStream(Paths.get("res/images/lockedProfile.png"));
        }
        final Image img = new Image(is);
        is.close();

        final ImageView profileImage = new ImageView(img);

        profileImage.setFitWidth(BOX_WIDTH);
        profileImage.setFitHeight(BOX_HEIGHT);
        this.setGraphic(profileImage);
        final DropShadow dropS = new DropShadow(BOX_DROP_SHADOW, Color.WHITE);
        dropS.setInput(new Glow());

        setOnMouseEntered(e -> {
            setEffect(dropS);
        });

        setOnMouseExited(e -> {
           setEffect(null);
        });
    }
}

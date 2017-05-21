package home.view.menu.fx;

import home.utility.view.FontManager;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * a sub class used to represent a general button in the menu.
 */
public class MenuButton extends StackPane {
    private static final int BOX_WIDTH = 35;
    private static final int BOX_HEIGHT = 300;
    private static final double BOX_OPACITY = 0.6;
    private static final int DROP_SHADOW = 50;
    private static final int FONT = 20;
    private static final double BLUR = 0.4;
    private final Text text;

    /**
     * 
     * @param name 
     *          text in the button
     * @param color 
     *          the shape color of button exit
     */
    public MenuButton(final String name, final Color color) {
        text = new Text(name);
        final int size = 30;
        text.setTranslateX(FONT);
        text.getFont();
        text.setFont(FontManager.getGeneralFont(size));
        text.setFill(Color.WHITE);
        final Rectangle bg = new Rectangle(BOX_HEIGHT, BOX_WIDTH);
        bg.setOpacity(BOX_OPACITY);
        bg.setFill(Color.BLACK);
        bg.setEffect(new GaussianBlur(BLUR));
        setAlignment(Pos.CENTER_LEFT);
        getChildren().addAll(bg, text);

        setOnMouseEntered(e -> {
            bg.setTranslateX(10);
            text.setTranslateX(FONT + 10);
            bg.setFill(color);
            text.setFill(Color.BLACK);
        });

        setOnMouseExited(e -> {
            bg.setTranslateX(0);
            text.setTranslateX(FONT);
            bg.setFill(Color.BLACK);
            text.setFill(Color.WHITE);
        });

        final DropShadow dropS = new DropShadow(DROP_SHADOW, Color.WHITE);
        dropS.setInput(new Glow());

        setOnMousePressed(e -> setEffect(dropS));
        setOnMouseReleased(e -> setEffect(null));

    }

    /**
     * set the text of menu button when menu is repainted.
     * @param text
     *      the text of button
     */
    public void setText(final String text) {
        this.text.setText(text);
    }
}

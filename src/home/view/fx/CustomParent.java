package home.view.fx;

import javafx.scene.Parent;
import javafx.scene.effect.BoxBlur;

/**
 * a custom parent used in all javafx view.
 */
public class CustomParent extends Parent {
    private final BoxBlur effect = new BoxBlur();
    /**
     * add focus to the parent.
     */
    public void addFocus() {
        this.setEffect(null);
    }
    /**
     * remove focus to the parent.
     */
    public void removeFocus() {
        this.setEffect(effect);
    }
}

package home.view.fx.parent;

import javafx.scene.Parent;
import javafx.scene.effect.BoxBlur;

/**
 * a custom parent used in all javafx view.
 */
class CustomParentImpl extends Parent implements CustomParent {
    private final BoxBlur effect = new BoxBlur();

    @Override
    public void addFocus() {
        this.setEffect(null);
    }

    @Override
    public void removeFocus() {
        this.setEffect(effect);
    }
}

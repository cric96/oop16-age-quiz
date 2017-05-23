package home.view.fx.parents;

import javafx.scene.Parent;
import javafx.scene.effect.BoxBlur;

/**
 * a custom parent used in all javafx view.
 */
class CustomParentImpl extends Parent implements CustomParent {
    private final BoxBlur effect = new BoxBlur();
    /* (non-Javadoc)
     * @see home.view.fx.parents.CustomP#addFocus()
     */
    @Override
    public void addFocus() {
        this.setEffect(null);
    }
    /* (non-Javadoc)
     * @see home.view.fx.parents.CustomP#removeFocus()
     */
    @Override
    public void removeFocus() {
        this.setEffect(effect);
    }
}

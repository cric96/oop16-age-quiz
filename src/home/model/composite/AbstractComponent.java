package home.model.composite;

import java.io.Serializable;
import java.util.Optional;
/**
 * a skeleton of a component class.
 * @param <E>
 *      the type of parent
 */
public abstract class AbstractComponent<E extends Composite> implements Component<E>,Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private E parent;
    @Override
    public final Optional<E> getParent() {
        return Optional.ofNullable(this.parent);
    }
    @Override
    public final void attachOn(final E parent) {
        //if is already attach on a object it can't attach in other composite
       if (this.getParent().isPresent()) {
           throw new IllegalStateException("the component is already attach");
       }
       this.parent = parent;
       parent.addComponent(this);
    }
    /**
     * by default all component are enable.
     */
    @Override 
    public boolean isEnable() {
        return true;
    }
}

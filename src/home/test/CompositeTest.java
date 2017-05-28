package home.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import home.model.composite.AbstractComponent;
import home.model.composite.AbstractComposite;
import home.model.composite.Component;
import home.model.composite.Composite;
import home.model.composite.Event;
import home.model.composite.IllegalSourceException;

/**
 * Some test on composite and component.
 */
public class CompositeTest {
    /**
     * some test on composite.
     */
    @Test
    public void simpleTestComposite() {
        final Composite composite = new CompositeImpl();
        assertTrue(composite.getComponents().isEmpty());
        assertTrue(composite.getComponents(ComponentImpl.class).isEmpty());
    }
    /**
     * some test on component.
     */
    @Test 
    public void simpleTestComponent() {
        final Component<CompositeImpl> component = new ComponentImpl();
        assertFalse(component.getParent().isPresent());
        assertEquals(component.getType(), ComponentImpl.class);
        //by default the component is enable
        assertTrue(component.isEnable());
        try {
            component.update(new EventImpl<CompositeTest>(this));
            fail();
        } catch (IllegalSourceException exc) {
            assertNotNull(exc);
        }
    }
    /**
     * some advanced test.
     */
    @Test
    public void advancedTest() {
        final Set<ComponentImpl> componets = new HashSet<>(Arrays.asList(new ComponentImpl(), new ComponentImpl()));
        final CompositeImpl composite = new CompositeImpl();
        componets.forEach(x -> Component.compositeAttach(composite, x));
        assertFalse(composite.getComponents().isEmpty());
        assertFalse(composite.getComponents(ComponentImpl.class).isEmpty());
        assertTrue(composite.getComponents(Composite.class).isEmpty());
        componets.forEach(x -> assertTrue(x.getParent().isPresent()));
        try {
            composite.sendEhy();
        } catch (IllegalSourceException exc) {
            fail();
        }
        componets.forEach(x -> assertNotEquals(Integer.valueOf(x.updateCount()), Integer.valueOf(0)));
    }
    private static class CompositeImpl extends AbstractComposite {

        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        public void sendEhy() {
            final EventImpl<CompositeImpl> event = new EventImpl<CompositeImpl>(this);
            this.getComponents().forEach(x -> x.update(event));
        }
    }
    private static class ComponentImpl extends AbstractComponent<CompositeImpl> {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private int count;
        @Override
        public Class<?> getType() {
            return this.getClass();
        }

        @Override
        public void update(final Event<?> event) {
            if (!CompositeImpl.class.isAssignableFrom(event.getSource().getClass())) {
                throw new IllegalSourceException();
            }
            this.count++;
        }
        public int updateCount() {
            return this.count;
        }
    }
    private static class EventImpl<E> implements Event<E> {
        protected static final String TYPE = "BASIC";
        private final E object;
        EventImpl(final E object) {
            this.object = object;
        }
        @Override
        public String getTypes() {
            return TYPE;
        }

        @Override
        public E getSource() {
            return this.object;
        }
    }
}

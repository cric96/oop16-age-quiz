package home.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import home.controller.dialog.Dialog;
/**
 * some test about dialog.
 */
public class DialogTest {
    /**
     * 
     */
    @Test
    public void basicTest() {
        final Dialog.Builder builder = Dialog.Builder.createBuilder();
        try {
            builder.build();
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
        final String name = "test";
        builder.setName(name);
        final Dialog dialog = builder.build();
        try {
            builder.setName(name);
            fail();
        } catch (IllegalStateException exc) {
            assertNotNull(exc);
        }
        assertNotNull(dialog.getName());
    }
}

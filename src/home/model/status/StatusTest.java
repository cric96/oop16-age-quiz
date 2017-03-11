package home.model.status;
import static org.junit.Assert.*;
import org.junit.*;
/* Some test on status */
/**
 *
 */
public class StatusTest {
    /**
     * 
     */
    @Test 
    public void test1() {
        final Status happiness = Status.createSimpleStatus(StatusName.HAPPINESS);
        final Status knowledge = Status.createStatusWithValue(StatusName.KNOWLEDGE, 10);
        assertEquals(happiness.getValue(), 0);
        assertEquals(knowledge.getValue(), 10);
        try {
            happiness.decValue(-1);
            fail();
        } catch (IllegalArgumentException e) { }
        try {
            happiness.decValue(1001);
            fail();
        } catch (IllegalArgumentException e) { }
        knowledge.decValue(11);
        assertEquals(happiness.getValue(), 0);
        happiness.addValue(100);
        assertEquals(happiness.getValue(), 100);
    }
}

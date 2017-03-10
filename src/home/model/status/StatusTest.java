package home.model.status;
import static org.junit.Assert.*;
import org.junit.*;
/* Nothing really important */
/**
 *
 */
public class StatusTest {
    /**
     * 
     */
    @Test 
    public void test1() {
        for (final Status s : EStatus.values()) {
            System.out.println(s.getName());
        }
        EStatus.HEALT.addValue(10);
        assertEquals(EStatus.HEALT.getValue(), 10);
        assertEquals(EStatus.LOGIC.getValue(), 0);
        EStatus.LOGIC.decValue(10);
        assertEquals(EStatus.LOGIC.getValue(), 0);
        EStatus.LOGIC.addValue(300);
        assertEquals(EStatus.LOGIC.getValue(), 100);
        EStatus.LOGIC.decValue(10);
        assertEquals(EStatus.LOGIC.getValue(), 90);
    }
}

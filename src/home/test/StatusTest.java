package home.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Set;
import org.junit.Test;

import home.model.status.Status;
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
        final Set<Status> statuses = Status.createStatuses();
        statuses.stream().forEach(x -> x.addValue(10));
        statuses.stream().forEach(x -> assertEquals(x.getValue(), 10));
        statuses.stream().forEach(x -> {
            try {
                x.decValue(-1);
                fail();
            } catch (IllegalArgumentException exc) {
                assertEquals(x.getValue(), 10);
            }
        });
    }
}

package home.model.quiz;

import java.util.Map;

import home.model.status.StatusName;

/**
 *It allows to calculate the score for every single query.
 */
public interface Calculator {
    /**
     * called when an answer is correct.
     */
    void correct();
    /**
     * called when an answer is wrong.
     */
    void wrong();
    /**
     * @return
     *  the corresponding XP
     * @throws 
     *  IllegalStateException, if it is called before correct() or wrong()
     */
    int getXP();
    /**
     * 
     * @return
     *  a map where status score for this query is updated
     * @throws 
     *  IllegalStateException, if it is called before correct() or wrong() 
     */
    Map<StatusName, Integer> getStatusScore();
}

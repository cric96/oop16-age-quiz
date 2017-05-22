package home.model.quiz;

import java.util.Map;

import home.model.status.StatusName;
//package-protected
abstract class CalcDecorator implements Calculator {
    private final Calculator calc;
    /**
     * 
     * @param calc
     *  what I want to decorate
     */
    CalcDecorator(final Calculator calc) {
        this.calc = calc;
    }

    @Override
    public void correct() {
       this.calc.correct();
    }

    @Override
    public void wrong() {
        this.calc.wrong();
    }

    @Override
    public int getXP() {
        return this.calc.getXP();
    }

    @Override
    public Map<StatusName, Integer> getStatusScore() {
       return this.calc.getStatusScore();
    }
    /**
     * 
     * @return
     *  the class I decored
     */
    protected Calculator getCalculator() {
        return this.calc;
    }

}

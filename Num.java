import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Num implements Expression {
    // field
    private double num;

    // constructor

    /**
     * constructor.
     * @param num number
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * returns the Num's num.
     * @return Num's num
     */
    double getNum() {
        return num;
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return num;
    }

    @Override
    public double evaluate() throws Exception {
        return num;
    }

    @Override
    public List<String> getVariables() {
        return new LinkedList<>();
    }
    @Override
    public String toString() {
        return String.valueOf(this.num);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(String.valueOf(this.num))) {
            return expression;
        }
        return this;
    }
    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }
    @Override
    public Expression simplify() {
        return this;
    }
}

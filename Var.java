import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap;

public class Var implements Expression {
    // field
    private String var1;

    // constructor

    /**
     * constructor.
     * @param var var
     */
    public Var(String var) {
        this.var1 = var;
    }

    /**
     * returns var1.
     * @return var1
     */
    public String getVar1() {
        return var1;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(var1)) {
            return assignment.get(var1);
        }
        throw new Exception("There are vars with no assignment in the expression");
    }

    @Override
    public double evaluate() throws Exception {
        Map<String, Double> m = new TreeMap<>();
        return evaluate(m);
    }

    @Override
    public List<String> getVariables() {
        List<String> variables = new ArrayList<>();
        variables.add(this.var1);
        return variables;
    }

    @Override
    public String toString() {
        return var1;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (var.equals(this.var1)) {
            return expression;
        }
        return this;
    }
    @Override
    public Expression differentiate(String var) {
        if (var.equals(this.var1)) {
            return new Num(1);
        }
        return new Num(0);
    }
    @Override
    public Expression simplify() {
        return this;
    }
}

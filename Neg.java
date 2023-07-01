import java.util.Map;

public class Neg extends UnaryExpression {
    /**
     * constructor.
     * @param exp the expression
     */
    public Neg(Expression exp) {
        super(exp);
    }

    @Override
    public String toString() {
        return "( -" + this.getExp().toString() + ")";
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return -1 * this.getExp().evaluate(assignment);
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(this.getExp().assign(var, expression));
    }
    @Override
    public Expression differentiate(String var) {
        return new Neg(this.getExp().differentiate(var));
    }
    @Override
    public Expression simplify() {
        if (this.getExp().getVariables().size() == 0) {
            try {
                return new Num(this.evaluate());
            } catch (Exception ignored) {
            }
        }
        return new Neg(this.getExp().simplify());
    }
}

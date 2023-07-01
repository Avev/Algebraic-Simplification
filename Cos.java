import java.util.Map;

public class Cos extends UnaryExpression {
    /**
     * constructor.
     * @param exp the expression
     */
    public Cos(Expression exp) {
        super(exp);
    }

    @Override
    public String toString() {
        return "cos(" + this.getExp().toString() + ")";
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.cos(Math.toRadians(this.getExp().evaluate(assignment)));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Cos(this.getExp().assign(var, expression));
    }
    @Override
    public Expression differentiate(String var) {
        if (this.getVariables().contains(var)) {
            return new Mult(new Neg(new Sin(this.getExp())), this.getExp().differentiate(var));
        }
        return new Num(0);
    }

    @Override
    public Expression simplify() {
        if (this.getExp().getVariables().size() == 0) {
            try {
                return new Num(this.evaluate());
            } catch (Exception ignored) {
            }
        }
        return new Cos(this.getExp().simplify());
    }
}

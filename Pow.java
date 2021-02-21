import java.util.Map;
/**
 * 316266683.
 * @author Aviv Rahamim
 */
public class Pow extends BinaryExpression {
    // constructor
    /**
     * constructor.
     * @param exp1 the left expression
     * @param exp2 the right expression
     */
    public Pow(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExp().toString() + "^" + this.getRightExp().toString() + ")";
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.pow(this.getLeftExp().evaluate(assignment), this.getRightExp().evaluate(assignment));
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(this.getLeftExp().assign(var, expression), this.getRightExp().assign(var, expression));
    }
    @Override
    public Expression differentiate(String var) {
        Expression f = this.getLeftExp();
        Expression g = this.getRightExp();
        if (g.getVariables().contains(var)) {
            Expression glnf = new Mult(g, new Log(new Var("e"), f));
            return new Mult(this, glnf.differentiate(var));
        }
        // this if can be removed
        if (f.getVariables().contains(var)) {
            return new Mult(new Mult(f.differentiate(var), g), new Pow(f, new Minus(g, new Num(1))));
        }
        return new Num(0);
    }
    @Override
    public Expression simplify() {
        if (this.getVariables().size() == 0) {
            try {
                return new Num(this.evaluate());
            } catch (Exception ignored) {
            }
        }
        if (this.getRightExp().simplify().toString().equals("1")
                || this.getRightExp().simplify().toString().equals("1.0")) {
            return this.getLeftExp().simplify();
        }
        if (this.getRightExp().simplify().toString().equals("0")
                || this.getRightExp().simplify().toString().equals("0.0")) {
            return new Num(1);
        }
        return new Pow(this.getLeftExp().simplify(), this.getRightExp().simplify());
    }
}

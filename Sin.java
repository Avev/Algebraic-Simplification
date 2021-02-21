import java.util.Map;
/**
 * 316266683.
 * @author Aviv Rahamim
 */
public class Sin extends UnaryExpression {
    /**
     * constructor.
     * @param exp the expression
     */
    public Sin(Expression exp) {
        super(exp);
    }

    @Override
    public String toString() {
        return "Sin(" + this.getExp().toString() + ")";
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return Math.sin(Math.toRadians(this.getExp().evaluate(assignment)));
    }

    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(this.getExp().assign(var, expression));
    }
    @Override
    public Expression differentiate(String var) {
        if (this.getVariables().contains(var)) {
            return new Mult(new Cos(this.getExp()), this.getExp().differentiate(var));
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
        return new Sin(this.getExp().simplify());
    }
}

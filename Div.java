import java.util.Map;

public class Div extends BinaryExpression {
    // constructor
    /**
     * constructor.
     * @param exp1 the left expression
     * @param exp2 the right expression
     */
    public Div(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toString() {
        return "(" + this.getLeftExp().toString() + " / " + this.getRightExp().toString() + ")";
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (this.getRightExp().evaluate(assignment) == 0) {
            throw new Exception("Dividing by zero");
        }
        return this.getLeftExp().evaluate(assignment) / this.getRightExp().evaluate(assignment);
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(this.getLeftExp().assign(var, expression), this.getRightExp().assign(var, expression));
    }
    @Override
    public Expression differentiate(String var) {
        if (this.getVariables().contains(var)) {
            Expression f = this.getLeftExp();
            Expression g = this.getRightExp();
            Expression ft = f.differentiate(var);
            Expression gt = g.differentiate(var);
            return new Div(new Minus(new Mult(ft, g), new Mult(f, gt)), new Pow(g, new Num(2)));
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
        if (this.getLeftExp().simplify().toString().equals(this.getRightExp().simplify().toString())) {
            return new Num(1);
        }
        if (this.getRightExp().simplify().toString().equals("1")
                || this.getRightExp().simplify().toString().equals("1.0")) {
            return this.getLeftExp().simplify();
        }
        if (this.getLeftExp().simplify().toString().equals("0")
                || this.getLeftExp().simplify().toString().equals("0.0")) {
            return new Num(0);
        }
        return new Div(this.getLeftExp().simplify(), this.getRightExp().simplify());
    }
}

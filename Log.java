import java.util.Map;

public class Log extends BinaryExpression {
    // constructor
    /**
     * constructor.
     * @param exp1 the left expression
     * @param exp2 the right expression
     */
    public Log(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    @Override
    public String toString() {
        return "log(" + this.getLeftExp().toString() + ", " + this.getRightExp().toString() + ")";
    }
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (Math.log(this.getLeftExp().evaluate(assignment)) == 0
                || this.getLeftExp().evaluate(assignment) <= 0
                || this.getRightExp().evaluate(assignment) <= 0) {
            throw new Exception("One of the log numbers is zero");
        }
        return Math.log(this.getRightExp().evaluate(assignment)) / Math.log(this.getLeftExp().evaluate(assignment));
    }
    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(this.getLeftExp().assign(var, expression), this.getRightExp().assign(var, expression));
    }
    @Override
    public Expression differentiate(String var) {
        if (this.getVariables().contains(var)) {
            Expression f = this.getLeftExp();
            Expression g = this.getRightExp();
            Expression ft = f.differentiate(var);
            Expression gt = g.differentiate(var);
            Expression fln = new Log(new Var("e"), f);
            Expression gln = new Log(new Var("e"), g);
            Expression flngtf = new Mult(new Mult(fln, gt), f);
            Expression glnftg = new Mult(new Mult(gln, ft), g);
            Expression division = new Mult(new Pow(fln, new Num(2)), new Mult(g, f));
            return new Div(new Minus(flngtf, glnftg), division);
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
        return new Log(this.getLeftExp().simplify(), this.getRightExp().simplify());
    }
}

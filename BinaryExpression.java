import java.util.LinkedList;
import java.util.List;

public abstract class BinaryExpression extends BaseExpression {
    // field
    private Expression leftExp;
    private Expression rightExp;

    /**
     * constructor.
     * @param exp1 the left expression
     * @param exp2 the right expression
     */
    public BinaryExpression(Expression exp1, Expression exp2) {
        this.leftExp = exp1;
        this.rightExp = exp2;
    }

    /**
     * returns the left expression.
     * @return the left expression
     */
    public Expression getLeftExp() {
        return leftExp;
    }

    /**
     * returns the right expression.
     * @return the right expression
     */
    public Expression getRightExp() {
        return rightExp;
    }
    @Override
    public List<String> getVariables() {
        List<String> myVars = new LinkedList<>();
        myVars.addAll(this.getLeftExp().getVariables());
        myVars.addAll(this.getRightExp().getVariables());
        return myVars;
    }
}

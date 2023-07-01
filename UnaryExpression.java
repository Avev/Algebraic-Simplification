import java.util.LinkedList;
import java.util.List;

public abstract class UnaryExpression extends BaseExpression {
    // field
    private Expression exp;

    /**
     * constructor.
     * @param exp expression
     */
    public UnaryExpression(Expression exp) {
        this.exp = exp;
    }

    /**
     * returns the expression.
     * @return the expression
     */
    public Expression getExp() {
        return exp;
    }

    @Override
    public List<String> getVariables() {
        return new LinkedList<>(this.getExp().getVariables());
    }
}

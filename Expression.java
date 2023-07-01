import java.util.Map;
import java.util.List;

public interface Expression {


    /**
     *Evaluate the expression using the variable values provided
     *in the assignment, and return the result.  If the expression
     *contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment the assignment requested
     * @return result of the evaluation after assignment
     * @throws Exception if the string is not in the map
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     *A convenience method. Like the `evaluate(assignment)` method above,
     *but uses an empty assignment.
     * @return evaluation with empty map assignment
     * @throws Exception If the expression contains a variable which is not in the assignment, an exception is thrown.
     */
    double evaluate() throws Exception;
    /**
     * Returns a list of the variables in the expression.
     * @return list of the variables in the expression
     */
    List<String> getVariables();

    /**
     *Returns a nice string representation of the expression.
     * @return nice string representation of the expression.
     */
    String toString();

    /**
     * Returns a new expression in which all occurrences of the variable are replaced with the provided expression
     * (Does not modify the current expression).
     * @param var var we want to assign
     * @param expression expression we want to put instead of the var
     * @return the new expression after replacing the requested var with the requested expression
     */
    Expression assign(String var, Expression expression);

    /**
     *Returns the expression tree resulting from differentiating the current expression relative to variable `var`.
     * @param var the variable we differentiate by
     * @return the expression tree resulting from differentiation
     */
    Expression differentiate(String var);

    /**
     *Returned a simplified version of the current expression.
     * @return simplified expression
     */
    Expression simplify();
}

import java.util.Map;
import java.util.TreeMap;

public class ExpressionTest {
    /**
     * main function.
     * @param args received input from the user
     */
    public static void main(String[] args) {
        // Create the expression (2x) + (sin(4y)) + (e^x).
        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression e = new Var("e");
        Expression x2 = new Mult(new Num(2), x);
        Expression sin4y = new Sin(new Mult(new Num(4), y));
        Expression epowx = new Pow(e, x);
        Expression e1 = new Plus(new Plus(x2, sin4y), epowx);
        System.out.println(e1.toString());
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x",  2.0);
        assignment.put("y",  0.25);
        assignment.put("e",  2.71);
        try {
            double value = e1.evaluate(assignment);
            System.out.println(value);
            System.out.println(e1.differentiate("x").toString());
            System.out.println(e1.differentiate("x").evaluate(assignment));
            System.out.println(e1.differentiate("x").simplify().toString());
        } catch (Exception ignored) {
        }
    }
}

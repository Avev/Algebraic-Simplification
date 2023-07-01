import java.util.Map;
import java.util.TreeMap;

public abstract class BaseExpression implements Expression {
    @Override
    public double evaluate() throws Exception {
        Map<String, Double> m = new TreeMap<>();
        return evaluate(m);
    }
}

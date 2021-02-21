import java.util.Map;
import java.util.TreeMap;
/**
 * 316266683.
 * @author Aviv Rahamim
 */
public abstract class BaseExpression implements Expression {
    @Override
    public double evaluate() throws Exception {
        Map<String, Double> m = new TreeMap<>();
        return evaluate(m);
    }
}

package com.ruleengine;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        RuleEngine engine = new RuleEngine();

        // Try a more complex rule
        String ruleString = "age > 30 OR (salary > 50000 AND experience > 5)";
        Node ruleAST = engine.createRule(ruleString);

        // Sample user data for testing
        Map<String, Object> userData = new HashMap<>();
        userData.put("age", 28);
        userData.put("salary", 60000);
        userData.put("experience", 6);

        // Evaluate the rule
        boolean result = engine.evaluateRule(ruleAST, userData);
        System.out.println("Rule Evaluation Result: " + result);  // Should print "true"
    }
}

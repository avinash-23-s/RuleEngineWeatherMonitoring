package com.ruleengine;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleEngine {

    // Function to create an AST from a rule string
    public Node createRule(String rule) {
        // Split the rule by "AND" or "OR" using a regex pattern
        String[] tokens = rule.split(" (AND|OR) ");
        Node current = null;
        Node previous = null;

        for (String token : tokens) {
            token = token.trim();

            // Create an operator node if the token is AND or OR
            if (token.contains("AND")) {
                current = new Node("operator", previous, null);
                current.type = "AND";
            } else if (token.contains("OR")) {
                current = new Node("operator", previous, null);
                current.type = "OR";
            }

            // Handle conditions (like "age > 30")
            Matcher matcher = Pattern.compile("(\\w+)\\s*(>|<|=)\\s*(\\d+)").matcher(token);
            if (matcher.find()) {
                String attribute = matcher.group(1);  // e.g., "age"
                String operator = matcher.group(2);   // e.g., ">"
                String value = matcher.group(3);      // e.g., "30"

                Node operandNode = new Node("operand", attribute + " " + operator + " " + value);
                if (current != null) {
                    current.right = operandNode; // Attach to the right of the operator node
                }
                previous = operandNode; // Save the operand as previous
            }
        }

        return current; // Return the root of the AST
    }

    // Function to evaluate the AST against user data
    public boolean evaluateRule(Node node, Map<String, Object> data) {
        if (node == null) return true; // Base case

        // If the node is an operator, recursively evaluate the left and right children
        if (node.type.equals("AND")) {
            return evaluateRule(node.left, data) && evaluateRule(node.right, data);
        } else if (node.type.equals("OR")) {
            return evaluateRule(node.left, data) || evaluateRule(node.right, data);
        }

        // If the node is an operand, check the condition
        if (node.type.equals("operand")) {
            // Safely split the condition
            String[] conditionParts = node.value.split(" ");
            if (conditionParts.length != 3) {
                System.out.println("Invalid operand format: " + node.value);
                return false; // Invalid operand
            }

            String attribute = conditionParts[0];  // e.g., "age"
            String operator = conditionParts[1];   // e.g., ">"
            int value;

            try {
                value = Integer.parseInt(conditionParts[2]); // e.g., 30
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format: " + conditionParts[2]);
                return false; // Invalid number
            }

            int userValue = (int) data.get(attribute);

            // Evaluate the condition based on the operator
            switch (operator) {
                case ">":
                    return userValue > value;
                case "<":
                    return userValue < value;
                case "=":
                    return userValue == value;
                default:
                    System.out.println("Invalid operator: " + operator);
                    return false; // Invalid operator
            }
        }

        return false; // Default case
    }
}

package com.ruleengine;

public class Node {

	String type;  // "operator" or "operand"
    Node left;    // Left child (for operators)
    Node right;   // Right child (for operators)
    String value; // Value (for operand nodes)

    // Constructor for operator nodes (AND/OR)
    public Node(String type, Node left, Node right) {
        this.type = type;
        this.left = left;
        this.right = right;
        this.value = null;
    }

    // Constructor for operand nodes (like age > 30)
    public Node(String type, String value) {
        this.type = type;
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

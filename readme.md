

import java.util.Stack;
public class BinaryTreeHalloween {
  protected class NodeParent<T> {
    private Object left;
    private Object right;
    private NodeParent<T> parent;
    public NodeParent() {
      this(null, null, null);
    }
    public NodeParent(Object right, Object left) {
      this(null, right, left);
    }
    public NodeParent(NodeParent<T> parent, Object left, Object right) {
      this.left = left;
      this.right = right;
      this.parent = parent;
    }
    public NodeParent<T> getParent() { return this.parent; }
    public void setParent(NodeParent<T> parent) { this.parent = parent; }
    
    
    public Object getLeft() { return this.left; }
    public void setLeft(T left) { this.left = left; }
    public void setLeft(NodeParent<T> left) { this.left = left; }
    
    public Object getRight() { return this.right; }
    public void setRight(T right) { this.right = right; }
    public void setRight(NodeParent<T> right) { this.right = right; }
  }
  private Object root;
  public BinaryTreeHalloween(String input) {
    this.root = null;
    this.load(input);
  }
  private void load(String input) {
    try {
      Stack<Object> stack = new Stack<>();
      int i = 0;
      while (i < input.length()) {
          char currentChar = input.charAt(i);
          if (Character.isDigit(currentChar)) {
              StringBuilder number = new StringBuilder();
              while (i < input.length() && Character.isDigit(input.charAt(i))) {
                  number.append(input.charAt(i));
                  i++;
              }
              Integer leaf = Integer.parseInt(number.toString());
              stack.push(leaf);
          } else if (currentChar == '(') {
              i++;
          } else if (currentChar == ')') {
Object right = stack.pop();
Object left = stack.pop();
NodeParent<Integer> parent = new NodeParent<>(left, right);
stack.push(parent);
 i++;
} else {
 i++;}}
if (!stack.isEmpty()) {
this.root = stack.pop();}
} catch (Exception e) {
throw new RuntimeException("Árvore inválida", e);}}
public int qtdRuas() {
if (root instanceof Integer) return 0;
NodeParent<?> node = (NodeParent<?>) root;
if(size(node.getLeft()) < size(node.getRight()))
return sumRuas(node.getLeft(), true) + sumRuas(node.getRight(), false);
else
return sumRuas(node.getLeft(), false) + sumRuas(node.getRight(), true);  }
private int sumRuas(Object current, boolean isMin) {
if (current instanceof Integer)
if(isMin) return 2;
else return 1;
NodeParent<?> node = (NodeParent<?>) current;
int count = 0;
if (isMin)
count += 2;
else
count += 1;
if (isMin)
return count + sumRuas(node.getLeft(), true) + sumRuas(node.getRight(), true);
else if(size(node.getLeft()) < size(node.getRight()))
return count + sumRuas(node.getLeft(), true) + sumRuas(node.getRight(), false);
else
return count + sumRuas(node.getLeft(), false) + sumRuas(node.getRight(), true);}
public int qtdDoces() {
if(root == null) return 0;
return sumDoces(root);}
private int sumDoces(Object current) {
if(current instanceof Integer) return (Integer) current;
return sumDoces(((NodeParent<?>) current).getLeft()) + sumDoces(((NodeParent<?>) current).getRight());}
 private int size(Object current) {
if(current instanceof Integer) return 0;
return 1 + size(((NodeParent<?>) current).getLeft()) + size(((NodeParent<?>) current).getRight());}
public String toString() {
if(root == null) return "{ }";
return getClass().getName()+"@"+size()+"..."+toString(1,root, "Root: ");}
  private String toString(int level, Object current, String Prefix) {
String spacer = "";
for(int i = 0; i < level * 2; i++) spacer += " ";
if(current instanceof Integer) return  "\n"+spacer+Prefix+"["+current+"]";
return "\n"+spacer+Prefix+"[ ]"+
toString(level+1, ((BinaryTreeHalloween.NodeParent<?>) current).getLeft(), "L--- ")+
toString(level+1, ((BinaryTreeHalloween.NodeParent<?>) current).getRight(), "R--- ");
  }
}
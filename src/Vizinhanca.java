package src;
import java.util.Stack;

public class Vizinhanca {
  private class NodeParent<X> {
    private Object left;
    private Object right;
    private NodeParent<X> parent;
    
    
    public NodeParent() {
      this(null, null, null);
    }
  
    public NodeParent(Object right, Object left) {
      this(null, right, left);
    }
    
    public NodeParent(NodeParent<X> parent, Object left, Object right) {
      this.left = left;
      this.right = right;
      this.parent = parent;
    }
    
    public NodeParent<X> getParent() { return this.parent; }
    public void setParent(NodeParent<X> parent) { this.parent = parent; }
    
    
    public Object getLeft() { return this.left; }
    public void setLeft(X left) { this.left = left; }
    public void setLeft(NodeParent<X> left) { this.left = left; }
    
    public Object getRight() { return this.right; }
    public void setRight(X right) { this.right = right; }
    public void setRight(NodeParent<X> right) { this.right = right; }
  }
  
  private Object raiz;
  
  public Vizinhanca(String input) {
    this.raiz = null;
    this.leitura(input);
  }

  private void leitura(String input) {
    try {
      Stack<Object> pilha = new Stack<>();
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
              pilha.push(leaf);
          } else if (currentChar == '(') {
              i++;
          } else if (currentChar == ')') {
              Object right = pilha.pop();
              Object left = pilha.pop();
              NodeParent<Integer> parent = new NodeParent<>(left, right);
              pilha.push(parent);
              i++;
          } else {
              i++;
          }
        }
      if (!pilha.isEmpty()) {
          this.raiz = pilha.pop();
      }
    } catch (Exception e) {
      throw new RuntimeException("Árvore inválida", e);
    }
  }

  public int qtdRuas() {
    if (raiz instanceof Integer) return 0;
    NodeParent<?> node = (NodeParent<?>) raiz;

    if(tamanho(node.getLeft()) < tamanho(node.getRight()))
      return somaRuas(node.getLeft(), true) + somaRuas(node.getRight(), false);
    else
      return somaRuas(node.getLeft(), false) + somaRuas(node.getRight(), true);  
  }

  private int somaRuas(Object current, boolean isMin) {
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
      return count + somaRuas(node.getLeft(), true) + somaRuas(node.getRight(), true);
    else if(tamanho(node.getLeft()) < tamanho(node.getRight()))
      return count + somaRuas(node.getLeft(), true) + somaRuas(node.getRight(), false);
    else
      return count + somaRuas(node.getLeft(), false) + somaRuas(node.getRight(), true);
  }

  public int qtdDoces() {
    if(raiz == null) return 0;
    return somaDoces(raiz);
  }

  private int somaDoces(Object current) {
    if(current instanceof Integer) return (Integer) current;
    return somaDoces(((NodeParent<?>) current).getLeft()) + somaDoces(((NodeParent<?>) current).getRight());
  }

  public int tamanho() {
    if(raiz == null) return 0;
    return tamanho(raiz);
  }

  private int tamanho(Object current) {
      if(current instanceof Integer) return 0;

      return 1 + tamanho(((NodeParent<?>) current).getLeft()) + tamanho(((NodeParent<?>) current).getRight());
  }
}

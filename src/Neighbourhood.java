package src;
import java.util.Stack;

public class Neighbourhood {
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
  
  private Object root;
  
  public Neighbourhood(String input) {
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
              // Encontrou um número, ler o valor completo
              StringBuilder number = new StringBuilder();
              while (i < input.length() && Character.isDigit(input.charAt(i))) {
                  number.append(input.charAt(i));
                  i++;
              }

              // Criar um nó folha e empilhar na pilha
              Integer leaf = Integer.parseInt(number.toString());
              stack.push(leaf);
          } else if (currentChar == '(') {
              // Encontrou abertura de parênteses, avançar para o próximo caractere
              i++;
          } else if (currentChar == ')') {
              // Encontrou fechamento de parênteses, desempilhar dois últimos elementos
              Object right = stack.pop();
              Object left = stack.pop();

              // Criar um nó pai e empilhar na pilha
              NodeParent<Integer> parent = new NodeParent<>(left, right);
              stack.push(parent);

              i++;
          } else {
              // Ignorar outros caracteres, avançar para o próximo caractere
              i++;
          }
        }

      // A raiz da árvore estará no topo da pilha
      if (!stack.isEmpty()) {
          this.root = stack.pop();
      }
    } catch (Exception e) {
      throw new RuntimeException("Árvore inválida", e);
    }
  }

  public int qtdRuas() {
    if (root instanceof Integer) return 0;
    NodeParent<?> node = (NodeParent<?>) root;

    if(size(node.getLeft()) < size(node.getRight()))
      return sumRuas(node.getLeft(), true) + sumRuas(node.getRight(), false);
    else
      return sumRuas(node.getLeft(), false) + sumRuas(node.getRight(), true);  
  }

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
      return count + sumRuas(node.getLeft(), false) + sumRuas(node.getRight(), true);
  }

  public int qtdDoces() {
    if(root == null) return 0;
    return sumDoces(root);
  }

  private int sumDoces(Object current) {
    if(current instanceof Integer) return (Integer) current;
    return sumDoces(((NodeParent<?>) current).getLeft()) + sumDoces(((NodeParent<?>) current).getRight());
  }


  public int size() {
    if(root == null) return 0;
    return size(root);
  }

  private int size(Object current) {
      if(current instanceof Integer) return 0;

      return 1 + size(((NodeParent<?>) current).getLeft()) + size(((NodeParent<?>) current).getRight());
  }

  @Override
  public String toString() {
      if(root == null) return "{ }";
      return getClass().getName()+"@"+size()+"..."+toString(1,root, "Root: ");
  }

  private String toString(int level, Object current, String Prefix) {
    
    String spacer = "";
    for(int i = 0; i < level * 2; i++) spacer += " ";
    if(current instanceof Integer) return  "\n"+spacer+Prefix+"["+current+"]";

    return "\n"+spacer+Prefix+"[ ]"+
      toString(level+1, ((Neighbourhood.NodeParent<?>) current).getLeft(), "L--- ")+
      toString(level+1, ((Neighbourhood.NodeParent<?>) current).getRight(), "R--- ");
  }

  @Override
  public int hashCode() {
    return hashCode(root, 23);
  }

  private int hashCode(Object current, int numb) {
    return hashCode(((Neighbourhood.NodeParent<?>) current).getLeft(), numb*23)+
    hashCode(((Neighbourhood.NodeParent<?>) current).getRight(), numb*23);
  }
}

package src;

public class Vizinhanca {  
  private class Node{
    private Node left, right;
    private int qtdCandy;    
    
    public Node(Node left, int qtdCandy, Node right) {
      this.left = left;
      this.qtdCandy = qtdCandy;
      this.right = right;
    }
  }
  private Node raiz;
  private int index;
  private String line;
  
  public void resolucao(String input) {
    this.index = 0;
    this.line = input;
    this.raiz = leitura();
    int candy = qtdDoces();
    int ruas = qtdRuas();
    System.out.println(ruas + " " + candy);
  }

  // private void leitura(String input) {
  //   try {
  //     Stack<Node> pilha = new Stack<Node>();
  //     int i = 0;

  //     while (i < input.length()) {
  //         char currentChar = input.charAt(i);
  //         if (Character.isDigit(currentChar)) {
  //             StringBuilder number = new StringBuilder();
  //             while (i < input.length() && Character.isDigit(input.charAt(i))) {
  //                 number.append(input.charAt(i));
  //                 i++;
  //             }
  //             Node leaf = new Node(null, Integer.parseInt(number.toString()), null);
  //             pilha.push(leaf);
  //         } else if (currentChar == '(') {
  //             i++;
  //         } else if (currentChar == ')') {
  //             Node right = pilha.pop();
  //             Node left = pilha.pop();
  //             Node parent = new Node(left,0, right);
  //             pilha.push(parent);
  //             i++;
  //         } else {
  //             i++;
  //         }
  //       }
  //     if (!pilha.isEmpty()) {
  //         this.raiz = pilha.pop();
  //     }
  //   } catch (Exception e) {
  //     throw new RuntimeException("Árvore inválida", e);
  //   }
  // }
  private Node leitura() {
    if (index >= line.length())
        throw new IllegalArgumentException("The index cannot be greater than or equal to the length of the line.");

    while (line.charAt(index) == ' ')
        index++;

    if (line.charAt(index) == '(') {
        index++;
        Node left = leitura();
        Node right = leitura();
        index++;
        return new Node(left, 0, right);
    } else {
        int start = index;
        while (index < line.length() && Character.isDigit(line.charAt(index)))
            index++;
        int end = index;
        return new Node(null,Integer.parseInt(line.substring(start, end)), null);
    }
}
  public int qtdRuas() {
    Node node = (Node) this.raiz;
    if (node == null) return 0;

    if(tamanho(node.left) < tamanho(node.right))
      return somaRuas(node.left, true) + somaRuas(node.right, false);
    else
      return somaRuas(node.left, false) + somaRuas(node.right, true);  
  }

  private int somaRuas(Node current, boolean isMin) {
    if (current == null) return 0;

    if(current.qtdCandy != 0) {
      if(isMin) return 2;
      else return 1;
    }

    int count = 0;

    if (isMin)
      count += 2;
    else
      count += 1;

    if (isMin)
      return count + somaRuas(current.left, true) + somaRuas(current.right, true);
    else if(tamanho(current.left) < tamanho(current.right))
      return count + somaRuas(current.left, true) + somaRuas(current.right, false);
    else
      return count + somaRuas(current.left, false) + somaRuas(current.right, true);
  }

  public int qtdDoces() {
    if(this.raiz == null) return 0;
    return somaDoces(this.raiz);
  }

  private int somaDoces(Node current) {
    if(current.qtdCandy != 0) return current.qtdCandy;
    return somaDoces(((Node) current).left) + somaDoces(((Node) current).right);
  }

  public int tamanho() {
    if(this.raiz == null) return 0;
    return tamanho(this.raiz);
  }

  private int tamanho(Node current) {

      if(current == null) return 0;
      // if(current.qtdCandy != 0) return 0;

      return 1 + tamanho(((Node) current).left) + tamanho(((Node) current).right);
  }
}

package src;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            
            FileInputStream fileInputStream = new FileInputStream(new File("src/teste.txt"));
            Scanner scanner = new Scanner(fileInputStream);
    
            long tempExec = 0;
    
            Vizinhanca[] trees = new Vizinhanca[5];
            Vizinhanca tree = new Vizinhanca();
            for (int i = 0; i < 5; i++) {
                for (;;) {
                    try {                        
                        String line = scanner.nextLine();
                        long startLoad = System.currentTimeMillis();
                        if (line.length() > 255)
                            throw new Exception();
                        if (line.length() == 0)
                            throw new Exception();

                        tree.resolucao(line);
                        tempExec += System.currentTimeMillis() - startLoad;
                        break;
                    } catch (Exception e) {
                        System.out.println("Árvore inválida");
                    }
                }
            }    
            scanner.close();
            fileInputStream.close(); 
            long start = System.currentTimeMillis();
            // for (int i = 0; i < 5; i++) {
            //     System.out.println((i + 1) + "°Árvore" + ": Quantidade de ruas: " + tree.qtdRuas() + ", Quantidade de Doces: " + trees[i].qtdDoces());
            // }
            System.out.println("\nTempo de execução: " + ((System.currentTimeMillis() - start) + tempExec) + "ms");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

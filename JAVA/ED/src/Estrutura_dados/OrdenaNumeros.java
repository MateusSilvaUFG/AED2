package Estrutura_dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrdenaNumeros {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        BinarySearchTree bst = new BinarySearchTree();
        
        // Ler números do arquivo
        try (BufferedReader br = new BufferedReader(new FileReader("RandomNumbers.txt"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                bst.insert(Integer.parseInt(linha));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        
        // Gravar números ordenados em outro arquivo
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("SortedNumbers.txt"))) {
            bst.inorder(bw);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        
        System.out.println("Tempo total de execução: " + totalTime + " milissegundos");
    }
}
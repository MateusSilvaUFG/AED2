package Hash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        // Iniciar o cronômetro
        long startTime = System.currentTimeMillis();

        // Ler números do arquivo para determinar o tamanho da tabela hash
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("RandomNumbers.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                numbers.add(Integer.parseInt(line.trim()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numElements = numbers.size();
        double loadFactor = 0.75;
        int tableSize = (int) (numElements / loadFactor);
        tableSize = nextPrime(tableSize);

        // Inicializar a tabela hash
        HashTable hashTable = new HashTable(tableSize);

        // Inserir números na tabela hash
        for (int number : numbers) {
            hashTable.insert(number);
        }

        // Gerar 100 números aleatórios e buscar na tabela hash
        Random rand = new Random();
        int[] randomNumbers = new int[100];
        for (int i = 0; i < 100; i++) {
            randomNumbers[i] = rand.nextInt(10000);
        }

        for (int number : randomNumbers) {
            int[] result = hashTable.search(number);
            if (result != null) {
                System.out.printf("Number: %d, Result: Found at index %d, position %d%n", number, result[0], result[1]);
            } else {
                System.out.printf("Number: %d, Result: Not found%n", number);
            }
        }

        // Finalizar o cronômetro e imprimir o tempo total
        long endTime = System.currentTimeMillis();
        System.out.printf("Total execution time: %.3f seconds%n", (endTime - startTime) / 1000.0);
    }

    // Função para encontrar o próximo número primo maior ou igual a n
    private static int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;
    }

    // Função para verificar se um número é primo
    private static boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }
}

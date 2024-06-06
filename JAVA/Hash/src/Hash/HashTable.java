package Hash;

import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private List<Integer>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        this.size = size;
        table = (List<Integer>[]) new List[size];
        for (int i = 0; i < size; i++) {
            table[i] = new ArrayList<>();
        }
    }

    // Função de hash para calcular o índice na tabela
    private int hashFunction(int key) {
        return key % size;
    }

    // Inserir um valor na tabela hash
    public void insert(int key) {
        int index = hashFunction(key);
        table[index].add(key);
    }

    // Buscar um valor na tabela hash
    public int[] search(int key) {
        int index = hashFunction(key);  // Calcular o índice usando a função de hash
        List<Integer> bucket = table[index];  // Acessar a lista (bucket) no índice calculado
        
        // Percorrer a lista para encontrar o elemento
        for (int i = 0; i < bucket.size(); i++) {
            if (bucket.get(i) == key) {
                return new int[]{index, i};  // Retornar o índice e a posição na lista
            }
        }
        return null;  // Retornar null se o elemento não for encontrado
    }
}

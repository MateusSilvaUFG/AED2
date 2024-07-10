package Estrutura_dados;

import java.io.BufferedWriter;
import java.io.IOException;

class BinarySearchTree {
 Node root;

 BinarySearchTree() {
     root = null;
 }

 // Método para inserir um novo valor na árvore
 void insert(int value) {
     root = insertRec(root, value);
 }

 // Função recursiva para inserir um novo valor na árvore
 Node insertRec(Node root, int value) {
     if (root == null) {
         root = new Node(value);
         return root;
     }
     if (value < root.value) {
         root.left = insertRec(root.left, value);
     } else if (value > root.value) {
         root.right = insertRec(root.right, value);
     }
     return root;
 }

 // Método para fazer a travessia em ordem (in-order) da árvore
 void inorderRec(Node root, BufferedWriter bw) throws IOException {
     if (root != null) {
         inorderRec(root.left, bw);
         bw.write(root.value + "\n");
         inorderRec(root.right, bw);
     }
 }

 // Método para iniciar a travessia em ordem da árvore
 void inorder(BufferedWriter bw) throws IOException {
     inorderRec(root, bw);
 }
}
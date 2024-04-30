#include <stdio.h>
#include <stdlib.h>

typedef struct TreeNode {
    int val;
    struct TreeNode *left;
    struct TreeNode *right;
} TreeNode;

TreeNode* buildTree(int* inorder, int inorderSize, int* preorder, int preorderSize) {
    if (inorderSize == 0 || preorderSize == 0) {
        return NULL;
    }
    
    // O primeiro elemento do pré-ordem é o valor do nó raiz
    int root_val = preorder[0];
    TreeNode* root = (TreeNode*)malloc(sizeof(TreeNode));
    root->val = root_val;
    
    // Procurando o índice do nó raiz no percurso em-ordem
    int root_index;
    for (root_index = 0; root_index < inorderSize; root_index++) {
        if (inorder[root_index] == root_val) {
            break;
        }
    }
    
    // Recursivamente construindo as subárvores esquerda e direita
    root->left = buildTree(inorder, root_index, preorder + 1, root_index);
    root->right = buildTree(inorder + root_index + 1, inorderSize - root_index - 1, preorder + root_index + 1, preorderSize - root_index - 1);
    
    return root;
}


void inorderTraversal(TreeNode* root) {
    if (root) {
        inorderTraversal(root->left);
        printf("%d ", root->val);
        inorderTraversal(root->right);
    }
}

void preorderTraversal(TreeNode* root) {
    if (root) {
        printf("%d ", root->val);
        preorderTraversal(root->left);
        preorderTraversal(root->right);
    }
}

int main() {
    int inorder[] = {9, 3, 15, 20, 7};
    int preorder[] = {3, 9, 20, 15, 7};
    int inorderSize = sizeof(inorder) / sizeof(inorder[0]);
    int preorderSize = sizeof(preorder) / sizeof(preorder[0]);
    
    TreeNode* root = buildTree(inorder, inorderSize, preorder, preorderSize);
    
    printf("arvore reconstruida:\n");
    printf("In-Ordem: ");
    inorderTraversal(root);
    printf("\nPré-Ordem: ");
    preorderTraversal(root);
    
    return 0;
}

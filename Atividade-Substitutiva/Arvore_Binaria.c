#include <stdio.h>
#include <stdlib.h>

struct No {
    int chave;
    struct No* esquerda;
    struct No* direita;
};

struct No* novoNo(int chave) {
    struct No* no = (struct No*)malloc(sizeof(struct No));
    no->chave = chave;
    no->esquerda = NULL;
    no->direita = NULL;
    return no;
}

struct No* inserir(struct No* raiz, int chave) {
    if (raiz == NULL) {
        return novoNo(chave); // Se a raiz for nula, cria um novo nó com a chave e retorna como a nova raiz
    }

    if (chave < raiz->chave) {
        raiz->esquerda = inserir(raiz->esquerda, chave); // Se a chave for menor que a chave do nó atual, insere na subárvore esquerda
    } else if (chave > raiz->chave) {
        raiz->direita = inserir(raiz->direita, chave); // Se a chave for maior que a chave do nó atual, insere na subárvore direita
    }

    return raiz; // Retorna a raiz atualizada após a inserção
}

struct No* minValorNo(struct No* no) {
    struct No* atual = no;

    while (atual->esquerda != NULL) {
        atual = atual->esquerda;
    }

    return atual;
}

struct No* remover(struct No* raiz, int chave) {
    if (raiz == NULL) {
        return raiz; // Retorna a raiz se for nula, indicando que a chave não está presente na árvore
    }

    if (chave < raiz->chave) {
        raiz->esquerda = remover(raiz->esquerda, chave); // Chama recursivamente a função remover na subárvore esquerda
    } else if (chave > raiz->chave) {
        raiz->direita = remover(raiz->direita, chave); // Chama recursivamente a função remover na subárvore direita
    } else {
        if (raiz->esquerda == NULL) {
            struct No* temp = raiz->direita;
            free(raiz);
            return temp; // Se o nó a ser removido tiver apenas um filho (direita), retorna o filho direito como novo nó raiz
        } else if (raiz->direita == NULL) {
            struct No* temp = raiz->esquerda;
            free(raiz);
            return temp; // Se o nó a ser removido tiver apenas um filho (esquerda), retorna o filho esquerdo como novo nó raiz
        }

        struct No* temp = minValorNo(raiz->direita); // Encontra o nó com o menor valor na subárvore direita
        raiz->chave = temp->chave; // Substitui a chave do nó atual pela chave do nó encontrado
        raiz->direita = remover(raiz->direita, temp->chave); // Remove recursivamente o nó encontrado na subárvore direita
    }

    return raiz; // Retorna a raiz atualizada após a remoção
}

int buscar(struct No* raiz, int chave) {
    if (raiz == NULL) {
        return 0; // Retorna 0 se a raiz for nula, indicando que a chave não foi encontrada
    }

    if (raiz->chave == chave) {
        return 1; // Retorna 1 se a chave for igual à chave do nó atual, indicando que a chave foi encontrada
    } else if (chave < raiz->chave) {
        return buscar(raiz->esquerda, chave); // Chama recursivamente buscar na subárvore esquerda
    } else {
        return buscar(raiz->direita, chave); // Chama recursivamente buscar na subárvore direita
    }
}

int buscarInterativa(struct No* raiz, int chave) {
    while (raiz != NULL) {
        if (raiz->chave == chave) {
            return 1; // Encontrou a chave
        } else if (chave < raiz->chave) {
            raiz = raiz->esquerda; // Busca na subárvore esquerda
        } else {
            raiz = raiz->direita; // Busca na subárvore direita
        }
    }
    return 0; // Não encontrou a chave
}


// Exemplo de uso:
int main() {
    struct No* raiz = NULL;
    raiz = inserir(raiz, 10);
    inserir(raiz, 5);
    inserir(raiz, 15);
    inserir(raiz, 3);
    inserir(raiz, 7);

    printf("Busca 10: %d\n", buscar(raiz, 10)); // Saída: 1 (True)
    printf("Busca 12: %d\n", buscar(raiz, 12)); // Saída: 0 (False)

    raiz = remover(raiz, 10);
    printf("Busca 10 apos remocao: %d\n", buscar(raiz, 10)); // Saída: 0 (False)

    return 0;
}

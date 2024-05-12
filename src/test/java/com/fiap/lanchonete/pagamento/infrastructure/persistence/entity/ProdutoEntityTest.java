package com.fiap.lanchonete.pagamento.infrastructure.persistence.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.infrastructure.persistence.entity.ProdutoEntity;

class ProdutoEntityTest {

    @Test
    void testGetters() {
        String nome = "Hambúrguer";
        String descricao = "Delicioso hambúrguer";
        Categoria categoria = Categoria.Lanche;
        BigDecimal valor = BigDecimal.valueOf(10.0);

        ProdutoEntity produtoEntity = new ProdutoEntity(categoria, nome, descricao, valor);

        assertEquals(nome, produtoEntity.getNome());
        assertEquals(descricao, produtoEntity.getDescricao());
        assertEquals(categoria, produtoEntity.getCategoria());
        assertEquals(valor, produtoEntity.getValor());
    }
}

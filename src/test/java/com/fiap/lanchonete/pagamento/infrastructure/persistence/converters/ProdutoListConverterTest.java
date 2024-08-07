package com.fiap.lanchonete.pagamento.infrastructure.persistence.converters;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fiap.lanchonete.domain.entity.Categoria;
import com.fiap.lanchonete.domain.entity.Produto;
import com.fiap.lanchonete.infrastructure.persistence.converters.ProdutoListConverter;

class ProdutoListConverterTest {

    @Test
    void testConvertToDatabaseColumn() {
        ProdutoListConverter converter = new ProdutoListConverter();
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto(Categoria.Lanche, "Hambúrguer", "Delicioso hambúrguer", BigDecimal.valueOf(10.0)));
        produtos.add(new Produto(Categoria.Bebida, "Refrigerante", "Bebida refrescante", BigDecimal.valueOf(5.0)));

        String json = converter.convertToDatabaseColumn(produtos);

        assertNotNull(json);
        assertFalse(json.isEmpty());
    }

    @Test
    void testConvertToEntityAttribute() {
        ProdutoListConverter converter = new ProdutoListConverter();
        String json = "[{\"categoria\":\"Lanche\",\"nome\":\"Hambúrguer\",\"descricao\":\"Delicioso hambúrguer\",\"valor\":10.0}," +
                      "{\"categoria\":\"Bebida\",\"nome\":\"Refrigerante\",\"descricao\":\"Bebida refrescante\",\"valor\":5.0}]";

        List<Produto> produtos = converter.convertToEntityAttribute(json);

        assertNotNull(produtos);
        assertFalse(produtos.isEmpty());
        assertEquals(2, produtos.size());
        assertEquals(Categoria.Lanche, produtos.get(0).getCategoria());
        assertEquals("Hambúrguer", produtos.get(0).getNome());
        assertEquals("Delicioso hambúrguer", produtos.get(0).getDescricao());
        assertEquals(BigDecimal.valueOf(10.0), produtos.get(0).getValor());
        assertEquals(Categoria.Bebida, produtos.get(1).getCategoria());
        assertEquals("Refrigerante", produtos.get(1).getNome());
        assertEquals("Bebida refrescante", produtos.get(1).getDescricao());
        assertEquals(BigDecimal.valueOf(5.0), produtos.get(1).getValor());
    }
}

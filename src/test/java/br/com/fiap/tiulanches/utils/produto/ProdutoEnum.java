package br.com.fiap.tiulanches.utils.produto;

import java.math.BigDecimal;

public enum ProdutoEnum {
    ID_PRODUTO(10L),
    NOME("Teste"),    
    PRECO(BigDecimal.valueOf(11.20));

    private Object valor;

    ProdutoEnum(Object valor){
        this.valor = valor;
    }

    public Object getValor(){
        return valor;
    }
}

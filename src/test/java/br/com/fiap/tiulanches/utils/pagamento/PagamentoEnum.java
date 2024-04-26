package br.com.fiap.tiulanches.utils.pagamento;

public enum PagamentoEnum {
    ID_PAGAMENTO("d92f76b0-6613-4053-b69b-206d4c0cb7ff"),
    ID_PEDIDO(10L),
    ID_MERCADO_LIVRE("1322490319"),
    TICKET_URL("https://sandbox.mercadopago.com.br/checkout/v1/redirect?pref_id=37046661-5f39bfb0-462b-4d29-9ee4-5d9eef6675fd");

    private Object valor;

    PagamentoEnum(Object valor){
        this.valor = valor;
    }

    public Object getValor(){
        return valor;
    }
}

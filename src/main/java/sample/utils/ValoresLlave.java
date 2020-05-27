package sample.utils;

public class ValoresLlave implements ValoresLlaves {
    private final String key;
    private final String valor;

    public ValoresLlave(String key, String valor) {
        this.key = key;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }

    @Override
    public String getLlave() {
        return key;
    }
}

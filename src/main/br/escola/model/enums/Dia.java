package main.br.escola.model.enums;

public enum Dia {

    DOMINGO(1, "Domingo"),
    SEGUNDA(2, "Segunda-feira"),
    TERCA(3, "Terça-feira"),
    QUARTA(4, "Quarta-feira"),
    QUINTA(5, "Quinta-feira"),
    SEXTA(6, "Sexta-feira"),
    SABADO(7, "Sábado"),
    ;

    Dia(int id, String value) {
        this.id = id;
        this.value = value;
    }

    private int id;
    private String value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static Dia get(int id) {
        Dia res = null;
        for (Dia dia : Dia.values()) {
            if (dia.getId() == id) {
                res = dia;
                break;
            }
        }
        return res;
    }

}

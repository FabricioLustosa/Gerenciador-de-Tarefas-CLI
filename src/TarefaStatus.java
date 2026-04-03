public enum TarefaStatus {
    PENDENTE(0, "Pendente"),
    CONCLUIDA(1, "Concluída"),
    EM_ANDAMENTO(2, "Em Andamento"),
    CANCELADA(3, "Cancelada");

    private int codigo;
    private String descricao;

   TarefaStatus(int codigo, String descricao) {
        this.descricao = descricao;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    //Converter código do banco para Enum
    public static TarefaStatus fromCodigo(int codigo){
        for(TarefaStatus status : TarefaStatus.values()){
            if(status.codigo == codigo){
                return status;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }

    //Converter String para Enum
    public static TarefaStatus fromString(String descricao){
        for(TarefaStatus status : TarefaStatus.values()){
            if(status.descricao.equalsIgnoreCase(descricao)){
                return status;
            }
        }
        throw new IllegalArgumentException("Status inválido: " + descricao);
    }

}

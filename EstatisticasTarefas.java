public class EstatisticasTarefas {
    private int total;
    private int concluidas;
    private int pendentes;

    public EstatisticasTarefas(int total, int concluidas, int pendentes){
        this.total = total;
        this.concluidas = concluidas;
        this.pendentes = pendentes;
    }

    public int getTotal(){
        return total;
    }

    public int getConcluidas(){
        return concluidas;
    }

    public int getPendentes(){
        return pendentes;
    }

    public double getPercentualConclusao(){
        if (total == 0) return 0.0;
        return (double) concluidas / total * 100;
    }
}

package calculadora;

public class FuncionarioTerceirizado extends Funcionario {
    private double despesasAdicionais;

    public FuncionarioTerceirizado(String nome, int horasTrabalhadas, double valorHora, double despesasAdicionais) {
        super(nome, horasTrabalhadas, valorHora);
        this.despesasAdicionais = despesasAdicionais;
    }

    @Override
    public double calcularPagamento() {
        double pagamento = super.calcularPagamento() + (despesasAdicionais * 1.10);
        if (pagamento < 1320.00) {
            pagamento = 1320.00;
        }
        return pagamento;
    }

    // Getters e Setters
    public double getDespesasAdicionais() {
        return despesasAdicionais;
    }

    public void setDespesasAdicionais(double despesasAdicionais) {
        if (despesasAdicionais > 1000.00) {
            this.despesasAdicionais = 1000.00;
        } else {
            this.despesasAdicionais = despesasAdicionais;
        }
    }
}

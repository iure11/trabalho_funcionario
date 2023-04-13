package calculadora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {


     //  Esse teste verifica se um método da classe Funcionario retorna o valor esperado para um funcinario com X horas trabalhadas com o da hora em 132
	@Test
	public void testCalcularPagamentoFuncionarios() {
		Funcionario funcionario = new Funcionario("João", 40, 132.00);
		assertEquals(5280.00, funcionario.calcularPagamento(), 0.01);
	}

	// teste cria um funcionario com despesas adicionais acima do limite permitido
	@Test
	public void testCriarFuncionarioTerceirizadoDespesasAdicionaisExcedido() {
		assertThrows(IllegalArgumentException.class, () -> new FuncionarioTerceirizado("João", 40, 80.00, 1500.00));
	}

	// teste cria um funciario e verifica se o cálculo do pagamento é feito corretamente.
	@Test
	public void testCalcularPagamentoFuncionarioValorHoraPermitido() {
		double salarioMinimo = 1320.00;
		Funcionario funcionario = new Funcionario("João", 40, salarioMinimo * 0.05);
		assertEquals(2640.00, funcionario.calcularPagamento(), 0.01);
	}

	//  irá falhar pois o valor por hora trabalhada está abaixo do valor mínimo permitido (4% do salário mínimo).
	@Test
	public void testCalcularPagamentoFuncionarioValorHoraAbaixoPermitido() {
		double salarioMinimo = 1320.00;
		Funcionario funcionario = new Funcionario("Maria", 40, salarioMinimo * 0.03);
		assertEquals(salarioMinimo * 0.04 * 40, funcionario.calcularPagamento(), 0.01);
	}

	// verifica se o cálculo do pagamento é feito corretamente de acordo com as regras estabelecidas
	// valor da hora trabalhada dentro do intervalo permitido e bônus de 110% sobre as despesas adicionais
	@Test
	public void testCalcularPagamentoFuncionarioTerceirizado() {
		double salarioMinimo = 1320.00;
		double despesasAdicionais = 500.00;
		FuncionarioTerceirizado funcionarioTerceirizado = new FuncionarioTerceirizado("João", 40, salarioMinimo * 0.08, despesasAdicionais);
		assertEquals((salarioMinimo * 0.08 * 40) + (despesasAdicionais * 1.1), funcionarioTerceirizado.calcularPagamento(), 0.01);
	}

   // Esse teste cria um funcionário terceirizado com uma despesa adicional válida (R$ 500.00), e verifica se os valores dos atributos foram definidos corretamente.
	@Test
	public void testConstrutorFuncionarioTerceirizadoDespesasValidas() {
		double salarioMinimo = 1320.00;
		double despesasAdicionais = 500.00;
		FuncionarioTerceirizado funcionarioTerceirizado = new FuncionarioTerceirizado("João", 40, salarioMinimo * 0.05, despesasAdicionais);
		assertEquals("João", funcionarioTerceirizado.getNome());
		assertEquals(40, funcionarioTerceirizado.getHorasTrabalhadas());
		assertEquals(salarioMinimo * 0.05, funcionarioTerceirizado.getValorHora(), 0.001);
		assertEquals(despesasAdicionais, funcionarioTerceirizado.getDespesasAdicionais(), 0.001);
	}

	// Esse teste verifica se um método  da classe Funcionario está lançando uma exceção do tipo IllegalArgumentException
	@Test
	public void testarModificarValorPagamentoInvalido() {
		double salarioMinimo = 1320.00;
		Funcionario funcionario = new Funcionario("João", 35, salarioMinimo * 0.08);
		try {
			funcionario.setValorHora(salarioMinimo * 0.12);
			fail("Exceção esperada não foi lançada");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor da hora inválido", e.getMessage());
		}
	}

	// Testar o cálculo do pagamento de um funcionário com horas trabalhadas dentro do limite (até 40 horas) e valor da hora trabalhada acima do salário mínimo
	@Test
	public void testarCalculoPagamentoValorHoraAcimaSalarioMinimo() {
		double salarioMinimo = 1320.00;
		Funcionario funcionario = new Funcionario("João", 40, salarioMinimo * 0.1);
		double pagamentoEsperado = 40 * salarioMinimo * 0.1;
		double pagamentoRealizado = funcionario.calcularPagamento();
		assertEquals(pagamentoEsperado, pagamentoRealizado, 0.01);
	}



  // valor da hora é menor do que o permitido (4% do salário mínimo). O teste espera que essa exceção seja lançada, o que nao acontece
	@Test
	public void testarValorHoraInvalido() {
		double valorHora = 130.0;
		try {
			Funcionario funcionario = new Funcionario("João", 30, valorHora);
			fail("Deveria ter lançado uma exceção IllegalArgumentException");
		} catch (IllegalArgumentException e) {
		}
	}

	// Esse teste verifica se uma exceção IllegalArgumentException é lançada caso o nome passado para o construtor seja uma string vazia.
	@Test
	public void testarNomeInvalido() {
		String nome = "";
		double valorHora = 30.0;
		try {
			Funcionario funcionario = new Funcionario(nome, 30, valorHora);
			fail("Deveria ter lançado uma exceção IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertEquals("Nome inválido", e.getMessage());
		}
	}

	//teste simples para verificar se o método getNome
	@Test
	public void testarGetNome() {
		String nome = "Maria";
		Funcionario funcionario = new Funcionario(nome, 25, 150.0);
		assertEquals(nome, funcionario.getNome());
	}


}
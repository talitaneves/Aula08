
public class CalculoImpostoPF {

	public static void main(String[] args) {
		
		// Definindo variáveis
		double impostoRendaRetido = 0;
		double impostoPrevRetido = 0;
		double aliquotaPrev = 0;
		double salarioSemIR = 0;
		double salarioLiquido = 0;

		// Capturando os dados da tela
		String nome = "Antonio Coutinho";
		int tempoEmpresa = 10;
		double salarioBruto = 10000.00;
		
		
		// efetuando os calculos
		impostoRendaRetido = calcularImpostoRendaRetido(salarioBruto);
		salarioSemIR = calcularSalarioSemIr( salarioBruto, impostoRendaRetido );
		aliquotaPrev = calcularAliquotaPrev( salarioBruto, tempoEmpresa);
		impostoPrevRetido = calcularImpostoPrevRetido ( salarioBruto, aliquotaPrev);
		salarioLiquido = calcularSalarioFinal( salarioBruto, tempoEmpresa );
		
		// imprimindo
		imprimirTelaResultadoImposto(
				nome, 
				salarioBruto, 
				impostoRendaRetido,
				salarioSemIR,
				aliquotaPrev,
				impostoPrevRetido,
				salarioLiquido);
	}

	
	
	private static double calcularSalarioFinal(double sal, int anos) {
		double aliq = calcularAliquotaPrev(sal, anos);
		double valorDescontadoPrev = calcularImpostoPrevRetido(sal, aliq );
		double valorDescontadoIr = calcularImpostoRendaRetido(sal);
		double totalDesconto = valorDescontadoPrev + valorDescontadoIr;
		
		return sal - totalDesconto;
	}



	private static double calcularImpostoPrevRetido(double salarioBruto, double aliquotaPrev) {
		double total = salarioBruto * (aliquotaPrev / 100);
		if (total > 1000) {
			total = 1000;
		}
		return total;
	}



	private static double calcularAliquotaPrev(double salarioBruto, int tempoEmpresa) {
		
		double aliq = 0;
		if ( tempoEmpresa < 10 ) {
			if (salarioBruto < 1000 ) {
				aliq = 5;
			} else if ( salarioBruto >= 1000 && salarioBruto < 2000) {
				aliq = 7.5;
			} else if ( salarioBruto >= 2000 && salarioBruto < 3000) {
				aliq = 10;
			} else if ( salarioBruto >= 3000 && salarioBruto < 5000) {
				aliq = 15;
			} else {
				aliq = 20;
			}
		}
		
		return aliq;
		
	}



	private static double calcularSalarioSemIr(double salarioBruto, double impostoRendaRetido) {
		return salarioBruto - impostoRendaRetido;
	}


	
	private static double calcularImpostoRendaRetido(double salario) {
		double valorRetido = 0;
		
		double[] aliquotas = {0,7.5, 15, 22.5, 27.5};
		//double[] pisoFaixa = {0, 1903.99, 2826.66,  3751.06,  4664.68};
		double[] tetoFaixa = {0, 1903.98, 2826.65,  3751.05,  4664.67};
		//double[] tetoDescontoFaixa = {0, 69.20, 138.66, 205.56};
		double[] tetoDescontoFaixaTotalizado = {0, 0, 69.20, 207.86, 413.42};
		

		// Salario > 4664.67
		if ( salario >= tetoFaixa[4] ) {
			double resto = salario - tetoFaixa[4];
			double descontoResto = resto * ( aliquotas[4] / 100);
			valorRetido = tetoDescontoFaixaTotalizado[4] + descontoResto;
		} else if ( salario < tetoFaixa[4] && salario >= tetoFaixa[3] ) {
			double resto = salario - tetoFaixa[3];
			double descontoResto = resto * ( aliquotas[3] / 100);
			valorRetido = tetoDescontoFaixaTotalizado[3] + descontoResto;
		} else if ( salario < tetoFaixa[3] && salario >= tetoFaixa[2] ) {
			double resto = salario - tetoFaixa[2];
			double descontoResto = resto * ( aliquotas[2] / 100);
			valorRetido = tetoDescontoFaixaTotalizado[2] + descontoResto;
		} else if ( salario < tetoFaixa[2] && salario >= tetoFaixa[1] ) {
			double resto = salario - tetoFaixa[1];
			double descontoResto = resto * ( aliquotas[1] / 100);
			valorRetido = tetoDescontoFaixaTotalizado[1] + descontoResto;
		} else {
			// não é preciso pagar IR
			valorRetido = 0;
		}
		
		return valorRetido;
	}
	
	
	
	
	public static void imprimirTelaCapturaDados(String nome, int idade, int tempo, double salario) {
		System.out.println("******************************************************************");
		System.out.println("******************************************************************");
		System.out.println("**");
		System.out.println("**");
		System.out.println("**     	DADOS DO EMPREGADO");
		System.out.println("**");
		System.out.println("**");
		System.out.println("**	         NOME: " + nome);
		System.out.println("**	        IDADE: " + idade + " anos");
		System.out.println("**	TEMPO EMPRESA: " + tempo + " anos");
		System.out.println("**	SALÁRIO BRUTO: " + salario + " BRL");
		System.out.println("**");
		System.out.println("**");
		System.out.println("******************************************************************");
		System.out.println("******************************************************************");
	}
	
	public static void imprimirTelaResultadoImposto(String nome, double salarioBruto, double irRetido, double salarioSemIr, double aliqPrev, double descontoPrev, double salarioLiquido) {
		System.out.println("******************************************************************");
		System.out.println("******************************************************************");
		System.out.println("**");
		System.out.println("**     	RESULTADO");
		System.out.println("**");
		System.out.println("**	   FUNCIONÁRIO: " + nome);
		System.out.println("******************************************************************");
		System.out.println("******************************************************************");
		System.out.println("**");
		System.out.println("**   IMPOSTO DE RENDA");
		System.out.println("**");
		System.out.println("**	 SALÁRIO BRUTO: " + salarioBruto + " BRL");
		System.out.println("**	IMPOSTO RETIDO: " + irRetido + " BRL");
		System.out.println("**	   SALÁRIO LÍQ: " + salarioSemIr + " BRL");
		System.out.println("**");
		System.out.println("******************************************************************");
		System.out.println("******************************************************************");
		System.out.println("**");
		System.out.println("**   PREVIDÊNCIA");
		System.out.println("**");
		System.out.println("**	 	  ALIQUOTA: " + aliqPrev + " %");
		System.out.println("**	IMPOSTO RETIDO: " + descontoPrev + " BRL");
		System.out.println("**");
		System.out.println("******************************************************************");
		System.out.println("******************************************************************");
		System.out.println("**");
		System.out.println("**   SALÁRIO LIQUIDO: " + salarioLiquido + " BRL");
		System.out.println("**");
		System.out.println("******************************************************************");
		System.out.println("******************************************************************");
	}
	
}



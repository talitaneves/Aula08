public class Login {

	public static void main(String[] args) {
		
		String emailDigitado = "admin@fiap.com.br";
		String senhaDigitada = "123456";
		String tokenDigitado = "beatriz";
		
		boolean loginOK = validarLogin(emailDigitado, tokenDigitado, senhaDigitada);
		
		System.out.println(loginOK);
		
	}

	
	
	
	
	
	
	
	
	public static boolean validarLogin(String email, String token,  String senha) {
		boolean retorno = false;
		
		if ( email.equals("admin@fiap.com.br") && senha.equals("123456") && token.equals("beatriz") ) {
			retorno = true;
		}
		
		return retorno;
	}
	
	
	
	/*
	modificadorAcesso palavraChave tipoRetorno nomeFuncao( parametros ) {
	  processo
	  retorno
	}
	
	calcularIdadeMinimaVacinaCovid19
	
	*/
	
}

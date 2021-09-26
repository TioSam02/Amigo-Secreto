package br.ufpb.amigosecreto;

import java.util.List;

public class TestaSistemaAmigo {
	public static void main (String [] args) {
		SistemaAmigo sistema = new SistemaAmigo();

		try {
			sistema.cadastraAmigo("José", "jose@gmail.com");
			sistema.cadastraAmigo("Maria", "jose@gmail.com");
			sistema.configuraAmigoSecretoDe("jose@gmail.com", "maria@gmail.com");
			sistema.configuraAmigoSecretoDe("maria@gmail.com", "jose@gmail.com");
		} catch (AmigoInexistenteException e) {
			e.printStackTrace();
		} catch(AmigoJaExisteException a) {
			a.printStackTrace();
		}
		sistema.enviarMensagemParaAlguem("Oi, Jose", "maria@gmail.com", "jose@gmail.com", true);
		sistema.enviarMensagemParaTodos("Que bom estar nessa brincadeira", "maria@gmail.com", true);
		
		List<Mensagem> mensagensAnonimas = sistema.pesquisaMensagensAnonimas();
		for(Mensagem m: mensagensAnonimas) {
			System.out.println(m.getTextoCompletoAExibir());
		}
		try {
			String emailDoAmigoDeJose = sistema.pesquisaAmigoSecretoDe("jose@gmail.com");
			if(emailDoAmigoDeJose.equals("maria@gmail.com")) {
				System.out.println("OK");
			}
		} catch (AmigoInexistenteException | AmigoNaoSorteadoException e) {
			e.printStackTrace();
		}
		
	}
}

package br.ufpb.amigosecreto;

import java.util.ArrayList;
import java.util.List;

public class SistemaAmigo {
	
	private List<Mensagem> mensagens;
	private List<Amigo> amigos;
	private int maxMensagens;
	
	public SistemaAmigo() {
		this(1000);
	}
	
	public SistemaAmigo(int maxMensagens) {                                 // é necessario iniciar os atributos!!
		this.mensagens = new ArrayList<Mensagem>();
		this.amigos = new ArrayList <Amigo>();
		this.maxMensagens = maxMensagens;
	}
	public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException{
		Amigo amigo = new Amigo(nomeAmigo, emailAmigo);
		if(this.amigos.contains(amigo)) {                     // o contains irá comparar e ver se ja tem uma pessoa com o email que vc deseja cadastrar
			throw new AmigoJaExisteException("Já existe amigo com o email " + amigo.getEmail());
		}else {
			this.amigos.add(amigo);
		}
	}
	
	public List<Mensagem> pesquisaMensagensAnonimas(){ 
		List<Mensagem> mensagensAnonimas = new ArrayList<>();
		
		for(Mensagem m: this.mensagens) {
			if(m.ehAnonima()) {
				mensagensAnonimas.add(m);
			}
		}
		return mensagensAnonimas;
	}
	//Método que pesquisa as mensagens que sãopu anônimas e retorna uma lista com tais mensagens.
	
	
	public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException{
		for(Amigo a: this.amigos) {
			if(a.getEmail().equals(emailDaPessoa)) {
				a.setAmigoSorteado(emailAmigoSorteado);
				return;
			}
		}
		throw new AmigoInexistenteException("Email inexistente");
	}
	// Método que configura o amigo secreto sorteado para a pessoa cujo e-mail é “emailDaPessoa”, atribuindo-lhe seu
	// amigo secreto como sendo a pessoa de e-mail “emailAmigoSecreto”. Caso não exista pessoa cadastrada no sistema
	// com o e-mail “emailDaPessoa, deve ser lançada a exceção “AmigoInexistenteException”.
	
	
	public List<Mensagem> pesquisaTodasAsMensagens(){
		List<Mensagem> copiaMensagens = new ArrayList<>();
		copiaMensagens.addAll(this.mensagens);                     //irá copiar todas as msg de mensagens
		return copiaMensagens;
	}
	//Método que pesquisa todas as mensagens já enviadas.
	
	public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
		for(Amigo a: this.amigos) {
			if(a.getEmail().equals(emailDaPessoa)) {
				String emailAmigoSorteado = a.getEmailAmigoSorteado();
				if(emailAmigoSorteado == null) {
					throw new AmigoNaoSorteadoException("Ainda não foi sorteado o amigo secreto de "+ emailDaPessoa);
				} else {
					return emailAmigoSorteado;
				}
			}
		}
		throw new AmigoInexistenteException("Não existe pessoa no sistema com o email "+ emailDaPessoa);
	}
	// Método que pesquisa o e-mail do amigo secreto sorteado para a pessoa cujo e-mail é “emailDaPessoa”
	// Caso não exista pessoa cadastrada no sistema com o e-mail “emailDaPessoa”, deve ser lançada a exceção
	// “AmigoInexistenteException”. Caso exista a pespusoa com esse e-mail, mas o seu amigo secreto sorteado
	// ainda não tenha sido configurado (o e-mail do amigo sorteado é null), deve ser lançada a exceção
	// “AmigoNaoSorteadoException”.
	
	public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException{
		for(Amigo a: this.amigos) {
			if(a.getEmail().equals(emailAmigo)) {
				return a;
			}
		}
		throw new AmigoInexistenteException("");
	}
	public void enviarMensagemParaTodos(String texto, String emailRemetente, boolean ehAnonima){
		MensagemParaTodos mensagem = new MensagemParaTodos(texto, emailRemetente, ehAnonima);
		this.mensagens.add(mensagem);
	}
	public void enviarMensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean ehAnonima) {
		MensagemParaAlguem mensagem = new MensagemParaAlguem(texto, emailRemetente,emailDestinatario, ehAnonima);
		this.mensagens.add(mensagem);
	}
	
}

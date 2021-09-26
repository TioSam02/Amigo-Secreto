package br.ufpb.amigosecreto;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SistemaAmigoMap{
	
	private List<Mensagem> mensagens; 
	private Map<String, Amigo> mapAmigos;
	private int maxMensagens;
	
	public SistemaAmigoMap() {
		this(1000);
	}
	
	public SistemaAmigoMap(int maxMensagens) {
		this.mensagens = new ArrayList<>();
		this.mapAmigos = new HashMap<>();
		this.maxMensagens = maxMensagens;
	}
	
	public void cadastraAmigo(String nomeAmigo, String emailAmigo) throws AmigoJaExisteException {
		Amigo amigo = new Amigo(nomeAmigo, emailAmigo);
		if(this.mapAmigos.containsKey(amigo.getEmail())) {                     // o contains irá comparar e ver se ja tem uma pessoa com o email que vc deseja cadastrar
			throw new AmigoJaExisteException("Já existe amigo com o email " + amigo.getEmail());
		}else {
			this.mapAmigos.put(amigo.getEmail(),amigo);
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
	public List<Mensagem> pesquisaTodasAsMensagens(){
		List<Mensagem> todasMensagens = new ArrayList<>();
		
		for(Mensagem m: this.mensagens) {
			todasMensagens.add(m);
		}
		return todasMensagens;
	}
	
	public void configuraAmigoSecretoDe(String emailDaPessoa, String emailAmigoSorteado) throws AmigoInexistenteException{
		for(Amigo a: this.mapAmigos.values()) {
			if(a.getEmail().contains(emailDaPessoa)) {
				a.setAmigoSorteado(emailAmigoSorteado);
				return;
			}
		}
		throw new AmigoInexistenteException("Email inexistente");
	}
	public String pesquisaAmigoSecretoDe(String emailDaPessoa) throws AmigoInexistenteException, AmigoNaoSorteadoException{
		for(Amigo a: this.mapAmigos.values()) {
			if(a.getEmail().contains(emailDaPessoa)) {
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
	
	public Amigo pesquisaAmigo(String emailAmigo) throws AmigoInexistenteException {
		for(Amigo a: this.mapAmigos.values()) {
			if(a.getEmail().contains(emailAmigo)) {
				return a;
			}
			
		}
		throw new AmigoInexistenteException("Email inexistente");
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

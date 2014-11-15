package br.com.angeliski.controller;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;

@Controller
@ConversationScoped
public class HomeController implements Serializable {

	private static final long serialVersionUID = 943045823176068998L;
	private Result result;
	private Conversation conversation;
	private Integer resultado;
	private int total;

	/**
	 * @deprecated CDI eyes only
	 */
	protected HomeController() {
		this(null, null);
	}

	@Inject
	public HomeController(Result result, Conversation conversation) {
		super();
		this.result = result;
		this.conversation = conversation;
	}

	@Get
	public void index() {
	}

	@Get
	public void game() {
		if (conversation.isTransient()) {
			conversation.begin();
		}
		result.include("cid", conversation.getId());
		gerarPergunta();
	}

	private void gerarPergunta() {
		Random random = new Random();
		Integer primeiroValor = random.nextInt(100);
		Integer segundoValor = random.nextInt(100);

		resultado = primeiroValor * segundoValor;

		result.include("primeiro", primeiroValor);
		result.include("segundo", segundoValor);
	}

	@Post
	public void game(Integer resposta) {
		if (resultado.equals(resposta)) {
			total++;
		}
		gerarPergunta();
		result.include("cid", conversation.getId());
	}

	@Get
	public void fim() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
		result.include("total", total);
	}

}

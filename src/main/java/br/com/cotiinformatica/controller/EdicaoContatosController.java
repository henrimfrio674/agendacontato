package br.com.cotiinformatica.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinfoirmatica.repositories.ContatoRepository;
import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.entities.Usuario;

@Controller
public class EdicaoContatosController {
	@RequestMapping(value = "/admin/edicao-contatos")
	public ModelAndView edicaoContatos(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contatos");
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Integer idContato = Integer.parseInt(request.getParameter("id"));
			ContatoRepository contatoRepository = new ContatoRepository();
			Contato contato = contatoRepository.findById(idContato);
			if (contato != null && contato.getIdUsuario() == usuario.getIdUsuario()) {
				modelAndView.addObject("contato", contato);
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao obter o contato: " + e.getMessage());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/admin/atualizar-contato", method = RequestMethod.POST)
	public ModelAndView atualizarContato(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/edicao-contatos");
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
			Integer idContato = Integer.parseInt(request.getParameter("idContato"));
			ContatoRepository contatoRepository = new ContatoRepository();
			Contato contato = contatoRepository.findById(idContato);
			if (contato != null && contato.getIdUsuario() == usuario.getIdUsuario()) {
				contato.setNome(request.getParameter("nome"));
				contato.setTelefone(request.getParameter("telefone"));
				contato.setEmail(request.getParameter("email"));
				contato.setObservacoes(request.getParameter("observacoes"));
				contatoRepository.update(contato);
				modelAndView.addObject("mensagem_sucesso", "Contato atualizado com sucesso.");
				modelAndView.addObject("contato", contato);
			}
		} catch (Exception e) {
			modelAndView.addObject("mensagem_erro", "Falha ao atualizar contato: " + e.getMessage());
		}
		return modelAndView;
	}
}
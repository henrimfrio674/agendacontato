package br.com.cotiinformatica.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinfoirmatica.repositories.ContatoRepository;
import br.com.cotiinformatica.entities.Contato;
import br.com.cotiinformatica.entities.Usuario;

@Controller
public class ExclusaoContatosController {
    
    @RequestMapping(value = "/admin/exclusao-contatos")
    public ModelAndView exclusaoContatos(HttpServletRequest request) {
        // WEB-INF/views/admin/consulta-contatos.jsp
        ModelAndView modelAndView = new ModelAndView("admin/consulta-contatos");
        try {
            // Capturando o usu�rio autenticado no sistema (sess�o)
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            
            // Capturando a vari�vel 'id' enviada na URL do link
            Integer idContato = Integer.parseInt(request.getParameter("id"));
            
            // Consultando no banco de dados 1 contato atrav�s do ID
            ContatoRepository contatoRepository = new ContatoRepository();
            Contato contato = contatoRepository.findById(idContato);
            
            // Verificando se o contato foi encontrado e se � um contato do usu�rio autenticado
            if (contato != null && contato.getIdUsuario() == usuario.getIdUsuario()) {
                // Excluindo o contato
                contatoRepository.delete(contato);
                modelAndView.addObject("mensagem_sucesso", "Contato exclu�do com sucesso!");
            }
            
            // Fazendo uma nova consulta de contatos e retornando os dados para a p�gina
            List<Contato> contatos = contatoRepository.findByUsuario(usuario.getIdUsuario());
            modelAndView.addObject("contatos", contatos);
        } catch (Exception e) {
            modelAndView.addObject("mensagem_erro", "Falha ao excluir contato: " + e.getMessage());
        }
        return modelAndView;
    }
}
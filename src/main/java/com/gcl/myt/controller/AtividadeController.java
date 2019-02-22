package com.gcl.myt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcl.myt.model.Atividade;
import com.gcl.myt.service.AtividadeService;
import com.gcl.myt.service.ObjetivoService;

@Controller
public class AtividadeController {

	@Autowired
    private AtividadeService atividadeService;
	
	@Autowired
	private ObjetivoService objetivoService;


    @RequestMapping(value = "/atividades")
    public String index() {
        return "redirect:/atividades/1";
    }

    @RequestMapping(value = "/atividades/{pageNumber}", method = RequestMethod.GET)
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Atividade> page = atividadeService.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "atividades/list";

    }

    @RequestMapping("/atividades/add")
    public String add(Model model) {
    	
    	model.addAttribute("objetivos", objetivoService.getList(1));
        model.addAttribute("atividade", new Atividade());
        return "atividades/form";

    }

    @RequestMapping("/atividades/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

    	model.addAttribute("objetivos", objetivoService.getList(1));
        model.addAttribute("atividade", atividadeService.get(id));
        return "atividades/form";

    }

    @RequestMapping(value = "/atividades/save", method = RequestMethod.POST)
    public String save(Atividade atividade, final RedirectAttributes ra) {

    	atividadeService.save(atividade);
        ra.addFlashAttribute("successFlash", "Atividade foi salvo com sucesso.");
        return "redirect:/atividades";

    }

    @RequestMapping("/atividades/delete/{id}")
    public String delete(@PathVariable Long id) {

        atividadeService.delete(id);
        return "redirect:/atividades";

    }

}

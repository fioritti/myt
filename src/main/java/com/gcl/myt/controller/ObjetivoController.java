package com.gcl.myt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gcl.myt.model.Objetivo;
import com.gcl.myt.service.ObjetivoService;

@Controller
public class ObjetivoController {

	@Autowired
    private ObjetivoService service;


    @RequestMapping(value = "/objetivos")
    public String index() {
        return "redirect:/objetivos/1";
    }

    @RequestMapping(value = "/objetivos/{pageNumber}", method = RequestMethod.GET)
    public String list(@PathVariable Integer pageNumber, Model model) {
        Page<Objetivo> page = service.getList(pageNumber);

        int current = page.getNumber() + 1;
        int begin = Math.max(1, current - 5);
        int end = Math.min(begin + 10, page.getTotalPages());

        model.addAttribute("list", page);
        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);

        return "objetivos/list";

    }

    @RequestMapping("/objetivos/add")
    public String add(Model model) {

        model.addAttribute("objetivo", new Objetivo());
        return "objetivos/form";

    }

    @RequestMapping("/objetivos/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("objetivo", service.get(id));
        return "objetivos/form";

    }

    @RequestMapping(value = "/objetivos/save", method = RequestMethod.POST)
    public String save(Objetivo objetivo, final RedirectAttributes ra) {

    	service.save(objetivo);
        ra.addFlashAttribute("successFlash", "Objetivo foi salvo com sucesso.");
        return "redirect:/objetivos";

    }

    @RequestMapping("/objetivos/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);
        return "redirect:/objetivos";

    }

}

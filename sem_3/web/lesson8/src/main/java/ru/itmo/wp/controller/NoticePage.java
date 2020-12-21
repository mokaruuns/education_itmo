package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeForm;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NoticePage extends Page {

    private final NoticeService noticeService;

    public NoticePage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @GetMapping("/notice")
    public String noticeGet(Model model, HttpSession httpSession) {
        if (model.getAttribute("user") != null) {
            model.addAttribute("createForm", new NoticeForm());
            return "NoticePage";
        } else {
            putMessage(httpSession, "You aren't entered!");
            return "redirect:";
        }
    }

    @PostMapping("/notice")
    public String noticePost(@Valid @ModelAttribute("createForm") NoticeForm createForm,
                             BindingResult bindingResult,
                             HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NoticePage";
        }

        noticeService.create(createForm);
        putMessage(httpSession, "Congrats, you have done creating notice !");

        return "redirect:";
    }
}

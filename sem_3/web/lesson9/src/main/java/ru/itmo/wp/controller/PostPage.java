package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.security.AnyRole;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.PostService;
import ru.itmo.wp.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {
    private final PostService postService;

    public PostPage(PostService postService) {
        this.postService = postService;
    }

    @Guest
    @GetMapping("/post/{id}")
    public String loadPageUser(@PathVariable("id") String id, Model model) {
        try {
            Long longId = Long.parseLong(id);
            Post post = postService.findById(longId);
            if (post == null) {
                model.addAttribute("message", "No such post");
            } else {
                model.addAttribute("p", post);
                model.addAttribute("comment", new Comment());
            }
        } catch (NumberFormatException e) {
            model.addAttribute("message", "No such post");
        }
        return "PostPage";
    }

    @PostMapping("/post/{id}")
    public String writeCommentPost(@PathVariable("id") String id,
                                   @Valid @ModelAttribute("comment")Comment comment,
                                   BindingResult bindingResult,
                                   HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "redirect:/post/"+id;
        }
        postService.writeComment(getUser(httpSession), postService.findById(Long.parseLong(id)), comment);
        putMessage(httpSession, "You write new comment");
        return "redirect:/post/"+id;
    }
}

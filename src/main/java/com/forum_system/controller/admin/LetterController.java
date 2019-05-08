package com.forum_system.controller.admin;

import com.forum_system.entity.*;
import com.forum_system.service.LetterService;
import com.forum_system.service.UserService;
import com.forum_system.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class LetterController {
    private final LetterService letterService;
    private final UserService userService;

    public LetterController(LetterService letterService, UserService userService) {
        this.letterService = letterService;
        this.userService = userService;
    }

    @GetMapping("/admin/letter")
    public String themeList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<Letter> letterPage;
        String cardTitle;
        letterPage = letterService.findAllLetter(20, page);
        cardTitle = "全部私信";

        model.addAttribute("cardTitle", cardTitle);
        model.addAttribute("letters", letterPage);
        model.addAttribute("idNameMap", userService.getIdNameMap());

        return "admin/letter-manage";
    }

    @GetMapping("/admin/letter/edit/{id}")
    public String ResponseLetter(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("letter", letterService.findById(id));
        model.addAttribute("idNameMap", userService.getIdNameMap());
        model.addAttribute("title","回复私信");
        return "admin/letter-edit";
    }

    @PostMapping("/admin/letter")
    public String responseLetter(HttpServletResponse response, HttpSession session, Letter letter) {

        Admin admin = (Admin) session.getAttribute("admin");
        Letter letter1 = letterService.findById(letter.getId());
        letter.setRecipientId(admin.getId());
        letter.setSenderId(letter1.getSenderId());
        letter.setSendContent(letter1.getSendContent());
        letter.setSendTime(letter1.getSendTime());
        letterService.update(letter);

        CookieUtil.addMessage(response, "admin",
                new Message(Message.TYPE_SUCCESS, "回复成功"), "/admin");
        return "redirect:/admin/letter";
    }

    @GetMapping("/admin/letter/delete/{id}")
    public String deleteLetter(@PathVariable("id") Integer id) {
        letterService.delete(id);
        return "redirect:/admin/letter";
    }

}

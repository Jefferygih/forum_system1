package com.forum_system.controller.admin;

import com.forum_system.entity.Message;
import com.forum_system.entity.Page;
import com.forum_system.entity.Theme;
import com.forum_system.service.ThemeService;
import com.forum_system.service.UserService;
import com.forum_system.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
public class AnnounmentCtroller {
    private ThemeService themeService;
    private UserService userService;

    public AnnounmentCtroller(ThemeService themeService, UserService userService) {
        this.themeService = themeService;
        this.userService = userService;
    }

    @GetMapping("/admin/announment")
    public String themeList(Model model, @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<Theme> themePage;
        String cardTitle;
            themePage = themeService.findAnnounment(20, page);
            cardTitle = "全部公告";

        model.addAttribute("cardTitle", cardTitle);
        model.addAttribute("announments", themePage);
        model.addAttribute("idNameMap",userService.getIdNameMap());

        return "admin/announment-manage";
    }

    @GetMapping("/admin/announment/new")
    public String editCategory(Model model) {
        model.addAttribute("announment", new Theme());
        model.addAttribute("title", "新建公告");
        return "admin/announment-edit";
    }

    @GetMapping("/admin/announment/edit/{id}")
    public String editAnnounment(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("announment", themeService.findById(id));
        return "admin/announment-edit";
    }


    @GetMapping("/admin/announment/delete/{id}")
    public String deleteAnnounment(@PathVariable("id") Integer id) {
        themeService.delete(id);
        return "redirect:/admin/announment";
    }

    @PostMapping("/admin/announment")
    public String updateCategory(HttpServletResponse response, Theme theme) {
        if (theme.getId() != null) {
           Theme theme1= themeService.findById(theme.getId());
            theme.setCategoryId(1);
            theme.setPublishTime(theme1.getPublishTime());
            theme.setCreatorId(theme1.getCreatorId());
            theme.setResNumber(theme1.getResNumber());
            theme.setPhoto(theme1.getPhoto());
            theme.setStatus(theme1.getStatus());
            themeService.update(theme);
        } else {
            theme.setPublishTime(new Date());
            theme.setCategoryId(1);
            theme.setCreatorId(3);
            theme.setResNumber("0");
            themeService.insert(theme);
        }
        CookieUtil.addMessage(response, "admin",
                new Message(Message.TYPE_SUCCESS, "公告信息已经保存"), "/admin");
        return "redirect:/admin/announment";
    }
}

package com.forum_system.controller.admin;

import com.forum_system.entity.Page;
import com.forum_system.entity.Theme;
import com.forum_system.service.CategoryService;
import com.forum_system.service.ThemeService;
import com.forum_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThemeController {
    private final ThemeService themeService;
    private final CategoryService categoryService;
    private final UserService userService;
    public ThemeController(ThemeService themeService, CategoryService categoryService, UserService userService) {
        this.themeService = themeService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/admin/theme")
    public String themeList(Model model,
                             @RequestParam(value = "keyword", defaultValue = "") String keyword,
                             @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<Theme> themePage;
        String cardTitle;
        if (keyword.isEmpty()) {
            themePage = themeService.findAll(20, page);
            cardTitle = "全部帖子";
        } else {
            themePage = themeService.findByTitle(keyword,20,page);
            cardTitle = "搜索结果";
        }
        model.addAttribute("cardTitle", cardTitle);
        model.addAttribute("keyword", keyword);
        model.addAttribute("themes", themePage);
        model.addAttribute("idNameMap",userService.getIdNameMap());
        model.addAttribute("categoryMap", categoryService.getIdNameMap());

        return "admin/theme-manage";
    }


    @GetMapping("/admin/demand/edit/{id}")
    public String editDemand(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("theme", themeService.findById(id));
        model.addAttribute("categories", categoryService.findAll());
        return "admin/demand-edit";
    }

    @PostMapping("/admin/theme/edit")
    public String editTheme(Theme theme) {
        themeService.update(theme);
        return "redirect:/admin/theme";
    }

    @GetMapping("/admin/theme/delete/{id}")
    public String deleteTheme(@PathVariable("id") Integer id) {
        themeService.delete(id);
        return "redirect:/admin/theme";
    }

    @GetMapping("/admin/theme/view/{id}")
    public String ediBid(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("theme", themeService.findById(id));
        model.addAttribute("idNameMap",userService.getIdNameMap());
        model.addAttribute("categoryMap", categoryService.getIdNameMap());
        return "admin/theme-view";
    }
}

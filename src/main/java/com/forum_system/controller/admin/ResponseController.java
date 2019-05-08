package com.forum_system.controller.admin;

import com.forum_system.entity.Page;
import com.forum_system.entity.Response;
import com.forum_system.service.CategoryService;
import com.forum_system.service.ResponseService;
import com.forum_system.service.ThemeService;
import com.forum_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResponseController {
    private final ResponseService responseService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ThemeService themeService;
    public ResponseController(ResponseService responseService, CategoryService categoryService, UserService userService, ThemeService themeService) {
        this.responseService = responseService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.themeService = themeService;
    }

    @GetMapping("/admin/response")
    public String themeList(Model model,
                            @RequestParam(value = "keyword", defaultValue = "") String keyword,
                            @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<Response> responsePage;
        String cardTitle;
        if (keyword.isEmpty()) {
            responsePage = responseService.findAllAdmin(20, page);
            cardTitle = "全部帖子回复";
        } else {
            responsePage = responseService.findByContent(keyword,20,page);
            cardTitle = "搜索结果";
        }
        model.addAttribute("cardTitle", cardTitle);
        model.addAttribute("keyword", keyword);
        model.addAttribute("responses", responsePage);
        model.addAttribute("idNameMap",userService.getIdNameMap());
        model.addAttribute("themeIdTitleMap",themeService.getIdNameMap());

        return "admin/response-manage";
    }



    @GetMapping("/admin/response/delete/{id}")
    public String deleteResponse(@PathVariable("id") Integer id) {
        responseService.delete(id);
        return "redirect:/admin/response";
    }

    @GetMapping("/admin/response/view/{id}")
    public String ediBid(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("response", responseService.findByIdAdmin(id));
        model.addAttribute("idNameMap",userService.getIdNameMap());
        model.addAttribute("themeIdTitleMap",themeService.getIdNameMap());
        return "admin/response-view";
    }
}

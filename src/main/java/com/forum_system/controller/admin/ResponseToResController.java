package com.forum_system.controller.admin;

import com.forum_system.entity.Page;
import com.forum_system.entity.ResponseToResponse;
import com.forum_system.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResponseToResController {
    private final ResponseToResponseService responseToResponseService;
    private final ResponseService responseService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ThemeService themeService;
    public ResponseToResController(ResponseToResponseService responseToResponseService, ResponseService responseService, CategoryService categoryService, UserService userService, ThemeService themeService) {
        this.responseToResponseService = responseToResponseService;
        this.responseService = responseService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.themeService = themeService;
    }

    @GetMapping("/admin/responseToRes")
    public String responseToResponseList(Model model,
                            @RequestParam(value = "keyword", defaultValue = "") String keyword,
                            @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<ResponseToResponse> responseToResponsePage;
        String cardTitle;
        if (keyword.isEmpty()) {
            responseToResponsePage = responseToResponseService.findAllAdmin(20, page);
            cardTitle = "全部指定回复";
        } else {
            responseToResponsePage = responseToResponseService.findByContent(keyword,20,page);
            cardTitle = "搜索结果";
        }
        model.addAttribute("cardTitle", cardTitle);
        model.addAttribute("keyword", keyword);
        model.addAttribute("responseToRess", responseToResponsePage);
        model.addAttribute("idNameMap",userService.getIdNameMap());
        model.addAttribute("themeIdTitleMap",themeService.getIdNameMap());
        model.addAttribute("userIdResponseToresponseIdMap",responseToResponseService.getUserIdResponseToresponseIdMap());
        model.addAttribute("idResponderIdMap",responseService.getIdResponderIdMap());

        return "admin/responseToRes-manage";
    }



    @GetMapping("/admin/responseToRes/delete/{id}")
    public String deleteResponse(@PathVariable("id") Integer id) {
        responseToResponseService.delete(id);
        return "redirect:/admin/responseToRes";
    }

    @GetMapping("/admin/responseToRes/view/{id}")
    public String ediBid(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("responseToRes", responseToResponseService.findByIdAdmin(id));
        model.addAttribute("idNameMap",userService.getIdNameMap());
        model.addAttribute("themeIdTitleMap",themeService.getIdNameMap());
        model.addAttribute("userIdResponseToresponseIdMap",responseToResponseService.getUserIdResponseToresponseIdMap());
        model.addAttribute("idResponderIdMap",responseService.getIdResponderIdMap());
        return "admin/responseToRes-view";
    }
}

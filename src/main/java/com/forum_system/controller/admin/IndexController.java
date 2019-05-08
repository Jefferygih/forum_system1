package com.forum_system.controller.admin;

import com.forum_system.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {

    private final StatService statService;

    public IndexController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/admin/index")
    public String index(Model model) {

        model.addAttribute("userCount", statService.getUsersCount());
        model.addAttribute("themeCount", statService.getThemesCount());
        model.addAttribute("responseCount", statService.getResponsesCount());
        model.addAttribute("resToResCount", statService.getResponseToResponseCount());
        model.addAttribute("categoryCount",statService.getCategoryCount());

        long totalMemory = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        model.addAttribute("usedMemory", (totalMemory - freeMemory) / 1024 / 1024);
        model.addAttribute("freeMemory", freeMemory / 1024 / 1024);
        return "admin/admin-index";
    }
}

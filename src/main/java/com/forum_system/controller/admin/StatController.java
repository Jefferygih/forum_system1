package com.forum_system.controller.admin;

import com.forum_system.service.StatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatController {

    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }

    @GetMapping("/admin/stat/theme")
    public String demandStat(Model model) {
        model.addAttribute("total", statService.getThemesCount());
        model.addAttribute("pending", statService.getUsersCount());
        model.addAttribute("pass", statService.getResponseToResponseCount());
        model.addAttribute("contracted", statService.getResponsesCount());
        return "admin/stat-theme";
    }
}

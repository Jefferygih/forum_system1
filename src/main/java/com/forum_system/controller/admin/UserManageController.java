package com.forum_system.controller.admin;

import com.forum_system.entity.Page;
import com.forum_system.entity.User;
import com.forum_system.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserManageController {
    private final UserService userService;

    public UserManageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin/user")
    public String userList(Model model,
                           @RequestParam(value = "keyword", defaultValue = "") String keyword,
                           @RequestParam(value = "page", defaultValue = "1") int page) {
        Page<User> userPage;
        String cardTitle;
        if (keyword.isEmpty()) {
            userPage = userService.findAllUser(20, page);
            cardTitle = "全部用户";
        } else {
            userPage = userService.findByNameLikeAdmin(keyword, 20, page);
            cardTitle = "搜索结果";
        }
        model.addAttribute("keyword", keyword);
        model.addAttribute("cardTitle", cardTitle);
        model.addAttribute("users", userPage);
        return "admin/user-manage";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/edit/{id}")
    public String userEdit(HttpSession session, @PathVariable("id") Integer id)  {
        User user = userService.findById(id);
        session.removeAttribute("user");
        if(user.getStatus().equals("是")){
            user.setStatus("否");
            userService.update(user);

        }
        else {
            user.setStatus("是");
            userService.update(user);
        }

        return "redirect:/admin/user";
    }
}

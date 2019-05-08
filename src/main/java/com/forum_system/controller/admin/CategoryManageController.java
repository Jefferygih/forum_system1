package com.forum_system.controller.admin;
import com.forum_system.entity.Category;
import com.forum_system.entity.Message;
import com.forum_system.service.CategoryService;
import com.forum_system.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CategoryManageController {

    private final CategoryService categoryService;

    public CategoryManageController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/category")
    public String categoryList(Model model) {
        model.addAttribute("categoryList", categoryService.findAll());
        return "admin/category-manage";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String editCategory(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("category", categoryService.findById(id));
        model.addAttribute("title", "编辑分类");
        return "admin/category-edit";
    }

    @GetMapping("/admin/category/new")
    public String editCategory(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("title", "新建分类");
        return "admin/category-edit";
    }

    @PostMapping("/admin/category")
    public String updateCategory(HttpServletResponse response, Category category) {
        if (category.getId() != null) {
            categoryService.update(category);
        } else {
            categoryService.insert(category);
        }
        CookieUtil.addMessage(response, "admin",
                new Message(Message.TYPE_SUCCESS, "分类信息已经保存"), "/admin");
        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deleteCategory(HttpServletResponse response, @PathVariable("id") Integer id) {
        categoryService.delete(id);
        CookieUtil.addMessage(response, "admin",
                new Message(Message.TYPE_SUCCESS, "分类已经删除"), "/admin");
        return "redirect:/admin/category";
    }

}

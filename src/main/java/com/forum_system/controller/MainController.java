package com.forum_system.controller;

import com.forum_system.entity.*;
import com.forum_system.service.*;
import com.forum_system.util.CookieUtil;
import com.forum_system.util.FileUtil;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {
    @Value("${com.forum_system.upload.path}")
    private String uploadPath;

    private final ThemeService themeService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ResponseService responseService;
    private final ResponseToResponseService responseToResponseService;
    private final LetterService letterService;

    @Autowired
    public MainController(ThemeService themeService, CategoryService categoryService, UserService userService, ResponseService responseService, ResponseToResponseService responseToResponseService, LetterService letterService) {
        this.themeService = themeService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.responseService = responseService;
        this.responseToResponseService = responseToResponseService;
        this.letterService = letterService;
    }

    @GetMapping("/")
    public String indexPage(Model model, HttpServletRequest request, HttpSession session,
                            @RequestParam(value = "category", defaultValue = "0") int categoryId,
                            @RequestParam(value = "page", defaultValue = "1") int page) {
//        if (session.getAttribute("user") == null) {
//            String loginCookie = CookieUtil.getCookieValue("USR_LOGIN", request.getCookies());
//            if (!loginCookie.isEmpty()) {
//                return "redirect:/user/login";
//            }
//        }
        User user = userService.findById(1);
        model.addAttribute("user", user);
        model.addAttribute("announcements", themeService.findFirstAnnouncement());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categoryMap", categoryService.getIdNameMap());
        if (categoryId == 0) {
            model.addAttribute("themes", themeService.findAll(10, page));
        } else {
            model.addAttribute("themes", themeService.findByCategoryId(categoryId, 10, page));
        }
        model.addAttribute("isSearch", false);
        // model.addAttribute("announcements", announcementService.findAll(10, 1));
        model.addAttribute("title", "ICanFly BBS");
        model.addAttribute("keyword", "");
        model.addAttribute("idNameMap", userService.getIdNameMap());
        model.addAttribute("categoryIdColorMap", categoryService.getIdColorMap());
        model.addAttribute("categoryIdContentMap", categoryService.getIdContentMap());
        model.addAttribute("idPhotoMap", userService.getIdPhotoMap());
        return "index";
    }

    @GetMapping("/category/view")
    public String categoryDetail(Model model) {
        model.addAttribute("title", "ICanFly BBS");
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("categoryIdColorMap", categoryService.getIdColorMap());
        model.addAttribute("categoryIdContentMap", categoryService.getIdContentMap());
        return "categoryDetail";
    }

    @GetMapping("/response/delete/{id}")
    public String deleteResponse(@PathVariable("id") Integer id) {
        responseService.delete(id);
        return "redirect:/user/profile";
    }

    @GetMapping("/theme/delete/{id}")
    public String deleteTheme(@PathVariable("id") Integer id) {
        themeService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/theme/edit/{id}")
    public String editTheme(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("theme", themeService.findById(id));
        model.addAttribute("themeIdNameMap", themeService.getIdNameMap());
        model.addAttribute("title", "帖子修改");
        model.addAttribute("categories", categoryService.findAll1());
        return "theme-edit";
    }

    @PostMapping("/theme/edit")
    public String updateTheme(Theme theme) {
        Theme theme1 = themeService.findById(theme.getId());
        if (theme.getPhoto() == null) {
            theme.setPhoto(theme1.getPhoto());
        } else {
            FileUtil.deleteFile(uploadPath, theme1.getPhoto());
        }
        theme.setResNumber(theme1.getResNumber());
        theme.setGoods(theme1.getGoods());
        theme.setStatus(theme1.getStatus());
        theme.setCreatorId(theme1.getCreatorId());
        theme.setPublishTime(theme1.getPublishTime());
        themeService.update(theme);
        return "redirect:/theme/view/" + theme.getId();
    }

    @GetMapping("/response/edit/{id}")
    public String editResponse(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("response", responseService.findByIdAdmin(id));
        model.addAttribute("themeIdNameMap", themeService.getIdNameMap());
        model.addAttribute("title", "回复修改");
        return "response-edit";
    }

    @PostMapping("/response/edit")
    public String updateResponse(Response response) {
        Response response1 = responseService.findByIdAdmin(response.getId());
        response.setResponderId(response1.getResponderId());
        response.setRecoveryTime(response1.getRecoveryTime());
        response.setThemeId(response1.getThemeId());
        response.setGoods(response1.getGoods());
        responseService.update(response);
        return "redirect:/user/profile";
    }


    @GetMapping("/theme/view/{id}")
    public String themePage(Model model, @PathVariable("id") Integer id) throws NotFoundException {
        List<Response> responseList = responseService.findAll(id);
        List<ResponseToResponse> responseToResponses = responseToResponseService.findByThemeId(id);
        Response response = new Response();
        Theme theme = themeService.findById(id);
        if (theme == null) throw new NotFoundException("找不到请求的帖子");
        model.addAttribute("theme", theme);
        model.addAttribute("response", response);
        model.addAttribute("categoryMap", categoryService.getIdNameMap());
        model.addAttribute("idNameMap", userService.getIdNameMap());
        model.addAttribute("idPhotoMap", userService.getIdPhotoMap());
        model.addAttribute("themeIdPhotoMap", themeService.getThemeIdPhotoMap());
        model.addAttribute("responseIdPhotoMap", responseService.getResponseIdPhotoMap());
        model.addAttribute("responseToResIdPhotoMap", responseToResponseService.getResponseToResIdPhotoMap());
        model.addAttribute("IdGoodsMap", responseService.getResponseIdGoodsMap());
        model.addAttribute("themeIdGoodsMap", themeService.getThemeIdGoodsMap());
        model.addAttribute("responseToResIdGoodsMap", responseToResponseService.getResponseToResIdGoodsMap());
        model.addAttribute("idResponderIdMap", responseService.getIdResponderIdMap());
        model.addAttribute("categoryIdColorMap", categoryService.getIdColorMap());
        model.addAttribute("categoryIdContentMap", categoryService.getIdContentMap());
        model.addAttribute("userIdResponseToresponseIdMap", responseToResponseService.getUserIdResponseToresponseIdMap());
        model.addAttribute("responses", responseList);
        model.addAttribute("responseToresponses", responseToResponses);
        return "theme-view";
    }


    @GetMapping("/response/goodsLike/{id}")
    public void goodsLike(@PathVariable("id") Integer id) {
        int goods = responseService.findGoods(id);
        goods = goods + 1;
        responseService.updateGoods(id, goods);
    }

    @GetMapping("/response/goodsNotLike/{id}")
    public void goodsNotLike(@PathVariable("id") Integer id) {
        int goods = responseService.findGoods(id);
        goods = goods - 1;
        responseService.updateGoods(id, goods);
    }

    @GetMapping("/theme/goodsLike/{id}")
    public void themeGoodsLike(@PathVariable("id") Integer id) {
        int goods = themeService.findGoods(id);
        goods = goods + 1;
        themeService.updateGoods(id, goods);
    }

    @GetMapping("/theme/goodsNotLike/{id}")
    public void themeGoodsNotLike(@PathVariable("id") Integer id) {
        int goods = themeService.findGoods(id);
        goods = goods - 1;
        themeService.updateGoods(id, goods);
    }


    @GetMapping("/responseToRes/goodsLike/{id}")
    public void responseToResGoodsLike(@PathVariable("id") Integer id) {
        int goods = responseToResponseService.findGoods(id);
        goods = goods + 1;
        responseToResponseService.updateGoods(id, goods);
    }

    @GetMapping("/responseToRes/goodsNotLike/{id}")
    public void responseToResGoodsNotLike(@PathVariable("id") Integer id) {
        int goods = responseToResponseService.findGoods(id);
        goods = goods - 1;
        responseToResponseService.updateGoods(id, goods);
    }


    @GetMapping("/demand/search")
    public String searchDemand(Model model,
                               @RequestParam(value = "keyword", required = false) String keyword,
                               @RequestParam(value = "categoryId", required = false) Integer categoryId,
                               @RequestParam(value = "page", defaultValue = "1") int page) {
        User user = userService.findById(1);
        model.addAttribute("user", user);
        model.addAttribute("announcements", themeService.findFirstAnnouncement());
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categoryMap", categoryService.getIdNameMap());
        model.addAttribute("themes", themeService.findByTitle(keyword, 100, page));
        model.addAttribute("idNameMap", userService.getIdNameMap());
        model.addAttribute("idPhotoMap", userService.getIdPhotoMap());
        model.addAttribute("categoryIdColorMap", categoryService.getIdColorMap());
        model.addAttribute("categoryIdContentMap", categoryService.getIdContentMap());
        model.addAttribute("isSearch", true);
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        model.addAttribute("title", "搜索结果");
        return "index";
    }


    @GetMapping("/theme/new")
    public String newTheme(Model model) {
        model.addAttribute("theme", new Theme());
        model.addAttribute("categories", categoryService.findAll1());
        return "theme-new";
    }

    @PostMapping("/theme/new")
    public String newTheme(Theme theme, HttpSession session) {
        User user = (User) session.getAttribute("user");
        theme.setCreatorId(user.getId());
        theme.setPublishTime(new Date());
        theme.setResNumber("0");
        theme.setGoods(0);
        themeService.insert(theme);
        Theme theme1 = themeService.findIdByNameAndContent(theme.getTitle(), theme.getContent());
        return "redirect:/theme/view/" + theme1.getId();
    }

    @PostMapping("/response/new/{themeId}")
    public String newResponse(Response response, HttpSession session, @PathVariable(value = "themeId") Integer themeId) {
        User user = (User) session.getAttribute("user");
        response.setThemeId(themeId);
        response.setRecoveryTime(new Date());
        response.setResponderId(user.getId());
        responseService.insert(response);
        themeService.updateResNumber(responseService.findNumber(themeId), themeId);
        return "redirect:/theme/view/" + themeId;
    }

    @PostMapping("/responseToResponse/new/{id}/{themeId}")
    public String newResponseToResponse(ResponseToResponse responseToResponse, HttpSession session, @PathVariable(value = "id") Integer id, @PathVariable(value = "themeId") Integer themeId) {
        User user = (User) session.getAttribute("user");
        responseToResponse.setResponseId(id);
        responseToResponse.setGoods(0);
        responseToResponse.setRecoveryTime(new Date());
        responseToResponse.setUserId(user.getId());
        responseToResponseService.insert(responseToResponse);
        return "redirect:/theme/view/" + responseToResponse.getThemeId();
    }


    @PostMapping("/responseToResponse1/new/{responseToresponseId}/{themeId}")
    public String newResponseToResponse1(ResponseToResponse responseToResponse, HttpSession session, @PathVariable(value = "responseToresponseId") Integer responseToresponseId, @PathVariable(value = "themeId") Integer themeId) {
        User user = (User) session.getAttribute("user");
        System.out.println(themeId);
        responseToResponse.setResponseToresponseId(responseToresponseId);
        responseToResponse.setRecoveryTime(new Date());
        responseToResponse.setGoods(0);
        responseToResponse.setUserId(user.getId());
        responseToResponseService.insert(responseToResponse);
        return "redirect:/theme/view/" + responseToResponse.getThemeId();
    }

    @GetMapping("/letter/new")
    public String newLetter(Model model) {
        model.addAttribute("letter", new Letter());
        model.addAttribute("title", "私信给管理员");
        return "letter";
    }

    @PostMapping("/letter")
    public String newLetter(HttpServletResponse response, HttpSession session,Letter letter) {
        if (letter.getId() != null) {
            Admin admin = (Admin) session.getAttribute("admin");
            Letter letter1 = letterService.findById(letter.getId());
            letter.setRecipientId(admin.getId());
            letter.setSenderId(letter1.getSenderId());
            letter.setAdminContent(letter1.getAdminContent());
            letter.setSendContent(letter1.getSendContent());
            letter.setSendTime(letter1.getSendTime());
            letterService.update(letter);
        } else {
            User user= (User) session.getAttribute("user");
            letter.setSenderId(user.getId());
            letter.setSendTime(new Date());
            letterService.insert(letter);
        }
        CookieUtil.addMessage(response, "user",
                new Message(Message.TYPE_SUCCESS, "发送成功等待管理员回复，可在个人中心查看。"), "/");
        return "redirect:/";
    }


}

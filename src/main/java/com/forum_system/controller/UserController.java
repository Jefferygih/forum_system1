package com.forum_system.controller;

import com.forum_system.entity.Message;
import com.forum_system.entity.User;
import com.forum_system.service.*;
import com.forum_system.util.CookieUtil;
import com.forum_system.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Value("${com.forum_system.upload.path}")
    private String uploadPath;

    private final UserService userService;
    private final ThemeService themeService;
    private final CategoryService categoryService;
    private final MailService mailService;
    private final ResponseService responseService;
    private final LetterService letterService;

    @Autowired
    public UserController(UserService userService, ThemeService themeService, CategoryService categoryService, MailService mailService, ResponseService responseService, LetterService letterService) {
        this.userService = userService;
        this.themeService = themeService;
        this.categoryService = categoryService;
        this.mailService = mailService;
        this.responseService = responseService;
        this.letterService = letterService;
    }


    @GetMapping("/user/login")
    public String loginPage(Model model, HttpServletResponse response,
                            @RequestParam(value = "action", defaultValue = "login") String action,
                            @RequestParam(value = "from", defaultValue = "/") String from,
                            @RequestParam(value = "activateCode", defaultValue = "") String activateCode) {
        if (action.equals("activate") && !activateCode.isEmpty()) {
            if (userService.activate(activateCode)) {
                CookieUtil.addMessage(response, "user",
                        new Message(Message.TYPE_SUCCESS, "账户激活成功，现在您可以登录了"), "/");
            } else {
                CookieUtil.addMessage(response, "user",
                        new Message(Message.TYPE_DANGER, "账户激活失败，激活码无效"), "/");
            }
        }
        model.addAttribute("from", from);
        model.addAttribute("userLogin", new User());
        model.addAttribute("userRegister", new User());
        model.addAttribute("action", action);
        return "login";
    }


    @PostMapping("/user/login")
    public String login(Model model, User user, HttpServletResponse response, HttpSession session,
                        @RequestParam(value = "from", defaultValue = "/") String from,
                        @RequestParam(value = "remember", defaultValue = "false") boolean remember) {
        User userResult = userService.login(user);
        if (userResult != null) {
            if (!userResult.isActivated()) {
                CookieUtil.addMessage(response, "user",
                        new Message(Message.TYPE_WARNING, "账户尚未激活，请先激活"), "/");
            } else if (userResult.getTwoFactor() != null) {
                session.setAttribute("userTo2FA", userResult);
                session.setAttribute("remember", remember);
                if (from.equals("/user/2fa")) {
                    return "redirect:/";
                }
                return "redirect:/user/2fa?from=" + from;
            } else {
                session.setAttribute("user", userResult);
                if (remember) {
                    addCookie(response, userResult);
                }
                CookieUtil.addMessage(response, "user",
                        new Message(Message.TYPE_DEFAULT, "欢迎，" + userResult.getUsername()), "/");
                return "redirect:" + from;
            }
        } else {
            CookieUtil.addMessage(response, "user",
                    new Message(Message.TYPE_DANGER, "用户名或密码错误"), "/");
        }
        user.setPassword("");
        model.addAttribute("from", from);
        model.addAttribute("userLogin", user);
        model.addAttribute("userRegister", new User());
        return "login";
    }


    @GetMapping("/user/profile")
    public String userCenter(Model model, HttpSession session,
                             @RequestParam(value = "page", defaultValue = "") String page) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("page", page);
        model.addAttribute("user", user);
        model.addAttribute("themes",themeService.findByCreatorId(user.getId()));
        model.addAttribute("idNameMap", userService.getIdNameMap());
        model.addAttribute("themeIdNameMap",themeService.getIdNameMap());
        model.addAttribute("categoryIdColorMap", categoryService.getIdColorMap());
        model.addAttribute("idPhotoMap", userService.getIdPhotoMap());
        model.addAttribute("categoryMap", categoryService.getIdNameMap());
        model.addAttribute("responses",responseService.findByResponderId(user.getId()));
        model.addAttribute("letters",letterService.findBySenderId(user.getId()));
        model.addAttribute("resNumber",themeService.resNumberfindByCreatorId(user.getId()));
        return "usercenter";
    }

    @GetMapping("/user/profile1/{id}")
    public String userCenter1(Model model, @PathVariable("id") Integer id,
                             @RequestParam(value = "page", defaultValue = "") String page) {
        User user = userService.findById(id);
        model.addAttribute("page", page);
        model.addAttribute("user", user);
        model.addAttribute("themes",themeService.findByCreatorId(user.getId()));
        model.addAttribute("idNameMap", userService.getIdNameMap());
        model.addAttribute("themeIdNameMap",themeService.getIdNameMap());
        model.addAttribute("categoryIdColorMap", categoryService.getIdColorMap());
        model.addAttribute("idPhotoMap", userService.getIdPhotoMap());
        model.addAttribute("categoryMap", categoryService.getIdNameMap());
        model.addAttribute("responses",responseService.findByResponderId(user.getId()));
        return "usercenter1";
    }


    private void addCookie(HttpServletResponse response, User userResult) {
        Cookie cookie = new Cookie("USR_LOGIN", userResult.getCookie());
        cookie.setPath("/");
        cookie.setMaxAge(3600 * 24 * 7);
        response.addCookie(cookie);
    }


    @PostMapping("/user/register")
    public String register(Model model, HttpServletResponse response, User user,
                           @RequestParam("confirmPassword") String confirmPassword) throws MessagingException {
        if (!userService.usernameExists(user.getUsername())) {
            if (confirmPassword.equals(user.getPassword())) {
                userService.register(user);
                mailService.sendActivateEmail(user.getEmail(), user.getUsername(), user.getActivateCode());
                model.addAttribute("result", "ok");
                CookieUtil.addMessage(response, "user",
                        new Message(Message.TYPE_SUCCESS, "注册成功，请查收账户激活邮件"), "/");
                user = new User();
            } else {
                CookieUtil.addMessage(response, "user",
                        new Message(Message.TYPE_WARNING, "密码和确认密码不匹配"), "/");
            }
        } else {
            CookieUtil.addMessage(response, "user",
                    new Message(Message.TYPE_WARNING, "用户名已被占用"), "/");
        }
        user.setPassword(null);
        model.addAttribute("userRegister", user);
        model.addAttribute("userLogin", new User());
        model.addAttribute("action", "register");
        return "login";
    }


    @PostMapping("/user/profile/updateDev")
    public String updateDevProfile(User user, Model model, @RequestParam(value = "category", defaultValue = "0") int categoryId,
                                   @RequestParam(value = "page", defaultValue = "1") int page) {
        user.setId(3);
        User current = userService.findById(3);
        FileUtil.deleteFile(uploadPath, current.getPhoto());
        user.setUsername(current.getUsername());
        user.setEmail(current.getEmail());
        userService.update(user);

        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categoryMap", categoryService.getIdNameMap());
        if (categoryId == 0) {
            model.addAttribute("themes", themeService.findAll(10, 1));
        } else {
            model.addAttribute("themes", themeService.findByCategoryId(categoryId, 10, 1));
        }
        model.addAttribute("isSearch", false);
        // model.addAttribute("announcements", announcementService.findAll(10, 1));
        model.addAttribute("title", "ICanFly BBS");
        model.addAttribute("keyword", "");
        model.addAttribute("idNameMap", userService.getIdNameMap());
        model.addAttribute("idPhotoMap", userService.getIdPhotoMap());
        return "index";
    }


    @GetMapping("/user/logout")
    public String logout(HttpServletResponse response, HttpSession session) {
        Cookie cookie = new Cookie("USR_LOGIN", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        session.removeAttribute("user");
        session.removeAttribute("userTo2FA");
        CookieUtil.addMessage(response, "user",
                new Message(Message.TYPE_INFO, "已经退出登录"), "/");
        return "redirect:/";
    }

    @GetMapping("/image")
    public String userCenter(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

}

package com.mysite.sbb.lyUser;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/lawyer")
public class LawyerController {

    private final LawyerService lawyerService;

    @GetMapping("/signup")
    public String signup(LawyerCreateForm lawyerCreateForm) {
        return "lawyer_signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid LawyerCreateForm lawyerCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "lawyer_signup_form";
        }

        if (!lawyerCreateForm.getPassword1().equals(lawyerCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "lawyer_signup_form";
        }

        try {
            lawyerService.create(lawyerCreateForm.getUsername(),
                    lawyerCreateForm.getEmail(), lawyerCreateForm.getPassword1());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "lawyer_signup_form";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "lawyer_signup_form";
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "lawyer_login_form";
    }

    @GetMapping("/l_page")
    public String lPage() {
        return "lawyer_page";
    }
}
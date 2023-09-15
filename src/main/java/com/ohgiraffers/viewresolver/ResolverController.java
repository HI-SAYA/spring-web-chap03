package com.ohgiraffers.viewresolver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ResolverController {

    /*문자열 뷰 반환이 ModelAndView보다 좀 더 보편적이다.*/
    @GetMapping("/string")
    public String stringReturning(Model model) {
        // Model : View에서 표현 되어야 하는 동적인 데이터를 담는 용도로 사용하는 객체
        model.addAttribute("forwardMessage", "문자열로 뷰 이름 반환함...");
        // String 타입으로 리턴할 경우 논리적인 뷰 이름을 리턴한다.
        // ViewResolver가 prefix/suffix를 합쳐서 물리적 뷰를 선택한다.
        return "result";
    }

    @GetMapping("/string-redirect")
    public String stringRedirect() {
        return "redirect:/";
    }

    @GetMapping("/string-redirect-attr")
    public String stringRedirectFlashAttribute(RedirectAttributes rttr) {
        // 저장하고 싶은 속성 값이 있을 때 - Attributes
        rttr.addFlashAttribute("flashMessage1", "리다이렉트 attr 사용하여 redirect...");
        return "redirect:/";
    }


    /* ModelAndView로 다시 작성 */

    @GetMapping("/modelandview")
    public ModelAndView modelAndViewReturning(ModelAndView mv) {

        // Model 객체에 attribute 저장
        mv.addObject("forwardMessage", "ModelAndView를 이용한 모델과 뷰 반환");
        // View 객체에 논리적 뷰 이름 설정
        mv.setViewName("result");

        return mv;
    }


    @GetMapping("/modelandview-redirect")
    public ModelAndView modelAndViewRedirect(ModelAndView mv) {

        // Redirect 시에도 동일하게 view name 설정에 작성
        mv.setViewName("redirect:/");
        return mv;
    }

    @GetMapping("/modelandview-redirect-attr")
    public ModelAndView modelAndViewRedirectFlashAttribute(ModelAndView mv, RedirectAttributes rttr) {

        mv.setViewName("redirect:/");
        rttr.addFlashAttribute("flashMessage2", "ModelAndView로 리다이렉트 attr 사용하여 redirect...");
        return mv;
    }

}

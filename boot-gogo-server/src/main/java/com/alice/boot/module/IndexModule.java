package com.alice.boot.module;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * @author Aozaki on 2018/11/20.
 * @version 1.0
 * @since 1.0
 */
@Controller
public class IndexModule {
    @GetMapping("index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("index.html")
    public RedirectView indexHtml() {
        return new RedirectView("index");
    }
}

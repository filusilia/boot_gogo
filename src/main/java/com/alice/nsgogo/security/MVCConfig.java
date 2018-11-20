package com.alice.nsgogo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author alice on 2018/3/27 0027.
 * @version 1.0
 * @since 1.0
 */
//@Configuration
//public class MVCConfig implements WebMvcConfigurer {
//    /**
//     * Configure simple automated controllers pre-configured with the response
//     * status code and/or a view to render the response body. This is useful in
//     * cases where there is no need for custom controller logic -- e.g. render a
//     * home page, perform simple site URL redirects, return a 404 status with
//     * HTML content, a 204 with no content, and more.
//     *
//     * @param registry
//     */
////    @Override
////    public void addViewControllers(ViewControllerRegistry registry) {
////        registry.addViewController("/assets/**").setViewName("login");
////        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
////    }
//
//
//    /**
//     * Add handlers to serve static resources such as images, js, and, css
//     * files from specific locations under web application root, the classpath,
//     * and others.
//     *
//     * @param registry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");
//    }
//}

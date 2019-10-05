package com.huben.blog.web;

import com.huben.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author koishi
 */
@Controller
public class ArchiveShowController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archives", blogService.archiveBlog());
        model.addAttribute("count", blogService.countBlog());

        return "archives";
    }
}

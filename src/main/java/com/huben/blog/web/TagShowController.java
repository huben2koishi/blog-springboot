package com.huben.blog.web;

import com.huben.blog.entity.Tag;
import com.huben.blog.service.BlogService;
import com.huben.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author koishi
 */
@Controller
public class TagShowController {
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags")
    public String tags() {
        return "forward:/tags/0";
    }

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id,
                        @PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        List<Tag> tagList = tagService.listTagTop(10000);

        if (id == 0) {
            id = tagList.get(0).getId();
        }

        model.addAttribute("tagList", tagList);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}

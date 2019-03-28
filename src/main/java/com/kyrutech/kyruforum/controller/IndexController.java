package com.kyrutech.kyruforum.controller;

import com.kyrutech.kyruforum.entities.Discussion;
import com.kyrutech.kyruforum.entities.Post;
import com.kyrutech.kyruforum.entities.Section;
import com.kyrutech.kyruforum.entities.User;
import com.kyrutech.kyruforum.repository.DiscussionRepository;
import com.kyrutech.kyruforum.repository.PostRepository;
import com.kyrutech.kyruforum.repository.SectionRepository;
import com.kyrutech.kyruforum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    SectionRepository sections;

    @Autowired
    DiscussionRepository discussions;

    @Autowired
    PostRepository posts;

    @Autowired
    UserRepository users;


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("sections", sections.findByParentIsNull());
        return "index";
    }

    @GetMapping("/section")
    public String section(Integer id, Model model) {

        Section section = sections.findById(id).orElse(null);
        List<Discussion> discussionList = discussions.findBySection(section);
        for(Discussion d : discussionList) {
            d.setPostCount(posts.countByDiscussion(d));
        }

        model.addAttribute("section", section);
        model.addAttribute("discussions", discussions.findBySection(section));

        return "section";
    }

    @GetMapping("/discussion")
    public String discussion(Integer id, Integer page, Model model) {

        Discussion disc = discussions.findById(id).orElse(null);
        Page<Post> postPage = null;
        if(page != null) {
            postPage = posts.findByDiscussion(disc, PageRequest.of(page-1, 20, Sort.by("timestamp")));
        } else {
            postPage = posts.findByDiscussion(disc, PageRequest.of(0, 20, Sort.by("timestamp")));
        }

        model.addAttribute("discussion", disc);
        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("pages", postPage.getTotalPages());
        model.addAttribute("currentPage", postPage.getNumber()+1);

        return "discussion";
    }

    @PostMapping("/addPost")
    public String addPost(Integer discussionId, String content, Integer currentPage, Principal principal) {
        Discussion discussion = discussions.findById(discussionId).orElse(null);
        User user = users.findByUsername(principal.getName());

        Post post = new Post();
        post.setDiscussion(discussion);
        post.setContent(content);
        post.setUser(user);
        post.setTimestamp(LocalDateTime.now());
        posts.save(post);

        return "redirect:/discussion?id=" + discussionId + "&page=" + currentPage;
    }

    @GetMapping("/createThread")
    public String createThreadGet(Integer sectionId, Model model) {
        model.addAttribute("section", sections.findById(sectionId).orElse(null));

        return "createThread";
    }

    @PostMapping("/createThread")
    public String createThread(Integer sectionId, String title, String content, Principal principal) {
        Section section = sections.findById(sectionId).orElse(null);
        User user = users.findByUsername(principal.getName());

        Discussion discussion = new Discussion();
        discussion.setSection(section);
        discussion.setTitle(title);
        discussion.setUser(user);
        discussion = discussions.save(discussion);

        Post post = new Post();
        post.setDiscussion(discussion);
        post.setContent(content);
        post.setUser(user);
        post.setTimestamp(LocalDateTime.now());
        post = posts.save(post);

        return "redirect:/discussion?id=" + discussion.getId();
    }
}

package com.example.demo.controller;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.dto.SearchCriteriaDto;
import com.example.demo.service.SearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SearchController {
    /** サービスクラス */
    private final SearchService searchService;

    @GetMapping("/search")
    private String getSearchForm(Model model) {
        model.addAttribute("criteria", new SearchCriteriaDto());
        model.addAttribute("results", new ArrayList<courseDto>());
        return "search";
    }

    @PostMapping("/search")
    private String search(@Valid @ModelAttribute SearchCriteriaDto criteria, BindingResult result) {
        return "redirect:/search";
    }
}

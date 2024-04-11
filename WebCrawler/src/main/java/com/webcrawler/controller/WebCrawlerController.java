package com.webcrawler.controller;

import com.webcrawler.model.URL;
import com.webcrawler.service.WebCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebCrawlerController {

    @Autowired
    private WebCrawlerService webCrawlerService;

    //access point to rest api
    //input - an entry point for a website, url
    @PostMapping("/crawl")
    public void crawlWebsite(@RequestBody URL url){
        webCrawlerService.crawlWebsite(url.getUrl());
    }
}

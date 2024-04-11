package com.webcrawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class WebCrawlerService {


    private String crawlFolderPath = "crawl/";

    @Autowired
    private TrieService node;

    public void crawlWebsite(String url) {
        try {
            downloadPage(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //
    private void downloadPage(String url) throws IOException {
        if (!node.isVisited(url) && !url.isEmpty()) {
            Document doc = Jsoup.connect(url).get();
            savePage(doc);
            node.markEndOfURL(url);

            Elements links = doc.select("a[href]");
            for (Element link : links) {
                String nextUrl = link.absUrl("href");
                if (!node.isVisited(nextUrl) ) {
                    downloadPage(nextUrl);
                }
            }
        }
    }



    private void savePage(Document doc) throws IOException {
        String fileName = URLEncoder.encode(doc.location(), StandardCharsets.UTF_8);
        File file = new File(crawlFolderPath + fileName);
        Files.createDirectories(Paths.get(crawlFolderPath));
        Files.writeString(file.toPath(), doc.outerHtml());
    }


}


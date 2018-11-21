package com.alice.nsgogo.reptile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Aozaki on 2018/11/20.
 * @version 1.0
 * @since 1.0
 */
public class ReptileGo {
    private final static Logger log = LoggerFactory.getLogger("nsgogo");

    public static void main(String[] args) {
//        https://api.51xhn.cn/simple/ps4dump/list?type=3
        Document doc = null;
        try {
            Map<String, String> heads = new HashMap<>();
            heads.put("Content-Type", "application/json;charset=UTF-8");
//            doc = Jsoup
//                    .connect("https://api.51xhn.cn/simple/ps4dump/data?pageNum=1&pageSize=15&search=&search_id=&search_order=1&search_type=3&_=1542707674746")
//                    .ignoreContentType(true)
//                    .header("Accept", "*/*")
////                    .header("Accept", "text/html, application/xhtml+xml, */*")
//                    .header("Content-Type", "application/json;charset=UTF-8")
//                    .userAgent("Mozilla")
//                    .get();

            Connection.Response response = Jsoup
//                    .connect("https://api.51xhn.cn/simple/ps4dump/data?pageNum=1&pageSize=1000&search=&search_id=&search_order=1&search_type=3&_=1542707674746")
                    .connect("https://switchbrew.org/wiki/Title_list/Games")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                    .header("accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("accept-encoding","gzip, deflate, br")
                    .header("accept-language","zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
//                    .header("Accept", "*/*")
//                    .header("Accept", "text/html, application/xhtml+xml, */*")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .userAgent("Mozilla")
                    .execute();
            log.info(response.body());
            Gson gson = new Gson();

//            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
//            log.info(jsonObject.get("rows").getAsJsonArray().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.info(doc.outerHtml());
//        log.info(doc.title());
//        Elements newsHeadlines = doc.select("text-align: center; vertical-align: middle;");
//        for (Element headline : newsHeadlines) {
//            log.info("%s\n\t%s", headline.attr("title"), headline.absUrl("href"));
//        }
    }
}

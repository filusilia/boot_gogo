package com.alice.nsgogo.reptile;

import com.alice.nsgogo.entity.NsGameDetail;
import com.google.gson.Gson;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Aozaki on 2018/11/20.
 * @version 1.0
 * @since 1.0
 */
public class ReptileGo {
    private final static Logger log = LoggerFactory.getLogger("nsgogo");

    public static void main(String[] args) {
        getExcel();
        List<NsGameDetail> reptileWikiList = reptileWiki();

//        log.info(list.toString());
    }

    /**
     * wiki网络抓取游戏列表
     *
     * @return
     */
    private static List<NsGameDetail> reptileWiki() {
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
            Connection.Response response;
//            response = Jsoup
            doc = Jsoup
//                    .connect("https://api.51xhn.cn/simple/ps4dump/data?pageNum=1&pageSize=1000&search=&search_id=&search_order=1&search_type=3&_=1542707674746")
                    .connect("https://switchbrew.org/wiki/Title_list/Games")
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("accept-encoding", "gzip, deflate, br")
                    .header("accept-language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7")
//                    .header("Accept", "*/*")
//                    .header("Accept", "text/html, application/xhtml+xml, */*")
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .userAgent("Mozilla")
                    .get();
//            log.info(response.body());
            Gson gson = new Gson();

//            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
//            log.info(jsonObject.get("rows").getAsJsonArray().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//        log.info(doc.outerHtml());
        log.info(doc.title());
        Elements newsHeadlines = doc.select("table.wikitable");
        List<NsGameDetail> list = new ArrayList<>();
        for (Element headline : newsHeadlines) {
//            log.info("{}\n{}", headline.attr("tr"), headline.toString());
            Elements tr = headline.select("tr");
            for (Element trbody : tr) {
//                log.info("tr :{}", trbody.toString());
                Elements td = trbody.select("td");
                NsGameDetail detail = new NsGameDetail();
                detail.setTitleId(td.eq(0).text());
                detail.setGameName(td.eq(1).text());
                detail.setRegion(td.eq(2).text());
                detail.setFirmware(td.eq(3).text());
                String cart = td.eq(4).text();
                cart.replace("Digital", "数字版").replace("Cartridge", "卡带");
                detail.setDistributionMethod(cart);
                detail.setTitleVersion(td.eq(5).text());
                detail.setCartridgeDescription(td.eq(6).text());
                list.add(detail);
            }
        }
        return list;
    }

    private static void getExcel() {
        try {
//            POIFSFileSystem fileSystem = new POIFSFileSystem(new File("d:/TitleID（1050Done by 火焰心情）.xlsx"));
//            HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

package com.ddzj.proxyip;

import com.ddzj.entity.IpVo;
import com.ddzj.utils.JacksonUtils;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class KuaidailiMain extends AbstractIp{
    private static Logger logger = LoggerFactory.getLogger(KuaidailiMain.class);

    public static void main(String[] args) {
        List<IpVo> ipVoList = getData();
        // 代理访问慢
        logger.debug(JacksonUtils.object2Json(ipVoList));
    }

    public static List<IpVo> getData(){
        List<IpVo> ipVoList = Lists.newArrayList();
        String url = "https://www.kuaidaili.com/free/inha/1/";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("table.table-striped tbody tr");

            for(Element element : elements){
                Elements tdElements = element.select("td");
                IpVo ipVo = new IpVo(IpVo.HTTP, tdElements.get(0).text(), tdElements.get(1).text());
                ipVoList.add(ipVo);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       return ipVoList;
    }
}

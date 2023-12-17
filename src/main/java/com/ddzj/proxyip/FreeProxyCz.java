package com.ddzj.proxyip;

import com.ddzj.entity.IpVo;
import com.ddzj.utils.JacksonUtils;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

public class FreeProxyCz extends AbstractIp {
    public static void main(String[] args) {
        FreeProxyCz spysOne = new FreeProxyCz();
       spysOne.testMain();


    }

    private  String getIp(Element element){
        String html = element.outerHtml();
        int startIndexOf = html.indexOf("Base64.decode(");
        int endIndexOf = html.indexOf("))</script>");
        String code = html.substring(startIndexOf+15, endIndexOf-1);
        String decodeStr = new String(Base64.getDecoder().decode(code.getBytes(StandardCharsets.UTF_8)));
        System.out.println(decodeStr);
        return decodeStr;
    }

    public void testMain() {
        List<IpVo> ipVoList = getData();
        // 代理访问慢
        logger.debug(JacksonUtils.object2Json(ipVoList));
    }

    public List<IpVo> getData() {
        List<IpVo> ipVoList = Lists.newArrayList();
        String url = "http://free-proxy.cz/en/";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("#proxy_list tbody tr");
            for (Element element : elements) {
                Elements elementTds = element.select("td");
                if (elementTds.size() > 2) {
                    if (elementTds.get(2).text().indexOf(IpVo.HTTPS.toUpperCase()) != -1) {
                        ipVoList.add(new IpVo(IpVo.HTTPS, getIp(elementTds.get(0)), elementTds.get(1).text()));
                    } else if (elementTds.get(2).text().indexOf(IpVo.HTTP.toUpperCase()) != -1) {
                        ipVoList.add(new IpVo(IpVo.HTTP, getIp(elementTds.get(0)), elementTds.get(1).text()));
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ipVoList;
    }

}

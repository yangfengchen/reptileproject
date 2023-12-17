package com.ddzj.proxyip;

import com.ddzj.entity.IpVo;
import com.ddzj.utils.JacksonUtils;
import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.internal.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SpysOne extends AbstractIp{

    public static void main(String[] args) {
        SpysOne spysOne = new SpysOne();
        spysOne.testMain();
    }

    public void testMain(){
        List<IpVo> ipVoList = getData();
        // 代理访问慢
        logger.debug(JacksonUtils.object2Json(ipVoList));
    }

    public  List<IpVo> getData(){
        List<IpVo> ipVoList = Lists.newArrayList();
        String url = "https://spys.one/";
        try {
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("table");
            List<Integer> index = Arrays.asList(1,2);
            Element elementsTable = getElements(index, elements);
            Elements elementsTrs = elementsTable.select("tr");
            int size = elementsTrs.size()-1;
            int i = 0;
            for(Element elementTr : elementsTrs){
                if(i == 0 || i == 1 || i == size){
                    i++;
                    continue;
                }
                i++;
                logger.debug("{}", elementTr.text());
                Elements elementsTd = elementTr.select("td");
                if(elementsTd.get(4).text().indexOf("+") >=0
                    && elementsTd.get(1).text().indexOf(IpVo.HTTP.toUpperCase()) >= 0){
                    System.out.println(elementsTd.get(0).text());
                    System.out.println(elementsTd.get(1).text());
                    System.out.println(elementsTd.get(4).text());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ipVoList;
    }

    public Element getElements(List<Integer> index, Elements elements){
        Elements elementsTable = elements;
        for(Integer integer : index){
            Element element = elementsTable.get(integer);
            elementsTable = element.select("table");
        }
        return elementsTable.get(0);
    }
}

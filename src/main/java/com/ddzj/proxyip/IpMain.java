package com.ddzj.proxyip;

import com.ddzj.entity.IpVo;
import com.ddzj.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class IpMain {
    private static Logger logger = LoggerFactory.getLogger(IpMain.class);
    public static List<IpVo> getIpVo() {
        FreeProxyCz spysOne = new FreeProxyCz();
        return spysOne.getData();
    }

    public static String getPorxy() {
        List<IpVo> ipVoList = getIpVo();
        IpVo ipVo = ipVoList.get(0);
        logger.debug("{}", JacksonUtils.object2Json(ipVo));
        return ipVo.getType() + "://" + ipVo.getIp() + ":" + ipVo.getPort();
    }

}

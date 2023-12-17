package com.ddzj.entity;

public class IpVo {
    public static String HTTP="http";
    public static String HTTPS="https";

    private String type;
    // ip
    private String ip;
    // 端口
    private String port;

    public IpVo(String type, String ip, String port){
        this.type = type;
        this.ip = ip;
        this.port = port;
    }

    /**
     * 获取 ip
     *
     * @return ip ip
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * 设置 ip
     *
     * @param ip ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取 端口
     *
     * @return port 端口
     */
    public String getPort() {
        return this.port;
    }

    /**
     * 设置 端口
     *
     * @param port 端口
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * 获取
     *
     * @return type
     */
    public String getType() {
        return this.type;
    }

    /**
     * 设置
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}

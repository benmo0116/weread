package com.wxy.controller;

import com.wxy.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * @author wxy
 * @create 2018-01-31 11:59
 * @desc ${DESCRIPTION}
 **/
public class BaseTestController {
    /**
     * 上传下载路径(物理路径)
     */
    @Value("${spring.http.multipart.location}")
    protected String remotePath;
    /**
     * 上传路径(相对路径)
     */
    protected String uploadPath = "/upload";
    /**
     * 下载路径
     */
    protected String downloadPath = "/download";

    public void print(Object str) {
        System.out.println("PRINT: " + str);
    }

    public void sendNotice(String message) {
        for (WebSocketServer item : WebSocketServer.webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}

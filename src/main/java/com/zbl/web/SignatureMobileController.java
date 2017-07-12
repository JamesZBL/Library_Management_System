package com.zbl.web;

import com.zbl.web.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * 移动端请求控制类
 * 请求来源：Android端（Retrofit）
 */
@Controller
@RequestMapping("/signature") // url:/模块/资源/{id}/细分
public class SignatureMobileController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    private Object booklist(HttpServletRequest request, String name, @RequestParam(value = "img", required = false) MultipartFile file) {

        String fileName;
        File targetFile;
        File targetPath;

        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        String temp = servletContext.getRealPath(" ");
        temp = temp.substring(0, temp.lastIndexOf("\\"));
        temp = temp.substring(0, temp.lastIndexOf("\\"));
        String path = temp + "\\sig_root" + "\\";
        UUID uuid = UUID.randomUUID();
        fileName = uuid.toString() + file.getOriginalFilename().trim().substring(file.getOriginalFilename().lastIndexOf(".")).replace("\"","");
        targetPath = new File(path);
        targetFile = new File(path, fileName);
        if (!targetFile.exists()) {// 判断是否有文件
            targetFile.mkdirs();
        }

        try {
//            FileOutputStream out = new FileOutputStream(path + "\\"
//                    + fileName);
//            // 写入文件
//            out.write(file.getBytes());
//            out.flush();
//            out.close();

            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
            return renderRetrofitError();
        }

        return renderRetrofitSuccess();
    }
}

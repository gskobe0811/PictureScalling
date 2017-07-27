package com.sx.picScale.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sx.picScale.entity.Picpath;
import com.sx.picScale.service.api.PicService;
import com.sx.picScale.util.PicHan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lenovo on 2017/7/26.
 */
@Controller
public class PicController {
    @Autowired
    PicService picService;



    @RequestMapping(value = "upload", method = {RequestMethod.POST, RequestMethod.GET})
    public void Upload(Picpath picpath, HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "file", required = false) MultipartFile items_pic) throws IOException {
        JSONObject ret = new JSONObject();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String pathRoot = request.getSession().getServletContext().getRealPath("/");
        String path = "";
        //插入图片
        if (items_pic.getSize() != 0) {
            String type = items_pic.getContentType();
            Pattern pattern = Pattern.compile("\\.(jpg)|(png)|(gif)|(jpeg)|(webp)|(JPG)|(PNG)|(GIF)|(JPEG)|(WEBP)");
            Matcher matcher = pattern.matcher(type);
            if (matcher.find()) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String contentType = items_pic.getContentType();
                String imageName = contentType.substring(contentType.indexOf("/") + 1);
                path = "pic/" + uuid + "." + imageName;
                File newFile = new File(pathRoot + path);
                //System.out.println(contentType+"   "+pathRoot);
                try {
                    items_pic.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                picpath.setSrc(path);
                try {
                    picService.addPic(picpath);
//                    PicHan picHan = new PicHan();
//                    BufferedImage b1 = picHan.scalling(pathRoot+picpath.getSrc(), 1080, 480, true);
//                    ServletOutputStream os=response.getOutputStream();
//                    ImageIO.write(b1,imageName,os);
                    ret.put("status", "y");
                  // ret.put("id",picpath.getId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                ret.put("status", "n");
            }
        } else {
            ret.put("status", "empty");
        }
        response.getWriter().write(ret.toString());

    }
    @RequestMapping(value = "scalle", method = {RequestMethod.POST, RequestMethod.GET})
   public String scalle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("image");
        PicHan picHan = new PicHan();
        long id=0L;
        String idStr=request.getParameter("id");
        if (idStr!=null&&idStr.equals("")&&idStr!="")
            id=Long.parseLong(idStr);
        String fileName=picService.getPic(id).getSrc();
        String pathRoot = request.getSession().getServletContext().getRealPath("/");
        BufferedImage b1 = picHan.scalling(pathRoot+fileName, 1080, 480, true);
        ServletOutputStream os=response.getOutputStream();
        ImageIO.write(b1,fileName.split(".")[fileName.split(".").length-1],os);
        return "index";
//
    }


}

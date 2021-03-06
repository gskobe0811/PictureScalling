package com.sx.picScale.controller;

import com.alibaba.fastjson.JSONObject;
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
    public ModelAndView upload(Picpath picpath, HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "file", required = false) MultipartFile items_pic) throws IOException {
        ModelAndView mv = new ModelAndView("index");
        picUpload(picpath,mv,request,items_pic);
        return mv;
    }
    @RequestMapping(value = "uploadCut", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView uploadCut(Picpath picpath, HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(value = "file", required = false) MultipartFile items_pic) throws IOException {
        ModelAndView mv = new ModelAndView("cutPic");
        picUpload(picpath,mv,request,items_pic);
        return mv;

    }
    public void picUpload(Picpath picpath,ModelAndView mv,HttpServletRequest request,MultipartFile items_pic){
        String pathRoot = request.getSession().getServletContext().getRealPath("/");
        String path = "";
        PicHan picHan=new PicHan();
        //插入图片
        if (items_pic.getSize() != 0) {
            if (picHan.isVaildImage(items_pic.getOriginalFilename())) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String contentType = items_pic.getContentType();
                String imageName = contentType.substring(contentType.indexOf("/") + 1);
                path = "pic/" + uuid + "." + imageName;
                File newFile = new File(pathRoot + path);
                try {
                    items_pic.transferTo(newFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                picpath.setSrc(path);
                try {
                    picService.addPic(picpath);
                    mv.addObject("pic", picService.getPic(path));
                    mv.addObject("status", 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                mv.addObject("status", 0);
            }
        } else {
            mv.addObject("status", 1);
        }
    }

    @RequestMapping(value = "scalle", method = {RequestMethod.POST, RequestMethod.GET})
    public void scalle(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "w") int w, @RequestParam(value = "id") long id, @RequestParam(value = "h") int h) throws IOException {
        PicHan picHan = new PicHan();
        String fileName = picService.getIdPic(id).getSrc();
        String pathRoot = request.getSession().getServletContext().getRealPath("/");
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        BufferedImage b = picHan.scalling(pathRoot + fileName, w, h, true);
        ServletOutputStream os = response.getOutputStream();
        try {
            ImageIO.write(b, suffix, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
        os.flush();
        os.close();

    }


}

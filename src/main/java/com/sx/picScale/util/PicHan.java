package com.sx.picScale.util;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lenovo on 2017/7/25.
 */
public class PicHan {

    private Logger logger = Logger.getLogger(PicHan.class);

    /*
    * 缩放图片
    * ori原图片地址
    * w缩放后的图片宽度
    * h缩放后的图片高度
    * isForce值true强制性宽高,值false根据原图调整*/
    public BufferedImage scalling(String ori, int w, int h, boolean isForce) {
        BufferedImage now = null;
        File file = new File(ori);
        if (file.exists()) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(ori));
            } catch (IOException e) {
                e.printStackTrace();
            }
            int width = img.getWidth();
            int height = img.getHeight();
            if (!isForce) {
                if (width / height > w / h) {
                    h = height * w / width;
                } else {
                    w = width * h / height;
                }
            }
            now = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            Graphics g = now.getGraphics();
            g.drawImage(img, 0, 0, w, h, null);
            g.dispose();
        } else {
            logger.error("想要处理的源文件不存在");
        }
        return now;
    }

    /*图片剪裁
    * ori图片源地址
    * x剪切的起点x坐标
    * y剪切的起点y坐标
    * w剪切的宽度
    * h剪切的高度*/
    public BufferedImage cutImage(String ori, int x, int y, int w, int h) {
        BufferedImage now = null;
        File file = new File(ori);
        if (file.exists()) {
            BufferedImage img = null;
            try {
                img = ImageIO.read(new File(ori));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String suffix = ori.substring(ori.lastIndexOf(".") + 1, ori.length());
            ImageReader reader=ImageIO.getImageReadersByFormatName(suffix).next();
            reader.setInput(img,true);
            ImageReadParam param=reader.getDefaultReadParam();
            Rectangle rectangle=new Rectangle(x,y,w,h);
            param.setSourceRegion(rectangle);
            try {
                now=reader.read(0,param);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.error("想要处理的源文件不存在");
        }
        return now;
    }

    /*是否是图片格式*/
    public boolean isVaildImage(String fileName) {
        String type = Arrays.toString(ImageIO.getReaderFormatNames()).replace("]", ",");
        String suffix = null;
        if (fileName.indexOf(".") > -1) {
            suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        if (suffix == null || type.toLowerCase().indexOf(suffix.toLowerCase() + ",") < 0) {
            return false;
        }
        return true;
    }

    public String genFileName(long id, int w, int h, boolean isforce) {
        String file = "";
        if (!isforce)
            file = id + "_" + "0" + "_" + w + "*" + h;
        else
            file = id + "_" + "1" + "_" + w + "*" + h;
        return file;
    }
}

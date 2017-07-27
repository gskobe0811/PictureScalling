package com.sx.picScale.util;

import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lenovo on 2017/7/25.
 */
public class PicHan {

    private Logger logger = Logger.getLogger(PicHan.class);
    public BufferedImage scalling(String ori, int w, int h, boolean isForce) {
        BufferedImage now=null;
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
        return  now;
    }

    public String genFileName(long id, int w, int h,boolean isforce) {
        String file="";
        if (!isforce)
            file=id+"_"+"0"+"_" + w + "*" + h;
        else
            file=id+"_"+"1"+"_" + w + "*" + h;
        return file;
    }
}

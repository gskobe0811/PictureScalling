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
                    height = height * w / width;
                } else {
                    width = width * h / height;
                }
            }
            now = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = now.getGraphics();
            g.drawImage(img, 0, 0, width, height, null);
            g.dispose();
        } else {
            logger.error("想要处理的源文件不存在");
        }
        return  now;
    }

    public String genFileName(String ori, int w, int h) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = format.format(new Date());
        String file = now + "_" + UUID.randomUUID() + "_" + w + "*" + h;
        return file;
    }
}

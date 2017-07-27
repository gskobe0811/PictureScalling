package com.sx.picScale.service.api;

import com.sx.picScale.entity.Picpath;

/**
 * Created by lenovo on 2017/7/26.
 */
public interface PicService {
    void addPic(Picpath picpath);
    Picpath getPic(long id);
}

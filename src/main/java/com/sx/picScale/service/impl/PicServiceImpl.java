package com.sx.picScale.service.impl;

import com.sx.picScale.dao.PicpathMapper;
import com.sx.picScale.entity.Picpath;
import com.sx.picScale.service.api.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lenovo on 2017/7/26.
 */
@Service
public class PicServiceImpl implements PicService {
    @Autowired
    PicpathMapper picDao;
    public void addPic(Picpath picpath) {
        picDao.insert(picpath);

    }

    public Picpath getIdPic(long id) {
        return picDao.selectByPrimaryKey(id);
    }

    public Picpath getPic(String src) {
        return picDao.selectBySrc(src);
    }
}

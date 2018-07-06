package cn.cocoding.cmfz.controller;

import cn.cocoding.cmfz.entity.Carousel;
import cn.cocoding.cmfz.entity.Guru;
import cn.cocoding.cmfz.service.CarouselService;
import cn.cocoding.cmfz.service.GuruService;
import cn.cocoding.cmfz.util.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by huzi on 2018/7/6.
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private CarouselService carouselService;
    @Autowired
    private GuruService guruService;
    //上传轮播图图片
    @RequestMapping(value = "/carouselImage")
    @ResponseBody
    public String uploadCarouselImage(MultipartFile myFile, HttpSession session, Carousel carousel) throws IOException {
        //获取上传到服务器后的图片全名
        String imageDetailName = UploadUtil.upload(myFile, session);
        //将上传的文件信息保存到数据库中
        carousel.setCarouselDate(new Date());
        carousel.setCarouselName(imageDetailName);
        int i = carouselService.insertSelective(carousel);
        if(i>0){
            return "success";
        }
        return "fail";
    }
    //上传轮播图图片
    @RequestMapping(value = "/guruImage")
    @ResponseBody
    public String uploadGuruImage(MultipartFile myFile, HttpSession session, Guru guru) throws IOException {
        //获取上传到服务器后的图片全名
        String imageDetailName = UploadUtil.upload(myFile, session);
        //将上传的文件信息保存到数据库中
        guru.setGuruImage(imageDetailName);
        int i = guruService.insertSelective(guru);
        if(i>0){
            return "success";
        }
        return "fail";
    }

}

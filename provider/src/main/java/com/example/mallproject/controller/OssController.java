package com.example.mallproject.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.common.api.Result;
import com.example.mallproject.common.utils.OssUtils;
import com.example.mallproject.entity.File;
import com.example.mallproject.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/file")
public class OssController {
    @Autowired
    FileService fileService;

    @GetMapping("/page")
    public Result<Page<File>> getPage(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam(defaultValue = "") String name) {
        return Result.Success(fileService.getPage(pageNum, pageSize, name));
    }

    @Autowired
    OssUtils ossUtils;
    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestBody MultipartFile file) throws Exception {
        //返回上传oss的url
        String url =  ossUtils.uploadOneFile(file);
        fileService.save(file, url);
        return Result.Success(url, "上传成功");
    }

    @PostMapping("/uploadArrayFile")
    public Result<List<String>> uploadArrayFile(@RequestBody MultipartFile[] files) {
        //返回上传oss的url
        List<String> urls = ossUtils.uploadArrayFile(files);
        fileService.save(files, urls);
        return Result.Success(urls);
    }

    @DeleteMapping("{id}")
    public Result<Boolean> deleteFile(@PathVariable Integer id) {
        //返回是否删除成功
        File file = fileService.getById(id);
        String fileUrl = file.getUrl();
        ossUtils.deleteFile(fileUrl);
        return Result.Success(fileService.remove(fileUrl));
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBath(@RequestBody List<Integer> ids) {
        //返回是否删除成功
        List<String> urls = fileService.getUrls(ids);
        ossUtils.deleteFile(urls);
        return Result.Success(fileService.remove(ids));
    }
}

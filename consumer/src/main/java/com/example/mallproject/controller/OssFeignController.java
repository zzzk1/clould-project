package com.example.mallproject.controller;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.File;
import com.example.mallproject.entity.SpuInfo;
import com.example.mallproject.feign.BrandFeignService;
import com.example.mallproject.feign.OssFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zzzk1
 */
@RestController
@RequestMapping("file")
public class OssFeignController {
    @Autowired
    private OssFeignService ossService;

    @GetMapping("/page")
    public Result<Page<File>> getPage(@RequestParam(defaultValue = "1") int pageNum,
                                      @RequestParam(defaultValue = "10") int pageSize,
                                      @RequestParam(defaultValue = "") String name) {
        return ossService.getPage(pageNum, pageSize, name);
    }

    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestBody MultipartFile file) throws Exception {
        return ossService.uploadFile(file);
    }

    @PostMapping("/uploadArrayFile")
    public Result<List<String>> uploadArrayFile(@RequestBody MultipartFile[] files) {
        return ossService.uploadArrayFile(files);
    }

    @DeleteMapping("{id}")
    public Result<Boolean> deleteFile(@PathVariable Integer id) {
        return ossService.deleteFile(id);
    }

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBath(@RequestBody List<Integer> ids) {
        return ossService.deleteBath(ids);
    }
}

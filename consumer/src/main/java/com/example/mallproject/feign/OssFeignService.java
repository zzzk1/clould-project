package com.example.mallproject.feign;

import api.Result;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.config.FeignMultipartSupportConfig;
import com.example.mallproject.config.FeignSpringFormEncoder;
import com.example.mallproject.entity.File;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author zzzk1
 */
@FeignClient(value="provider",path = "/file", configuration = FeignMultipartSupportConfig.class)
public interface OssFeignService {

    @GetMapping("/page")
    public Result<Page<File>> getPage(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "name", defaultValue = "") String name);

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<String> uploadFile(@RequestBody MultipartFile file) throws Exception;

    @PostMapping(value = "/uploadArrayFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Result<List<String>> uploadArrayFile(@RequestBody MultipartFile[] files);

    @DeleteMapping("{id}")
    public Result<Boolean> deleteFile(@PathVariable("id") Integer id);

    @PostMapping("/del/batch")
    public Result<Boolean> deleteBath(@RequestBody List<Integer> ids);
}

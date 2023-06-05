package com.example.mallproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mallproject.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-26
 */
public interface FileService extends IService<File> {

    Boolean save(MultipartFile file, String url);

    Boolean save(MultipartFile[] files, List<String> urls);

    Boolean remove(String fileUrl);

    Boolean remove(List<Integer> ids);



    Page<File> getPage(Integer pageNum, Integer pageSize, String name);

    List<String> getUrls(List<Integer> ids);
}

package com.example.mallproject.common.utils;

import com.example.mallproject.entity.File;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzzk1
 */
public class FileUtil {
    public static File buildDBFile(MultipartFile file, String url) {
        String name  = file.getOriginalFilename();
        long size = file.getSize();
        String type = file.getContentType();
        name += "md5";
        String md5Str = DigestUtils.md5DigestAsHex(name.getBytes());
        size /= 1024;
        File DBFile = new File();
        DBFile.setName(md5Str);
        DBFile.setSize(size);
        DBFile.setUrl(url);
        DBFile.setType(type);
        return DBFile;
    }
}

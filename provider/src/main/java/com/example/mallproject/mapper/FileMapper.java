package com.example.mallproject.mapper;

import com.example.mallproject.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzzk1
 * @since 2023-04-26
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {
    int saveByUrls(List<String> urls);
    List<String> getUrls(@Param("ids") List<Integer> ids);
}

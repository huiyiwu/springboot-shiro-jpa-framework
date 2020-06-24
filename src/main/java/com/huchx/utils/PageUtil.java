package com.huchx.utils;

import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.HashMap;
import java.util.Map;

/**
 * 分页对象处理工具
 */
public class PageUtil {
    public static Map<String,Object> parseToModel(Page page){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("content",page.getContent());
        resultMap.put("total",page.getTotalElements());
        resultMap.put("totalPage",page.getTotalPages());
        resultMap.put("pageNo",page.getPageable().getPageNumber());
        resultMap.put("pageSize",page.getPageable().getPageSize());
        resultMap.put("count",page.getNumberOfElements());
        return  resultMap;
    }
}

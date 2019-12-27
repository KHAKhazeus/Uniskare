package com.uniskare.eureka_skill.service.Helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
public class MyPageHelper{
    /**
     *
     * @param list 原始数据data
     * @param pageable 分页参数，index(zero based)和data_per_page
     * @param <T> 数据的元素类型
     * @return 对应的页
     */
    public static <T> List<T> convert2Page(List<T> list, Pageable pageable)
    {
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), list.size());
        Page<T> dtoPage = new PageImpl<T>(
                list.subList(start, end), pageable, list.size()
        );

        return dtoPage.getContent();
    }
}

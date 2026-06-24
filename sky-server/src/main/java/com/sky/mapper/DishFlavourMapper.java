package com.sky.mapper;


import com.sky.annotation.AntoFill;
import com.sky.entity.DishFlavor;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavourMapper {
    /**
     * 批量插入口味数据
     * @param flavors
     */
    @AntoFill(value = OperationType.INSERT)
    void insertBatch(List<DishFlavor> flavors);
}

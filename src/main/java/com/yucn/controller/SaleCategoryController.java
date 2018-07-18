package com.yucn.controller;

import com.yucn.entity.SaleCategory;
import com.yucn.service.SaleCategoryService;
import com.yucn.utils.ResultVOUtil;
import com.yucn.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */
@RestController
@RequestMapping("/salecategory")
@Slf4j
public class SaleCategoryController {
    @Autowired
    private SaleCategoryService saleCategoryService;

    @PostMapping("/add")
    public ResultVO<SaleCategory> add(@RequestBody SaleCategory saleCategory) {
        log.info("添加销售种类");
        saleCategoryService.add(saleCategory);
        return ResultVOUtil.success(saleCategory);
    }

    @GetMapping("/list")
    public ResultVO<List<SaleCategory>> list() {
        log.info("查询消费种类列表");
        List<SaleCategory> saleCategoryList = saleCategoryService.list();
        return ResultVOUtil.success(saleCategoryList);
    }

    @PostMapping(value = "/delete")
    public ResultVO<SaleCategory> delete(@RequestParam Long id) {
        log.info("删除消费种类【{}】",id);
        return ResultVOUtil.success(saleCategoryService.delete(id));
    }
}

package com.yucn.controller;

import com.yucn.entity.Customer;
import com.yucn.enums.DetailTypeEnum;
import com.yucn.entity.CustomerFeeDetail;
import com.yucn.service.CustomerFeeDetailService;
import com.yucn.utils.EnumUtil;
import com.yucn.utils.ResultVOUtil;
import com.yucn.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/3/7.
 */
@RestController
@RequestMapping("/customerfee")
@Slf4j
public class CustomerFeeDetailController {
    @Autowired
    private CustomerFeeDetailService customerFeeDetailService;

    @GetMapping(value = "/list/{type}")
    public ResultVO<List<CustomerFeeDetail>> list(@PathVariable("type") Integer type, @RequestParam(value = "listMode", defaultValue = "byAddress") String mode,
                                                        @RequestParam(value = "keyValue", defaultValue = "") String key,
                                                        @RequestParam(value = "currentPage", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "pageSize", defaultValue = "10") Integer size) {
        DetailTypeEnum detailTypeEnum= EnumUtil.getByCode(DetailTypeEnum.class,type);
        log.info("查询{}", detailTypeEnum.getMessage());
        Sort sort = new Sort(Sort.Direction.DESC, "occurTime");
        PageRequest request = new PageRequest(page-1, size, sort);
        Page<CustomerFeeDetail> customerFeeDetailPage = customerFeeDetailService.findList(type, mode, key, request);
        return ResultVOUtil.success(customerFeeDetailPage);
    }
}

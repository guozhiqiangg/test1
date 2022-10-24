package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Category;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("brand")
@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;


    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,//当前页
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,//每页大小
            @RequestParam(value = "sortBy", required = false) String sortBy,//排序字段
            @RequestParam(value = "desc", defaultValue = "false") Boolean desc,//是否为降序
            @RequestParam(value = "key", required = false) String key) {//搜索关键字

        PageResult<Brand> result = this.brandService.queryBrandByPageAndSort(page, rows, sortBy, desc, key);
        if (result == null || result.getItems().size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {

        this.brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.updateBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping("delete")
    public ResponseEntity<Void> deleteBrand(Long bid) {
        this.brandService.deleteBrand(bid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandListByCid(@PathVariable("cid") Long cid) {
        List<Brand> brandList = this.brandService.queryByCid(cid);
        if (CollectionUtils.isEmpty(brandList)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(brandList);
    }

    @GetMapping("bid/{bid}")
    public Brand queryBrandByBid(@PathVariable("bid") Long bid){
        Brand brand=this.brandService.queryBrandByBid(bid);

        return brand;
    }

}

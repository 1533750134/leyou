package cn.wojia.item.controller;

import cn.wojia.common.PageResult;
import cn.wojia.item.pojo.Brand;
import cn.wojia.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
           @RequestParam(value = "key",required = false) String key,
           @RequestParam(value = "page",defaultValue = "1")  Integer page,
           @RequestParam(value = "rows",defaultValue = "5") Integer rows,
           @RequestParam(value = "sortBy",required = false) String sortBy,
           @RequestParam(value = "desc",required = false) Boolean desc){
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        if (CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 新增品牌
     * @param brand
     * @param cids
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        this.brandService.saveBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 修改品牌
     * @param brand
     * @param cids
     */
    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids") List<Long> cids){
        this.brandService.updateBrand(brand, cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 删除品牌
     * @param bid
     * @return
     */
    @PostMapping("/del/{bid}")
    public ResponseEntity<Void> delByBrandId(@PathVariable("bid")Long bid){
        int i = brandService.delByBrandId(bid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable("cid")Long cid){
        List<Brand> brands = this.brandService.queryBrandsByCid(cid);
        if (CollectionUtils.isEmpty(brands)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brands);
    }
}

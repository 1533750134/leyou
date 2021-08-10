package cn.wojia.item.controller;

import cn.wojia.item.pojo.Category;
import cn.wojia.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("list")
    public ResponseEntity<List<Category>> findCategoryByPid(@RequestParam("pid") Long pid){
        if (pid == null||pid.longValue()<0) {
            return ResponseEntity.badRequest().build();//400
        }
        List<Category> categoryByPid = categoryService.findCategoryByPid(pid);
        if (CollectionUtils.isEmpty(categoryByPid)) {
            return ResponseEntity.noContent().build();//404
        }
        return ResponseEntity.ok(categoryByPid);
    }
    @GetMapping("/bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long bid){
        List<Category> categories = categoryService.queryByBrandId(bid);
        if (categories == null ||categories.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categories);
    }




}

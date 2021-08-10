package cn.wojia.item.service;

import cn.wojia.item.mapper.CategoryMapper;
import cn.wojia.item.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据parentId查询子类
     * @param pid
     * @return
     */
    public List<Category> findCategoryByPid(Long pid){
        Category category = new Category();
        category.setParentId(pid);
        List<Category> select = categoryMapper.select(category);
        return select;
    }

    /**
     * 根据品牌id查询商品分类
     * @param bid
     * @return
     */
    public  List<Category> queryByBrandId(Long bid){
        return categoryMapper.queryByBrandId(bid);
    }

    public int insertCategory(Category category){
        return  categoryMapper.insert(category);
    }

    /**
     *
     * @param ids
     * @return
     */
    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> list = this.categoryMapper.selectByIdList(ids);
        List<String> names = new ArrayList<>();
        for (Category category : list) {
            names.add(category.getName());
        }
        return names;
        // return list.stream().map(category -> category.getName()).collect(Collectors.toList());
    }
}

package cn.wojia.item.mapper;

import cn.wojia.item.pojo.Stock;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface StockMapper extends Mapper<Stock> {
}

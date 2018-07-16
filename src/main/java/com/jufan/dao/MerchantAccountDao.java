package com.jufan.dao;

import javafx.beans.binding.ObjectExpression;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface MerchantAccountDao {
     /**
      * 根据jf和query拓展表的名字和商户id查询产品id和产品月调用次数
      */
     List<Map<String,Object >> getAccountDetailByOrgId(Map<String,Object> map);
     /**
      * 根据产品id查询产品的价格和名字
      */
     Map<String,Object>  getProductNameById(@Param("id") String id);

}

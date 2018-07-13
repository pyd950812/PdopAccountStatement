package com.jufan.dao;

import javafx.beans.binding.ObjectExpression;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface MerchantAccountDao {

     List<Map<String,Object >> getAccountDetailByOrgId(Map<String,Object> map);

     Map<String,Object>  getProductNameById(@Param("id") String id);

}

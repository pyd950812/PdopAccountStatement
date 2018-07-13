package com.jufan.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;
@Repository
public interface MerchantAccountDao {

    Map<String,String> getAccountDetailByOrgId(String orgId);

}

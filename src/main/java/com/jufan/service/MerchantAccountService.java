package com.jufan.service;

import java.util.List;
import java.util.Map;

public interface MerchantAccountService {

    /**
     * 获取渠道月账单的详细信息
     * @param orgId
     */
    public List<Map<String, Object>> getMerchantCountByOrgId(String orgId);

    /**
     * 将商户的信息生成Excle  入参商户Id
     */
    void buildExcel(String id);


}

package com.jufan.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PdopQueryLogDao {

    public List selectQueryDataByHour(Map<String,Object> map);

}

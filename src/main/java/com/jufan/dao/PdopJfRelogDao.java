package com.jufan.dao;

import java.util.Date;
import java.util.List;

public interface PdopJfRelogDao {

    public List selectJfDataByHour(Date beginTime,Date endTime);
}

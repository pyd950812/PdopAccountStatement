package com.jufan.model;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

@SuppressWarnings("serial")
public class PdopQueryLog  {

	/** 主键id */
	private String id;
	/** 产品id */
	private String productId;
	/** 组织id */
	private String orgId;
	/** 操作人id */
	private String userId;
	/** 数据库id */
	private String dataSourceId;
	/** 结果id */
	private String jfReqlogId;
	/** 批次id */
	private String batchId;
	/** 请求参数 */
	private String requestParam;
	/** 创建日期 */
	private Date createDatetime;
	/** 查询类型 */
	private String queryType;

	private String ids;

	private String groupSql;

	/** 是否计费 */
	private String isCharge;
	
	public String getId(){
		return id;
  	} 
  	
  	public void setId(String id){
  		this.id=id;
  	}
  	
	public String getProductId(){
		return productId;
  	} 
  	
  	public void setProductId(String productId){
  		this.productId=productId;
  	}
  	
	public String getOrgId(){
		return orgId;
  	} 
  	
  	public void setOrgId(String orgId){
  		this.orgId=orgId;
  	}
  	
	public String getUserId(){
		return userId;
  	} 
  	
  	public void setUserId(String userId){
  		this.userId=userId;
  	}
  	
	public String getDataSourceId(){
		return dataSourceId;
  	} 
  	
  	public void setDataSourceId(String dataSourceId){
  		this.dataSourceId=dataSourceId;
  	}
  	
	public String getJfReqlogId(){
		return jfReqlogId;
  	} 
  	
  	public void setJfReqlogId(String jfReqlogId){
  		this.jfReqlogId=jfReqlogId;
  	}
  	
	public String getBatchId(){
		return batchId;
  	} 
  	
  	public void setBatchId(String batchId){
  		this.batchId=batchId;
  	}
  	
	public String getRequestParam(){
		return requestParam;
  	} 
  	
  	public void setRequestParam(String requestParam){
  		this.requestParam=requestParam;
  	}

	public Date getCreateDatetime(){
		return createDatetime;
  	} 
  	
  	public void setCreateDatetime(Date createDatetime){
  		this.createDatetime=createDatetime;
  	}
  	
	public String getQueryType(){
		return queryType;
  	} 
  	
  	public void setQueryType(String queryType){
  		this.queryType=queryType;
  	}

	public String getGroupSql() {
		return groupSql;
	}

	public void setGroupSql(String groupSql) {
		this.groupSql = groupSql;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIsCharge() {
		return isCharge;
	}

	public void setIsCharge(String isCharge) {
		this.isCharge = isCharge;
	}
	
	
}

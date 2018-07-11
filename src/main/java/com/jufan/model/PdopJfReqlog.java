package com.jufan.model;

import java.util.Date;

@SuppressWarnings("serial")
public class PdopJfReqlog  {

	/** PK */
	private String id;
	/** 产品ID */
	private String productId;
	/** 请求URL */
	private String url;
	/** 姓名 */
	private String name;
	/** 身份证号码 */
	private String certNumber;
	/** 请求参数 */
	private String requestParam;
	/** 返回值 */
	private String responseRaw;
	/** 原始输出字符串 */
	private String originalResponseRaw;
	/** 状态 */
	private String status;
	/** 创建时间 */
	private Date createDatetime;
	/** 创建人 */
	private String createOperatorId;
	/** 最后修改时间 */
	private Date lastUpdateDatetime;
	/** 最后修改人 */
	private String lastUpdateOperatorId;
	/** 扩展信息(JSON) */
	private String extInfo;

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
  	
	public String getUrl(){
		return url;
  	} 
  	
  	public void setUrl(String url){
  		this.url=url;
  	}
  	
	public String getName(){
		return name;
  	} 
  	
  	public void setName(String name){
  		this.name=name;
  	}
  	
	public String getCertNumber(){
		return certNumber;
  	} 
  	
  	public void setCertNumber(String certNumber){
  		this.certNumber=certNumber;
  	}
  	
	public String getRequestParam(){
		return requestParam;
  	} 
  	
  	public void setRequestParam(String requestParam){
  		this.requestParam=requestParam;
  	}
  	
	public String getResponseRaw(){
		return responseRaw;
  	} 
  	
  	public void setResponseRaw(String responseRaw){
  		this.responseRaw=responseRaw;
  	}
  	
	public String getOriginalResponseRaw(){
		return originalResponseRaw;
  	} 
  	
  	public void setOriginalResponseRaw(String originalResponseRaw){
  		this.originalResponseRaw=originalResponseRaw;
  	}
  	
	public String getStatus(){
		return status;
  	} 
  	
  	public void setStatus(String status){
  		this.status=status;
  	}

	public Date getCreateDatetime(){
		return createDatetime;
  	} 
  	
  	public void setCreateDatetime(Date createDatetime){
  		this.createDatetime=createDatetime;
  	}
  	
	public String getCreateOperatorId(){
		return createOperatorId;
  	} 
  	
  	public void setCreateOperatorId(String createOperatorId){
  		this.createOperatorId=createOperatorId;
  	}

	public Date getLastUpdateDatetime(){
		return lastUpdateDatetime;
  	} 
  	
  	public void setLastUpdateDatetime(Date lastUpdateDatetime){
  		this.lastUpdateDatetime=lastUpdateDatetime;
  	}
  	
	public String getLastUpdateOperatorId(){
		return lastUpdateOperatorId;
  	} 
  	
  	public void setLastUpdateOperatorId(String lastUpdateOperatorId){
  		this.lastUpdateOperatorId=lastUpdateOperatorId;
  	}
  	
	public String getExtInfo(){
		return extInfo;
  	} 
  	
  	public void setExtInfo(String extInfo){
  		this.extInfo=extInfo;
  	}
  	
  
}

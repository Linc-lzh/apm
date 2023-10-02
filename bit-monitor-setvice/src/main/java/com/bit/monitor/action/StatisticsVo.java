package com.bit.monitor.action;
/**
 * 统计信息
 * @author tommy@bit.com
 *
 */
public class StatisticsVo implements java.io.Serializable {
	private String serviceName;// 服务名称
	private String serviceMethod;// 方法名称
	public long count=0;// 执行次数
	public long faills=0;// 失败次数
	public long fastTime=0;// 最快时间
	public long slowTime=0;// 最慢时间
	public long avgTime=0;// 平均时间
	public long totalTime;// 总时间
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getServiceMethod() {
		return serviceMethod;
	}
	public void setServiceMethod(String serviceMethod) {
		this.serviceMethod = serviceMethod;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getFaills() {
		return faills;
	}
	public void setFaills(long faills) {
		this.faills = faills;
	}
	public long getFastTime() {
		return fastTime;
	}
	public void setFastTime(long fastTime) {
		this.fastTime = fastTime;
	}
	public long getSlowTime() {
		return slowTime;
	}
	public void setSlowTime(long slowTime) {
		this.slowTime = slowTime;
	}
	public long getAvgTime() {
		return avgTime;
	}
	public void setAvgTime(long avgTime) {
		this.avgTime = avgTime;
	}
	public long getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(long totalTime) {
		this.totalTime = totalTime;
	}
	
}

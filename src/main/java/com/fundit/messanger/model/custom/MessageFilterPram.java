package com.fundit.messanger.model.custom;

import javax.ws.rs.QueryParam;

public class MessageFilterPram {

	private @QueryParam("year") int year;
	private @QueryParam("start") Integer start;
	private @QueryParam("limit") Integer limit;
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}

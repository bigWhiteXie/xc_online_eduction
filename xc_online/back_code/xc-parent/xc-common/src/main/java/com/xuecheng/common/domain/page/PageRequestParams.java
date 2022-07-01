package com.xuecheng.common.domain.page;

import lombok.Data;

@Data
public class PageRequestParams {
	public static final Integer DEFAULT_PAGE_SIZE = 20;
	public static final Long DEFAULT_PAGE_NUM = Long.valueOf(1);

	/**
	 * 当前页
	 */
	private Long pageNo = DEFAULT_PAGE_NUM;
	/**
	 * 每页条数
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	/**
	 * 是否排序
	 */
	private String order;
	/**
	 * 排序的字段
	 */
	private String sortBy;


	public PageRequestParams () {

	}

	private PageRequestParams(Long pageNo, Integer limit){
		this.pageNo = pageNo;
		this.pageSize = limit;
	}
	
	public static PageRequestParams of(Integer pageNo, Integer pageSize){
		Long startRow = Long.valueOf((pageNo - 1) * pageSize);
		return new PageRequestParams(startRow, pageSize);
	}

}

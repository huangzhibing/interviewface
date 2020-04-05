package com.qc.pojo;

import java.awt.*;

/**
 * @Author: huangzhibing
 * @Date: 2020/3/20 22:43
 *
 * @Description:
 */
public class Ajaxson {
	private boolean success;
	private String message;
	private List data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}
}

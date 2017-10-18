/**
 * 
 */
package com.stashinvest.userservice.common;

import java.util.Collection;

/**
 * @author abhimanyu
 *
 */
public class ViewResponse<T> {
	private Collection<T> rows;
	private Object data;
	private boolean success;
	private String reason;
	private String total;

	public Collection<T> getRows() {
		return rows;
	}

	public void setRows(Collection<T> rows) {
		this.rows = rows;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

}

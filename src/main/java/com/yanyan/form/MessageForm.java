package com.yanyan.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageForm {

	@NotNull
	@Size(max = 80)
	private String type;

	@NotNull
	private int qNo;

	@NotNull
	@Size(min = 1, max = 140)
	private String text;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getqNo() {
		return qNo;
	}

	public void setqNo(int qNo) {
		this.qNo = qNo;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}

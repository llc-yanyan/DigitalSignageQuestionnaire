package com.yanyan.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QrForm {

	@NotNull
	@Size(max = 80)
	private String qrcode;

	public String getQrcode() {
		return qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}
}

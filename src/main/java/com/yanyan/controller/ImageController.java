package com.yanyan.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.yanyan.form.QrForm;

@RestController
@RequestMapping("/qr")
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	@RequestMapping(method = RequestMethod.POST)
	public String qr(@ModelAttribute QrForm form) {
		try {
			return toByteArray(form.getQrcode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private String toByteArray(String contents) throws IOException, WriterException {
		BarcodeFormat format = BarcodeFormat.QR_CODE;
		int width = 160;
		int height = 160;

		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

		try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
			QRCodeWriter writer = new QRCodeWriter();
			BitMatrix bitMatrix = writer.encode(contents, format, width, height, hints);
			MatrixToImageWriter.writeToStream(bitMatrix, "png", output);

			Base64 base64 = new Base64();
			byte[] encoded = base64.encode(output.toByteArray());
			String base64Image = new String(encoded);

			return base64Image;
		}
	}
}

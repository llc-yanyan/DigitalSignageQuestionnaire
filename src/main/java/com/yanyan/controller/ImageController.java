package com.yanyan.controller;

import java.io.ByteArrayOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;


/**
 * ImageController
 * @author yanai
 *
 */
@RestController
@RequestMapping("qr")
public class ImageController {

	private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

	/**
	 * Make QR code with custom setting.
	 * @param width
	 * @param height
	 * @param src
	 * @return
	 */
	@RequestMapping(value = "/{width}/{height}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> qr(@PathVariable("width") Integer width, @PathVariable("height") Integer height,
			@RequestParam("src") String src) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(makeQRCode(src, width, height), headers, HttpStatus.CREATED);
	}

	/**
	 * Make QR Code with default setting.
	 * @param src
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<byte[]> qr(@RequestParam("src") String src) {
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);

		return new ResponseEntity<byte[]>(makeQRCode(src, 100, 100), headers, HttpStatus.CREATED);
	}

	/**
	 * Make QR code
	 * @param src
	 * @param width
	 * @param height
	 * @return
	 */
	private byte[] makeQRCode(String src, int width, int height) {
		ByteArrayOutputStream stream = QRCode.from(src)
				.withSize(width, height)
				.withErrorCorrection(ErrorCorrectionLevel.L)
				.to(ImageType.PNG)
				.stream();
		return stream.toByteArray();
	}
}

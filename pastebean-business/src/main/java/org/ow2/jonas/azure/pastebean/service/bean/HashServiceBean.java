package org.ow2.jonas.azure.pastebean.service.bean;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;

import org.ow2.jonas.azure.pastebean.model.Paste;
import org.ow2.jonas.azure.pastebean.service.HashService;

/**
 * Session Bean implementation class HashServiceBean
 */
@Stateless
public class HashServiceBean implements HashService {
	
	private MessageDigest md;
	
	public HashServiceBean() {
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
	}

	public String createHash(Paste paste) {
		md.update(utf8(paste.getContent()));
		md.update(utf8(paste.getAuthor()));
		md.update(utf8(paste.getDescription()));
		md.update(utf8(String.valueOf(paste.getCreationDate().getTime())));
        return toHex(md.digest());
    }

	private byte[] utf8(String fragment) {
		if (fragment != null) {
			try {
				return fragment.getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException(e);
			}
		}
		return new byte[0];
	}

    private String toHex(byte[] bytes) {
        BigInteger bi = new BigInteger(1, bytes);
        return String.format("%0" + (bytes.length << 1) + "x", bi);
    }

}

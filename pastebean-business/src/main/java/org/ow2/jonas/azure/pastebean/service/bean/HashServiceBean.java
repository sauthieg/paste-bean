/**
 * Copyright 2012 Bull S.A.S.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
		md.update(utf8(String.valueOf(paste.getTimestamp())));
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

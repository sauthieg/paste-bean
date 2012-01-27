package org.ow2.jonas.azure.pastebean.service;
import javax.ejb.Local;

import org.ow2.jonas.azure.pastebean.model.Paste;

@Local
public interface HashService {

	String createHash(Paste paste);
}

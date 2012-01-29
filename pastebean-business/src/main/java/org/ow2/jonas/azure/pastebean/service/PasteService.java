package org.ow2.jonas.azure.pastebean.service;
import java.util.List;

import javax.ejb.Local;

import org.ow2.jonas.azure.pastebean.model.Paste;

@Local
public interface PasteService {
  Paste createPaste(String name, String author, String desc, String content);
  void deletePaste(Paste paste);
  List<Paste> findPastes();
  List<Paste> findMatchingPastes(String hashFragment);
  List<Paste> findLatestPastes(int i);
}

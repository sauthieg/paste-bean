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

package org.ow2.jonas.azure.pastebean.service;
import java.util.List;

import javax.ejb.Local;

import org.ow2.jonas.azure.pastebean.model.Paste;

@Local
public interface PasteService {
  Paste createPaste(String title, String author, String desc, String content);
  void deletePaste(Paste paste);
  List<Paste> findPastes();
  List<Paste> findMatchingPastes(String hashFragment);
  List<Paste> findLatestPastes(int i);
  List<Paste> searchPastes(String token);
}

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

import java.util.Date;

import org.ow2.jonas.azure.pastebean.model.Paste;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * A {@code HashServiceBeanTestCase} is ...
 *
 * @author Guillaume Sauthier
 */
public class HashServiceBeanTestCase {

    @Test
    public void testUsualCreateHash() throws Exception {
        HashServiceBean bean = new HashServiceBean();

        Paste paste = new Paste();
        paste.setAuthor("Guillaume");
        paste.setDescription("Test paste");
        paste.setTimestamp(System.currentTimeMillis());
        paste.setContent("Pasted content");
        
        String hash = bean.createHash(paste);
        Assert.assertNotNull(hash);
    }

    @Test
    public void testCreateHashWithOptionalFields() throws Exception {
        HashServiceBean bean = new HashServiceBean();

        // author is optional
        Paste paste = new Paste();
        paste.setAuthor(null);
        paste.setDescription("Test paste");
        paste.setTimestamp(System.currentTimeMillis());
        paste.setContent("Pasted content");

        String hash = bean.createHash(paste);
        Assert.assertNotNull(hash);

        // description is optional
        paste = new Paste();
        paste.setAuthor("Guillaume");
        paste.setDescription(null);
        paste.setTimestamp(System.currentTimeMillis());
        paste.setContent("Pasted content");

        String hash2 = bean.createHash(paste);
        Assert.assertNotNull(hash2);
    }

    @Test
    public void testSamePasteHasSameHash() throws Exception {
        HashServiceBean bean = new HashServiceBean();

        Paste paste = new Paste();
        paste.setAuthor("Guillaume");
        paste.setDescription("Test paste");
        paste.setTimestamp(System.currentTimeMillis());
        paste.setContent("Pasted content");

        String hash = bean.createHash(paste);
        String hash2 = bean.createHash(paste);
        Assert.assertEquals(hash, hash2);
    }

}

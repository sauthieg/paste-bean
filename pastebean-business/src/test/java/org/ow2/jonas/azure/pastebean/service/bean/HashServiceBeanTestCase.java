/**
 * JOnAS: Java(TM) Open Application Server
 * Copyright (C) 2012 Bull S.A.S.
 * Contact: jonas-team@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 *
 * --------------------------------------------------------------------------
 * $Id: $
 * --------------------------------------------------------------------------
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
        paste.setCreationDate(new Date());
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
        paste.setCreationDate(new Date());
        paste.setContent("Pasted content");

        String hash = bean.createHash(paste);
        Assert.assertNotNull(hash);

        // description is optional
        paste = new Paste();
        paste.setAuthor("Guillaume");
        paste.setDescription(null);
        paste.setCreationDate(new Date());
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
        paste.setCreationDate(new Date());
        paste.setContent("Pasted content");

        String hash = bean.createHash(paste);
        String hash2 = bean.createHash(paste);
        Assert.assertEquals(hash, hash2);
    }

}

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

package org.ow2.jonas.azure.pastebean.bean;

import org.ow2.jonas.azure.pastebean.model.Paste;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 29/01/12
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
public class AgeBuilder {
    private Paste paste;

    public Paste getPaste() {
        return paste;
    }

    public void setPaste(Paste paste) {
        this.paste = paste;
    }
    
    public String getAge() {

        StringBuilder sb = new StringBuilder();

        long diff = System.currentTimeMillis() - paste.getTimestamp();

        long inSeconds = diff / 1000;

        long remainingSeconds = inSeconds % 60;
        long inMinutes = (inSeconds - remainingSeconds) / 60;

        if (inMinutes > 0) {
            sb.append(inMinutes);
            sb.append(" minutes ");
        }

        sb.append(remainingSeconds);
        sb.append(" seconds");
        sb.append(" ago");

        return sb.toString();
    }
}

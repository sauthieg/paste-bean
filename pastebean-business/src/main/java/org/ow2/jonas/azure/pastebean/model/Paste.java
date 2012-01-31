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

package org.ow2.jonas.azure.pastebean.model;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Paste
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "all",
            query = "SELECT p FROM Paste p ORDER BY p.timestamp DESC"),
    @NamedQuery(name = "from_hash",
            query = "SELECT p FROM Paste p WHERE p.hash LIKE :pattern"),
        @NamedQuery(name = "search",
        query = "SELECT DISTINCT p FROM Paste p " +
                "WHERE p.author LIKE :token OR " +
                "      p.title LIKE :token OR " +
                "      p.description LIKE :token OR " +
                "      p.content LIKE :token " +
                "ORDER BY p.timestamp DESC")
})
public class Paste implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id
	private String hash;
	private String author;
    private String title;
	private String description;
    @Column(length = 4096)
    private String content;

    private Long timestamp;

	public Paste() {
		super();
	}   
	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}   
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}   
	public Long getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
   
}

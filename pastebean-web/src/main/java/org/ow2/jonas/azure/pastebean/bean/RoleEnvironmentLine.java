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


package org.ow2.jonas.azure.pastebean.bean;

import com.microsoft.windowsazure.serviceruntime.Role;
import com.microsoft.windowsazure.serviceruntime.RoleInstance;
import com.microsoft.windowsazure.serviceruntime.RoleInstanceEndpoint;

/**
 * A {@code RoleEnvironmentLine} is ...
 *
 * @author Guillaume Sauthier
 */
public class RoleEnvironmentLine {
    private Role role;
    private RoleInstance instance;
    private RoleInstanceEndpoint endpoint;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public RoleInstance getInstance() {
        return instance;
    }

    public void setInstance(RoleInstance instance) {
        this.instance = instance;
    }

    public RoleInstanceEndpoint getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(RoleInstanceEndpoint endpoint) {
        this.endpoint = endpoint;
    }
}

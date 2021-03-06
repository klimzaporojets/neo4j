/**
 * Copyright (c) 2002-2012 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.neo4j.cluster.member.paxos;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.URI;

/**
 * This message is broadcast when a member of the cluster declares that
 * it is not ready to serve a particular role for the cluster.
 */
public class MemberIsUnavailable
        implements Externalizable
{
    private String role;
    private URI clusterUri;

    public MemberIsUnavailable()
    {
    }

    public MemberIsUnavailable( String role, URI clusterUri)
    {
        this.role = role;
        this.clusterUri = clusterUri;
    }

    public String getRole()
    {
        return role;
    }

    public URI getClusterUri()
    {
        return clusterUri;
    }

    @Override
    public void writeExternal( ObjectOutput out ) throws IOException
    {
        out.writeUTF( role );
        out.writeUTF( clusterUri.toString() );
    }

    @Override
    public void readExternal( ObjectInput in ) throws IOException, ClassNotFoundException
    {
        role = in.readUTF();
        clusterUri = URI.create( in.readUTF() );
    }
}

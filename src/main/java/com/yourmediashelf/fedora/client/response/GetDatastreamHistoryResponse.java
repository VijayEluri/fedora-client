/**
 * Copyright (C) 2010 MediaShelf <http://www.yourmediashelf.com/>
 *
 * This file is part of fedora-client.
 *
 * fedora-client is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * fedora-client is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with fedora-client.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.yourmediashelf.fedora.client.response;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.ClientResponse;
import com.yourmediashelf.fedora.client.FedoraClientException;
import com.yourmediashelf.fedora.client.request.GetDatastreamHistory;
import com.yourmediashelf.fedora.generated.management.DatastreamHistory;

/**
 * <p>A {@link FedoraResponse} for the {@link GetDatastreamHistory} request.</p>
 *
 * @author Edwin Shin
 * @since 0.1.1
 */
public class GetDatastreamHistoryResponse
        extends FedoraResponseImpl {
    private DatastreamHistory datastreamHistory;

    public GetDatastreamHistoryResponse(ClientResponse cr) throws FedoraClientException {
        super(cr);
    }

    /**
     * Get the datastreamHistory. Note this method will fail if the request
     * specified format=html rather than xml.
     *
     * @return the DatastreamHistory
     * @throws FedoraClientException
     */
    public DatastreamHistory getDatastreamProfile() throws FedoraClientException {
        if (datastreamHistory == null) {
            try {
                JAXBContext context = JAXBContext
                        .newInstance("com.yourmediashelf.fedora.generated.management");
                Unmarshaller unmarshaller = context.createUnmarshaller();
                datastreamHistory =
                        (DatastreamHistory) unmarshaller
                                .unmarshal(new BufferedReader(new InputStreamReader(getEntityInputStream())));
            } catch (JAXBException e) {
                throw new FedoraClientException(e.getMessage(), e);
            }
        }
        return datastreamHistory;
    }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zeppelin.rest;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.zeppelin.server.JsonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Zeppelin security rest api endpoint.
 *
 */
@Path("/sendMail")
@Produces("application/json")
public class SendMailRestApi {
  /*** Required by Swagger.*/
  private static final Logger LOGGER = LoggerFactory.getLogger(SendMailRestApi.class);

  /**
    * Get ticket Returns username & ticket for anonymous access, username is
    * always anonymous. After getting this ticket, access through websockets
    * become safe
    *
    * @return 200 response
  */
  @POST
  public Response cloneNote(String html) throws IOException, IllegalArgumentException {
    LOGGER.info(html);
    return new JsonResponse<>(Status.OK).build();
  }
  /**
    * Get ticket Returns username & ticket for anonymous access, username is
    * always anonymous. After getting this ticket, access through websockets
    * become safe
    *
    * @return 200 response
  */
  @GET
  @Path("status")
  public Response statusOk() {
    return new JsonResponse<>(Status.OK).build();
  }
}

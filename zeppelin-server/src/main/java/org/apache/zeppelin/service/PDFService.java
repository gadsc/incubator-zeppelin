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

package org.apache.zeppelin.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.pdf.PdfWriter;

/**
 * Zeppelin security rest api endpoint.
 */
public class PDFService {
  /**
   * Get ticket Returns username & ticket for anonymous access, username is
   * always anonymous. After getting this ticket, access through websockets
   * become safe
   *
   * @return 200 response
  */
  public byte[] createPdfFromHtmlAsByteArray(String html) {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    try {
      Document document = new Document(PageSize.A4);
      PdfWriter.getInstance(document, bos);
      document.open();
      HTMLWorker hw = new HTMLWorker(document);
      hw.parse(new StringReader(html));
      document.close();
    } catch (DocumentException | IOException e) {
      throw new RuntimeException(e);
    }
    return bos.toByteArray();
  }
}

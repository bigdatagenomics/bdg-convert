/**
 * Licensed to Big Data Genomics (BDG) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The BDG licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bdgenomics.convert.ga4gh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import org.bdgenomics.convert.Converter;
import org.bdgenomics.convert.ConversionException;
import org.bdgenomics.convert.ConversionStringency;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for BdgenomicsOntologyTermToGa4ghOntologyTerm.
 */
public final class BdgenomicsOntologyTermToGa4ghOntologyTermTest {
    private final Logger logger = LoggerFactory.getLogger(BdgenomicsOntologyTermToGa4ghOntologyTermTest.class);
    private Converter<org.bdgenomics.formats.avro.OntologyTerm, ga4gh.Common.OntologyTerm> ontologyTermConverter;

    @Before
    public void setUp() {
        ontologyTermConverter = new BdgenomicsOntologyTermToGa4ghOntologyTerm();
    }

    @Test
    public void testConstructor() {
        assertNotNull(ontologyTermConverter);
    }

    @Test(expected=ConversionException.class)
    public void testConvertNullStrict() {
        ontologyTermConverter.convert(null, ConversionStringency.STRICT, logger);
    }

    @Test
    public void testConvertNullLenient() {
        assertNull(ontologyTermConverter.convert(null, ConversionStringency.LENIENT, logger));
    }

    @Test
    public void testConvertNullSilent() {
        assertNull(ontologyTermConverter.convert(null, ConversionStringency.SILENT, logger));
    }

    @Test
    public void testConvert() {
        org.bdgenomics.formats.avro.OntologyTerm ontologyTerm = org.bdgenomics.formats.avro.OntologyTerm.newBuilder()
            //.setDb("GO")
            //.setAccession("0046703")
            .build();

        ga4gh.Common.OntologyTerm expected = ga4gh.Common.OntologyTerm.newBuilder()
            // not sure how these map to DBTAG:ID format
            //.setTermId("")
            //.setTerm("")
            .build();

        assertEquals(expected, ontologyTermConverter.convert(ontologyTerm, ConversionStringency.STRICT, logger));
    }
}

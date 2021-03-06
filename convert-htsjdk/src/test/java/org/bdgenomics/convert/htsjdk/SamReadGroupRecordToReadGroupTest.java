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
package org.bdgenomics.convert.htsjdk;

import static org.bdgenomics.convert.ConversionStringency.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collections;
import java.util.List;

import htsjdk.samtools.SAMReadGroupRecord;

import org.bdgenomics.convert.Converter;
import org.bdgenomics.convert.ConversionException;

import org.bdgenomics.formats.avro.ReadGroup;

import org.junit.Before;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Unit test for SamReadGroupRecordToReadGroup.
 */
public final class SamReadGroupRecordToReadGroupTest {
    private final Logger logger = LoggerFactory.getLogger(SamReadGroupRecordToReadGroupTest.class);

    SAMReadGroupRecord record;
    SAMReadGroupRecord nullReadGroupId;
    SAMReadGroupRecord nullSample;
    Converter<SAMReadGroupRecord, ReadGroup> converter;

    @Before
    public void setUp() {
        record = new SAMReadGroupRecord("SRR062634");
        record.setSample("HG00096");

        nullReadGroupId = new SAMReadGroupRecord(null);
        nullReadGroupId.setSample("HG00096");

        nullSample = new SAMReadGroupRecord("SRR062634");
        nullSample.setSample(null);

        converter = new SamReadGroupRecordToReadGroup();
    }

    @Test
    public void testConstructor() {
        assertNotNull(converter);
    }

    @Test(expected=ConversionException.class)
    public void testConvertNullSourceStrict() {
        converter.convert(null, STRICT, logger);
    }

    @Test
    public void testConvertNullSourceLenient() {
        assertEquals(null, converter.convert(null, LENIENT, logger));
    }

    @Test
    public void testConvertNullSourceSilent() {
        assertEquals(null, converter.convert(null, SILENT, logger));
    }

    @Test(expected=ConversionException.class)
    public void testConvertNullReadGroupIdStrict() {
        converter.convert(nullReadGroupId, STRICT, logger);
    }

    @Test
    public void testConvertNullReadGroupIdLenient() {
        assertEquals(null, converter.convert(nullReadGroupId, LENIENT, logger));
    }

    @Test
    public void testConvertNullReadGroupIdSilent() {
        assertEquals(null, converter.convert(nullReadGroupId, SILENT, logger));
    }

    @Test(expected=ConversionException.class)
    public void testConvertNullSampleStrict() {
        converter.convert(nullSample, STRICT, logger);
    }

    @Test
    public void testConvertNullSampleLenient() {
        assertEquals(null, converter.convert(nullSample, LENIENT, logger));
    }

    @Test
    public void testConvertNullSampleSilent() {
        assertEquals(null, converter.convert(nullSample, SILENT, logger));
    }

    @Test
    public void testConvert() {
        // todo
    }
}

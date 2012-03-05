/*
 * Copyright 2012 FuseSource
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.fusesource.byexample.filebatchsplitter;

import org.apache.camel.spi.BrowsableEndpoint;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class CamelContextXmlTest extends CamelSpringTestSupport {

	private static final String OUTBOX_DIR = "target/data/outbox";

	private static final int EXPECTED_NUMBER_OF_LINE_ITEMS = 5;

	private static final long MAX_WAIT_TIME_MILLIS = 10 * 1000;

	/**
	 * This test relies on the batch file 'order1.xml' in the src/data/inbox dir which includes five line items.
	 * The Camel route under test will create one file per line item in the target/data/outbox dir.
	 */
	@Test
	public void testCamelRoute() throws Exception {

		int numLineItemsReceived = 0;

		long endTime = System.currentTimeMillis() + MAX_WAIT_TIME_MILLIS;

		BrowsableEndpoint outbox = context.getEndpoint("file:" + OUTBOX_DIR, BrowsableEndpoint.class);

		while (numLineItemsReceived < EXPECTED_NUMBER_OF_LINE_ITEMS && System.currentTimeMillis() < endTime) {
			Thread.sleep(200);
			numLineItemsReceived = outbox.getExchanges().size();
		}

		assertTrue("Test failed: received " + numLineItemsReceived + " line items, but expected " + EXPECTED_NUMBER_OF_LINE_ITEMS,
				numLineItemsReceived == EXPECTED_NUMBER_OF_LINE_ITEMS);
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		cleanOutbox();
	}
	
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		cleanOutbox();
	}
	
	private void cleanOutbox(){
		deleteDirectory(OUTBOX_DIR);
		createDirectory(OUTBOX_DIR);
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}
}

/*
 * Copyright (C) Red Hat, Inc.
 * http://www.redhat.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fusesource.byexample.filebatchsplitter;

import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.test.junit4.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class CamelContextXmlTest extends CamelSpringTestSupport {

	private static final int EXPECTED_NUMBER_OF_LINE_ITEMS = 5;

	private static final String OUTBOX_DIR = "target/data/outbox";

	/**
	 * This test relies on the batch file 'order1.xml' in the src/data/inbox dir
	 * which includes five line items. The Camel route under test should create
	 * one file per line item in the target/data/outbox dir.
	 */
	@Test
	public void testCamelRoute() throws Exception {

		// Mock the file endpoints
		context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
				mockEndpoints("file*");
			}
		});

		// make sure we get the right number of line items
		getMockEndpoint("mock:file:" + OUTBOX_DIR).expectedMessageCount(EXPECTED_NUMBER_OF_LINE_ITEMS);
		
		assertMockEndpointsSatisfied();
	}

	@Override
	public void setUp() throws Exception {
		super.setUp();
		deleteDirectory(OUTBOX_DIR);
		createDirectory(OUTBOX_DIR);
	}

	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		deleteDirectory(OUTBOX_DIR);
	}

	@Override
	protected ClassPathXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
	}
}

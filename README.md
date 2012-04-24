File Batch Splitter Camel Project
=================================

This project demonstrates how to consume files from the file system and split the content of the file into multiple messages using Apache Camel.

The sample implements the Splitter EIP (Enterprise Integration Pattern) which splits an incoming message into a series of outgoing messages. Each of the outgoing messages contains a piece of the original message.

A sample incoming message file 'batch1.xml' is located in src/data/inbox.  When the route is run, the split result files will be located in target/data/outbox.

This project was created with FuseSource Camel IDE available at http://fusesource.com/products/fuse-ide-camel/ which gives you a nice visualization of your routes, the ability to drag and drop Apache Camel components to design and edit routes, and testing capabilities within the Eclipse IDE.

To build the project and execute the unit test, execute the following command:
> mvn clean install

To run the example project as a standalone Camel route, execute the following command:
> mvn camel:run

For more help see the Apache Camel documentation

http://camel.apache.org/splitter.html
    
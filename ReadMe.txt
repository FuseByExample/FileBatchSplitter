File Batch Splitter Camel Project
=================================

This project demonstrates how to consume files from the file system and
split the content of the file into multiple messages using Apache Camel.

The sample basically implements the Splitter EIP (Enterprise Integration Pattern) 
which splits an incoming message into a series of outgoing messages. Each of the 
outgoing messages contains a piece of the original message.


This project was created with FuseSource Camel IDE available at http://fusesource.com/products/fuse-ide-camel/ 
which gives you a nice visualization of your routes, ability to drag and drop for the Apache Camel components and 
testing capabilities right from the Eclipse IDE.

To run the example project as a standalone Camel route you can execute the following command:
>mvn camel:run

For more help see the Apache Camel documentation

    http://camel.apache.org/splitter.html
    
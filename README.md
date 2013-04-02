# File Batch Splitter Camel Project

This project demonstrates how to consume files from the file system and split
the content of the file into multiple messages using Apache Camel.

The sample implements the Splitter EIP (Enterprise Integration Pattern), which
splits an incoming message into a series of outgoing messages. Each of the
outgoing messages contains a piece of the original message.

A sample incoming message file 'order1.xml' is located in src/data/inbox. When
the route is run, the split result files will be located in target/data/outbox.

This project was created with FuseSource Camel IDE available at
http://fusesource.com/products/fuse-ide-camel/ which gives you a nice visualization
of your routes, the ability to drag and drop Apache Camel components to design
and edit routes, and testing capabilities within the Eclipse IDE.

To build the project and execute the unit test, execute the following command:

    mvn clean install

To run the example project as a standalone Camel route, execute the following command:

    mvn camel:run

For more help see the Apache Camel documentation

http://camel.apache.org/splitter.html

---

# File batch Splitter with Fuse

You can also run this bundle by installing it into JBoss Fuse

To build the project and execute the unit test, execute the following command:

    $PROJECT_HOME> mvn clean install

Start JBoss Fuse, by running the included start script

    $JBOSS_FUSE_HOME> ./bin/fuse

Now You can install the bundle using the Fuse console

    JBossFuse:karaf@root> osgi:install mvn:com.fusesource.byexample/FileBatchSplitter/1.0.1

You can see that the bundle is installed by running the list command

    JBossFuse:karaf@root> list

    ...
    [ 250] [Installed  ] [            ] [       ] [   60] File Batch Splitter (1.0.1)
    ...

In this case, 250 is the bundle id. This can be different on your instance of
Fuse. You can start this bundle by executing `osgi:start (bundle-id)`.

    JBossFuse:karaf@root> osgi:start 250
    JBossFuse:karaf@root> list

    ...
    [ 250] [Active     ] [            ] [Started] [   60] File Batch Splitter (1.0.1)
    ...

To activate this route, you have to copy the `order1.xml` file to
the `$JBOSS_FUSE_HOME/src/data/inbox` folder. So you open a new console...

    $PROJECT_HOME> cp src/data/inbox/order1.xml $JBOSS_FUSE_HOME/src/data/inbox/

If we go to the `$JBOSS_FUSE_HOME/target/data/outbox` folder, you can see te resulting files.

    item1-2013032213582300693.xml
    item2-2013032213582300693.xml
    item3-2013032213582300693.xml
    item4-2013032213582300693.xml
    item5-2013032213582300693.xml

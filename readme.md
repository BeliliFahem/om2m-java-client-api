# About OM2M

For a Java developer, develop solutions based on "Open M2M" server while enjoying all its features and services, is another concern in addition to business concerns. It is from this realization that the need for an API to communicate with the server "Open M2M" by object %100 Java, was born.

OM2M provides an open source service platform for M2M interoperability based on the ETSI-M2M standard. OM2M follows a RESTful approach with open interfaces to enable developing services and applications independently of the underlying network. It proposes a modular architecture running on top of an OSGi layer, making it highly extensible via plugins. It supports multiple protocol bindings such as HTTP and CoAP. Various interworking proxies are provided to enable seamless communication with vendor-specific technologies such as Zigbee and Phidgets devices. (from http://www.eclipse.org/om2m/)

See more about OM2M Project : http://www.eclipse.org/om2m/.

#Upadate 26-06-2015 :
* Retrieve all applications, from the server, as list of Java objects. 

# Implemented functions in this version
* Create an application
* Create a container
* Create a content Instance
* Verify if an application exist
* Verify if a container exist

# Implemented functions in this version
* Manage OM2M server responses

# How to use this API
Befor any manipulation with this API, verify if your OM2M plateform is running well (http://wiki.eclipse.org/OM2M/REST_API)

## Requirements 
* Local or distante OM2M plateform : host, port and Basic Authorization header. See http://wiki.eclipse.org/OM2M/REST_API
* JAVA 1.7 or higher version
* If you wante to build OM2M-java-client-api from sources, you need Apaceh Maven 3.
Else download the API from here.
* Include the API in your projet 

## How to create an application
```java
Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

// create an application "MyApp1"
OM2MApplication myApp1 = new OM2MApplication();

myApp1.setAppId("MyApp1");

List<String> searchStrings = Arrays.asList(new String[] { "A/Z", "B/Y", "C/X" });
myApp1.setSearchStrings(searchStrings);

ApplicationManager appManager = (ApplicationManager)   Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
appManager.create(myApp1);
```

## How to create a container
```java
// create a container for data named "MyData1"
Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

OM2MApplication myApp1 = new OM2MApplication("MyApp1");

ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);

OM2MContainer container = new OM2MContainer("MyData1", "MyApp1");
containerManager.create(container);
```

## How to create a contentInstence
```java
// create a data contentInstance
Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

ContentInstanceManager contentInstanceManager = (ContentInstanceManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTENT_INSTANCE_MANAGER);

contentInstanceManager.create(new OM2MContentInstance("MyData1", "MyData1", "60"));
```

## How to verify if an application exist
```java
Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");

// create OM2MApplication java object
OM2MApplication myApp1 = new OM2MApplication("MyApp1");

ApplicationManager appManager = (ApplicationManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.APP_MANAGER);
System.out.println( appManager.exist(myApp1));
```

## How to verify if a container exist
```java
Om2mManagersFactorty.configure("http://127.0.0.1:8080", "Basic YWRtaW46YWRtaW4=");
		
ContainerManager containerManager = (ContainerManager) Om2mManagersFactorty.getManager(Om2mManagersFactorty.CONTAINER_MANAGER);
		
OM2MContainer container = new OM2MContainer("MyApp6", "MyData1");
System.out.println( containerManager.exist(container) );
```

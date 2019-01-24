# coffee-works -  Find your favourite coffee
Company: <b>IBA Group</b> <br>
Author: <b>Mr Crader</b> <br>
Technologies: <b>EJB, JPA, JAX-RS, BeanValidation, JUnit</b> <br>
DBMS: <b>MySQL, PostgreSQL</b> <br>
Target Project: <b>WildFly</b> <br>
Source: <a href="https://gitlab.com/mrcrader/coffee-works/">GitLab</a> <br>

System requirements
-------------------

All you need to build this project is Java 7.0 (Java SDK 1.7) or better, Maven 3.1 or better.

The application this project produces is designed to be run on JBoss WildFly.

 
Configure Maven
---------------

If you have not yet done so, you must [Configure Maven](https://github.com/jboss-developer/jboss-developer-shared-resources/blob/master/guides/CONFIGURE_MAVEN.md) before testing the project.


Start JBoss WildFly with the Web Profile
-------------------------

1. Open a command line and navigate to the root of the JBoss server directory.
2. The following shows the command line to start the server with the web profile:

        For Linux:   JBOSS_HOME/bin/standalone.sh
        For Windows: JBOSS_HOME\bin\standalone.bat

 
Build and Deploy the Project
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](https://github.com/jboss-developer/jboss-eap-quickstarts#build-and-deploy-the-quickstarts) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build and deploy the archive:

        mvn clean package wildfly:deploy

4. This will deploy `target/coffee-works.war` to the running instance of the server.
 

Access the application 
---------------------

The application will be running at the following URL: <http://localhost:8080/coffee-works/>.


Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn wildfly:undeploy

## Jackson
For Jackson activator insert this into pom.xml

```xml
<!-- Import for @JsonIgnoreProperties -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-annotations</artifactId>
    <version>2.9.7</version>
</dependency>
```
		
## CORS
Unfortunately, by default we cannot use CORS on WildFly. Like a result, we also cannot use Angular front end.
But, we can simply enable Cross Origin Access on WildFly server for all projects.
OK, go to your WildFly home directory, then standalone->configuration->standalone.xml
Find and insert this code (here an example for WildFly 12):

```xml
    <subsystem xmlns="urn:jboss:domain:undertow:5.0">
                <buffer-cache name="default"/>
                <server name="default-server">
                    <http-listener name="default" socket-binding="http" redirect-socket="https" enable-http2="true"/>
                    <https-listener name="https" socket-binding="https" security-realm="ApplicationRealm" enable-http2="true"/>
                    <host name="default-host" alias="localhost">
                        <location name="/" handler="welcome-content"/>
                        <filter-ref name="server-header"/>
                        <filter-ref name="x-powered-by-header"/>
                        <filter-ref name="Access-Control-Allow-Origin"/>
                        <filter-ref name="Access-Control-Allow-Methods"/>
                        <filter-ref name="Access-Control-Allow-Headers"/>
                        <filter-ref name="Access-Control-Allow-Credentials"/>
                        <filter-ref name="Access-Control-Max-Age"/>
                    </host>
                </server>
                <servlet-container name="default">
                    <jsp-config/>
                    <websockets/>
                </servlet-container>
                <handlers>
                    <file name="welcome-content" path="${jboss.home.dir}/welcome-content"/>
                </handlers>
                <filters>
                    <response-header name="server-header" header-name="Server" header-value="WildFly/12"/>
                    <response-header name="x-powered-by-header" header-name="X-Powered-By" header-value="Undertow/1"/>
                    <response-header name="Access-Control-Allow-Origin" header-name="Access-Control-Allow-Origin" header-value="http://localhost:4200"/>
                    <response-header name="Access-Control-Allow-Methods" header-name="Access-Control-Allow-Methods" header-value="GET, POST, OPTIONS, PUT, DELETE"/>
                    <response-header name="Access-Control-Allow-Headers" header-name="Access-Control-Allow-Headers" header-value="accept, authorization, content-type, x-requested-with"/>
                    <response-header name="Access-Control-Allow-Credentials" header-name="Access-Control-Allow-Credentials" header-value="true"/>
                    <response-header name="Access-Control-Max-Age" header-name="Access-Control-Max-Age" header-value="1"/>
                </filters>
    </subsystem>
```
    
header-value="http://localhost:4200"/ is a default url for Angular project. You can change it for custom.
Clean your project and enjoy!

Read about how to enable <b>CORS</b> on Wildfly. 
<a href="https://forum.camunda.org/t/enable-cors-on-wildfly/673">Click</a>

## The system cannot find the path specified for WildFly

I ran into this problem when I was trying to add a new user to WildFly server.
After more than hour and tons of forums posts and  articles I found the solution for me. Actually I have the same problem like on this <a href = https://www.experts-exchange.com/questions/28004843/jboss-as-7.html>forum</a>.
<br>
Ok, first of all set up your JAVA_HOME variable (eg C:\Program Files\Java\jdk1.8.0_172). <br>
Hah, it is very important <b>not to use \bin after java version</b>. Why? Listen. Go to your WildFly directory, then bin\ and <b>jdr.bat</b> . Right click and Edit. Find JAVA_HOME and see the following code: <br>
```
    rem Setup JBoss specific properties
    if "x%JAVA_HOME%" == "x" (
      set  JAVA=java
      echo JAVA_HOME is not set. Unexpected results may occur.
      echo Set JAVA_HOME to the directory of your local JDK to avoid this message.
    ) else (
      set "JAVA=%JAVA_HOME%\bin\java"
```
See this? Thw system already added bin\java to your system path.

<p>
Summary. Just add JAVA_HOME variable for your jdk without bin folder!
</p>

## Admin console for WildFly

Some troubles with port 9990? Use this address http://127.0.0.1:9990

## JUnit Tests
Here is a table for all kinds of JUnit tests

| Test Reason            | Link                                                                                                |
| -----------------------|:---------------------------------------------------------------------------------------------------:|
| REST Endpoint Test     | [visit](https://gitlab.com/mrcrader/coffee-works/blob/master/Docs/JUnitExampleForRestWebServices.md)|
| DAO Test               | <a href ="#">Click</a>                                                                              |

# simple-servlet-container

The project is managed by maven.
The main class is com.mituta.container.SimpleServletContainer.

After starting, the container will listen to HTTP request at the 8080 port.

Servlet configuration is available in src/main/resources/web.xml file.

The example of a request with default configuration is:
http://localhost:8080/sample/hello-world

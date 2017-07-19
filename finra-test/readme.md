This code sample is an apache camel-based RESTful service.

Highlights;

1. Interface Flexibility: 
   This service is built to be very flexible in the kinds of payloads it supports. Using a combination of Java inheritance and JAXB's @XmlAnyElement,
   we are able to support literally any defined datatatype that extends the FINRACommand and FINRACommandResponse abstract class. 
   This way, this service is able to flexibly support new request and payload types. The service exposes a single, consistent entry point without 
   limitation to the kinds of payloads it can support.
2. Versioning 
   This service is written from the ground-up with versioning. The packages in the project are versioned, to support durability and flexibility in functionality
   With versioning in the packages, we can introduce new functionality by incrementing version numbers without breaking existing functionality
3. Clean separation of concerns/testability
   The functions of this service (file uploads, metadata retrieval and uploads) are separated into different routes, so that each function can be 
   tested in isolation.
4. Flexible Design
   The application is underpinned by the command pattern, a facade and a content-based router: incoming XML payloads are routed from the FrontendRouteBuilder
   to the common RoutingTransformer. This component then helps to set the request type, which will be used by the front-end router to route to the intended camel routes and 
   destinations by introspecting the XML payload to determine the payload type. Combined with the flexibility of @XmlAnyElement, we are able to 
   route incoming messages to any number of destinations based on the request type

This is a Springboot-Camel application that demonstrates the concept of a TrustLine. Apart from using Camel as glue code, there are the following high-level components:

1. TrustLineRequestValidator ensures that requests sent to the server meet some basic criteria
2. TrustLineFactoryImpl supplies instances of TrustLineImpl which is a basic implementation of a trust line
3. TrustLineRepositoryImpl acts as a store of trustlines, mapped by identifier-trustline
4. TrustLineRequestProcessor handles the business logic of updating the transactions inside a trustline
5. The Controller route is the main entry point into the application.

Everything else is largely trivial Camel glue code. 

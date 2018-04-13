1. TrustLineRequestValidator ensures that requests sent to the server meet some basic criteria
2. TrustLineFactoryImpl supplies instances of TrustLineImpl which is a basic implementation of a trust line
3. TrustLineRepositoryImpl acts as a store of trustlines, mapped by identifier-trustline
4. TrustLineRequestProcessor handles the business logic of updating the transactions inside a trustline
5. The Controller route is the main entry point into the application.

Everything else is largely trivial Camel glue code. 

To run:

1. At the command-line, "mvn spring-boot:run".
2. HTTP POST the sample request to http://localhost:8080/trustline-processor/trustline/xyz/debit to trigger a debit request against the trustline. This means that xyz will request the amount of "value" specified in the sample payload from the partner server, i.e. this server will owe the partner server the "value" amount
3. HTTP POST the sample request to http://localhost:8080/trustline-processor/trustline/xyz/credit to trigger a credit request against the trustline. This means that xyz will send the amount of "value" specified in the sample payload to the partner server, i.e. this server will be in credit against the partner server the "value" amount

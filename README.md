# Spring Security + SAML and Database Authentication
 
This example shows how to build a Spring Boot application that leverages Spring Security for SAML and database authentication.

Please read [Spring Security SAML and Database Authentication](https://developer.okta.com/blog/2020/10/14/spring-security-saml-database-authentication) to see how this example was created. 

**Prerequisites:** 

* [Java 11](https://adoptopenjdk.net/)+
* An [Okta Developer Account](https://developer.okta.com/signup/)

> [Okta](https://developer.okta.com/) has Authentication and User Management APIs that reduce development time with instant-on, scalable user infrastructure. Okta's intuitive API and expert support make it easy for developers to authenticate, manage and secure users and roles in any application.

* [Getting Started](#getting-started)
* [Links](#links)
* [Help](#help)
* [License](#license)

## Getting Started

To install this example application, run the following commands:

```bash
git clone https://github.com/oktadeveloper/okta-spring-security-saml-db-example.git
cd okta-spring-security-saml-db-example
```

This will get a copy of the project installed locally. To install all of its dependencies and start each app, follow the instructions below.

### Spring Security Configuration

Please see [Set Up Your Okta Account with SAML and Run the Application](https://developer.okta.com/blog/2020/10/14/spring-security-saml-database-authentication#set-up-your-okta-account-with-saml-and-run-the-application) to see how to configure a SAML app on Okta.

After you've configured everything, start your app.

```bash
mvn spring-boot:run
```

Open your browser to `http://localhost:8080` and login using SAML or database credentials.

## Links

This example uses the following open source libraries from Okta:

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security SAML](https://spring.io/projects/spring-security-saml)

## Help

Please post any questions as comments on the [blog post](https://developer.okta.com/blog/2020/10/14/spring-security-saml-database-authentication), or visit our [Okta Developer Forums](https://devforum.okta.com/).

## License

Apache 2.0, see [LICENSE](LICENSE).

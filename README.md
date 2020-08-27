# okta-saml-spring-boot

A Spring Boot application combining standard database authentication with SAML 2.0 authentication via Okta ( https://www.okta.com/ )

## Get Running

#### Step 1
Clone this project

#### Step 2
Sign up for a free trial account at https://www.okta.com/free-trial/ (this is required to create SAML 2.0 applications in Okta)

#### Step 3
Log in to your Okta at `https://my-okta-domain.okta.com`

#### Step 4
Create a new application via `Admin > Applications > Add Application > Create New App` with the following settings:

* Platform: `Web`
* Sign On Method: `SAML 2.0`
* App Name: `Spring Boot DB/SAML` (or whatever you'd like)
* Single Sign On URL: `http://localhost:8080/saml/SSO`
* Use this for Recipient URL and Destination URL: `YES`
* Audience URI: `http://localhost:8080/saml/metadata`
* `I'm an Okta customer adding an internal app`
* `This is an internal app that we have created`

#### Step 5
Navigate to `Assignments > Assign to People`

#### Step 6
Assign to your account with the custom username samluser@oktaauth.com

#### Step 7
Navigate to `Sign On > View Setup Instructions` and copy the following values to your `/src/main/resources/application.properties` file
* `saml.metadataUrl` -- Identity Provider Metadata URL
* `saml.idp` -- Identity Provider Issuer

#### Step 8
Run your Spring Boot application in your IDE or via Maven:<br>
`mvn install`<br>
`mvn spring-boot:run`

#### Step 9
Navigate to your application's home page at http://localhost:8080

#### Step 10
* For DB Authentication, log in using `dbuser@dbauth.com / oktaiscool`
* For SAML/Okta Authentication, log in using `samluser@oktaauth.com`
    * You should be redirected to the SAML/Okta auth flow and returned to your application following successful authentication
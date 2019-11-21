Simple Document Extraction Service
========

A spring boot application I once did as a coding task for a job application.

## Objectives
* create an API service that accepts a scanned document (various image format)
* extracts textual information
* persist the resulting data structure into a database
* return the extracted result to the user
* deliverable: docker-compose.yml

## Build Docker Image
```
mvn install
```
! Credentials needed at path ./build !

## Notes
### Design Decision
As this was a coding task for a job interview that was timeboxed to 4 hours, I chose an in-memory db (h2).

### Credentials
AWS Textract credentials are needed for this application to run successfully. (See: https://docs.aws.amazon.com/amazonswf/latest/awsrbflowguide/set-up-creds.html)


### Links
AWS Textract, API Reference: https://docs.aws.amazon.com/de_de/textract/latest/dg/API_Reference.html
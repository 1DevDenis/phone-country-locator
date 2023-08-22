# Phone country locator

Application to determine the country by phone number

## Project build

mvn clean package

## Application launch

java -jar target/phone-country-locator-0.0.1-SNAPSHOT.jar

## Run tests

mvn test

## Generate a report

mvn surefire-report:report

The generated report will be available in the directory: **target/site/surefire-report.html**
or open with the command: **xdg-open target/site/surefire-report.html**

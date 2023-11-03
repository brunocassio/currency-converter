# currency-converter

This application is intended to accept and store (i.e., persist) a purchase transaction with a description, transaction
date, and a purchase amount in United States dollars.
Based upon purchase transactions previously submitted and stored, the application provides the
stored purchase transactions converted to currencies supported by the Treasury Reporting Rates of Exchange API based
upon the exchange rate active for the date of the purchase.

## Clone the repository
git clone https://github.com/brunocassio/currency-converter.git

## Install dependencies
mvn clean install

## Usage

- If you are using Intellij IDEA, after cloning the project, you just need to run the CurrencyConverterApplication.java class in order to run the application, it's a SpringBoot standalone application.
- It uses an in-memory H2 database which is reset the data everytime you launch the application.

## Testing

- Swagger url: http://localhost:8080/swagger-ui/index.html
You will find all information inside swagger page, but basicaly you just need to include a trasaction using the POST /purchase-transaction method and get its id retrieved by this endpoint and try the GET /purchase-transaction/{id} method to see the converted values.
- In case there is no data for the exchange rate time, you will see an error message.

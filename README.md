# currency-converter

This application is intended to accept and store (i.e., persist) a purchase transaction with a description, transaction
date, and a purchase amount in United States dollars.
Based upon purchase transactions previously submitted and stored, the application provides the
stored purchase transactions converted to currencies supported by the Treasury Reporting Rates of Exchange API based
upon the exchange rate active for the date of the purchase.

## Installation

Provide instructions on how to install your AwesomeTool. Include prerequisites and system requirements.

## Clone the repository
git clone https://github.com/brunocassio/currency-converter.git

## Install dependencies
mvn clean install

## Usage

If you are using Intellij IDEA, after cloning the project, you just need to run the CurrencyConverterApplication.java class in order to run the application, it's a SpringBoot standalone application.
It uses an in-memory H2 database which is reset the data everytime you launch the application. 

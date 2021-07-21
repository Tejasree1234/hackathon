--> Run testng.xml file to get three test reports on a single report 

--> screenshots are present in Screenshot folder and drivers are present in drivers folder
 (Screenshot is added on the report as well on failure of methods)

-->'excel-files' folder contains the individual excel sheets for CarInsurance and TravelInsurance

--> src/main/java has Baseclasses package and that package contains BaseTestClass and         PageBaseClass which has many basic functions/methods like invoking the browser,click the            element and open,close the browser

--> src/main/java has PageClasses package and it contains the basic automation script for        automate the webpage

--> src/main/java has another package called utilities it contains the basic script for read data       from the excel file,write into the excel file and for extended report

--> src/test/java has TestCase package and it contains three classes which act as a testcases for       the classes which is present inside the Pageclasses package

--> Browser type can be changed in config.properties file 
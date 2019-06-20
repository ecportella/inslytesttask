# inslyTestTask
### Test automation for Insly Demo Instance.

Hello, this is the project for the test automation of the Insly Demo Instance.
It was written in __Java__ and the technologies used was __Maven__, __Selenide__ (Selenium wrapper), __TestNG__ and __ExtentReport__.

- With __Maven__ we control dependencies and we can run the project from the terminal.
- __Selenide__ is a wrapper of the Selenium WebDriver and was used in this project to automate the browser.
- __TestNG__ was used to organize with annotations and test some assertions.
Finally, __ExtentReport__ is used to create a report from the tests runned.

Shall we prepare our machine to run those scripts?

### 1. Preparing

- In order to run the scripts, we need to install on our machines the __Java JDK__ and the __Maven__ technologies.
- Make sure to install the latest version of each technology.
- After you do install, make sure you can use then from wherever you are, adding then to your local path variable.

### 2. Project Architecture

- The project is splitted in 3 folders inside the src file of the project.
- In the __functions__ folder we have the __base test__ that represents the test set up, configurations and report creation.
- In the __pageObjects__ we have mapped all the elements that we want to control over the browser.
- Lastly we have the __tests__ folder where we find our test itself.

### 3. Running the scripts

- In order to run our scripts, we need to open our terminal in the root folder where we can find the file __pom.xml__. This file is the one who tells maven what to do.
- Now, all we need to do is run the following line from the terminal: `mvn test`
- The scripts run by default over Google Chrome. If you wish, you can run then on Firefox as well with the following command: `mvn test -Dselenide.browser=firefox`

### 4. Test Report

- After our test run, we can see that a new file was created on the reports folder in the root of the project. Open that html file and see the entire logged run.
- If the test fail, we can se what happened, what exception we got and we can see a ScreenShot from the moment of the fail. Nice, ins't?

<br>
What do you think of this project?
Please let me know your thoughts.
Thank you.

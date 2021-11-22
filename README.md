# 11282 Group45
Member1: Christopher William Driggers-Ellis  
Member2: Rahul Chari  
Member3: Kieran Ford 

# 45mph: A Green Gas Mileage App
The Green Gas Mileage App is a gas mileage calculator application for Android devices targeted at environmentally-conscious drivers. In addition to providing the standard functionality of a gas mileage calculator, the app gauges the environmental impact of users' fuel consumption and quality of driving.  
___
## Features
- A basic fuel mileage calculator with units mpg and km/l
- Fuel usage and cost calculators
- Vehicle Profiles to track fuel consumption, cost, mileage and environmental impact of separate vehicles.
- Running fuel economy averages for each vehicle
- Tracks distance travelled during separate trips
- Detailed, long-term reports of gas mileage 

## Product Vision
For the environmentally-conscious driver who doesn’t have an electric car but would like to ensure they are using gasoline responsibly, the Green Gas Mileage Calculator is a gas mileage calculator that allows the user to keep records of their mileage and fuel consumption across several automobiles and evaluate their carbon footprint from said data. Unlike competing gas mileage calculators in the Google Play Store, our product incorporates unique features that empower users to track their environmental impact through analysis of fuel consumption and quality of driving.
___
# README from Project Documentation with Slight Adjustment

## The Green Gas Mileage Calculator
This file is packaged with the external GitHub repository, source code or exported APK file for the Green Gas Mileage Calculator by 45mph (Group 45 in CEN 3031 Fall 2021), a team which consists of the following members:
* Rahul Chari: Scrum Master
* Christopher William Driggers-Ellis: Project Manager
* Kieran Ford: Development Team Member

## Installation Instructions
Installation instructions vary depending on which version of the project this file is packaged with and whether you already have Android Studio installed on your device.

If you are reading this on GitHub, clone the repository to a directory on your computer. If you are reading this with an export or either the source code or APK of the system, place the applicable resource in a directory on your system. If you already have Android Studio installed, feel free to clone the repository or copy the export of the project’s source code to a folder in the AndroidStudioProjects directory (Or whatever your projects directory has been renamed to). Further, skip the following instructions for installing Android Studio.

An Android emulator is required for running our application on desktop. As such, we have provided the following list of steps for installing Android Studio and the necessary Android emulator on your device.

* Navigate online to the address: https://developer.android.com/studio  
*	Click the large green button that says Download Android Studio. The website should have automatically determined   the correct version of the product for your device, but if you are able, verify that this version is correct for your operating system and use the Download Options button on the bottom left to select a correct version. Please download an executable installation wizard rather than a zipped archive of the application.  
*	After obtaining an installation wizard for a version of Android Studio for your machine’s operating system. Run the wizard and allow it to bypass any warnings that might appear.  
*	From here, follow the installation wizard’s instructions to install Android Studio to your device.

Following this set of instructions, you will have to go through the process of installing the android emulator on your computer. The steps are outlined in the list below.
*	Start Android Studio and open the project’s repository in the application.
*	From here, use the installation instructions available at https://developer.android.com/studio/run/emulator to install Android Studio’s emulator and and the instructions at https://developer.android.com/studio/run/managing-avds to set up a virtual device via the AVD Manager.
*	For this process to work for our application, ensure that the Android Virtual Device (AVD) that you set up via the AVD Manager is for an Android phone and has a minimum API of 26, though at least 28 is recommended.
*	Note as well that you may have to install the appropriate SDK tools for the version of Android chosen before the application will build successfully.  

Having followed these instructions and cloned/moved the project to a known location on your device, you should be ready to move into the operation instructions where we will cover how to use the application using your newly installed emulator.

## Operation Instructions
Instructions for operating the application are less complicated than installing Android Studio and its emulators. Take the following steps to run the emulator in the correct configuration.
*	Ensure that the configuration “app” is selected if you’re using a repository cloned from our project’s GitHub repo or an export of the source code. Ensure that the selected configuration is “GGMC” if you are using an exported APK version of this project.  
*	With the correct configuration selected, press the green run button. If the configuration is not correct, use the configuration dropdown menu next to the green run button to select it.  
*	The application should open automatically, but if it does not, navigate to the emulator’s app menu and select the application called “Green Gas Mileage Calculator” with the 45mph logo in its app icon.  
*	With the application running, use the information available in our documentation’s Requirements Documentation and the System Documentation for support in using the app.  

## Citations
Through the course of its development, the members of 45mph used the following references to learn about Android Studio, Java, and the tools for Android development available through Android Studio.
* Oracle’s Java Documentation
* Android Studio Documentation including
* Android Studio Developer Guides (https://developer.android.com/guide)
* Android Studio User Guides (https://developer.android.com/studio/intro) 
* Various Video and Website Tutorials including…  
  *	This site which inspired our RecyclerViews  
    * https://www.codebrainer.com/blog/how-to-display-data-with-recyclerview  
  * These videos on the same topic which also informed our RecyclerViews	
    * https://youtu.be/18VcnYN5_LM
    * https://youtu.be/xgpLYwEmlO0  
  *	This Video Tutorial on Radio Groups
    * https://www.youtube.com/watch?v=fwSJ1OkK304 
  * A Stackoverflow question that shows how to make Activities Scrollable
    * https://stackoverflow.com/questions/4055537/how-do-you-make-a-linearlayout-scrollable/4055570
  *	This Video Tutorial which showed us the basics of Android Studio and how to develop Android apps
    *	https://youtu.be/tZvjSl9dswg 
  *	This website that shows how to work with formatting times using the LocalDateTime class
    *	https://javarevisited.blogspot.com/2017/01/how-to-create-localdatetime-in-java-8.html 
* Data  
  * We obtained the vehicle emissions data used in this application from the website fueleconomy.gov
    *	https://www.fueleconomy.gov/feg/ws/index.shtml#emissions

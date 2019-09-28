# Service installation and using

### Description
App is a Spring Boot REST service providing translating functions using Google Translate API.  

Service API:  
**GET /translate**

Params:  
* **text** - text for translate.  
* **from** - source language code (en, ru, fr, es ...).  
* **to** - target language code.

Response:  
Text translated word by word or error message if request malformed.  
E.g. if language not supported or parameters are missing.

### Google private key setting
Before using service: 
1) Create or get existing Google private key (JSON).    
2) Set up the environment variable **GOOGLE_APPLICATION_CREDENTIALS** to the private key path.  
Detail information: [Google Translate API](https://cloud.google.com/translate/docs/quickstart-client-libraries)

Or alternatively you can specify the key path in the configuration file. This will be shown later.

### Download app
Source code available at [Translate app (Github)](https://github.com/Twayn/Translate).

### Settings
There are two configuration files for this app:
1) Common spring application properties.  
*\src\main\resources\application.properties*
2) Translate properties.  
*\src\main\resources\translate.properties*

#####Google private key path

To let application know about key location **google.key.from.environment** property exists.  
It can take two values:  
1) **true** - key will be taken from path specified in **GOOGLE_APPLICATION_CREDENTIALS**.  
2) **false** - key will be taken from **google.key.path** property.   
Example: **google.key.path=C:/dev/example_key.json**.

### Build
To build an app run (cmd): '**mvnw package**' in project root folder.

### Launch
After build you can find a .jar file in a '**target**' folder.  
Run it with: **java -jar [name.jar]**.

### Use
Now you can send a get requests on:  
http://localhost:8080/translate.  
To check if a service works type in browser simple request:   
http://localhost:8080/translate?text=home&from=en&to=ru

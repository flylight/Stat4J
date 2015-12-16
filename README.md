This is open-source project for measure application performance using AOP and Annotations.

Main features:
- Add method tracking via adding annotation;
- Output results as formatted String , JSON, ready to view HTML;

Using:
- Upload / build jar file and add them into your project;
- Annotate your mathods to enable tracking
- Add Aspect building plugin

NOTICE:

This plugin add 3-4 millisecods to each time , so just keep in mind this information when you will analyze results.

**How can I use it ?**

- You can provide this statistic into log file as String;
- You can communicate with your application thru any of interface and send performance statistic as JSON data to process it on other side;
- You can use JSON output to create own web page and display statistic as you wish;
- You can use HTML output inside your REST end-point and send back it to display on browser when you need to visualize results without any UI skills;
- You can use String output to throw results into console when you need quick results;
- You can use short output (without history) and full output (with history) to manage output stack.

**How it works ?**

- You just need to annotate your methods that should be measured
- Run your application and retrieve results in runtime
- Call get statistic method inside Stat4J object and select one of next output format type : STRING, JSON, HTTP, also you need specify history type. If you set "false" then you will receive general statistic for all annotated methods and if you set "true" then you will receive history of method calls.

**Output example :**

String output type:

![](http://s29.postimg.org/gfx0ld33b/2015_08_12_00_44_53.png)

JSON output type:

![](http://s10.postimg.org/tvkxvr6m1/2015_08_12_00_48_10.png)

HTTP output type : 

![](http://s15.postimg.org/ystewb59n/Stat4_J_THML_examples.png)

**Benefits**

- Just use annotation to start measuring 
- Get your results in most popular formats

**Additional Plugins**

- Need to include AOP builder plugin into build script (tested and works on Apache Maven)

Wiki page : https://github.com/flylight/Stat4J/wiki

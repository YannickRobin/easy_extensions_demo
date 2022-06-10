# Hello World

## Overview
This demo is a simple example of Easy extension.

## Demo
In this example, [GreeterService.groovy](/helloworld/src/main/groovy/GreeterService.groovy) is a cutom bean loaded from _helloworld_ extension.

Execute the following script in HAC:

```groovy
println greeterService.sayHello('Yannick');
```

From your local Commerce installation, change the message in _<SAP Commerce>/temp/easy_extensions/helloworld/src/main/groovy/GreeterService.groovy_ and reload easy extensions with the following HAC script.

```groovy
easyExtensionService.reloadEasyBeans();
println greeterService.sayHello('Yannick');
```
If you work with a remote SAP Commerce Cloud instance, change the message in [GreeterService.groovy](/helloworld/src/main/groovy/GreeterService.groovy), commit, push and reload easy extensions with the following HAC script.

```groovy
easyExtensionService.reloadEasyExtensions();
println greeterService.sayHello('Yannick');
```

---
title: growth-helloworld
date: 2022/10/12 19:18:00
tags:
- Spring Boot
- Maven
- Java
categories:
- Spring Boot
---

# growth-helloworld

###### 使用Spring Boot编写一个hello world

创建一个Spring Initializr项目，添加spring-web依赖，创建请求返回hello world的接口。

###### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.genuinenonage</groupId>
	<artifactId>growth-helloworld</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>growth-helloworld</name>
	<description>快速搭建一个可运行的Spring Boot项目</description>
	<properties>
		<java.version>1.8</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
	</dependencies>

</project>
```

###### GrowthHelloworldApplication.java

```java
package com.genuinenonage.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrowthHelloworldApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrowthHelloworldApplication.class, args);
	}

}
```


# Election Simulator
![Static Badge](https://img.shields.io/badge/Build-passing-flat)
[![Static Badge](https://img.shields.io/badge/Coverage-100%25-flat)](https://jacobnatural.github.io/election-simulator/jacoco/index.html)
[![Static Badge](https://img.shields.io/badge/docs-blue)](https://jacobnatural.github.io/election-simulator/apidocs/index.html)
## Overview

Description: The Election Simulator is a software application designed to simulate election processes. 
It generates candidates and voters, conducts a virtual election, and determines the winner based on the votes cast. 
This simulator is useful for educational purposes, research, and testing various election scenarios and algorithms.
All components of the application are thoroughly tested using JUnit.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK) 22+**
- **Apache Maven 3.9.6+**


### Cloning the repository:
- To clone the repository and navigate into the project directory, run:

```bash
git clone https://github.com/JacobNatural/election-simulator.git
cd election-simulator
```
### Running the application:
- To build the application and run it:
- You can customize the simulation by passing parameters for the number of candidates and voters. Here are the options:

`--cMin<number>`: Set the minimum number of candidates per electoral district.  
`--cMax=<number>`: Set the maximum number of candidates per electoral district.  
`--vMin=<number>`: Set the minimum number of candidates per electoral district.  
`--vMax=<number>`: Set the maximum number of candidates per electoral district.
```bash
mvn clean package -DskipTests
cd target  
java --enable-preview -jar election-simulator-1.0.jar --cMin=5 --cMax=10 --vMin=50 --vMax=100
```
### Running Tests
- To execute the tests, use the following command:
```bash
mvn clean test
```

### Integrate into Your Project
- If you want to use this application as a dependency in another Maven project,
  first install it into your local Maven repository by running:
```bash
mvn clean install -DskipTests
```
- Then, you can add the following dependency to your pom.xml file:

```xml
<dependency>
    <groupId>com.app</groupId>
    <artifactId>election-simulator</artifactId>
    <version>1.0</version>
</dependency>
```

## Contributing

We welcome contributions to improve the Election Simulator Application. Here's how you can contribute:

1. Fork the repository on GitHub.
2. Make enhancements or fix issues in your forked copy.
3. Submit a pull request to merge your changes into the main repository.

Please ensure your code adheres to our coding standards and is thoroughly tested before submitting a pull request.
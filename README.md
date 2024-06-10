# jsmith

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.volodya-lombrozo/jsmith/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.volodya-lombrozo/jsmith)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/volodya-lombrozo/jsmith/blob/main/LICENSE.txt)
[![Hits-of-Code](https://hitsofcode.com/github/volodya-lombrozo/jsmith?branch=main&label=Hits-of-Code)](https://hitsofcode.com/github/volodya-lombrozo/jsmith/view?branch=main&label=Hits-of-Code)
![Lines of code](https://img.shields.io/tokei/lines/github/volodya-lombrozo/jsmith?branch=main&label=Lines-of-Code)
[![codecov](https://codecov.io/gh/volodya-lombrozo/jsmith/branch/main/graph/badge.svg)](https://codecov.io/gh/volodya-lombrozo/jsmith)

Jsmith is a random Java program generator. The project is largely inspired by
[csmith](https://github.com/csmith-project/csmith), a tool for the C language.
The primary purpose of the library is to provide classes for generating random
Java programs to test Java compilers or translators.

If you need to generate random Java programs for any other purpose, you can also
give Jsmith a try.

## How to add

The library is available on Maven Central. To add it to your project, add the
following snippet to your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.volodya-lombrozo</groupId>
  <artifactId>jsmith</artifactId>
  <version>0.0.1</version>
</dependency>
```

## How to use

The library provides a set of classes for generating random Java programs. To
generate a random class you can use the following code:

```java
import com.github.lombrozo.jsmith.RandomJavaClass;

// Generate a random class.
RandomJavaClass clazz = new RandomJavaClass();
// Get source code of the class.
String code = clazz.src();
// Get the name of the class.
String name = clazz.name();
```

## How to Contribute

Fork repository, make changes, send us a pull request. We will review your
changes and apply them to the `main` branch shortly, provided they don't violate
our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need [Maven 3.3+](https://maven.apache.org) and Java 8+ installed.
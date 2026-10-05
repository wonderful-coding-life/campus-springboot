# 스타터 프로젝트

Spring Boot 애플리케이션에서 사용할 CoffeeMachine Bean을 자동으로 등록해 주는 커스텀 Starter 예제이다.

## 프로젝트 설정

Spring Initializr에서 Gradle 프로젝트를 생성한 후 build.gradle에 Auto Configuration 관련 의존성을 추가한다.

```groovy
dependencies {
	implementation 'org.springframework.boot:spring-boot-autoconfigure'
	annotationProcessor 'org.springframework.boot:spring-boot-autoconfigure-processor'
}
```

* spring-boot-autoconfigure: Auto Configuration 구현에 필요한 기능을 제공한다.
* spring-boot-autoconfigure-processor: Auto Configuration 관련 메타데이터를 생성한다.

## Auto Configuration 클래스 생성

애플리케이션에서 CoffeeMachine 타입의 Bean을 직접 등록하지 않은 경우 Starter에서 기본 Bean을 등록한다.

```java
@AutoConfiguration
@ConditionalOnMissingBean(CoffeeMachine.class)
public class CoffeeAutoConfiguration {

    @Bean
    @Primary
    public CoffeeMachine dripCoffeeMachine() {
        return new DripCoffeeMachine();
    }

    @Bean
    public CoffeeMachine espressoMachine() {
        return new EspressoMachine();
    }

    @Bean
    public CoffeeMachine mochaCoffeeMachine() {
        return new MochaCoffeeMachine();
    }
}
```

@ConditionalOnMissingBean(CoffeeMachine.class)에 의해 애플리케이션에 CoffeeMachine 타입의 Bean이 이미 존재하면 이 Auto Configuration은 적용되지 않는다.

기본으로 등록되는 세 개의 CoffeeMachine 중 DripCoffeeMachine을 @Primary Bean으로 지정한다.

## Auto Configuration 등록

다음 파일을 생성한다.
```
resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 파일에 AutoConfigure full package path를 설정한다.
```

파일에 Auto Configuration 클래스의 전체 클래스 이름(FQCN)을 등록한다.
```
com.example.coffee.CoffeeAutoConfiguration
```

Spring Boot는 이 파일을 통해 Starter가 제공하는 Auto Configuration 클래스를 발견한다.

## 프로젝트 빌드

Starter는 실행 애플리케이션이 아니라 라이브러리이므로 일반 JAR을 생성한다.
```
gradlew jar
```

생성된 JAR은 다음 디렉터리에서 확인할 수 있다.
```
build/libs
```

## 애플리케이션에서 사용

생성된 JAR 파일을 애플리케이션 프로젝트의 libs 디렉터리에 복사한다.
```
libs/
└── coffee-starter.jar
```

build.gradle에 의존성을 추가한다.
```groovy
dependencies {
	implementation files('libs/coffee-starter.jar')
```

이후 애플리케이션에서는 별도의 Bean 설정 없이 CoffeeMachine을 주입받아 사용할 수 있다.
```java
    @Autowired
    private CoffeeMachine coffeeMachine;
    @Autowired
    private List<CoffeeMachine> coffeeMachines;
```



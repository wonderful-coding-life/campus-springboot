# 스타터 프로젝트

## 프로젝트 설정

Spring Initializer 프로젝트 생성 후 다음과 같이 autoconfigure 관련 의존성을 포함한다.

```groovy
dependencies {
	implementation 'org.springframework.boot:spring-boot-autoconfigure'
	annotationProcessor 'org.springframework.boot:spring-boot-autoconfigure-processor'
}
```

## @AutoConfiguration 클래스 생성

애플리케이션에서 직접 CoffeeMachine bean 객체를 등록하지 않았다면 starter에서 bean 객체를 등롤한다.

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

## imports 파일 생성

resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports 파일에 AutoConfigure full package path를 설정한다.

```
com.example.coffee.CoffeeAutoConfiguration
```

## 프로젝트 빌드

gradlew.bat jar

## 애플리케이션에서 포함

```groovy
dependencies {
	implementation files('libs/coffee-starter.jar')
```




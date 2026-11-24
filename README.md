# Melipayamak Web Service Wrapper (SDK) for Java

![Java Version](https://img.shields.io/badge/Java-11%2B-ED8B00?logo=openjdk&logoColor=white)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
![Status](https://img.shields.io/badge/Status-Stable-brightgreen)
![Maven](https://img.shields.io/badge/build-Maven-C71A36?logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/testing-JUnit-25A162?logo=junit5&logoColor=white)
<!-- ![Coverage](https://img.shields.io/badge/coverage-95%25-brightgreen) -->
<!-- ![Gradle](https://img.shields.io/badge/build-Gradle-02303A?logo=gradle&logoColor=white) -->

### معرفی وب سرویس ملی پیامک
ملی پیامک یک وب سرویس کامل برای ارسال و دریافت پیامک و پیامک صوتی و مدیریت کامل خدمات دیگر است. قبل از استفاده نیاز به [**ثبت نام به همراه دریافت 200 پیامک هدیه جهت تست وبسرویس**](http://www.melipayamak.com/) دارید

### پلتفرم های مورد پشتیبانی
قابل استفاده در پروژه های مبتنی بر جاوا مثل:

- Spring Boot
- Jakarta EE
- Quarkus
- Micronaut
- Play Framework
- Plain Java applications
- Scala


### اجرای تست ها

```bash
mvn clean test
```

### نحوه افزودن به پروژه

با مراجعه به [Sonatype](https://central.sonatype.com/artifact/com.melipayamak/melipayamak-java) کانفیگ متناسب با پروژه خود را اضافه کنید

### نحوه استفاده

این پروژه هر دو حالت `sync` و `async` را به صورت زیر پشتیبانی میکند

```java
SoapClient soapClient = new SoapClient("username", "password", new DefaultHttpTransport());

HttpResponse syncSoapResponse = soapClient.SendSimpleSMS2(
    "09123456789",
    "5000xxx",
    "Hello from my Java SDK!",
    false
).toCompletableFuture().get();

// Async
soapClient.SendSimpleSMS(
    new String[]{"09123456789", "09123456789"},
    "5000xxx",
    "Hello!",
    false
).thenAccept(response -> {
    System.out.println("Response: " + response);
}).exceptionally(error -> {
    error.printStackTrace();
    return null;
});


RestClient restClient = new RestClient("username", "password");

String syncRestResponse = restClient.SendSMS(
    "09123456789",
    "5000xxx",
    "Hello",
    false
);

// rest async example
CountDownLatch latch = new CountDownLatch(1);

restClient.SendSMSAsync(
    "09123456789",
    "5000xxx",
    "Hello!",
    false
).thenAccept(response -> {
    System.out.println("Response: " + response);
}).exceptionally(error -> {
    error.printStackTrace();
    return null;
})
.whenComplete((result, error) -> latch.countDown());

// Keep the application alive until completion.
latch.await();

```

<!-- publish to generate locally
mvn clean package
mvn clean deploy -->
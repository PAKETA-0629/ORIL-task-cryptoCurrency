# Getting Started

Go to src/main/resources/application.properties and fill database properties:
```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
spring.datasource.driver-class-name=org.postgresql.Driver
spring.sql.init.mode=embedded
```

Application fetch cryptocurrency data prices from CEX.IO every 60 seconds and records them to database

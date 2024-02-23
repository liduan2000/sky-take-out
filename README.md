# Sky Take Out

## Description
This project is an online food ordering system(backend) developed using the Spring Boot framework.

It has two frontend services: user side and admin side, both source code can be found at: [user-wxmini](https://github.com/liduan2000/sky-user-wxmini) and [admin-web](https://github.com/liduan2000/sky-admin-vue-ts)

## Build SDK and Software
- Java SDK 17
- IntelliJ IDEA: This IDE is recommended for developing and running the project.

## Tech Stack
- Backend
    - Springboot
    - Spring Task
    - Spring Cache
    - Mybatis
    - JWT
    - WebSocket
    - HttpClient
    - Swagger
    - Apache ECharts
- Frontend
    - Vue
    - wx-mini
    - Uniapp
    - ElementUI
- Database
    - MySQL
    - Redis
- API
    - RESTful API

## Getting Started
1. Install Java 17 SDK and add it to system path.
2. Open the project in IntelliJ IDEA.
3. Connect to MySQL database and run `sky-take-out.sql`.
4. Modify config file [application-dev.yml](./sky-server/src/main/resources/application-dev.yml)
```
sky:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    host: localhost
    port: [your port]
    database: sky_take_out
    username: [your username]
    password: [your password]
```
5. Modify other configs
6. Run application

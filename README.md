# HotelBooking

## Оглавление

- [Описание проекта](#1.1)
- [Спецификация API](#2.1)
- [Стек используемых технологий](#3.1)
- [Запуск проекта](#4.1)

## 1. Описание проекта.<a id='1.1'></a>
HotelBooking - это учебный проект на Java и SpringBoot.

Он представляет собой бэкенд-составляющую сервиса бронирования отелей с
возможностью управлять контентом через административную панель CMS.

Главная его задача — обеспечить пользователям возможность забронировать понравившийся
отель на определённый период.

Помимо этого, обеспечить возможность поиска отелей по критериям и рейтингам и систему
выставления оценок в приложении (от 1 до 5).

Сервис позволяет администраторам выгружать статистику по работе в формате
CSV-файла.

### Структура проекта.
Проект состоит из 3 модулей.

1. Основной модуль - booking. Он содержит в себе контроллеры для работы со всеми эндпоинтами, авторизацию через Spring Security, работу с БД, обработку исключений.
2. Вспомогательный модуль - statistics. Он получает из модуля booking данные через Apache Kafka с помошью Spring Cloud Stream Kafka по событиям регистрации пользователей и по оформленным бронированиям, сохраняет их в БД MongoDB. И также имеет функцию выгрузки всех статистических данных в CSV-файл.
3. Модуль common-resources - для общих классов и зависимостей.


## 2. Спецификация API.<a id='2.1'></a>

Для тестирования методов сервиса можно использовать Swagger, по умолчанию он запускается по URL:
http://localhost:8080/swagger-ui/index.html#/

Swagger позволяет ознакомиться со всеми эндпоинтами сервиса и также отправлять запросы по ним:
<p align="center"><img  src="/readme_assets/swagger_1.png" width="100%"></p>

Подробнее об этом в разделе [Запуск проекта](#4.1)

## 3. Стек используемых технологий.<a id='3.1'></a>
`Java 21` `Spring Boot` `REST API` `Feign Client` `Spring Cloud Stream` `Spring Data` `Spring Security` `Spring Boot Starter Validation` `JDBC-JPA-Hibernate` `MapStruct` `PostgreSQL` `MongoDB` `Apache Kafka` `Lombok` `Maven` 

## 4. Запуск проекта.<a id='4.1'></a>

Для запуска необходимы:

`JDK 21`
`docker-compose`

Из корня проекта выполняем команды в терминале:

для запуска окружения (БД `PostgreSQL` `MongoDB`, брокер сообщений `Apache Kafka` и `Zookeeper`):

```
docker-compose up -d
```

для запуска проекта (из корня):

```
mvn clean install
java -jar booking/target/booking-0.0.1-SNAPSHOT.jar
java -jar statistics/target/statistics-0.0.1-SNAPSHOT.jar
```

Для тестирования эндпоинтов удобно использовать Swagger, по умолчанию он запускается по URL:
http://localhost:8080/swagger-ui/index.html#/

Прежде всего нужно создать пользователя, так как все другие методы требуют авторизации. Доступны 2 роли - USER и ADMIN:
<p align="center"><img  src="/readme_assets/swagger_2.png" width="100%"></p>

Далее необходимо пройти авторизацию в Swagger, зелёная кнопка в правом верхнем углу интерфейса:
<p align="center"><img  src="/readme_assets/swagger_3.png" width="100%"></p>

После этого можно пробовать отправлять запросы.
Ролевая политика следующая:

- Создание, редактирование и удаление отелей и комнат доступно только
администратору. Так же, как и получение списка бронирований, и выгрузка статистики в CSV.
- Остальные методы доступны для любой роли.

### Валидация входящих данных
При отправке запросов входящие данные проходят валидацию, с использованием `Spring Boot Starter Validation`. В случае неверного заполнения полей запрос возвращает описание ошибки:
<p align="center"><img  src="/readme_assets/error_1.png" width="80%"></p>

### Обработка ошибок
При ошибках в работе сервиса описан `RestControllerAdvice`, который обрабатывает ошибки и возвращает в ответе описание:
<p align="center"><img  src="/readme_assets/error_2.png" width="80%"></p>

### Пагинация
Для оптимизации запросов снижения нагрузки на БД, и в целом для удобства, в методах поиска реализована постраничная выдача информации. 
Через сваггер так же можно задавать параметры постраничной выдачи и сортировки по полям:   
<p align="center"><img  src="/readme_assets/swagger_4.png" width="100%"></p>

А в RequestBody можно использовать поля для поиска по записям.

### Выгрузка статистики в CSV
Выгрузка статистики происходит с помощью методов контроллера StatisticsController - Получение статистических данных.
При этом происходит запрос через Feign Client в модуль statistics, в котором сервис получает данные из MongoDB и генерирует из них CSV файл:
<p align="center"><img  src="/readme_assets/statistics.png" width="80%"></p>
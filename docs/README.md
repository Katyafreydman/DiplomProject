[![Build status](https://ci.appveyor.com/api/projects/status/68q6fcwlggwwwbvh?svg=true)](https://ci.appveyor.com/project/Katyafreydman/diplomproject)

## Дипломный проект

Дипломный проект представляет собой автоматизацию тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

### Документация

 - [План автоматизации тестирования веб-формы сервиса покупки туров интернет-банка](https://github.com/Katyafreydman/DiplomProject/blob/master/docs/Plan.md)
 - [Отчёт о проведенном тестировании](https://github.com/Katyafreydman/DiplomProject/blob/master/docs/Report.md)
 - [Отчёт о проведённой автоматизации](https://github.com/Katyafreydman/DiplomProject/blob/master/docs/Summary.md)


### Запуск приложения

 Предусловия

1. Необходимо склонировать репозиторий по ссылке  https://github.com/Katyafreydman/DiplomProject
2. Установить и открыть проект в  IntelliJ IDEA
3. Установить запустить Docker Desktop

### Запуск
1. Запустить MySQL, PostgreSQL, NodeJS через терминал командой: `docker-compose up`.
2. В новой вкладке терминала ввести следующую команду в зависимости от базы данных
- `java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app" -jar artifacts/aqa-shop.jar` - для MySQL.
- `java "-Dspring.datasource.url=jdbc:postgresql://localhost:5432/app" -jar artifacts/aqa-shop.jar` - для PostgreSQL.
3. Приложение должно запуститься по адресу `http://localhost:8080/`


### Запуск автотестов 

1. Для запуска автотестов с "MySQL", необходимо открыть новую вкладку терминала и ввести следующую команду: `.\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"`
2. Для запуска автотестов с "PostgreSQL", необходимо открыть новую вкладку терминала и ввести следующую команду: `.\gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"`

### Запуск отчета тестирования
Для создания отчета запустить команду: `./gradlew allureServe`
### Завершения работы Sut
Для завершения работы SUT, необходимо в терминале, где был запущен SUT, ввести команду `Cntrl C`.
### Остановка и удаление контейнера
Для остановки работы контейнеров "Docker-Compose", необходимо ввести в терминал следующую команду: `docker-compose down`

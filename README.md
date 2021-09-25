# social-protect-integration-service


Предварительно нужно запустить docker-compose kafka и zookeeper

Создастся kafka-server c топиком update

Написал producer, который при старте бина посылает в кафку сообщение
Написал consumer, который подписывается на топик и выводит в лог

Для просмотра содержимого кафки нужно использовать утилиту 
https://www.kafkatool.com/download.html
Через неё можно и слать топики
Запишу видео как её настроить

У нас сообщение key - ключ пользователя, value сообщение
Пока value строка - но туда можно слать и json и byte-array
а потом парсить как надо через ObjectMapper или срузу указав тип класса (см. примеры)

https://www.baeldung.com/ops/kafka-docker-setup
https://www.baeldung.com/spring-kafka


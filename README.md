# SocksAccounting
Spring application for socks accounting.

Выполненное задание:
Что нужно сделать
Реализовать приложение для автоматизации учёта носков на складе магазина. Кладовщик должен иметь возможность:
учесть приход и отпуск носков;
узнать общее количество носков определенного цвета и состава в данный момент времени.

Внешний интерфейс приложения представлен в виде HTTP API (REST, если хочется).

Список URL HTTP-методов
POST /api/socks/income
Регистрирует приход носков на склад.

Параметры запроса передаются в теле запроса в виде JSON-объекта со следующими атрибутами:
color — цвет носков, строка (например, black, red, yellow);
cottonPart — процентное содержание хлопка в составе носков, целое число от 0 до 100 (например, 30, 18, 42);
quantity — количество пар носков, целое число больше 0.

Результаты:
HTTP 200 — удалось добавить приход;
HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).
POST /api/socks/outcome
Регистрирует отпуск носков со склада. Здесь параметры и результаты аналогичные, но общее количество носков указанного цвета и состава не увеличивается, а уменьшается.

GET /api/socks
Возвращает общее количество носков на складе, соответствующих переданным в параметрах критериям запроса.

Параметры запроса передаются в URL:
color — цвет носков, строка;
operation — оператор сравнения значения количества хлопка в составе носков, одно значение из: moreThan, lessThan, equal;
cottonPart — значение процента хлопка в составе носков из сравнения.

Результаты:
HTTP 200 — запрос выполнен, результат в теле ответа в виде строкового представления целого числа;
HTTP 400 — параметры запроса отсутствуют или имеют некорректный формат;
HTTP 500 — произошла ошибка, не зависящая от вызывающей стороны (например, база данных недоступна).

Примеры запросов:
/api/socks?color=red&operation=moreThan&cottonPart=90 — должен вернуть общее количество красных носков с долей хлопка более 90%;
/api/socks?color=black&operation=lessThan&cottonPart=10 — должен вернуть общее количество черных носков с долей хлопка менее 10%.
Для хранения данных системы можно использовать любую реляционную базу данных. Схему БД желательно хранить в репозитории в любом удобном виде.
Технологии для построения сервиса могут быть выбраны произвольно.
Код готового сервиса разместить в своем репозитории на github.com и предоставить ссылку.



Технологии использованные мной при создании:
Java 11, Maven, Spring Boot 2.6.0, MySQL 8.0, Jackson, Hibernate-validator.

Имя базы: acc_socks.
Имя таблицы: tb_socks.

Схема таблицы: 
| tb_socks | CREATE TABLE `tb_socks` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `color` varchar(255) NOT NULL,
  `cotton_part` int NOT NULL,
  `quantity` bigint NOT NULL,
  PRIMARY KEY (`id`)
)

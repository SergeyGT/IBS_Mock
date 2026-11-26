# Заглушка процессинга микрозаймов

## Запуск из командной строки

Требуется JDK 21. Соберите приложение:

```powershell
.\mvnw.cmd clean package
```

Запуск по умолчанию (`8080`, задержка удаления 1 секунда):

```powershell
java -Xms2g -Xmx4g -jar .\target\demo-0.0.1-SNAPSHOT.jar
```

Профили `test1` (`7770`, задержка 2 секунды) и `test2` (`7775`, задержка 4 секунды):

```powershell
java -Xms2g -Xmx4g -jar .\target\demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test1
java -Xms2g -Xmx4g -jar .\target\demo-0.0.1-SNAPSHOT.jar --spring.profiles.active=test2
```

## API

- `GET /v2/checkAccount?acc=1234567890&days=2` — проверка счета, HTTP 202.
- `POST /v2/payment/` — подтверждение платежа. Требуется заголовок `BankCode: 22`.
- `DELETE /v1/transactions/cleare/{id}` — удаление транзакции с задержкой профиля,
  ответ: HTTP 200 и текст `deleted success`.

Все API описаны в Swagger UI: `http://localhost:8080/swagger-ui.html`
(порт меняется для профилей `test1` и `test2`).

## Служебные интерфейсы

- H2 Console: `http://localhost:8080/h2-console/`
  - JDBC URL: `jdbc:h2:mem:processingdb`
  - User: `sa`, пароль пустой
- Метрики: `/actuator/metrics` и `/actuator/prometheus`.
- Логи вызовов API записываются в `processing-stub.log` в текущей директории.

Запуск тестов:

```powershell
.\mvnw.cmd test
```

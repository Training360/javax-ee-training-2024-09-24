# Employees alkalmazás

## Adatbázis kapcsolódás

* Adatbázis indítása Docker konténerként

```shell
docker run -d -e POSTGRES_DB=employees -e POSTGRES_USER=employees  -e POSTGRES_PASSWORD=employees  -p 5432:5432  --name employees-postgres postgres
```

* Csatlakozás IDEA-ból
* JDBC driver letöltése: https://jdbc.postgresql.org/download/
* Fájl bemásolása a WildFly főkönyvtárába
* WildFly főkönyvtárából kiadni a következő parancsot:

```shell
bin\jboss-cli --connect
```

CLI-ben

```
deploy postgresql-42.7.4.jar
```

```
data-source add --name=EmployeeDS --jndi-name=java:/jdbc/EmployeeDS \
  --driver-name=postgresql-42.7.4.jar \
  --connection-url=jdbc:postgresql://localhost:5432/employees \
  --user-name=employees \
  --password=employees
```

* Elkezdhető a JPA használata az alkalmazásban
* `persistence.xml`, ide kell a DataSource hivatkozást betenni
* Entity: `@Entity`, `@Id`
* `EntityManager`, `@PersistenceContext`
* JPA generálhatja a táblákat - csak oktatási céllal
  * Helyette: Liqubase

```sql
insert into employees(id, emp_name) values (nextval('employees_seq'), 'Jack Doe');
```

## OpenAPI

```
/extension=org.wildfly.extension.microprofile.openapi-smallrye:add()
/subsystem=microprofile-openapi-smallrye:add()
```

Swagger UI elérhető:

http://localhost:8080/employees-web-1.0-SNAPSHOT/api/openapi-ui

## Docker

(`gradle build` kell, hogy a WAR fájl létrejöjjön)

* `Dockerfile` létrehozása, tartalom átmásolása
* `customization` könyvtár átmásolása, benne a két fájllal
* Build

```shell
docker build -t employees-db .
```

* Futtatás (előtte le kell állítani a saját WildFly-t a port miatt)

```shell
docker run -d -p 8080:8080 --name my-employees-db employees-db
docker logs -f my-employees-db
```

Ennek eredménye, hogy nem talál adatbázist a Liquibase:

`java.net.ConnectException: Connection refused`

## JMS

```
/subsystem=messaging-activemq/server=default/jms-queue=\
  EmployeeQueue:add(entries=[java:/jms/queue/EmployeeQueue])

/subsystem=messaging-activemq/server=default/address-setting=\
  jms.queue.EmployeeQueue:add(redelivery-delay=1500, \
  redelivery-multiplier=1.5, \
  max-redelivery-delay=5000, max-delivery-attempts=-1)
```
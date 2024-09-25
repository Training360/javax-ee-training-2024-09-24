# Adatbázis kapcsolódás

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
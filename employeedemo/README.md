# employeedemo

A Maven project that connects a JPA entity (`Employee`) to a database using Hibernate.

## Tech stack
- Java 17+
- Maven
- Hibernate ORM 6.4 (Jakarta Persistence)
- H2 in-memory database (MySQL config included as a commented alternative)

## Project structure
```
employeedemo
├── pom.xml
└── src/main
    ├── java/com/example/employeedemo
    │   ├── MainApp.java                 # create / read / update / delete demo
    │   ├── entity/Employee.java         # JPA entity
    │   └── util/HibernateUtil.java      # builds the SessionFactory
    └── resources
        └── hibernate.cfg.xml            # datasource + Hibernate settings
```

## Employee entity
| Field     | Type   | Column       |
|-----------|--------|--------------|
| id        | Long   | id (PK, auto-generated) |
| firstName | String | first_name   |
| lastName  | String | last_name    |
| salary    | int    | salary       |

Annotations used: `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`.

## Run
```bash
mvn clean compile exec:java
```
The app inserts three employees, updates one, deletes one, and prints the table after each step
(SQL is logged because `hibernate.show_sql` is `true`).

## Switching to MySQL
1. In `pom.xml`, swap the H2 dependency for the `mysql-connector-j` one.
2. In `hibernate.cfg.xml`, use the commented MySQL properties and set your username/password.

# LeaveSystem Back-End
This project run on port [http://localhost:8080](http://localhost:8080/) spring version 3.3.2

## Installizer
Spring Jpa
spring 3.3.2
Spring Security( I comment this dependency because cant run with security) i will fig in future)

## Data base
I used ProgreSQL the database name is `LeaveSystem` have 4 table, ME create user name is admin and password is 1234 to concent to database.
users
```
CREATE TABLE users (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(200) NOT NULL UNIQUE,
    email VARCHAR(200) NOT NULL UNIQUE,
    role VARCHAR(100) NOT NULL DEFAULT 'user',
    department VARCHAR(200) NOT NULL
);
```
leave_types
```
CREATE TABLE leave_types (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    max_days INT NOT NULL
);
```

leave_requests
```
CREATE TABLE leave_requests (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'pending',
    reason TEXT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_leave_type FOREIGN KEY (leave_type_id) REFERENCES leave_types(id) ON DELETE CASCADE,
    CHECK (start_date <= end_date)
);
```

leave_balances 
```
CREATE TABLE leave_balances (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_id INT NOT NULL,
    leave_type_id INT NOT NULL,
    year INT NOT NULL ,
    remaining_days INT NOT NULL,
    CONSTRAINT fk_balance_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_balance_leave_type FOREIGN KEY (leave_type_id) REFERENCES leave_types(id) ON DELETE CASCADE,
    UNIQUE(user_id, leave_type_id, year)
);
```
## How to Connect Data ProgeSql
In project Spring edit in application.properties add this code        
```
spring.datasource.url=jdbc:postgresql://localhost:5432/LeaveSystem
spring.datasource.username=Admin
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

```

## Feture
- recive request form front end
- sent data to front end
- update the request


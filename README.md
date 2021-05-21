# Hades

Hades is backend written in Kotlin using Ktor. It's purpose is to demonstrate basic usages of CRUD operations on InMemory database / H2 database (in memory) / PostgreSQL database.
If you need GraphQL version of this (simplifed of course for learning purposes), visit: [HadesQL](https://github.com/primepixel/HadesQL)

## Install

Clone the project and run it. Simple as that.

By default it uses H2 database while working with Exposed to store data, but if you prefer to use InMemory one then in framework package, under di, 
use `single<MainRepository> { InMemoryRepository() }` instead of `single<MainRepository> { DefaultMainRepository() }` in RepositoriesModule.

> All endpoints aside from authentication will contain API version: "http://localhost:8080/api/v1/..."

## Built with

- [Kotlin](https://kotlinlang.org/) - Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference.
- [Ktor](https://ktor.io/) - Ktor is an asynchronous framework for creating microservices, web applications, etc.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
 - [Dependency Injection](https://developer.android.com/training/dependency-injection) - 
   - [Koin](https://insert-koin.io/) - A pragmatic lightweight dependency injection framework for Kotlin developers.
- [Exposed](https://github.com/JetBrains/Exposed) - Exposed is a lightweight SQL library on top of JDBC driver for Kotlin language.
- [Gson](https://ktor.io/docs/gson.html) - Content negotiation which provides Gson converter for handling JSON data.
- [Hikari](https://github.com/brettwooldridge/HikariCP) - HikariCP is a "zero-overhead" production ready JDBC connection pool.
- [PostgreSQL](https://github.com/postgres/postgres) - PostgreSQL is an advanced object-relational database management syste.
- [Locations]() - Type-safe routes.
- Also contains JWT authentication.

## Database

Project has three data sources:
1. InMemory (*turned off*)
2. H2 (also in memory but uses actual data) (*default*)
3. PostgreSQL

In case you want to switch from H2 to PostgreSQL you have two options (edit `DatabaseFactory.kt` file):
1. Use regular connection to PostgreSQL; Turn off H2 (comment it out) and turn on PostgreSQL, that is
```
Database.connect(
    url = "jdbc:postgresql://database:5432/thoughtsDb",
    driver = "org.postgresql.Driver",
    user = "username",
    password = "secret"
)
```

2. Setup via Hikari, with connection pooling; turn on following:
```
val config = HikariConfig().apply {
    jdbcUrl = "jdbc:postgresql://database:5432/thoughtsDb"
    driverClassName = "org.postgresql.Driver"
    username = "username"
    password = "secret"
    maximumPoolSize = 10
}

val dataSource = HikariDataSource(config)
Database.connect(dataSource)
```

## Running the project

In case of H2/InMemory data source (*default is H2 already*), running the project as is should be fine. Simply use one of the two and run it.
In case of PostgreSQL, there's bit of a setup although major work is in place already:

1. Install [PostgreSQL](https://www.postgresql.org/)
2. Install [Docker](https://www.docker.com/). Make sure after you install Docker, that it's running as expected.
3. Setup `docker-compose.yml` file according to your specification (or keep as is for default):
  - Container 1#app with name, port and database dependency
  - Container 2#database with image, environment, port
  - **Note**: Environment contains database name, username and password. Keep them secret. They're here and alive for learning purposes.
4. In terminal, navigate to your project and run command: `docker-compose up --build` (in case you are re-running the project, simply omit the `--build`)
5. Then in new tab in terminal, in root of your workspace, run command: `docker exec -t -i thoughts-app bash` (thoughts-app is name of our container which will be created)
  - Once that's done, you should be in *bash*
  - In there run command: `./gradlew -t installDist`
  - Wait until it's done (you'll see line: "Waiting for changes...")
6. Test via Postman

If all done correctly, you'll should see two containers created in docker, as well as your terminal windows being updated as you work with postman to test the data.

## Contribute

If you want to contribute to this repository, you're always welcome!

## Contact

If you need any help, feel free to contact me: kenan.karic@outlook.com.

## License
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)


### To setup the Database execute the following command on the ~/mongo: 
- On the ~/mongo path execute:
```
docker compose up
```
  - To populate the database choose either:
    - use the ~/mongo/import.sh on the container console
    - run directly:
    ```
    mongoimport --uri "mongodb://root:MongoDB2019%21@localhost:27017/?authSource=admin&readPreference=primary&directConnection=true&ssl=false" --db challenge  --collection movies  movies.json
    ```
      - import via mongo_compass

### Build
```
mvn clean package -DskipTests
```

### Test
```
mvn test
```

### Run
```
mvn clean package spring-boot:repackage
java -jar target/XpandIT-Backend-Challenge-0.0.1-SNAPSHOT.jar
```

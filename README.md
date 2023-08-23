# Run
```
./gradlew clean bootRun
```

# Run and pipe into jq
```
./gradlew --console plain -q clean bootRun | jq .
```

# Run and pipe into a node pretty printer
```
./gradlew --console plain -q clean bootRun | util/presttyPrint.js
```

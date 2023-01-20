kcat -P -b localhost:50432 -t test sample.json

kcat -P -b localhost:50432 -t test sample.json --property value.serializer=custom.class.serialization.JsonSerializer


kcat -C -b localhost:50432 -t test -o beginning


docker run -p 8529:8529 -e ARANGO_ROOT_PASSWORD=rootpassword arangodb/arangodb:3.10.1


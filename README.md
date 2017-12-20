# Car-Park-Manager

Car-Park-Manager was created as a team project at the Faculty of Informatics at Masaryk University in Brno. It emulates a situation in a imaginary car park company.

It's a multi-tiered application developed in Java. Modern technologies like Spring, REST API, AngularJS etc. are used.

## Building the project and accesing webapp

Build:
```
cd carparkmanager
mvn clean install
```

Start tomcat:
```
cd carparkmanager-web
mvn
```
or
```
cd carparkmanager-web
mvn tomcat7:run
```

webapp url:
```
localhost:8080/pa165
```

## Building the project and accessing REST interface

Build:

```
cd carparkmanager
mvn clean install
```

Start tomcat:
```
cd carparkmanager-rest
mvn tomcat7:run
```

URL of the REST interface:
```
http://localhost:8080/pa165/rest
```

Available methods of the REST interface:
```
GET /drive/findall
GET /drive/find/{id}
DELETE /drive/delete/{id}
POST /drive/create
PUT /drive/update
```

Curl examples:
```
curl http://localhost:8080/pa165/rest/drive/findall
curl http://localhost:8080/pa165/rest/drive/find/1
```

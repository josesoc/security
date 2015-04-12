# Spring Boot default security credentials
# user:     user
# password: Using default security password: e33a3f9b-f6ab-498e-bcfc-747ea186e3f4 (in console after mvn spring-boot:run)
#
$ curl -i -u user:e33a3f9b-f6ab-498e-bcfc-747ea186e3f4 http://localhost:8080/greeting

  HTTP/1.1 200 OK
  Server: Apache-Coyote/1.1
  Strict-Transport-Security: max-age=31536000 ; includeSubDomains
  Content-Type: application/json;charset=UTF-8
  Transfer-Encoding: chunked
  Date: Fri, 03 Apr 2015 06:57:33 GMT

  {"id":3,"content":"Hello, World!"}
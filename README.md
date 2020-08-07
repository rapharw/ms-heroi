# ms-heroi
Micro Serviço para CRUD de um Herói

## Profile DSV
O Profile DSV inicializa em *modo H2 (em memória)*. Por default, o script *sqlserver_schema.sql* é executado.

Ver mais em **application-dsv.yml**

## Profile HML
Utiliza SqlServer.

Ver mais em **application-hml.yml**

*OBS:* Ao inicializar a aplicação, informar para as variáveis de ambiente:

```
  ${URL_DOMAIN}
  ${URL_DATABASE}
  ${USERNAME_DATABASE}
  ${PASSWORD_DATABASE}
```

*OBS2:* Lembrando que os valores acima devem ser de acordo com o seu ambiente de Homologação.


Exemplo (VM Arguments):
```
-DURL_DOMAIN=http://localhost:8080 -DURL_DATABASE=jdbc:sqlserver://localhost:1433 -DUSERNAME_DATABASE=sa -DPASSWORD_DATABASE=Teste@123
```

# Sql Server (Docker)
docker run -d -p 1433:1433 --name heroi-sqlserver -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=Teste@123" -v msheroi:/var/opt/mssql mcr.microsoft.com/mssql/server


![Sql Server Connect](src/main/resources/images/sqlserver-connect.png?raw=true "Sql Server Connect")



# API

## Postman Collection
https://www.getpostman.com/collections/10313f184d9b872d5509

*OBS:* Ao importar a Collection, as url's estarão utilizando a url http://localhost:8086, condinzentes ao profile de DSV.

*OBS2:* Para testar o profile de HML, altere-o nas config's de inicialização do springboot e altere a não esqueça de mudar as config's no Postman, para apontar para a nova url. 


## CORS
Nos arquivos application-{env}.yml, existe a propriedade *allowed.origin*. Ela define quais as origens são permitidas para se fazer um request ao backend.


## Rotas

* /api/** <br>
Rota com a ROLE_COMUM. Todos os usuários contendo o perfil ROLE_COMUM acessam esta rota.

* /admin/** <br>
Rota com a ROLE_ADMIN. Apenas usuários contendo o perfil ROLE_ADMIN acessam esta rota.
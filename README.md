# IXCWatchTask

Rotina Spring Boot para remover acessos Watch TV quando o contrato correspondente no IXCSoft estiver bloqueado.

## Fluxo

1. Consulta usuarios TV da plataforma Watch no IXCSoft.
2. Consulta contratos bloqueados no IXCSoft.
3. Busca o token Watch dentro do IXCSoft via Selenium.
4. Localiza o ticket na API Watch.
5. Tenta excluir o ticket na Watch.
6. Exclui o usuario TV no IXCSoft.

Observacao: se o ticket nao existir mais na Watch, a rotina ainda limpa o usuario TV no IXCSoft.

## Requisitos

- Java 17
- Maven
- Chrome/Chromedriver local para automacao Selenium
- Acesso ao IXCSoft
- Acesso de rede para APIs IXCSoft e Watch TV

## Variaveis de ambiente

Obrigatorias:

```text
IXC_AUTH
IXC_WEB_LOGIN
IXC_WEB_PASSWORD
```

Opcionais:

```text
IXC_API_BASE_URL
WATCH_API_DELETE_URL
WATCH_API_SEARCH_URL
WATCH_WEB_DEBUG
SELENIUM_CHROME_DRIVER_PATH
SELENIUM_CHROME_BINARY_PATH
```

Valores padrao relevantes:

```text
SELENIUM_CHROME_DRIVER_PATH=C:\ixcwatchtask\driver\chromedriver.exe
SELENIUM_CHROME_BINARY_PATH=C:\ixcwatchtask\browser\chrome-win\chrome.exe
```

## Compilar

```powershell
mvn package -DskipTests
```

## Rodar

```powershell
java -jar target\IXCWatchTask-0.1.0.jar
```

Ou, depois de copiar o JAR para a pasta de deploy:

```powershell
run-app.bat
```

## Logs

Os logs sao gravados em:

```text
logs/app/ixc-watch-task.YYYY-MM-DD.log
logs/task/ixc-watch-execucao.YYYY-MM-DD.log
logs/error/ixc-watch-error.YYYY-MM-DD.log
```

O log operacional da rotina usa `executionId` para correlacionar os eventos de uma mesma execucao.

## Agendamento

A rotina esta agendada para executar diariamente as 7h:

```text
0 0 7 * * *
```

## Monitoramento

Endpoints uteis para acompanhamento local e Uptime Kuma:

```text
GET  http://localhost:5052/actuator/health
GET  http://localhost:5052/monitoring/task-status
POST http://localhost:5052/tasks/ixc-watch/run
```

Para monitoramento de disponibilidade no Uptime Kuma, use:

```text
http://localhost:5052/actuator/health
```

Para consultar a ultima execucao da rotina, use:

```text
http://localhost:5052/monitoring/task-status
```

## Deploy Windows

Padrao recomendado:

```text
C:\ixcwatchtask
├─ IXCWatchTask-0.1.0.jar
├─ run-app.bat
├─ logs
├─ driver
└─ browser
```

Para producao, preferir execucao como servico Windows usando NSSM.

## Deploy Portainer

O projeto possui `Dockerfile` e `docker-compose.portainer.yml` para deploy como Stack no Portainer.

Variaveis obrigatorias na Stack:

```text
IXC_AUTH
IXC_WEB_LOGIN
IXC_WEB_PASSWORD
```

Variaveis opcionais:

```text
IXC_API_BASE_URL
WATCH_API_DELETE_URL
WATCH_API_SEARCH_URL
WATCH_WEB_DEBUG=false
```

O container instala Chromium e Chromedriver internamente e usa:

```text
SELENIUM_CHROME_DRIVER_PATH=/usr/bin/chromedriver
SELENIUM_CHROME_BINARY_PATH=/usr/bin/chromium
TZ=America/Sao_Paulo
```

No Portainer:

1. Crie uma Stack a partir do repositorio Git.
2. Use `docker-compose.portainer.yml` como compose path.
3. Configure as variaveis obrigatorias como Environment variables da Stack.
4. Publique a porta `5052`.
5. Valide:

```text
GET http://SERVIDOR:5052/actuator/health
GET http://SERVIDOR:5052/monitoring/task-status
```

Os logs da aplicacao ficam persistidos no volume Docker `ixc-watch-task-logs`.

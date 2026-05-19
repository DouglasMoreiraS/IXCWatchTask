# Changelog

## v0.1.0 - 2026-05-19

### Adicionado
- Rotina de consulta de usuarios TV e contratos bloqueados no IXCSoft.
- Busca de token Watch dentro do IXCSoft via Selenium.
- Exclusao de tickets Watch e limpeza de usuarios TV no IXCSoft.
- Logs separados por aplicacao, rotina e erros.
- Identificador `executionId` para acompanhar cada execucao da rotina.
- Endpoint Actuator `/actuator/health` para monitoramento externo.
- Endpoint `/monitoring/task-status` para consultar a ultima execucao da rotina.
- Endpoint manual `POST /tasks/ixc-watch/run`.

### Alterado
- Credenciais e URLs externalizadas para variaveis de ambiente.
- Agendamento habilitado via Spring Scheduling.
- Configuracao de timeout para chamadas HTTP.
- Tratamento defensivo para respostas incompletas das APIs.
- Agendamento diario ajustado para 7h.

### Corrigido
- Contador de exclusoes IXC.
- Risco de falha quando contratos duplicados sao retornados em filtros diferentes.
- Remocao de logs sensiveis com token Watch.

### Observacoes
- A aplicacao ainda sera preparada para execucao como servico Windows e monitoramento via Uptime Kuma.
- Testes existentes sao integracoes manuais e podem acionar APIs reais; nao executar sem revisar o impacto.

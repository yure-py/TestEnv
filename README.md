### Solar-ERP

### Guia de Contribuição - Projeto SolarSystem

Este documento define o **padrão de branches**, **nomenclatura de commits** e o **fluxo de trabalho Git** adotado neste projeto.

### Estrutura de Branches

Todas as branches devem começar com o **prefixo do tipo da branch**, seguido do **código da tarefa (ex: SOLAR-0001)** e uma **descrição opcional**.

### Padrão de nomes de branch:

Exemplo: tipo/SOLAR-XXXX-descricao-breve

### Tipos permitidos de branch:

| Tipo de Branch | Prefixo                | Exemplo                               | Uso |
|----------------|------------------------|---------------------------------------|----|
| Feature        | `feature/`             | `feature/SOLAR-0001-cadastro-cliente` | Novas funcionalidades |
| Bugfix         | `bugfix/`              | `bugfix/SOLAR-0002-corrigir-login`    | Correção de bugs |
| Hotfix         | `hotfix/`              | `hotfix/SOLAR-0003-ajuste-deploy`     | Correções urgentes em produção |
| Release        | `release/`             | `release/SOLAR-0004-v1.2.0`           | Preparação para nova versão |
| Refactor       | `refactor/`            | `refactor/SOLAR-0005-otimizar-query`  | Melhorias internas (sem alterar comportamento) |
| Test           | `test/`                | `test/SOLAR-0006-cobertura-login`     | Branches específicas para testes |
| Docs           | `docs/`                | `docs/SOLAR-0007-atualizar-readme`    | Atualizações de documentação |

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### Padrão de Commits

Cada commit deve começar obrigatoriamente com o **código da tarefa entre colchetes**, seguido por um traço, o **tipo de alteração** e uma descrição breve.

### Formato padrão:

### Exemplos de mensagens de commit:

- `[SOLAR-0001] - feat: criar tela de cadastro de cliente`
- `[SOLAR-0002] - fix: corrigir validação de email`
- `[SOLAR-0003] - refactor: melhorar performance da consulta`
- `[SOLAR-0004] - docs: atualizar documentação da API`
- `[SOLAR-0005] - test: adicionar teste unitário para serviço X`

### Tipos permitidos de commits (baseado em Conventional Commits):

| Tipo       | Quando usar                         |
|----------- | ----------------------------------- |
| feat       | Para adicionar nova funcionalidade |
| fix        | Para correção de bugs |
| refactor   | Para melhorias internas no código (sem alteração de comportamento) |
| docs       | Para atualização de documentação |
| test       | Para adicionar ou alterar testes |

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

### Fluxo de Trabalho Exemplo (Feature)

1. **Atualizar a branch `develop`:**

```bash
git checkout develop
git pull

Exemplo:
  Criar a branch com o código da tarefa:
    git checkout -b feature/SOLAR-0001-cadastro-cliente
  Fazer as alterações, adicionar e commitar:
    git add .
    git commit -m "[SOLAR-0001] - feat: criar tela de cadastro de cliente"
  Subir a branch para o repositório remoto:
    git push -u origin feature/SOLAR-0001-cadastro-cliente
### Seja feliz!

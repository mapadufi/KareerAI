# Estrutura do Projeto - KareerAI 📁

Este documento descreve a organização das pastas e a responsabilidade de cada camada no projeto KareerAI.

## 🏗️ Camadas do Aplicativo

### 1. `data/` (Camada de Dados)
Responsável pelo gerenciamento de dados, persistência local e comunicação com APIs externas.
*   **`local/`**: Configuração do banco de dados Room.
    *   `dao/`: Interfaces de acesso ao banco (Usuario, Job, Curriculo).
    *   `database/`: Classe principal `KareerAIDatabase`.
    *   `entity/`: Tabelas do banco de dados.
    *   `datastore/`: Preferências do usuário (Tema, API Keys).
*   **`model/`**: Modelos de dados utilizados em todo o app (POJOs/Data Classes).
*   **`remote/`**: Comunicação com APIs externas.
    *   `ai/`: Integração com Google Gemini (Requests, Responses, Services).
    *   `jobs/`: Integração com API de Vagas Remotive.
    *   `network/`: Configuração do Retrofit e Clientes HTTP.
*   **`repository/`**: Abstração que decide a fonte dos dados (Local vs Remoto).

### 2. `domain/` (Camada de Domínio)
Contém a lógica de negócio pura do aplicativo, independente de frameworks.
*   **`usecase/`**: Ações específicas que o usuário pode realizar (Analisar currículo, Buscar vagas, Salvar vaga).

### 3. `ui/` (Camada de Interface)
Tudo o que é visível para o usuário.
*   **`screen/`**: Telas completas do aplicativo (Login, Splash, Dashboard/Main, Detalhes).
*   **`theme/`**: Configurações de cores, tipografia e definição dos temas customizados.

### 4. `components/` (Componentes Reutilizáveis)
Biblioteca de componentes de UI padronizados para manter a consistência visual.
*   `buttons/`: Botões customizados (`KikoExtraButton`).
*   `layout/`: Estruturas de base como `LayoutKiko` e `NavigationDrawerKiko`.
*   `outlined/`, `toast/`, `progress/`: Inputs, notificações e indicadores de carregamento.

### 5. `navigation/` (Navegação)
Gerenciamento de rotas e fluxo entre as telas.
*   `Screen.kt`: Definição de rotas e nomes das telas.
*   `AppNavigation.kt`: Gráfico de navegação principal (`NavHost`).

### 6. `viewmodel/` (Camada de Estado)
Intermediário entre o UI e o Domínio, gerencia o estado da tela e sobrevive a mudanças de configuração.

---

## 🛠️ Arquivos de Configuração
*   `build.gradle.kts`: Dependências e configurações de compilação do Android.
*   `README.md`: Documentação geral do projeto.
*   `PROJECT_STRUCTURE.md`: Este guia de arquitetura.

# KareerAI 🚀 - Seu Assistente de Carreira Inteligente

O **KareerAI** é um aplicativo Android moderno desenvolvido para revolucionar a busca por emprego. Utilizando Inteligência Artificial de ponta, o app analisa currículos, encontra as melhores vagas com base no perfil do usuário e gera mensagens de apresentação personalizadas para recrutadores.

## ✨ Funcionalidades Principais

*   **🧠 Análise de Currículo por IA:** Envie seu currículo (texto ou arquivo) e deixe o Google Gemini identificar seu cargo, nível de senioridade e principais tecnologias.
*   **📍 Busca Geolocalizada:** Procure vagas de emprego em países e cidades específicos, filtrando as melhores oportunidades para sua região.
*   **📊 Match Score Inteligente:** A IA compara seu perfil com a descrição de cada vaga e atribui uma porcentagem de compatibilidade, ajudando você a focar no que realmente importa.
*   **✍️ IA Pitch (Candidatura):** Gere mensagens de apresentação (Cover Letters) curtas e impactantes, escritas pela IA para cada vaga específica.
*   **💾 Gestão de Vagas:** Salve suas vagas favoritas para consultar depois e gerencie seu histórico localmente.
*   **🎨 Temas Personalizados:** Suporte completo a Modo Escuro (Dark Mode) e 4 paletas de cores (Padrão, Verde, Vermelho e Roxo).
*   **🔑 Acesso Universal:** Configuração dinâmica de API Key, permitindo que cada usuário utilize sua própria cota gratuita do Google Gemini.

## 🛠️ Tecnologias Utilizadas

*   **Linguagem:** [Kotlin](https://kotlinlang.org/)
*   **Interface:** [Jetpack Compose](https://developer.android.com/jetpack/compose) com Material 3
*   **Arquitetura:** MVVM (Model-View-ViewModel) com Use Cases e Repositories
*   **Banco de Dados:** [Room Database](https://developer.android.com/training/data-storage/room) para persistência local
*   **Preferências:** [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
*   **Rede:** [Retrofit](https://square.github.io/retrofit/) & OkHttp para integração com APIs
*   **IA:** [Google Gemini 1.5 Flash](https://aistudio.google.com/) (API de Inteligência Artificial)
*   **Navegação:** Navigation Compose

## 🚀 Como Executar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/mapadufi/KareerAI.git
    ```
2.  **Abra no Android Studio:** Certifique-se de estar usando a versão Ladybug ou superior.
3.  **Configuração da IA:**
    *   Acesse o [Google AI Studio](https://aistudio.google.com/).
    *   Gere uma **API Key** gratuita.
    *   No aplicativo, vá em **Configurações > Inteligência Artificial** e cole sua chave.
4.  **Execute o app:** Escolha um emulador ou dispositivo físico e clique em Run.

## 📁 Estrutura do Projeto

*   `components/`: Componentes de UI customizados e reutilizáveis (Kiko Components).
*   `data/`: Camada de dados (DAOs, Entities, Repositories, Retrofit Clients).
*   `domain/`: Regras de negócio e Use Cases.
*   `ui/`: Telas (Screens) e Temas (Theme).
*   `viewmodel/`: Lógica de estado e integração com a UI.

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---
Desenvolvido com ❤️ por [Marcos Figueiredo](https://github.com/mapadufi)

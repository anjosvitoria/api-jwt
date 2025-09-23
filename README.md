# API de Validação de JWT

Esta é uma API REST desenvolvida em Spring Boot para validação de tokens JWT (JSON Web Tokens). A API verifica a validade de tokens JWT, incluindo a validação de claims específicas como nome, função (role) e seed.

## 🔧 Tecnologias Utilizadas

- Java 17
- Spring Boot 3.5.4
- Spring Web
- Spring Actuator
- auth0
- JJWT (Java JWT)
- Lombok
- Maven

## 📋 Requisitos

- Java 17 ou superior
- Maven 3.6.3 ou superior
- Qualquer IDE de desenvolvimento Java (recomendado IntelliJ IDEA ou VS Code)

## 🚀 Como Executar

1. Clone o repositório:
   ```bash
   git clone [URL_DO_REPOSITÓRIO]
   cd api-jwt
   ```

2. Compile o projeto:
   ```bash
   mvn clean install
   ```

3. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

A aplicação estará disponível em `http://localhost:8080`

## 📡 Endpoints

### Validar JWT

```
POST /validJwt
```

**Request Body:**
```json
{
    "jwt": "seu_token_jwt_aqui"
}
```

**Respostas:**
- 200 OK: Token válido
  ```json
  {
    "valid": true,
    "message": "Valid JWT"
  }
  ```
- 200 OK: Token inválido
  ```json
  {
    "valid": false,
    "message": "Mensagem de erro detalhada"
  }
  ```

## 🔐 Validações Implementadas

A API realiza as seguintes validações no token JWT:

1. **Estrutura do Token**
   - Verifica se o token não está vazio
   - Verifica se o token pode ser decodificado corretamente

2. **Claims Obrigatórias**
   - `name`: Nome do usuário (não pode conter números, máximo de 256 caracteres)
   - `role`: Função do usuário (deve estar entre os valores permitidos)
   - `seed`: Valor de seed para validação adicional

3. **Validações Específicas**
   - O nome não pode conter dígitos
   - A role deve estar entre os valores permitidos (definidos em `statics.VALID_ROLES`)
   - A seed deve ser um número primo
   - Apenas 3 claims são permitidas no token

## 🏗️ Estrutura do Projeto

```
src/main/java/anjos/api_jwt/
├── controllers/
│   └── JwtController.java         # Controlador da API
├── dto/
│   ├── JwtRequest.java           # DTO para requisição de validação
│   └── JwtResponse.java          # DTO para resposta da API
├── models/
│   ├── enums/
│   │   └── Statics.java          # Constantes e enums do sistema
│   └── services/
│       ├── JwtValidationService.java # Serviço principal de validação
│       └── JwtValidation.java     # Lógica de validação específica
└── ApiJwtApplication.java        # Classe principal da aplicação
```

## 📚 Decisões de Projeto

1. **Validação em Camadas**
   - Separação clara entre validação de estrutura e validação de negócio
   - Tratamento de erros com mensagens descritivas

2. **Segurança**
   - Validação de token sem necessidade de chave secreta (apenas decodificação)
   - Verificação de claims obrigatórias
   - Validação de formato dos dados

3. **Arquitetura**
   - Padrão MVC com separação clara de responsabilidades
   - Injeção de dependências com `@Autowired` e construtores

4. **Boas Práticas**
   - Código documentado
   - Tratamento de exceções
   - Validações específicas para cada campo

## 🧪 Testes

Para executar os testes do projeto:

```bash
mvn test
```

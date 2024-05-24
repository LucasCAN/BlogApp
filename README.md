# Orientações para executar o projeto

1. Instalar o Node.js e o npm
   
Verificar se já está instalado:
```
node -v
npm -v
```
Caso não esteja instalado, faça o download e instale a versão mais recente do Node.js, que já inclui o npm, a partir do [site oficial](https://nodejs.org/en)

2. Após as configurações executar o comando no caminho BlogApp/api
```
npm run start
```
3. Com a api rodando corretamente em seu estado inicial, configurar o ip local no modulo de dependências da aplicação. 
  Seguir até app/src/main/java/com/example/blogapp/di/ModuleDI.kt, onde deve-se alterar para o ip local

```
   fun providesBaseUrl() = "http://{ipLocal}:5500/api/v1/"
   
   Exemplo: "http://10.0.1.1:5500/api/v1/"
```

Com isso, será possível executar corretamente a aplicação localmente.

PS: Qualquer dúvida ou problema na execução favor entrar em contato.


# O que foi usado no projeto
API
- Implementaçao da api com Node.js
- Pacotes e scripts NPM

APP
- Jetpack Compose
- Material3
- Retrofit
- Navigation
- Coroutines
- Injeção de Dependências
- MVVM

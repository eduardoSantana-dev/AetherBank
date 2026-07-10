# HTTP Status - Guia Rápido (Spring Boot)

## 2xx - Sucesso

  Status               Quando usar
  -------------------- -----------------------------------
  **200 OK**           Requisição realizada com sucesso.
  **201 CREATED**      Recurso criado (cadastro).
  **204 NO_CONTENT**   Sucesso sem retornar conteúdo.

## 4xx - Erro do Cliente

  -----------------------------------------------------------------------
  Status                      Quando usar
  --------------------------- -------------------------------------------
  **400 BAD_REQUEST**         Dados inválidos ou mal formatados.

  **401 UNAUTHORIZED**        Login ou token inválido.

  **403 FORBIDDEN**           Usuário autenticado, mas sem permissão.

  **404 NOT_FOUND**           Recurso não encontrado.

  **409 CONFLICT**            Conflito (ex.: e-mail já cadastrado).

  **422                       Regra de negócio inválida (ex.: saldo
  UNPROCESSABLE_ENTITY**      insuficiente).
  -----------------------------------------------------------------------

## 5xx - Erro do Servidor

  Status                          Quando usar
  ------------------------------- ---------------------------------------
  **500 INTERNAL_SERVER_ERROR**   Erro inesperado no servidor.
  **503 SERVICE_UNAVAILABLE**     Serviço indisponível temporariamente.

## Exemplos no Spring Boot

``` kotlin
return ResponseEntity.ok(body)                    // 200
return ResponseEntity.status(HttpStatus.CREATED).body(body) // 201
return ResponseEntity.noContent().build()         // 204

return ResponseEntity.badRequest().build()        // 400
return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build() // 401
return ResponseEntity.status(HttpStatus.FORBIDDEN).build()    // 403
return ResponseEntity.notFound().build()          // 404
return ResponseEntity.status(HttpStatus.CONFLICT).build()     // 409
```

## Para o AetherBank

  Situação                  Status
  ------------------------- -------------------------------
  Cadastro                  **201 CREATED**
  Login                     **200 OK**
  E-mail já existe          **409 CONFLICT**
  Senha/E-mail incorretos   **401 UNAUTHORIZED**
  Conta inexistente         **404 NOT_FOUND**
  Saldo insuficiente        **422 UNPROCESSABLE_ENTITY**
  Dados inválidos           **400 BAD_REQUEST**
  Erro interno              **500 INTERNAL_SERVER_ERROR**

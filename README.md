# Como funciona e para que serve o encurtador de url?
# Como iniciar um java spring. (com rest/jpa)
# Vscode para java (pesquisar)

# desenhos de arquitetura https://whimsical.com/

# health check

https://app.quicktype.io/

1. mudar o event_time timestamp
2. mudar o count para long
3. mudar no banco para a coluna hasg unique

gerar o hash (estudar sobre hash) (https://www.browserling.com/tools/all-hashes) CRC32
8 bytes hash

A entrada post e get deve ser esse json: (olhar funcionamento DTO)
POST
{
    "origin_location": ""
}

GET LISTAR
{
    "id": "",
    "origin_location": "",
    "event_time": timestamp,
    "hash": "",
    "count": 1
}

sua api sera com esse prefixo: /api/v1/shortener

// redirect

precisa ser a url + o hash

http://localhost:8080/{hash}

buscar pelo hash, pegar origin_location

# Shortener URL

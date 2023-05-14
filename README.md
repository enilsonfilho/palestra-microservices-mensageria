# Configurando o Ambiente Kafka com Kafdrop

Este documento fornece instruções para configurar e executar um ambiente Kafka juntamente com o Kafdrop. Siga os passos abaixo para configurar o ambiente corretamente.

## Passo 1: Criar o arquivo docker-compose.yml

Crie um novo arquivo chamado `docker-compose.yml` no diretório desejado e cole o seguinte script:

```yaml
version: '3'
services:
  zookeeper:
    image: confluentinc/cp-zookeeper:latest
    networks:
      - broker-kafka
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000

  kafka:
    image: confluentinc/cp-kafka:latest
    networks:
      - broker-kafka
    depends_on:
      - zookeeper
    ports:
      - 9092:9092
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:29092,PLAINTEXT_HOST://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1

  kafdrop:
    image: obsidiandynamics/kafdrop:latest
    networks:
      - broker-kafka
    depends_on:
      - kafka
    ports:
      - 19000:9000
    environment:
      KAFKA_BROKERCONNECT: kafka:29092
```

networks:
  broker-kafka:
    driver: bridge

## Passo 2: Executar o ambiente Kafka e Kafdrop

No diretório onde o arquivo docker-compose.yml foi criado, execute o seguinte comando:

```
docker-compose up -d
```

Isso iniciará os containers Docker para o Kafka e o Kafdrop no modo de execução em segundo plano.

## Passo 3: Acessar o Kafdrop
Após a execução bem-sucedida, você poderá acessar o Kafdrop no seu navegador usando o seguinte endereço:

```
http://localhost:19000
```

O Kafdrop é uma interface de usuário para visualizar e navegar pelos tópicos e partições do Kafka. Você poderá explorar os detalhes do cluster Kafka e gerenciar as mensagens.

## Passo 4: Utilizar o Kafka

O Kafka estará disponível na porta 9092. Você pode utilizar as bibliotecas e ferramentas Kafka para se conectar a ele e interagir com os tópicos e as mensagens.

Agora você configurou com sucesso o ambiente Kafka com o Kafdrop. Você pode começar a explorar e utilizar o Kafka para criar fluxos de dados eficientes e escaláveis em sua aplicação.

# 이더리움 JSON-RPC, 통신할 때 web3j 라이브러리를 사용하지 않은 프로젝트

web3j는 좋은 프로젝트라고 생각합니다. 하지만 초급자가 사용하기에는 어려운 라이브러리입니다. 
그래서 저는 web3j 프로젝트를 조금 더 쉽게 접근할 수 있는 프로젝트를 개발하였습니다. 
web3j를 사용하시기 전에 이 프로젝트를 먼저 사용하시면 web3j를 더 쉽게 사용하실 수 있습니다.

---

## Simple Ethereum Json-RPC With Java Spring

Ethereum의 Smart Contracdt는 JSON-RPC를 제공합니다. 해당문서는 여기에서 확인하실 수 있습니다. [here](https://github.com/ethereum/wiki/wiki/JSON-RPC)

이 프로젝트는 위 통신 규약을 토대로  만들어진 기본 테스트 프로젝트입니다.

web3j를 전혀 사용하지 않은것은 아닙니다. hash등의 변환과 객체처리방법 등은 이용하였습니다.

---

## 사용한 도구

### Java
- JDK 8
- Spring + Spring Boot
- Maven
- Swagger (REST API 호출 테스트 및 Doc을 위하여 사용)

### Smart Contract
- truffle
- [ganache-cli](https://github.com/trufflesuite/ganache-cli)


이 프로젝트의 목적은 JAVA Client에 있으므로 Smart Contract는 상대적으로 구현 및 테스트가 쉬운 truffle과 ganache-cli를 사용하였습니다.

---

## 테스트 순서


### 1. truffle 설치 후 ganache-cli 실행

- ganache-cli는 ethereum server를 쉽게 구현 할 수 있게 해줍니다.

1. node.js 설치 URL(https://nodejs.org/en/)
2. truffle 설치 URL

```bash
npm install -g truffle
```

3. ganache-cli 설치

```bash
npm install -g ganache-cli
```

4. ganache-cli 실행

```bash
ganache-cli -d -m tutorial
```


### 2. smart contract 컴파일 후 배포

1. 이 프로젝트의 solidity 폴더를 다운로드 받은 후 truffle을 이용하여 컴파일 하여 정상임을 확인한다.
    
```bash
truffle compile
```

2. ganache-cli로 서버가 로컬 서버가 올라가 있는 것을 확인 한 후 truffle을 이용하여 서버에 배포한다. 그리고 contract address를 꼭 복사하여 둔다.

```bash
truffle migrate
```

### 3. spring boot로 테스트 진행

1. application.yml 파일열어서 복사해 둔 contract address 주소를 넣는다.
2. 테스트를 진행한다.

---

## 요약
바쁘신 분들은 [code](./src/test/java/org/BlockChainService/service/GethwithmavenApplicationTests.java) 여기를 보시고 Ethereum과의 json-rpc 기본 방식을 익시히면 좋습니다.


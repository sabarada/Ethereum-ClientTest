# Ethereum JSON-RPC, Did not use the web3j when communicate smart contract

I think web3j is a good project. However, the Project is difficult for beginners to use.
So I started this project that makes the web3j project a little easier to access. 
If you use this project before using web3j, you can use web3j more easily.

## Simple Ethereum Json-RPC With Java Spring

Ethereum provides json-rpc. You Can see the Ethereum json-rpc doc [here](https://github.com/ethereum/wiki/wiki/JSON-RPC)

this project is a basic test project based on the above communication protocol.

did not use web3j at all. I use hash conversion and Type etc...

## Uses Tool

### Java Client
- JDK 8
- Spring
- Maven
- Swagger ( for REST API call test )

### Smart Contract
- truffle
- [ganache-cli](https://github.com/trufflesuite/ganache-cli)
   
since the purpose  of this project is in the JAVA Client, Smart Contract uses truffle and ganache-cli, which ar relatively easy to implement and test.

## Test Sequence

### 1. truffle setup and execute ganache-cli 

- ganache-cli makes it easy to implement an ethereum server

1. node.js setup URL(https://nodejs.org/en/)
2. truffle setup

```bash
npm install -g truffle
```

3. ganache-cli setup

```bash
npm install -g ganache-cli
```

4. ganache-cli execute

```bash
ganache-cli -d -m tutorial
```

### 2. smart contract compile & deploy

1. access the solidity directory in my project and run the following command

```bash
truffle compile
```

2. After confirming that the local server is up with ganache-cli, deploy it to the server using truffle. Make sure to copy the contract address.

```bash
truffle migrate
```

### 3. spring boot test
1. Open the application.yml file and enter the contract address that you copied.
2. Proceed with the test.

## Look
if you are in a hurry, you can use the json-rpc default method with ethereum here [code](./src/test/java/org/BlockChainService/service/EthT.java)


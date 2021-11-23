# Getting Started

solc编译合约

1、安装 nodejs

2、安装 web3j

3、安装 solc

4、合约代码放在src/main/resources/solidity

5、安装 @openzeppelin
npm install @openzeppelin/contracts
把 生成的@openzeppelin文件移到src/main/resources/solidity

6、生成abi，bin文件
solcjs AuctionMarket.sol --abi --bin --optimize -o ./build

7、生成包装类
web3j generate solidity -a src/main/resources/solidity/build/AuctionMarket_sol_AuctionMarket.abi -b src/main/resources/solidity/build/AuctionMarket_sol_AuctionMarket.bin -o src/main/java/ -p com.odcchina.cafoserver.contract



代码 com.odcchina.cafoserver.contract.AuctionMarket 生成的包装类。监听合约事件和调用合约方法
com.odcchina.cafoserver.contract.ContractHelper  合约调用

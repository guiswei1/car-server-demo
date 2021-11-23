package com.odcchina.cafoserver.contract;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Async;

import java.math.BigInteger;
import java.util.List;

//智能合约调用
@Component
public class ContractHelper {
    //合约地址
    private String contractAddress = "0x310280Ac17ED0e7A0fE3F3774B8F3007F98D3229";
    //公共节点地址
    private String url = "https://ropsten.infura.io/v3/bb285df8fd6147a7b9765e0ca2b7cffa";
    //owner 钱包私钥
    private String privateKey = "3093012f8f951a5b296cc85079a583053061439565a9fc412121c045ae99de5a";

    private Web3j web3j;
    private AuctionMarket auctionMarket;

    public ContractHelper() throws Exception {

        web3j = Web3j.build(new HttpService(url), 1000, Async.defaultExecutorService());
        Credentials credentials = Credentials.create(privateKey);
        auctionMarket = AuctionMarket.load(
            contractAddress, web3j, credentials, new DefaultGasProvider()
        );
    }


    //定时任务监听事件,监听上架要拍卖的nft
    @Scheduled(fixedRate = 1000)
    public void eventFilter( ){
        EthFilter filter = new EthFilter(DefaultBlockParameterName.EARLIEST, DefaultBlockParameterName.LATEST,
            contractAddress);

        auctionMarket.newItemAuctionIdEventFlowable(filter).subscribe(event -> {
            System.out.println(event.auctionId);
        });

    }

    //销毁是转移当前合约的token
    public void withdraw( String address ) throws Exception {
        TransactionReceipt receipt =  auctionMarket.withdraw(address).send();
        System.out.println(receipt.getTransactionHash());
    }
}

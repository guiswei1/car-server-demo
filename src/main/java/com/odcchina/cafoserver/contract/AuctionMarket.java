package com.odcchina.cafoserver.contract;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple4;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class AuctionMarket extends Contract {
    public static final String BINARY = "60c060405234801561001057600080fd5b5060405161117a38038061117a83398101604081905261002f916100bb565b6100383361004f565b6001600160a01b039182166080521660a0526100ee565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b80516001600160a01b03811681146100b657600080fd5b919050565b600080604083850312156100ce57600080fd5b6100d78361009f565b91506100e56020840161009f565b90509250929050565b60805160a051611036610144600039600081816101e90152818161036201526109a40152600081816101970152818161057b015281816105f50152818161080d015281816108ed015261091c01526110366000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c806360eae0b51161007157806360eae0b51461020b578063715018a61461022c5780638da5cb5b14610234578063b75c7dc614610245578063e123182b14610258578063f2fde38b1461026b57600080fd5b806309527fe2146100b95780631304e535146101115780631edbc5be14610126578063253f65e81461019257806351cff8d9146101d15780635bf8633a146101e4575b600080fd5b6100cc6100c7366004610de7565b61027e565b604051610108919081516001600160a01b0316815260208083015190820152604080830151908201526060918201519181019190915260800190565b60405180910390f35b61012461011f366004610e00565b6102ff565b005b610168610134366004610de7565b600160208190526000918252604090912080549181015460028201546003909201546001600160a01b039093169290919084565b604080516001600160a01b0390951685526020850193909352918301526060820152608001610108565b6101b97f000000000000000000000000000000000000000000000000000000000000000081565b6040516001600160a01b039091168152602001610108565b6101246101df366004610e2c565b610539565b6101b97f000000000000000000000000000000000000000000000000000000000000000081565b61021e610219366004610e55565b61061f565b604051908152602001610108565b610124610680565b6000546001600160a01b03166101b9565b610124610253366004610de7565b6106b6565b610124610266366004610e6d565b610772565b610124610279366004610e2c565b610a0a565b6102b2604051806080016040528060006001600160a01b031681526020016000815260200160008152602001600081525090565b50600090815260016020818152604092839020835160808101855281546001600160a01b031681529281015491830191909152600281015492820192909252600390910154606082015290565b600081116103445760405162461bcd60e51b815260206004820152600d60248201526c496e76616c696420706172616d60981b60448201526064015b60405180910390fd5b60405163e985e9c560e01b81523360048201819052306024830152907f00000000000000000000000000000000000000000000000000000000000000006001600160a01b03169063e985e9c590604401602060405180830381865afa1580156103b1573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906103d59190610e8f565b6104105760405162461bcd60e51b815260206004820152600c60248201526b139bdd08185c1c1c9bdd995960a21b604482015260640161033b565b6040516bffffffffffffffffffffffff19606083901b166020820152603481018590526054810184905260009060740160408051601f198184030181529181528151602092830120600081815260019093529120549091506001600160a01b0316156104a25760008181526001602052604081206003018054859290610497908490610ec7565b909155506105079050565b604080516080810182526001600160a01b038481168252602080830189815283850189815260608501898152600088815260019485905296909620945185546001600160a01b0319169416939093178455519083015551600282015590516003909101555b60405181907f94facd1f25dcb29a5497a1ba55954fdaeaca0a75fb2b4224495c97120928dd9f90600090a25050505050565b6000546001600160a01b031633146105635760405162461bcd60e51b815260040161033b90610edf565b6040516370a0823160e01b81523060048201526000907f00000000000000000000000000000000000000000000000000000000000000006001600160a01b0316906370a0823190602401602060405180830381865afa1580156105ca573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105ee9190610f14565b905061061b7f00000000000000000000000000000000000000000000000000000000000000008383610aa5565b5050565b600061062e6020830183610e2c565b6040805160609290921b6bffffffffffffffffffffffff191660208084019190915284013560348301528301356054820152607401604051602081830303815290604052805190602001209050919050565b6000546001600160a01b031633146106aa5760405162461bcd60e51b815260040161033b90610edf565b6106b46000610b0d565b565b600081815260016020818152604092839020835160808101855281546001600160a01b0316808252938201549281019290925260028101549382019390935260039092015460608301526107405760405162461bcd60e51b81526020600482015260116024820152701d5b9adb9bdddb88185d58dd1a5bdb9259607a1b604482015260640161033b565b506000908152600160208190526040822080546001600160a01b03191681559081018290556002810182905560030155565b600082815260016020526040902060038101543391908311156107d75760405162461bcd60e51b815260206004820152601860248201527f4e6f7420656e6f75676820746f6b656e20746f2073656c6c0000000000000000604482015260640161033b565b60008160020154846107e99190610f2d565b6040516370a0823160e01b81526001600160a01b03858116600483015291925082917f000000000000000000000000000000000000000000000000000000000000000016906370a0823190602401602060405180830381865afa158015610854573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108789190610f14565b10156108bf5760405162461bcd60e51b8152602060048201526016602482015275084eaf2cae440cceadcc8e640dcdee840cadcdeeaced60531b604482015260640161033b565b838260030160008282546108d39190610f4c565b90915550600090506108e6606483610f63565b90506109147f0000000000000000000000000000000000000000000000000000000000000000853084610b5d565b8254610956907f00000000000000000000000000000000000000000000000000000000000000009086906001600160a01b03166109518587610f4c565b610b5d565b825460018401546003850154604051637921219560e11b81526001600160a01b03938416600482015287841660248201526044810192909252606482015260a06084820152600060a48201527f00000000000000000000000000000000000000000000000000000000000000009091169063f242432a9060c401600060405180830381600087803b1580156109ea57600080fd5b505af11580156109fe573d6000803e3d6000fd5b50505050505050505050565b6000546001600160a01b03163314610a345760405162461bcd60e51b815260040161033b90610edf565b6001600160a01b038116610a995760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b606482015260840161033b565b610aa281610b0d565b50565b6040516001600160a01b038316602482015260448101829052610b0890849063a9059cbb60e01b906064015b60408051601f198184030181529190526020810180516001600160e01b03166001600160e01b031990931692909217909152610b9b565b505050565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b6040516001600160a01b0380851660248301528316604482015260648101829052610b959085906323b872dd60e01b90608401610ad1565b50505050565b6000610bf0826040518060400160405280602081526020017f5361666545524332303a206c6f772d6c6576656c2063616c6c206661696c6564815250856001600160a01b0316610c6d9092919063ffffffff16565b805190915015610b085780806020019051810190610c0e9190610e8f565b610b085760405162461bcd60e51b815260206004820152602a60248201527f5361666545524332303a204552433230206f7065726174696f6e20646964206e6044820152691bdd081cdd58d8d9595960b21b606482015260840161033b565b6060610c7c8484600085610c86565b90505b9392505050565b606082471015610ce75760405162461bcd60e51b815260206004820152602660248201527f416464726573733a20696e73756666696369656e742062616c616e636520666f6044820152651c8818d85b1b60d21b606482015260840161033b565b843b610d355760405162461bcd60e51b815260206004820152601d60248201527f416464726573733a2063616c6c20746f206e6f6e2d636f6e7472616374000000604482015260640161033b565b600080866001600160a01b03168587604051610d519190610fb1565b60006040518083038185875af1925050503d8060008114610d8e576040519150601f19603f3d011682016040523d82523d6000602084013e610d93565b606091505b5091509150610da3828286610dae565b979650505050505050565b60608315610dbd575081610c7f565b825115610dcd5782518084602001fd5b8160405162461bcd60e51b815260040161033b9190610fcd565b600060208284031215610df957600080fd5b5035919050565b600080600060608486031215610e1557600080fd5b505081359360208301359350604090920135919050565b600060208284031215610e3e57600080fd5b81356001600160a01b0381168114610c7f57600080fd5b600060808284031215610e6757600080fd5b50919050565b60008060408385031215610e8057600080fd5b50508035926020909101359150565b600060208284031215610ea157600080fd5b81518015158114610c7f57600080fd5b634e487b7160e01b600052601160045260246000fd5b60008219821115610eda57610eda610eb1565b500190565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b600060208284031215610f2657600080fd5b5051919050565b6000816000190483118215151615610f4757610f47610eb1565b500290565b600082821015610f5e57610f5e610eb1565b500390565b600082610f8057634e487b7160e01b600052601260045260246000fd5b500490565b60005b83811015610fa0578181015183820152602001610f88565b83811115610b955750506000910152565b60008251610fc3818460208701610f85565b9190910192915050565b6020815260008251806020840152610fec816040850160208701610f85565b601f01601f1916919091016040019291505056fea264697066735822122005214179b668e246d09b70d5219a4a5551d52f7b5d4c5c779944b1aca39a594c64736f6c634300080a0033";

    public static final String FUNC_AUCTIONS = "auctions";

    public static final String FUNC_CALCITEMAUCTIONID = "calcItemAuctionId";

    public static final String FUNC_CCTADDRESS = "cctAddress";

    public static final String FUNC_GETAUCTIONITEM = "getAuctionItem";

    public static final String FUNC_NFTADDRESS = "nftAddress";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PURCHASE = "purchase";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_REVOKE = "revoke";

    public static final String FUNC_SELLITEM = "sellItem";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final String FUNC_WITHDRAW = "withdraw";

    public static final Event NEWITEMAUCTIONID_EVENT = new Event("NewItemAuctionId", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>(true) {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    @Deprecated
    protected AuctionMarket(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected AuctionMarket(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected AuctionMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected AuctionMarket(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<NewItemAuctionIdEventResponse> getNewItemAuctionIdEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWITEMAUCTIONID_EVENT, transactionReceipt);
        ArrayList<NewItemAuctionIdEventResponse> responses = new ArrayList<NewItemAuctionIdEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewItemAuctionIdEventResponse typedResponse = new NewItemAuctionIdEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.auctionId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewItemAuctionIdEventResponse> newItemAuctionIdEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewItemAuctionIdEventResponse>() {
            @Override
            public NewItemAuctionIdEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWITEMAUCTIONID_EVENT, log);
                NewItemAuctionIdEventResponse typedResponse = new NewItemAuctionIdEventResponse();
                typedResponse.log = log;
                typedResponse.auctionId = (byte[]) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewItemAuctionIdEventResponse> newItemAuctionIdEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWITEMAUCTIONID_EVENT));
        return newItemAuctionIdEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, BigInteger>> auctions(byte[] param0) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_AUCTIONS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple4<String, BigInteger, BigInteger, BigInteger>>(function,
                new Callable<Tuple4<String, BigInteger, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple4<String, BigInteger, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple4<String, BigInteger, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue(), 
                                (BigInteger) results.get(3).getValue());
                    }
                });
    }

    public RemoteFunctionCall<byte[]> calcItemAuctionId(Auction auction) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CALCITEMAUCTIONID, 
                Arrays.<Type>asList(auction), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteFunctionCall<String> cctAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_CCTADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<Auction> getAuctionItem(byte[] auctionId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETAUCTIONITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(auctionId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Auction>() {}));
        return executeRemoteCallSingleValueReturn(function, Auction.class);
    }

    public RemoteFunctionCall<String> nftAddress() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_NFTADDRESS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> purchase(byte[] auctionId, BigInteger _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PURCHASE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(auctionId), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> revoke(byte[] auctionId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_REVOKE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Bytes32(auctionId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> sellItem(BigInteger _tokenId, BigInteger _price, BigInteger _amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELLITEM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_tokenId), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, newOwner)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> withdraw(String to) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_WITHDRAW, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static AuctionMarket load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuctionMarket(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static AuctionMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new AuctionMarket(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static AuctionMarket load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new AuctionMarket(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static AuctionMarket load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new AuctionMarket(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<AuctionMarket> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String _cctAddress, String _nftAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cctAddress), 
                new org.web3j.abi.datatypes.Address(160, _nftAddress)));
        return deployRemoteCall(AuctionMarket.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<AuctionMarket> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String _cctAddress, String _nftAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cctAddress), 
                new org.web3j.abi.datatypes.Address(160, _nftAddress)));
        return deployRemoteCall(AuctionMarket.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AuctionMarket> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String _cctAddress, String _nftAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cctAddress), 
                new org.web3j.abi.datatypes.Address(160, _nftAddress)));
        return deployRemoteCall(AuctionMarket.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<AuctionMarket> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String _cctAddress, String _nftAddress) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _cctAddress), 
                new org.web3j.abi.datatypes.Address(160, _nftAddress)));
        return deployRemoteCall(AuctionMarket.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class Auction extends StaticStruct {
        public String seller;

        public BigInteger tokenId;

        public BigInteger price;

        public BigInteger amount;

        public Auction(String seller, BigInteger tokenId, BigInteger price, BigInteger amount) {
            super(new org.web3j.abi.datatypes.Address(seller),new org.web3j.abi.datatypes.generated.Uint256(tokenId),new org.web3j.abi.datatypes.generated.Uint256(price),new org.web3j.abi.datatypes.generated.Uint256(amount));
            this.seller = seller;
            this.tokenId = tokenId;
            this.price = price;
            this.amount = amount;
        }

        public Auction(Address seller, Uint256 tokenId, Uint256 price, Uint256 amount) {
            super(seller,tokenId,price,amount);
            this.seller = seller.getValue();
            this.tokenId = tokenId.getValue();
            this.price = price.getValue();
            this.amount = amount.getValue();
        }
    }

    public static class NewItemAuctionIdEventResponse extends BaseEventResponse {
        public byte[] auctionId;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }
}

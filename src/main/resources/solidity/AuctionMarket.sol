// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts/access/Ownable.sol";
import "@openzeppelin/contracts/token/ERC1155/IERC1155.sol";
import "@openzeppelin/contracts/token/ERC20/IERC20.sol";
import "@openzeppelin/contracts/token/ERC20/utils/SafeERC20.sol";

//物品拍卖
contract AuctionMarket is Ownable {
    //拍卖实体类
    struct Auction {
        address seller;
        uint256 tokenId;
        uint256 price;
        uint256 amount;
    }

    event NewItemAuctionId(bytes32 indexed auctionId);

    mapping(bytes32 => Auction) public auctions;
    address public immutable cctAddress;
    address public immutable nftAddress;

    constructor(address _cctAddress, address _nftAddress) {
        cctAddress = _cctAddress;
        nftAddress = _nftAddress;
    }


    /// @dev 根据id获取物品拍卖信息
    ///
    /// @param auctionId auction id
    /// @return the details of this auction
    function getAuctionItem(bytes32 auctionId) external view returns (Auction memory) {
        return auctions[auctionId];
    }

    /// @dev 计算拍卖id
    /// 类似于java中的随机数生成
    /// @param auction The auction to calculate
    /// @return auction id
    function calcItemAuctionId(Auction calldata auction) external pure returns (bytes32) {
        return keccak256(abi.encodePacked(auction.seller, auction.tokenId, auction.price));
    }

    /// @dev 上架要拍卖的nft
    ///
    /// @param _tokenId Token id that use want to sell
    /// @param _price Price that use want to sell
    /// @param _amount Amount that user want to sell
    function sellItem(uint256 _tokenId,uint256 _price,uint256 _amount) external {

        require(_amount > 0, "Invalid param");

        address seller = msg.sender;

        //判断当前合约是否有权限转移卖家的token
        require(IERC1155(nftAddress).isApprovedForAll(seller, address(this)), "Not approved");

        bytes32 auctionId = keccak256(abi.encodePacked(seller, _tokenId, _price));

        // seller append more tokens with same price
        //判断拍卖信息是否为空，为空创建拍卖信息，不为空amount累加
        if (auctions[auctionId].seller != address(0)) {
            auctions[auctionId].amount += _amount;
        } else {
            auctions[auctionId] = Auction(seller, _tokenId, _price, _amount);
        }

        emit NewItemAuctionId(auctionId);
    }

    /// @dev 通过auctionId购买指定的nft
    ///
    /// @param auctionId Auction id user want to buy
    /// @param _amount Aount user want to buy
    function purchase(bytes32 auctionId, uint256 _amount) external {
        // TODO: optimzie gas usage
        address buyer = msg.sender;
        Auction storage auction = auctions[auctionId];
        require(auction.amount >= _amount, "Not enough token to sell");
        uint256 cost = _amount * auction.price;
        //判断用户的代币是否充足
        require(IERC20(cctAddress).balanceOf(buyer) >= cost, "Buyer funds not enough");

        auction.amount -= _amount;

        //每笔交易扣除卖方1%的成交额作为手续费
        uint256 fee = cost / 100;
        // 1% fee 转到 市场的地址（当前合约）
        SafeERC20.safeTransferFrom(IERC20(cctAddress), buyer, address(this), fee);
        // 剩下的转给卖家
        SafeERC20.safeTransferFrom(IERC20(cctAddress), buyer, auction.seller, cost - fee);
        //转移nft
        IERC1155(nftAddress).safeTransferFrom(auction.seller, buyer, auction.tokenId, auction.amount, "");
    }

    /// @dev 撤销拍卖
    ///
    /// @param auctionId Auction id to revoke
    function revoke(bytes32 auctionId) external {
        Auction memory auction = auctions[auctionId];
        require(auction.seller != address(0), "unknown auctionId");
        delete auctions[auctionId];
    }

    /// @dev 销毁是转移当前合约的token
    ///
    /// @param to Address to withraw to
    function withdraw(address to) external onlyOwner {
        uint256 balance = IERC20(cctAddress).balanceOf(address(this));
        SafeERC20.safeTransfer(IERC20(cctAddress), to, balance);
    }
}

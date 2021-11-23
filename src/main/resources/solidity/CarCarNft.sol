// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts/token/ERC1155/ERC1155.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract CarCarNft is ERC1155, Ownable {
    // 汽车种类
    uint256 public constant nClassCar = 0;
    uint256 public constant rClassCar = 1;
    uint256 public constant srClassCar = 2;
    uint256 public constant ssrClassCar = 3;
    // 赛车场土地
    uint256 public constant velodromeLand = 4;

    // 胜利者碎片
    uint256 public constant winnerFragment = 5;
    // 胜利者盲盒
    uint256 public constant winnerBox = 6;

    // 升级零件
    uint256 public constant nClassUpgradeComp = 7;
    uint256 public constant rClassUpgradeComp = 8;
    uint256 public constant srClassUpgradeComp = 9;
    uint256 public constant ssrClassUpgradeComp = 10;

    address private minter;

    // 赛车场
    uint256 public constant velodrome = 11;

    //批量铸造
    constructor() ERC1155("") {
        // pre minted
        _mint(msg.sender, nClassCar, 400000, "");
        _mint(msg.sender, nClassCar, 80000, "");
        _mint(msg.sender, nClassCar, 18000, "");
        _mint(msg.sender, nClassCar, 2000, "");

        _mint(msg.sender, velodromeLand, 1000, "");
    }

    function setMinter(address _minter) external onlyOwner {
        minter = _minter;
    }

    //销毁nft
    //从to销毁令牌类型tokenId的数量令牌
    //to地址不能是0地址
    function burn(address to,uint256 tokenId,uint256 amount) external {
        require(to != address(0), "burn zero address");
        require(tokenIdExist(tokenId), "tokenId not exist");
        require(minter == msg.sender, "Unauthorized caller");

        _burn(to, tokenId, amount);
    }

    //创建nft
    //创建nft令牌是类型是tokenId，数量是amount，并将它们分配给to地址。
    //to地址不能是0地址
    function mint(address to,uint256 tokenId,uint256 amount) external {
        require(to != address(0), "mint to zero address");
        require(tokenIdExist(tokenId), "tokenId not exist");
        require(minter == msg.sender, "Unauthorized caller");

        _mint(to, tokenId, amount, "");
    }

    //所有令牌类型设置新的 URI
    function setURI(string memory newuri) external onlyOwner {
        _setURI(newuri);
    }

    function tokenIdExist(uint256 _tokenId) public pure returns (bool) {
        return _tokenId >= 0 && _tokenId <= 11;
    }
}

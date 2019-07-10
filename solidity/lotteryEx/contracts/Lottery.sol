pragma solidity >=0.4.21 <0.6.0;

contract Lottery
{
    struct BetInfo
    {
        uint256 answerBlockNumber;
        address payable bettor;
        byte challenges; // 0xab 등 1byte 문자.
    }

    uint256 private _tail;
    uint256 private _head;

    mapping (uint256 => BetInfo) private _bets; // map 으로 만드는 queue
    
    address public owner;

    uint256 constant internal BET_AMOUNT = 5 * 10 ** 15;
    uint256 constant internal BET_BLOCK_INTERVAL = 3;
    uint256 constant internal BET_LIMIT = 256;

    uint256 private _pot;

    enum BlockStatus {Checkable, Not_Revealed, Block_Limit_Passes}
    enum BettingResult {Win, Lose, Draw}

    event BET(uint256 index, address bettor, uint256 amount, byte challenges, uint256 answerBlockNumber);

    constructor() public 
    {
        owner = msg.sender;
    }

    function getPot() public view returns(uint256)
    {
        return _pot;
    }

    // Bet
    /**
    * @dev 배팅을 한다. 유저는 0.005 ETH를 보내고, 배팅용 1byte 글자를 보낸다. 
    * 큐에 저장된 배팅 정보는 이후 distribute함수에서 해결된다.
    * @param challenges characters, betted by user
    * @return bool check the function correct
     */
    function bet(byte challenges) public payable returns (bool result)
    {
        // check the proper ether is sent
        require(msg.value == BET_AMOUNT, "Not enough ETH");

        // push bet to the queue
        require(pushBet(challenges), "Fail To add a new Bet Info");

        // emit event
        emit BET(_tail - 1, msg.sender, msg.value, challenges, block.number + BET_BLOCK_INTERVAL);

        return true;
    }

     // save the bet to the queue

     // Distribute
    function distribute() public{
        uint256 cur;
        BetInfo memory betInfo;
        BlockStatus currentStatus;

        for(cur=_head; cur<_tail; cur++)
        {
            betInfo = _bets[cur];
            currentStatus = getBlockStatus(betInfo.answerBlockNumber);

            // checkable : block.number > AnswerBlockNumber && block.number < BLOCK_LIMIT + AnswerBlockNumber 1
            if(currentStatus == BlockStatus.Checkable)
            {
                // win, bettor gets pot

                // fail, bettor`s money goes pot

                // draw, bettor return betting pot
            }

            // Not Revealed : block.number <= AnserBLockNumber 2
            if(currentStatus == BlockStatus.Not_Revealed)
            {

            }
            // Block Limit Passed : block.number >= AnswerBlockNumber + BLOCK_LIMIT 3
            if(currentStatus == BlockStatus.Block_Limit_Passes)
            {

            }
            // check the answer
        }
    }

    /**
    * @dev 결과 확인
    * @param challenges 도전자가 제시한 코드
    * @param answer Block에 들어있는 정답
    */
    function isMatch(byte challenges, byte answer) public pure returns(BettingResult)
    {   
        byte c1 = challenges;
        byte c2 = challenges;

        byte a1 = answer[0];
        byte a2 = answer[0];

        c1 = c1 >> 4; // 0xab -> 0x0a;
        c1 = c1 << 4; // 0xab -> 0xa0;

        a1 = a1 >> 4;
        a1 = a1 << 4;

        c2 = c2 << 4;
        c2 = c2 >> 4;

        a2 = a2 << 4;
        a2 = a2 >> 4;

        if(a1 == c1 && a2 == c2) return BettingResult.Win;
        if((a1 == c1 && a2 != c2) || (a1 != c1 && a2 == c2)) return BettingResult.Draw;
        if(a1 != c1 && a2 != c2) return BettingResult.Lose;
        return BettingResult.Draw;
    }

    function getBlockStatus(uint256 answerBlockNumber) internal view returns(BlockStatus)
    {
        if(block.number > answerBlockNumber && block.number < BET_LIMIT + answerBlockNumber) return BlockStatus.Checkable;
        if(block.number <= answerBlockNumber) return BlockStatus.Not_Revealed;
        if(block.number >= answerBlockNumber + BET_LIMIT) return BlockStatus.Block_Limit_Passes;
        return BlockStatus.Block_Limit_Passes;
    }

     // check the answer

     function getBetInfo(uint256 index) public view returns (uint256 answerBlockNumber, address bettor, byte challenges)
     {
         BetInfo memory betInfo = _bets[index];
         answerBlockNumber = betInfo.answerBlockNumber;
         bettor = betInfo.bettor;
         challenges = betInfo.challenges;
     }

     function pushBet(byte challenges) internal returns(bool){
         BetInfo memory betInfo;
         betInfo.bettor = msg.sender;
         betInfo.answerBlockNumber = block.number + BET_BLOCK_INTERVAL;
         betInfo.challenges = challenges;

         _bets[_tail] = betInfo;
         _tail++;

         return true;
     }

     function popBet(uint256 index) internal returns (bool)
     {
         delete _bets[index]; // 뽑아 내는 것 gas를 돌려받는다. 사용하지 않은 값에 대해서 사용하는게 좋음.
        return true;
     }
}
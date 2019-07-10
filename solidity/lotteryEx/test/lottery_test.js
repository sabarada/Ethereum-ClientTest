const Lottery = artifacts.require("Lottery");
const assertRevert = require('./assertRevert');

contract ('Lottery', function([deployer, user1, user2]){
    // deployer - user1 - user2 순서대로 계좌 주소
    let lottery;

    beforeEach(async() =>{
        console.log('Before each');
        lottery = await Lottery.new();
    })

    it('Basic test', async() =>{
        console.log('Basic test');
        let owner = await lottery.owner();
    
        console.log(`onwer ${owner}`);
    })

   describe('Bet', function(){
        it('should fail when the bet money is not 0.005 ETH', async() => {
            //Fail transaction
            await assertRevert(lottery.bet('0xab', {from : user1, value: 4 * 10 ** 15}));
            // transactino object (chainId, value, to, from, gas(Limit), gasPrice)
        })
        it.only('should put the bet to the bet queue with 1 bet', async() => {
            // bet
            let receipt = await lottery.bet('0xab', {from : user1, value: 5 * 10 ** 15});
            console.log(receipt);

            let pot = await lottery.getPot();
            assert.equal(pot, 0);

            // check contract balance == 0.005
            let contractBalance = await web3.eth.getBalance(lottery.address);
            assert.equal(contractBalance, 5 * 10 ** 15);
            // check bet info

            let currentBlockNumber = await web3.eth.getBlockNumber();
            let bet = await lottery.getBetInfo(0);
            assert.equal(bet.answerBlockNumber, currentBlockNumber + 3);
            assert.equal(bet.bettor, user1);
            // assert.equal(bet.chellenges, '0xab');
            
            // check log
        })
   })

   describe.only('isMatch', function(){
       let blockHash = '0xab5ec98ed338d741971f61298130636c867f0a6fd2b5fc7b1b1027806191f983';

       it('should be BettingResult.Win when two characters match', async() =>{
         let matchingResult = await lottery.isMatch('0xab', blockHash);
         console.log("matchingResult : " + matchingResult);
         assert.equal(matchingResult, 0);  
       })
       it('should be BettingResult.Draw when two characters match', async() =>{
         let matchingResult = await lottery.isMatch('0xbb', blockHash);
         console.log("matchingResult : " + matchingResult);
         assert.equal(matchingResult, 2);  
       })
       it('should be BettingResult.Lose when two characters match', async() =>{
         let matchingResult = await lottery.isMatch('0x12', blockHash);
         console.log("matchingResult : " + matchingResult);
         assert.equal(matchingResult, 1);  
       })
    })
});
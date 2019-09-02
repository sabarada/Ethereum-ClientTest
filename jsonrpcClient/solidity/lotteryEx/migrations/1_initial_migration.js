const Lottery = artifacts.require("Migrations");

module.exports = function(deployer) {
  deployer.deploy(Lottery);
};

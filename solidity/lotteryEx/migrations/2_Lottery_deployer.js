const Migrations = artifacts.require("Lottery");

module.exports = function(deployer) {
  deployer.deploy(Migrations);
};


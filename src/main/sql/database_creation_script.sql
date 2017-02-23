CREATE TABLE IF NOT EXISTS `javarush`.`user` (
  `id` INT(8) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(25) NOT NULL,
  `age` INT(11) NOT NULL,
  `isAdmin` BIT NOT NULL,
  `createdDate` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC));


INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('daniil',47,1,'1996-06-10 14:07:15');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('ivan',21,1,'1996-07-07 01:01:06');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('ignat',49,1,'1996-07-03 12:01:06');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('viktor',45,0,'1996-07-03 12:01:06');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('gena',52,0,'1985-06-03 01:01:06');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('michno',19,0,'1994-12-24 12:36:09');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('james',56,0,'1985-06-03 01:01:06');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('Romzik',18,1,'1996-07-03 12:01:06');
INSERT INTO `user` (`name`,`age`,`isAdmin`,`createdDate`) VALUES ('tolik',78,0,'1994-12-24 12:34:59');


SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `TDVelo` DEFAULT CHARACTER SET latin1 ;
USE `TDVelo` ;

-- -----------------------------------------------------
-- Table `TDVelo`.`coureur`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TDVelo`.`coureur` (
  `dossard` INT(11) NOT NULL ,
  `nom` VARCHAR(45) NOT NULL ,
  `prenom` VARCHAR(45) NOT NULL ,
  `sexe` VARCHAR(1) NOT NULL ,
  `categorie` VARCHAR(45) NOT NULL ,
  `club` VARCHAR(100) NULL DEFAULT NULL ,
  `licence` VARCHAR(200) NULL DEFAULT NULL ,
  PRIMARY KEY (`dossard`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `TDVelo`.`epreuve`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `TDVelo`.`epreuve` (
  `discipline` VARCHAR(45) NOT NULL ,
  `dossard` INT(11) NOT NULL ,
  `temps` DOUBLE NULL DEFAULT NULL ,
  `penalite` DOUBLE NULL DEFAULT NULL ,
  `classement` INT(11) NULL DEFAULT NULL COMMENT '		' ,
  PRIMARY KEY (`discipline`, `dossard`) ,
  INDEX `fk_epreuve_1` (`dossard` ASC) ,
  CONSTRAINT `fk_epreuve_1`
    FOREIGN KEY (`dossard` )
    REFERENCES `TDVelo`.`coureur` (`dossard` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

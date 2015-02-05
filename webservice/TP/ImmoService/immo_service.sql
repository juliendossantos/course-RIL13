-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 15 Décembre 2013 à 20:10
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `immo_service`
--
CREATE DATABASE IF NOT EXISTS `immo_service` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `immo_service`;

-- --------------------------------------------------------

--
-- Structure de la table `agences`
--

CREATE TABLE IF NOT EXISTS `agences` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ref` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `mdp` varchar(255) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_expire` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ref_UNIQUE` (`ref`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `agences`
--

INSERT INTO `agences` (`id`, `ref`, `nom`, `mdp`, `token`, `token_expire`) VALUES
(1, 'CI2567', 'Capbraton Immobilier', 'caP2089', 'c3427ef63762a5b853be42b5e00692b8', '2013-12-16 20:54:37'),
(2, 'MB9087', 'Maison de Brodeaux', 'mDb6548', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `biens`
--

CREATE TABLE IF NOT EXISTS `biens` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ref` varchar(255) NOT NULL,
  `desc` varchar(45) DEFAULT NULL,
  `adresse` varchar(45) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `cp` int(5) DEFAULT NULL,
  `superficie` int(11) DEFAULT NULL,
  `nb_pieces` int(11) DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `type_action_id` int(11) DEFAULT NULL,
  `agences_id` int(11) NOT NULL,
  `type_bien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ref_UNIQUE` (`ref`),
  KEY `fk_biens_comptes_idx` (`agences_id`),
  KEY `fk_biens_type1_idx` (`type_bien_id`),
  KEY `action_id` (`type_action_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=23 ;

--
-- Contenu de la table `biens`
--

INSERT INTO `biens` (`id`, `ref`, `desc`, `adresse`, `ville`, `cp`, `superficie`, `nb_pieces`, `prix`, `type_action_id`, `agences_id`, `type_bien_id`) VALUES
(5, 'CI78866', 'Maison bord de mer', '15 rue bord de plage', 'Capbreton', 40230, 45, 6, 250000, 1, 1, 1),
(8, 'CI78754', 'Maison de plage 2', '26 avenue taratata', 'Capbreton', 40230, 80, 5, 350, 2, 1, 2),
(9, 'CI31634', 'Terrain de plage', '145 au bout', 'Capbreton', 40230, 76, 2, 500, 2, 1, 3),
(10, 'CI67839', 'Maison sur la côtes', '17 ponton', 'Capbreton', 40230, 170, 7, 356000, 1, 1, 1),
(12, 'MB78390', 'Appartement place de la Victoire', '126 place de la Victoire', 'Bordeaux', 33000, 27, 2, 500, 2, 2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `bien_photo`
--

CREATE TABLE IF NOT EXISTS `bien_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `photo_id` int(11) NOT NULL,
  `bien_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_bien_photo_photos1_idx` (`photo_id`),
  KEY `fk_bien_photo_biens1_idx` (`bien_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=16 ;

-- --------------------------------------------------------

--
-- Structure de la table `locations`
--

CREATE TABLE IF NOT EXISTS `locations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bien_id` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bien_id` (`bien_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `locations`
--

INSERT INTO `locations` (`id`, `bien_id`, `date_debut`, `date_fin`) VALUES
(9, 8, '2013-12-12', '2013-12-19');

-- --------------------------------------------------------

--
-- Structure de la table `photos`
--

CREATE TABLE IF NOT EXISTS `photos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chemin` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=21 ;

-- --------------------------------------------------------

--
-- Structure de la table `type_action`
--

CREATE TABLE IF NOT EXISTS `type_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Contenu de la table `type_action`
--

INSERT INTO `type_action` (`id`, `action`) VALUES
(1, 'vente'),
(2, 'location');

-- --------------------------------------------------------

--
-- Structure de la table `type_bien`
--

CREATE TABLE IF NOT EXISTS `type_bien` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Contenu de la table `type_bien`
--

INSERT INTO `type_bien` (`id`, `type`) VALUES
(1, 'maison'),
(2, 'appartement'),
(3, 'terrain');

-- --------------------------------------------------------

--
-- Structure de la table `ventes`
--

CREATE TABLE IF NOT EXISTS `ventes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bien_id` int(11) NOT NULL,
  `date_vente` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bien_id` (`bien_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Contenu de la table `ventes`
--

INSERT INTO `ventes` (`id`, `bien_id`, `date_vente`) VALUES
(2, 5, '2013-12-10'),
(4, 10, '2013-12-12');

-- --------------------------------------------------------

--
-- Structure de la table `visites`
--

CREATE TABLE IF NOT EXISTS `visites` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bien_id` int(11) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bien_id` (`bien_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Contenu de la table `visites`
--

INSERT INTO `visites` (`id`, `bien_id`, `date`) VALUES
(4, 5, '2013-12-06'),
(5, 10, '2013-12-13'),
(6, 8, '2013-12-05'),
(7, 8, '2013-12-05'),
(8, 5, '2013-12-11'),
(9, 10, '2013-12-15');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `biens`
--
ALTER TABLE `biens`
  ADD CONSTRAINT `fk_biens_comptes` FOREIGN KEY (`agences_id`) REFERENCES `agences` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_biens_type1` FOREIGN KEY (`type_bien_id`) REFERENCES `type_bien` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_biens_type_action` FOREIGN KEY (`type_action_id`) REFERENCES `type_action` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `bien_photo`
--
ALTER TABLE `bien_photo`
  ADD CONSTRAINT `fk_bien_photo_biens1` FOREIGN KEY (`bien_id`) REFERENCES `biens` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_bien_photo_photos1` FOREIGN KEY (`photo_id`) REFERENCES `photos` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Contraintes pour la table `locations`
--
ALTER TABLE `locations`
  ADD CONSTRAINT `FK_locations_biens` FOREIGN KEY (`bien_id`) REFERENCES `biens` (`id`);

--
-- Contraintes pour la table `ventes`
--
ALTER TABLE `ventes`
  ADD CONSTRAINT `FK_ventes_biens` FOREIGN KEY (`bien_id`) REFERENCES `biens` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `visites`
--
ALTER TABLE `visites`
  ADD CONSTRAINT `FK_visites_biens` FOREIGN KEY (`bien_id`) REFERENCES `biens` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: May 23, 2021 at 02:34 PM
-- Server version: 5.7.24
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fitnessdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `training_program`
--

CREATE TABLE `training_program` (
  `id` bigint(20) NOT NULL,
  `complexity` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hours` int(11) NOT NULL,
  `thumbnail` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `trainings` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `training_program`
--

INSERT INTO `training_program` (`id`, `complexity`, `description`, `hours`, `thumbnail`, `title`, `trainings`) VALUES
(1, 4, 'Класс, направленный на изучение базовой техники ударов и защиты, тактики ведения боя.', 60, 'https://images.unsplash.com/photo-1591504771094-a1ca4de142d7?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=634&q=80', 'Boxing', 60),
(2, 8, 'Класс, включающий в себя элементы и технические приемы из арсенала карате, дзюдо, бокса, а также других единоборств.', 35, 'https://images.pexels.com/photos/841130/pexels-photo-841130.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260', 'KUDO', 35),
(3, 2, 'Кардиотренировка, включающая в себя разучивание комбинаций по классической аэробике.', 25, 'https://images.pexels.com/photos/2475878/pexels-photo-2475878.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260', 'AERO', 20),
(4, 3, 'Разучивание основных базовых движений и простых комбинаций. ', 40, 'https://images.pexels.com/photos/3822719/pexels-photo-3822719.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260', 'AQUA STEP', 20),
(5, 6, 'Класс направлен на укрепление всех мышц с акцентом на спину, ноги, глубокие мышцы пресса.', 50, 'https://images.pexels.com/photos/3900844/pexels-photo-3900844.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260', 'CORE & STRETCH', 55),
(6, 5, 'Программа, включающая в себя 3 разные 30-минутные тренировки с интенсивными атлетическими упражнениями.', 30, 'https://images.pexels.com/photos/416754/pexels-photo-416754.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260', 'GRIT', 60);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` bigint(20) NOT NULL,
  `age` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `days` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `hours` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `plan` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `second_name` varchar(255) DEFAULT NULL,
  `subscription_end` varchar(255) DEFAULT NULL,
  `subscription_start` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `visits` varchar(255) DEFAULT NULL,
  `weight` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `age`, `avatar_url`, `days`, `enabled`, `first_name`, `height`, `hours`, `password`, `plan`, `price`, `role`, `second_name`, `subscription_end`, `subscription_start`, `username`, `visits`, `weight`) VALUES
(1, '0', 'https://i.imgur.com/4DINNqs.png', '0', b'1', 'Admin', '0', '0', '$2a$10$1yT08KxX6ptU68u.8/v1Q.OIze6DzTu4sgZcjygBRRv2t/GvX0gyC', '...', '0', 'ROLE_ADMIN', 'Admin', '23.05.2021', '23.05.2021', 'admin', '0', '0'),
(3, '30', 'https://i.imgur.com/3Ksd4OR.png', '180', b'1', 'Андрей', '181', '71', '$2a$10$dJrGXi/mKFUNvJCFediDOOCoNRGiQ9y1XgE3sg23aaav4vuIE12D6', 'VIP', '17820 руб', 'ROLE_USER', 'Андреев', '16.11.2021', '20.05.2021', 'andrey', '63', '80'),
(4, '22', 'https://i.imgur.com/AzEe4qP.png', '30', b'1', 'Иван', '177', '112', '$2a$10$8nM46tCz1e5HFJHCmr8S3eIYh4Ij311mjJMypEcTAzuZPrAmvf15C', '...', '2970 руб', 'ROLE_USER', 'Иванов', '22.06.2021', '23.05.2021', 'ivan', '34', '76'),
(5, '22', 'https://i.imgur.com/ECsO4cY.png', '0', b'1', 'User', '188', '0', '$2a$10$AtkwtgD2WwCYnIKUSU9rg.LxsdjV98c1U4tWsPaQvdIXbZJqRdfJC', '...', '0', 'ROLE_USER', 'User', '23.05.2021', '23.05.2021', 'user', '0', '87');

-- --------------------------------------------------------

--
-- Table structure for table `users_training_programs`
--

CREATE TABLE `users_training_programs` (
  `users_user_id` bigint(20) NOT NULL,
  `training_programs_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users_training_programs`
--

INSERT INTO `users_training_programs` (`users_user_id`, `training_programs_id`) VALUES
(5, 1),
(4, 2),
(4, 3),
(5, 4),
(5, 5),
(4, 6);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `training_program`
--
ALTER TABLE `training_program`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `users_training_programs`
--
ALTER TABLE `users_training_programs`
  ADD PRIMARY KEY (`users_user_id`,`training_programs_id`),
  ADD KEY `FKh2i7j8ugk4in29j9k3230xlo0` (`training_programs_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `training_program`
--
ALTER TABLE `training_program`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `users_training_programs`
--
ALTER TABLE `users_training_programs`
  ADD CONSTRAINT `FKh2i7j8ugk4in29j9k3230xlo0` FOREIGN KEY (`training_programs_id`) REFERENCES `training_program` (`id`),
  ADD CONSTRAINT `FKpdwqilnm0d0goh6twib63c8fj` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2023 at 11:08 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `emsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(20) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `department` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `position` varchar(20) NOT NULL,
  `salary` double NOT NULL,
  `dateJoining` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `firstName`, `lastName`, `gender`, `department`, `email`, `position`, `salary`, `dateJoining`) VALUES
(1, 'Mamo', 'Belay', 'Male', 'IT', 'mamo@gmail.com', 'Project leader', 13000, '2023-06-07'),
(2, 'Henok', 'Abraham', 'Male', 'SE', 'henok@gmail.com', 'Project Manager', 15000, '2023-06-09'),
(3, 'elias', 'ayana', 'Male', 'SE', 'eam@gmail.com', 'CTO', 45000, '2023-06-06'),
(4, 'ab', 'bc', 'Male', 'de', 'cd', 'ef', 3488, '2023-06-06'),
(5, 'gh', 'h', 'Female', 'SE', 'dh@gmail.com', 'Typist', 23000, '2023-06-06'),
(7, 'u', 'c', 'Female', 'e', 'm', 'n', 4564, '2023-06-06'),
(8, 'sdfgs', 'ooi', 'Female', 'SE', 'sdfg@gmail.com', 'Typist', 23000, '2023-06-06'),
(9, 'u', 'c', 'Male', 't', 'n', 'c', 4564, '2023-06-06'),
(11, 'Ezra', 'Tigab', 'Male', 'SE', 'ezraa@gmail.com', 'Back-end Developer', 15000, '2023-06-09'),
(12, 'Jemal', 'Workie', 'Male', 'IT', 'jemal@jemu.com', 'IT Specialist', 5000, '2023-06-10'),
(13, 'Kirubel', 'Mamuye', 'Male', 'SE', 'kira@gmail.com', 'Nop', 23000, '2023-06-14');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

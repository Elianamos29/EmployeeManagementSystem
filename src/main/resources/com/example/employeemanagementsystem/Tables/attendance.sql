-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 14, 2023 at 11:09 PM
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
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `date` date NOT NULL,
  `staffID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `status` varchar(30) NOT NULL,
  `timeIn` time DEFAULT NULL,
  `timeOut` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`date`, `staffID`, `name`, `status`, `timeIn`, `timeOut`) VALUES
('2023-06-11', 1, 'Mamo Belay', 'inactive', '19:30:29', '19:30:34'),
('2023-06-12', 1, 'Mamo Belay', 'inactive', '09:38:16', NULL),
('2023-06-13', 1, 'Mamo Belay', 'inactive', '16:53:06', NULL),
('2023-06-11', 2, 'Henok Abraham', 'inactive', '15:43:11', '15:43:14'),
('2023-06-12', 2, 'Henok Abraham', 'inactive', '09:43:21', NULL),
('2023-06-13', 2, 'Henok Abraham', 'inactive', '13:03:19', NULL),
('2023-06-14', 2, 'Henok Abraham', 'inactive', '09:27:26', '09:27:31'),
('2023-06-11', 7, 'u c', 'active', '15:25:12', '15:37:34'),
('2023-06-12', 7, 'u c', 'active', '09:43:00', NULL),
('2023-06-13', 7, 'u c', 'active', '16:52:33', NULL),
('2023-06-14', 7, 'u c', 'active', '16:22:27', NULL),
('2023-06-12', 11, 'Ezra Tigab', 'inactive', '09:41:47', NULL),
('2023-06-14', 13, 'Kirubel Mamuye', 'active', '22:56:07', '22:56:10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`staffID`,`date`) USING BTREE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

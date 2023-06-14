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
-- Table structure for table `leaverequest`
--

CREATE TABLE `leaverequest` (
  `requestID` int(11) NOT NULL,
  `staffID` int(11) NOT NULL,
  `leaveType` varchar(30) NOT NULL,
  `description` text NOT NULL,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'pending',
  `comment` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `leaverequest`
--

INSERT INTO `leaverequest` (`requestID`, `staffID`, `leaveType`, `description`, `fromDate`, `toDate`, `status`, `comment`) VALUES
(1, 2, 'vacation', 'I wana have a vacation', '2023-06-08', '2023-06-08', 'approved', ''),
(2, 2, 'Sick', 'nothing just \'cause i\'m sick', '2023-06-08', '2023-06-08', 'approved', 'hope you will be fine'),
(3, 4, 'other', 'i am on to something', '2023-06-09', '2023-06-09', 'rejected', 'do not be on to something'),
(4, 4, 'other', 'I am out of country', '2023-06-09', '2023-06-09', 'rejected', 'out of this company, as well!'),
(5, 4, 'other', 'something happend', '2023-06-09', '2023-06-09', 'rejected', ''),
(6, 7, 'vacation', 'I am going somewhere', '2023-06-09', '2023-06-09', 'rejected', 'ddd'),
(7, 7, 'Sick', 'i am very sick', '2023-06-09', '2023-06-09', 'approved', ''),
(8, 7, 'other', 'i have got a really bad problem', '2023-06-09', '2023-06-09', 'rejected', 'you have guts asking a leave'),
(9, 2, 'vacation', 'want a day off', '2023-06-09', '2023-06-09', 'rejected', 'you will not have what you want'),
(10, 1, 'Sick', 'i am so sick', '2023-06-09', '2023-06-09', 'approved', 'ok just recover quickly'),
(11, 7, 'Sick', 'wgsdgf', '2023-06-09', '2023-06-09', 'pending', NULL),
(12, 1, 'vacation', 'holiday vacation', '2023-06-11', '2023-06-12', 'approved', ''),
(13, 2, 'vacation', 'sdf', '2023-06-09', '2023-06-09', 'rejected', ''),
(14, 2, 'vacation', 'bncv', '2023-06-11', '2023-06-09', 'rejected', ''),
(15, 2, 'vacation', 'fgdfg', '2023-06-09', '2023-06-09', 'rejected', ''),
(16, 2, 'vacation', 'fdgs', '2023-06-10', '2023-06-09', 'rejected', ''),
(17, 2, 'vacation', 'sfdgs', '2023-06-10', '2023-06-10', 'rejected', ''),
(18, 2, 'vacation', 'holiday vacation', '2023-06-12', '2023-06-15', 'approved', 'ok'),
(19, 1, 'vacation', 'vacation', '2023-06-10', '2023-06-13', 'rejected', ''),
(20, 1, 'vacation', 'vacation', '2023-06-09', '2023-06-12', 'rejected', ''),
(21, 1, 'vacation', 'vac', '2023-06-12', '2023-06-17', 'rejected', ''),
(22, 1, 'vacation', 'fsdf', '2023-06-11', '2023-06-12', 'approved', ''),
(23, 11, 'other', 'I have got a really bad problem', '2023-06-12', '2023-06-15', 'approved', 'ooh sorry man good luck'),
(24, 1, 'vacation', 'vacation', '2023-06-13', '2023-06-17', 'approved', 'you want vacation? you can have it!');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `leaverequest`
--
ALTER TABLE `leaverequest`
  ADD PRIMARY KEY (`requestID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `leaverequest`
--
ALTER TABLE `leaverequest`
  MODIFY `requestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

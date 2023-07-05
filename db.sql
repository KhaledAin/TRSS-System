-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2023 at 10:23 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db`
--

-- --------------------------------------------------------


-- --------------------------------------------------------

--
-- Table structure for table `contract`
--

CREATE TABLE `contract` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `total_cost` decimal(10,2) NOT NULL,
  `deposit` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `contract`
--

INSERT INTO `contract` (`id`, `user_id`, `customer_id`, `date`, `total_cost`, `deposit`) VALUES
(1, 1, 35, '2023-06-09', '66.00', '4.00'),
(2, 1, 42, '2023-06-09', '192.00', '5.00'),
(3, 1, 34, '2023-06-09', '1288.00', '333.00'),
(4, 1, 35, '2023-06-06', '100.00', '33.00'),
(5, 1, 32, '2023-06-03', '218.00', '100.00'),
(6, 1, 32, '2023-06-01', '284.00', '84.00'),
(7, 1, 43, '2023-06-09', '68.00', '20.00'),
(8, 1, 45, '2023-06-07', '368.00', '100.00'),
(9, 1, 37, '2023-06-09', '1131.00', '2.00'),
(10, 1, 35, '2023-06-02', '538.00', '44.00'),
(11, 1, 45, '2023-06-02', '435.00', '50.00'),
(12, 1, 41, '2023-06-09', '116.00', '33.00'),
(13, 1, 35, '2023-06-01', '174.00', '4.00'),
(14, 1, 40, '2023-06-03', '68.00', '33.00'),
(15, 1, 41, '2023-06-02', '290.00', '5.00'),
(16, 1, 37, '2023-06-10', '186.00', '4.00'),
(17, 1, 35, '2023-06-09', '120.00', '50.00'),
(18, 1, 33, '2023-06-11', '128.00', '3.00');

-- --------------------------------------------------------

--
-- Table structure for table `contractproduct`
--

CREATE TABLE `contractproduct` (
  `id` int(11) NOT NULL,
  `contract_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `rental_date` date NOT NULL,
  `rental_period` int(11) NOT NULL,
  `rental_time` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `NIC` varchar(10) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(10) NOT NULL,
  `date` date DEFAULT curdate()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `name`, `NIC`, `address`, `phone`, `date`) VALUES
(32, 'John Smith', '1234567890', 'johnsmith@hotmail.com', '0586324759', '2023-06-06'),
(33, 'Sarah Johnson', '0987654321', 'sarahjohnson@gmail.com', '0589965744', '2023-06-06'),
(34, 'Michael Brown', '5678901234', 'michaelbrown@gmail.com', '0598777885', '2023-06-06'),
(35, 'Emily Neom', '4321098765', 'emilydavis@gmail.com', '0544668825', '2023-06-06'),
(36, 'Emily Davis', '4321098765', 'emilydavis@gmail.com', '0522368854', '2023-06-06'),
(37, 'David Wilson', '6789012345', 'davidwilson@example.com', '0566478525', '2023-06-06'),
(39, 'Olivia Miller', '3456789012', 'oliviamiller@hotmail.com', '0599547846', '2023-06-06'),
(40, 'James Anderson', '7890123456', 'jamesanderson@hotmail.com', '0533225596', '2023-06-06'),
(41, 'Emma Thomas', '4567890123', 'emmathomas@hotmail.com', '0577866953', '2023-06-06'),
(42, 'William Martinez', '9012345678', 'williammartinez@gmail.com', '0578745963', '2023-06-06'),
(43, 'Sophia Lee', '2345678901', 'sophialee@hotmail.com', '0547896514', '2023-06-06'),
(44, 'Benjamin Taylor', '3456789012', 'benjamintaylor@hotmail.com', '0541122589', '2023-06-06'),
(45, 'Sophia Mike', '2345444401', 'sophialee@hotmail.com', '0549872311', '2023-06-06'),
(46, 'Ethan White', '5678901234', 'ethanwhite@example.com', '0541269746', '2023-06-06'),
(47, '', '', '', '', '2023-06-08');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `category` varchar(30) DEFAULT NULL,
  `price` double NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT 'Available'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `category`, `price`, `status`) VALUES
(29, 'Drill', '', 20, 'Avalabie'),
(30, 'Drill', '00N', 22, 'Avalabie'),
(31, 'Grinder', '00T', 24, 'Avalabie'),
(32, 'Grinder', '', 22, 'Avalabie'),
(33, 'Saw', '', 10, 'Avalabie'),
(34, 'Nail gun', '00N', 30, 'Avalabie'),
(35, 'Chainsaw', '', 32, 'Avalabie'),
(36, 'Wheelbarrow', '00N', 18, 'Avalabie'),
(37, 'Shovel', '', 12, 'Avalabie'),
(38, 'Sprayer', '', 14, 'Avalabie'),
(39, 'Hammer', '', 12, 'Avalabie'),
(40, 'Screwdriver', 'flathead', 3, 'Avalabie'),
(41, 'Screwdriver', 'Phillips', 3, 'Avalabie'),
(42, 'Pliers ', '', 5, 'Avalabie'),
(43, 'Shovel', '', 12, 'Avalabie'),
(44, 'Hammer', '00T', 14, 'Avalabie');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(10) NOT NULL,
  `username` varchar(25) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `role` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `name`, `password`, `email`, `role`) VALUES
(1, 'KhaledAin', 'Khaled', 'Ainieh', 'khaledain10.101@gmail.com', 'Admin'),
(14, 'AnasMus', 'Anas', 'Muslmani', 'AnasMuslmani@gmail.com', 'Admin'),
(15, 'user', 'employee', 'one', 'emp@gmail.com', 'Ruglar user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `contractproduct`
--
ALTER TABLE `contractproduct`
  ADD PRIMARY KEY (`id`),
  ADD KEY `contract_id` (`contract_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `contract`
--
ALTER TABLE `contract`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `contractproduct`
--
ALTER TABLE `contractproduct`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `contract`
--
ALTER TABLE `contract`
  ADD CONSTRAINT `contract_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `contract_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

--
-- Constraints for table `contractproduct`
--
ALTER TABLE `contractproduct`
  ADD CONSTRAINT `contractproduct_ibfk_1` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`),
  ADD CONSTRAINT `contractproduct_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

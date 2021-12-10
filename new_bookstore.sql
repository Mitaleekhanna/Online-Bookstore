-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2021 at 08:53 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 7.4.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `new_bookstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `isbn` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `description` longtext NOT NULL,
  `genre` varchar(50) NOT NULL,
  `publication` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `date_added` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`isbn`, `name`, `author`, `description`, `genre`, `publication`, `quantity`, `price`, `date_added`) VALUES
(1, '1', '1', '1', '1', '1', -1, 1, '2021-12-01 01:06:05'),
(2, '2', '2', '2', '2', '2', -1, 2, '2021-12-01 01:07:15'),
(123, 'new book 1', 'new author 1', 'new description 1', 'new genre 1', 'new publication 1', -1, 500, '2021-12-07 13:54:52'),
(124, 'book2', 'author2', 'description2', 'genre2', 'publication2', -1, 700, '2021-12-07 15:31:07'),
(125, 'book3', 'author3', 'desc 3', 'genre3', 'publication 3', -1, 900, '2021-12-07 16:33:26'),
(126, 'asdwf', 'fwfwf', 'fwqffwfgw', 'fwef', 'fwg', 1, 500, '2021-12-07 16:37:09'),
(128, 'TEST', 'TEST', 'TEST', 'TWST', 'TEST', -1, 500, '2021-12-07 16:41:17'),
(190, 'na', 'au', 'des', 'gen', 'apub', 1, 123, '2021-12-01 01:36:42'),
(963, 'test1', 'test1', 'test1', 'test1', 'test1', 1, 200, '2021-12-09 18:48:04'),
(965, 'test2', 'test2', 'test2', 'test2', 'test2', 1, 855, '2021-12-09 18:49:11'),
(987, 'test3', 'test3', 'test3', 'test3', 'test3', 1, 1, '2021-12-09 18:54:40'),
(12436, 'fregr', 'fergbf', 'regeg', 'fewgerg', 'sfget', 1, 600, '2021-12-09 17:04:07');

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `cart_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `date_added` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`id`, `cart_id`, `user_id`, `isbn`, `quantity`, `date_added`) VALUES
(7, 1, 1, 190, 1, '2021-12-01 17:02:25');

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `feedback` longtext NOT NULL,
  `rating` int(11) NOT NULL,
  `date_added` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `payment_id` int(11) NOT NULL,
  `status` varchar(50) NOT NULL,
  `quantity` int(11) NOT NULL,
  `date_added` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`id`, `order_id`, `isbn`, `user_id`, `payment_id`, `status`, `quantity`, `date_added`) VALUES
(1, 918547, 2, 1, 401661, 'sucess', 1, '2021-12-01 16:41:50'),
(2, 860630, 2, 1, 245532, 'sucess', 1, '2021-12-01 16:45:05'),
(3, 860630, 190, 1, 245532, 'sucess', 2, '2021-12-01 16:45:05'),
(4, 726638, 2, 1, 687612, 'sucess', 1, '2021-12-01 16:45:24'),
(5, 726638, 190, 1, 687612, 'sucess', 2, '2021-12-01 16:45:24'),
(6, 880242, 2, 1, 340877, 'sucess', 1, '2021-12-01 16:48:16'),
(7, 880242, 190, 1, 340877, 'sucess', 2, '2021-12-01 16:48:16'),
(8, 644567, 2, 1, 582019, 'sucess', 1, '2021-12-01 16:51:59'),
(9, 644567, 190, 1, 582019, 'sucess', 2, '2021-12-01 16:51:59'),
(10, 192650, 1, 1, 122438, 'sucess', 1, '2021-12-01 16:58:17'),
(11, 555194, 190, 1, 890830, 'sucess', 1, '2021-12-01 17:01:35'),
(12, 462375, 190, 1, 634957, 'sucess', 1, '2021-12-01 17:02:19'),
(13, 675927, 123, 2, 989408, 'sucess', 1, '2021-12-09 16:11:56'),
(14, 675927, 124, 2, 989408, 'sucess', 1, '2021-12-09 16:11:56'),
(15, 706390, 125, 2, 105641, 'sucess', 1, '2021-12-09 17:02:17'),
(16, 812980, 128, 2, 836202, 'sucess', 1, '2021-12-09 23:45:11');

-- --------------------------------------------------------

--
-- Table structure for table `payments`
--

CREATE TABLE `payments` (
  `payment_id` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  `payment_type` varchar(50) NOT NULL,
  `date_added` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payments`
--

INSERT INTO `payments` (`payment_id`, `order_id`, `amount`, `payment_type`, `date_added`) VALUES
(105641, 706390, 900, 'debit', '2021-12-09 17:02:17'),
(122438, 192650, 1, 'debit', '2021-12-01 16:58:17'),
(214675, 25128, 0, 'credit', '2021-12-01 16:52:49'),
(245532, 860630, 248, 'credit', '2021-12-01 16:45:05'),
(340877, 880242, 248, 'debit', '2021-12-01 16:48:16'),
(401661, 918547, 248, 'credit', '2021-12-01 16:41:49'),
(582019, 644567, 248, 'credit', '2021-12-01 16:51:59'),
(634957, 462375, 123, 'debit', '2021-12-01 17:02:19'),
(687612, 726638, 248, 'credit', '2021-12-01 16:45:24'),
(836202, 812980, 500, 'debit', '2021-12-09 23:45:11'),
(890830, 555194, 123, 'debit', '2021-12-01 17:01:35'),
(989408, 675927, 1200, 'debit', '2021-12-09 16:11:56');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `user_role` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `feedback` longtext NOT NULL,
  `address` varchar(50) NOT NULL,
  `phone_number` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `date_added` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `user_role`, `name`, `password`, `feedback`, `address`, `phone_number`, `email`, `date_added`) VALUES
(1, 'librarian', 'asd', 'asd', '', 'asd', 123456789, 'asd', '2021-12-01 00:08:32'),
(2, 'customer', 'qwe', 'qwe', '', '123', 123456789, 'qwe', '2021-12-01 01:28:21'),
(3, 'customer', 'zxc', 'zxc', '', 'asdsf', 23, 'zxc', '2021-12-09 17:40:00'),
(4, 'librarian', 'rty', 'rty', '', 'sdfF', 1234567891, 'rty', '2021-12-09 17:43:02'),
(5, 'customer', 'fgh', 'fgh', '', 'safwef', 12345698, 'fgh', '2021-12-09 17:44:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`isbn`);

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `payments`
--
ALTER TABLE `payments`
  ADD PRIMARY KEY (`payment_id`,`order_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `isbn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12437;

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `payments`
--
ALTER TABLE `payments`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=989409;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

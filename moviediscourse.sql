-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Sep 03, 2023 at 03:04 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `moviediscourse`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `commentText` text DEFAULT NULL,
  `parent` bigint(20) UNSIGNED DEFAULT NULL,
  `creator` bigint(20) UNSIGNED NOT NULL,
  `movie` bigint(20) UNSIGNED NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `commentText`, `parent`, `creator`, `movie`, `created_at`, `updated_at`) VALUES
(1, 'Time dilation around black holes is mind-blowing! Anyone else captivated by the blend of science and emotion?', NULL, 1, 816692, '2023-09-02 03:55:50', '2023-09-02 07:21:30'),
(2, 'Nolan\'s storytelling paired with Zimmer\'s soundtrack is unparalleled. The scene with Cooper and the messages from his kids? Tears every time.', 1, 2, 816692, '2023-09-02 03:55:50', '2023-09-02 03:55:50'),
(3, 'Really good movie!', NULL, 2, 816692, '2023-09-02 03:55:50', '2023-09-02 03:55:50'),
(12, 'Totally agree!!', 2, 1, 816692, '2023-09-02 07:22:27', '2023-09-02 07:23:23'),
(13, 'Oh, for sure! The mix of complex scientific theories with raw human emotions was just... stellar. 😉 I\'ve rewatched it a couple of times just to fully grasp the concepts!', 1, 3, 816692, '2023-09-02 07:25:25', '2023-09-02 07:25:30'),
(14, 'I couldn\'t agree more! It\'s one of those movies that offers something new with every watch. And I caught that pun – nice one! 😉', 13, 1, 816692, '2023-09-02 07:27:22', '2023-09-02 07:27:29'),
(15, 'Same here, Sam. Every time I watch it, I catch some new detail or subtlety.', 14, 2, 816692, '2023-09-02 07:28:03', '2023-09-02 07:28:09');

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `connection` text NOT NULL,
  `queue` text NOT NULL,
  `payload` longtext NOT NULL,
  `exception` longtext NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_reset_tokens_table', 1),
(3, '2019_08_19_000000_create_failed_jobs_table', 1),
(4, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(7, '2023_09_02_032617_create_movies_table', 2),
(9, '2023_09_02_093740_create_comments_table', 3),
(10, '2023_09_02_120204_comments_text_nullable', 4);

-- --------------------------------------------------------

--
-- Table structure for table `movies`
--

CREATE TABLE `movies` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `thumbnail` text NOT NULL,
  `director` varchar(255) NOT NULL,
  `writer` text NOT NULL,
  `youtube_trailer` text NOT NULL,
  `release_date` varchar(255) NOT NULL,
  `age_rating` varchar(255) NOT NULL,
  `tags` text NOT NULL,
  `short_description` text NOT NULL,
  `rating` double(8,2) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `movies`
--

INSERT INTO `movies` (`id`, `name`, `thumbnail`, `director`, `writer`, `youtube_trailer`, `release_date`, `age_rating`, `tags`, `short_description`, `rating`, `created_at`, `updated_at`) VALUES
(816692, 'Interstellar', 'https://picfiles.alphacoders.com/101/101684.jpg', 'Christopher Nolan', 'Jonathan Nolan, Christopher Nolan', 'zSWdZVtXT7E', '2014', 'PG-13', 'Adventure;Drama;Sci-Fi', 'When Earth becomes uninhabitable in the future, a farmer and ex-NASA pilot, Joseph Cooper, is tasked to pilot a spacecraft, along with a team of researchers, to find a new planet for humans.', 4.20, '2023-09-02 03:55:50', '2023-09-02 03:55:50'),
(7286456, 'Joker', 'https://image.tmdb.org/t/p/original/4TgXVa5gmOzMgpmYfBkpPjhMF7i.jpg', 'Todd Phillips', 'Todd Phillips, Scott Silver;Bob Kane', 'zAGVQLHvwOY', '2019', 'R', 'Crime;Drama;Thriller', 'The rise of Arthur Fleck, from aspiring stand-up comedian and pariah to Gotham\'s clown prince and leader of the revolution.', 4.60, '2023-09-02 04:00:34', '2023-09-02 04:00:34');

-- --------------------------------------------------------

--
-- Table structure for table `password_reset_tokens`
--

CREATE TABLE `password_reset_tokens` (
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `token` varchar(64) NOT NULL,
  `abilities` text DEFAULT NULL,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `expires_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'Sid', 'test@test.com', NULL, '$2y$10$hbKphwtapjEZt/pCgm3rxO83A4rvd3Q7Ee3DvQr9loT0BhaHyV80C', NULL, '2023-09-01 09:48:53', '2023-09-01 09:48:53'),
(2, 'Dave', 'test2@test.com', NULL, '$2y$10$ts1IEOr5NvtQcIvppzIe0uEsjFNul321R95mIgbl6SCXCWbPa0d6W', NULL, '2023-09-02 05:18:35', '2023-09-02 05:18:35'),
(3, 'Sam', 'test3@test.com', NULL, '$2y$10$ZS/sA19e5d5cm2WOVNPbh.fhKrs2xxqDfQeNyRaAHlN48vT0m/lGu', NULL, '2023-09-02 07:25:19', '2023-09-02 07:25:19');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comments_parent_foreign` (`parent`),
  ADD KEY `comments_creator_foreign` (`creator`),
  ADD KEY `comments_movie_foreign` (`movie`);

--
-- Indexes for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `movies`
--
ALTER TABLE `movies`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_reset_tokens`
--
ALTER TABLE `password_reset_tokens`
  ADD PRIMARY KEY (`email`);

--
-- Indexes for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `movies`
--
ALTER TABLE `movies`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7286457;

--
-- AUTO_INCREMENT for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comments_creator_foreign` FOREIGN KEY (`creator`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comments_movie_foreign` FOREIGN KEY (`movie`) REFERENCES `movies` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `comments_parent_foreign` FOREIGN KEY (`parent`) REFERENCES `comments` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

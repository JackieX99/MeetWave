-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2024. Jan 13. 16:27
-- Kiszolgáló verziója: 10.4.27-MariaDB
-- PHP verzió: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `s372_meetwave`
--
CREATE DATABASE IF NOT EXISTS `s372_meetwave` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `s372_meetwave`;

DELIMITER $$
--
-- Eljárások
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `banUser` (IN `userID` INT(11))   BEGIN
    
	UPDATE `user`
	SET `user`.`isBanned` = 1
	WHERE `user`.`userID` = userID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `changePassword` (IN `userID` INT(11), IN `newpassword` VARCHAR(255))   BEGIN
    UPDATE `user`
    SET `user`.`password` = newpassword
    WHERE `user`.`userID` = userID;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `changeUserPermission` (IN `userID` INT(11), IN `isAdminIN` BOOLEAN)   BEGIN 
	DECLARE userExists BOOLEAN;	

SELECT COUNT(*) INTO userExists
FROM `user`
WHERE `user`.`userID` = userID;

IF userExists THEN
	UPDATE `user`
    SET `user`.`isAdmin` = isAdminIN
    WHERE `user`.`userID` = userID;
  END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkExpiredSubscriptions` ()  NO SQL UPDATE `user`
SET `subscriptionType` = 0, `user`.`subscriptionEndOfDate` = NULL
WHERE 
  `subscriptionType` <> 0 
  AND `subscriptionEndOfDate` IS NOT NULL 
  AND `subscriptionEndOfDate` <> '0000-00-00' 
  AND `subscriptionEndOfDate` < DATE_ADD(NOW(), INTERVAL 1 HOUR)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkIfUserExists` (IN `email` VARCHAR(200), OUT `userExists` BOOLEAN)   BEGIN
    DECLARE userCount INT;
    SET userCount = 0;

    SELECT COUNT(*) INTO userCount FROM `user` WHERE `user`.`email` = email;

    IF userCount > 0 THEN
        SET userExists = 1;
    ELSE
        SET userExists = 0; 
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `checkServers` (OUT `status` VARCHAR(100))  NO SQL SET status = "running"$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `createEvent` (IN `eventTitle` VARCHAR(255), IN `description` VARCHAR(750), IN `dateOfTheEvent` DATETIME, IN `place` VARCHAR(100), IN `founder` VARCHAR(255), IN `dateOfCreatingEvent` TIMESTAMP, IN `maxParticipants` INT(6))   BEGIN

    DECLARE dateOfCreatingEvent DATETIME;
    SET dateOfCreatingEvent = CURRENT_TIMESTAMP;

    INSERT INTO `event` (`event`.`title`,
                         `event`.`description`,
                         `event`.`dateOfTheEvent`,
                         `event`.`placeOfTheEvent`,
                         `event`.`founderOfTheEvent`,
                         `event`.`dateOfCreatingEvent`,
                         `event`.`maxParticipants`)
    VALUES (eventTitle,
            description,
            dateOfTheEvent,
            place,
            founder,
            dateOfCreatingEvent,
            maxParticipants);

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteComment` (IN `commentID` INT(11))   BEGIN
    IF EXISTS (SELECT 1 FROM `comments` WHERE `comments`.`ID` = commentID) THEN
        DELETE FROM `comments` WHERE `comments`.`ID` = commentID;
        
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteEvent` (IN `eventID` INT(11))   BEGIN
  
  
  DELETE FROM `event` 
  WHERE `event`.`ID` = eventID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteUser` (IN `userID` INT(11))   BEGIN

    IF EXISTS (SELECT 1 FROM `user` WHERE `user`.`userID` = userID) THEN
       
        DELETE FROM `user` WHERE `user`.`userID` = userID;

END IF;
 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllBannedUsers` (OUT `result` VARCHAR(20))   BEGIN
	DECLARE affected_rows INT;
	SELECT * FROM `user`
    WHERE `user`.`isBanned` = 1;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllCommentByEvent` (IN `eventID` INT(11))   BEGIN 
	SELECT `comments`.`userID`, `comments`.`userComment`, `comments`.`dateOfComment` FROM `comments`
    WHERE `comments`.`eventID` = eventID;
    
  
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllEvent` ()   SELECT * FROM `event`$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getAllMutedUsers` ()   BEGIN

	SELECT * FROM `user`
    WHERE `user`.`isMuted` = 1;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserData` (IN `emailIN` VARCHAR(255))   BEGIN
    CREATE TEMPORARY TABLE IF NOT EXISTS tempUserDetails AS
    SELECT
        fullName,
        email,
        password,
        subscriptionType,
        subscriptionEndOfDate,
        isAdmin,
        isBanned,
        isMuted,
        profilePicture,
        phoneNumber,
        dateOfRegister
    FROM user
    WHERE email = emailIN;

    SELECT * FROM tempUserDetails;
    DROP TEMPORARY TABLE IF EXISTS tempUserDetails;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserParticipate` (IN `userID` INT(11))  NO SQL SELECT 
    `event`.`ID`,
    `event`.`title`,
    `event`.`description`,
    `event`.`dateOfTheEvent`,
    `event`.`placeOfTheEvent`,
    `event`.`founderOfTheEvent`,
    `event`.`eventPicture`,
    `event`.`dateOfCreatingEvent`,
    `event`.`maxParticipants`,
    `event`.`tickets`,
    `event`.`endOfEvent`,
    `participants`.`typeOfParticipation`
FROM 
    `event`
JOIN 
    `participants` ON `participants`.`eventID` = `event`.`ID`
JOIN
    `user` ON `user`.`userID` = `participants`.`userID`
WHERE
    (`participants`.`typeOfParticipation` = 1 
     OR `participants`.`typeOfParticipation` = 2)
    AND `user`.`userID` = userID$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getUserSubscription` (IN `userID` INT(11))   BEGIN
    SELECT * FROM `subscriptions`
    WHERE `subscriptions`.`userID` = userID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `loginUser` (IN `emailIN` VARCHAR(255), IN `passwordIN` VARCHAR(255), OUT `resultStatus` VARCHAR(20))   BEGIN
    DECLARE user_id INT;

  
    SELECT userID INTO user_id FROM user WHERE email = emailIN;


    IF user_id IS NULL THEN
        SET resultStatus = 'emailnotfound';
    ELSE
        
        SELECT COUNT(*) INTO user_id FROM user WHERE email = emailIN AND password = passwordIN;

      
        IF user_id = 0 THEN
            SET resultStatus = 'wrongpassword';
        ELSE 
            SET resultStatus = 'successful';
        END IF;
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `muteUser` (IN `userID` INT(11))   BEGIN
    
	UPDATE `user`
	SET `user`.`isMuted` = 1
	WHERE `user`.`userID` = userID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `postComment` (IN `eventID` INT(11), IN `userID` INT(11), IN `userCommentIN` VARCHAR(500), IN `dateOfCommentIN` DATETIME)   BEGIN

    INSERT INTO `comments` (`eventID`, `userID`, `userComment`, `dateOfComment`)
    VALUES (eventID, userID, userCommentIN, NOW());

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `profilePictureDelete` (IN `userID` INT(11))   BEGIN 

	UPDATE `user`
    SET `user`.`profilePicture` = NULL
    WHERE `user`.`userID` = userID;
   
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `profilePictureUpload` (IN `userID` INT(11), IN `fileContent` LONGBLOB)   BEGIN
    DECLARE path VARCHAR(255);

    SET path = '/eleresi_ut/kep.jpg';

    UPDATE `user`
    SET `user`.`profilePicture` = fileContent
    WHERE `user`.`userID` = userID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `registerUser` (IN `fullNameIN` VARCHAR(255), IN `emailIN` VARCHAR(255), IN `passwordIN` VARCHAR(255), IN `phoneNumberIN` CHAR(12))   BEGIN
    
    INSERT INTO `user` (`user`.`fullName`, `user`.`email`, `user`.`password`, `user`.`phoneNumber`, `user`.`dateOfRegister`, `user`.`subscriptionType`)
    VALUES (fullNameIN, emailIN, passwordIN, phoneNumberIN, NOW() + INTERVAL 1 HOUR, 0);
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `setSubscription` (IN `userID` INT(11), IN `subscriptionTypeIN` INT(1), OUT `result` VARCHAR(20))   BEGIN 
  
    DECLARE affected_rows INT; 
 
    UPDATE `user` 
    SET 
        `user`.`subscriptionType` = subscriptionTypeIN,
        `user`.`subscriptionEndOfDate` = IFNULL(DATE_ADD(`user`.`subscriptionEndOfDate`, INTERVAL 30 DAY), NOW()) 
    WHERE 
        (`user`.`userID` = userID 
        OR `user`.`subscriptionEndOfDate` IS NULL) AND (subscriptionTypeIN = 1 OR subscriptionTypeIN = 2);

	SELECT ROW_COUNT() INTO affected_rows;
    
   	IF affected_rows IS TRUE THEN
    SET result = 'successful';
    ELSE 
    SET result = 'failed';
    END IF;
    
  
    
    IF subscriptionTypeIN = 1 OR subscriptionTypeIN = 2 THEN
    INSERT INTO `subscriptions` (`subscriptions`.`userID`, `subscriptions`.`typeOfSubscription`, `subscriptions`.`dateOfSubscription`, `subscriptions`.`subscriptionEndOfDate`)
	VALUES (userID, subscriptionTypeIN, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY) ); 
    SET result = 'successful';
    ELSE 
    SET result = 'failed';
    END IF;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `store_profile_image` (IN `userId` INT(200), IN `imageBlob` BLOB)   BEGIN
    UPDATE `user` SET `user`.`profilePicture` = imageBlob WHERE `user`.`userID` = userId;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `subscriptionExtendDate` (IN `userID` INT(11))   BEGIN

  UPDATE `user`
  SET `user`.`subscriptionEndOfDate` = DATE_ADD(`user`.`subscriptionEndOfDate`, INTERVAL 30 DAY) 
  WHERE `user`.`userID` = userID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `unBanUser` (IN `userID` INT(11))   BEGIN
    
    UPDATE `user`
    SET `user`.`isBanned` = 0
    WHERE `user`.`userID` = userID;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `unMuteUser` (IN `userID` INT(11))   BEGIN 
    
    UPDATE `user`
    SET `user`.`isMuted` = 0
    WHERE `user`.`userID` = userID;
    
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateComment` (IN `userCommentID` INT(11), IN `userCommentIN` VARCHAR(750))   BEGIN
    DECLARE currentUserCommentIN VARCHAR(750);
  
    SELECT userComment INTO currentUserCommentIN FROM `comments`
    WHERE ID = userCommentID LIMIT 1;

    IF userCommentIN IS NULL OR userCommentIN = '' THEN
        SET userCommentIN = currentUserCommentIN;
    END IF;


    UPDATE `comments`
    SET 
       `comments`.`userComment` = userCommentIN
    WHERE `comments`.`ID` = userCommentID;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateEvent` (IN `eventID` INT(11), IN `eventTitleIN` VARCHAR(300), IN `descriptionIN` VARCHAR(750), IN `dateOfTheEventIN` DATETIME, IN `placeOfTheEventIN` VARCHAR(255), IN `founderOfTheEventIN` VARCHAR(255), IN `maxParticipantsIN` INT(6))   BEGIN
    DECLARE currentEventTitle VARCHAR(255);
    DECLARE currentDescription VARCHAR(255);
    DECLARE currentDateOfTheEvent DATE;
    DECLARE currentPlaceOfTheEvent VARCHAR(255);
    DECLARE currentFounderOfTheEvent VARCHAR(255);
    DECLARE currentMaxParticipants INTEGER;

    SELECT
        `event`.`title`,
        `event`.`description`,
        `event`.`dateOfTheEvent`,
        `event`.`placeOfTheEvent`,
        `event`.`founderOfTheEvent`,
        `event`.`maxParticipants`
    INTO
        currentEventTitle,
        currentDescription,
        currentDateOfTheEvent,
        currentPlaceOfTheEvent,
        currentFounderOfTheEvent,
        currentMaxParticipants
    FROM `event`
    WHERE `event`.`ID` = eventID
    LIMIT 1;

    IF eventTitleIN IS NULL OR eventTitleIN = '' THEN
        SET eventTitleIN = currentEventTitle;
    END IF;

    IF descriptionIN IS NULL OR descriptionIN = '' THEN
        SET descriptionIN = currentDescription;
    END IF;
    
    IF dateOfTheEventIN IS NULL OR dateOfTheEventIN = '' THEN
        SET dateOfTheEventIN = currentDateOfTheEvent;
    END IF;
    
    IF placeOfTheEventIN IS NULL OR placeOfTheEventIN = '' THEN
        SET placeOfTheEventIN = currentPlaceOfTheEvent;
    END IF;
    
    IF founderOfTheEventIN IS NULL OR founderOfTheEventIN = '' THEN
        SET founderOfTheEventIN = currentFounderOfTheEvent;
    END IF;
    
    IF maxParticipantsIN IS NULL OR maxParticipantsIN = '' THEN
        SET maxParticipantsIN = currentMaxParticipants;
    END IF;

    UPDATE `event`
    SET 
        `event`.`title` = eventTitleIN,
        `event`.`description` = descriptionIN,
        `event`.`dateOfTheEvent` = dateOfTheEventIN,
        `event`.`placeOfTheEvent` = placeOfTheEventIN,
        `event`.`founderOfTheEvent` = founderOfTheEventIN,
        `event`.`maxParticipants` = maxParticipantsIN
    WHERE `event`.`ID` = eventID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `updateUser` (IN `userID` INT(11), IN `newFullName` VARCHAR(255), IN `newEmail` VARCHAR(255), IN `newPhoneNumber` VARCHAR(11))   BEGIN
    DECLARE currentPhoneNumber VARCHAR(20);
    DECLARE currentFullName VARCHAR(255);
    DECLARE currentEmail VARCHAR(255);

    SELECT phoneNumber, fullName, email INTO currentPhoneNumber, currentFullName, currentEmail FROM `user` WHERE userID = UserID LIMIT 1;

    IF NewFullName IS NULL OR NewFullName = '' THEN
        SET NewFullName = currentFullName;
    END IF;

    IF NewEmail IS NULL OR NewEmail = '' THEN
        SET NewEmail = currentEmail;
    END IF;

    IF NewPhoneNumber IS NULL OR NewPhoneNumber = '' THEN
        SET NewPhoneNumber = currentPhoneNumber;
    END IF;

    UPDATE `user`
    SET 
        `user`.`fullName` = NewFullName,
        `user`.`email` = NewEmail,
        `user`.`phoneNumber` = NewPhoneNumber
    WHERE `user`.`userID` = UserID;

END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `userParticipateOnEvent` (IN `eventIDIN` INT(11), IN `userIDIN` INT(11), IN `typeOfParticipationIN` INT(11))   BEGIN
  DECLARE successFlag BOOLEAN;

  IF EXISTS (SELECT * FROM `participants` WHERE `participants`.`eventID` = eventIDIN AND `participants`.`userID` = userIDIN) THEN
     IF typeOfParticipationIN = 0 THEN
      DELETE FROM `participants` WHERE `participants`.`eventID` = eventIDIN AND `participants`.`userID` = userIDIN;
      SET successFlag = TRUE;
    ELSE
    
      UPDATE `participants`
      SET `participants`.`typeOfParticipation` = typeOfParticipationIN
      WHERE `participants`.`eventID` = eventIDIN AND `participants`.`userID` = userIDIN;

      SET successFlag = TRUE;
    END IF;
  ELSE
    
    INSERT INTO `participants` (eventID, userID, typeOfParticipation)
    VALUES (eventIDIN, userIDIN, typeOfParticipationIN);

 
    IF ROW_COUNT() > 0 THEN
      SET successFlag = TRUE;
    ELSE
      SET successFlag = FALSE;
    END IF;
  END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `comments`
--

CREATE TABLE `comments` (
  `ID` int(11) NOT NULL,
  `eventID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `userComment` varchar(500) NOT NULL,
  `dateOfComment` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `comments`
--

INSERT INTO `comments` (`ID`, `eventID`, `userID`, `userComment`, `dateOfComment`) VALUES
(1, 1, 2, 'Nagyon jó Dj-k lesznek! Nagyon várom már, hogy ott legyünk! 😊', '2023-11-28 00:00:00'),
(3, 1, 1, 'edeeded', '2023-12-04 15:49:19'),
(11, 1, 1, 'EZ NAGYON KOMOLY BULI LESZ.', '2023-12-05 13:05:10'),
(14, 1, 1, 'hűha', '2024-01-12 09:15:59');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `event`
--

CREATE TABLE `event` (
  `ID` int(11) NOT NULL,
  `title` varchar(300) NOT NULL,
  `description` varchar(1000) NOT NULL,
  `dateOfTheEvent` datetime NOT NULL,
  `placeOfTheEvent` varchar(150) NOT NULL,
  `founderOfTheEvent` varchar(150) NOT NULL,
  `eventPicture` blob DEFAULT NULL,
  `dateOfCreatingEvent` datetime NOT NULL,
  `maxParticipants` int(6) NOT NULL,
  `tickets` int(11) NOT NULL,
  `endOfEvent` datetime NOT NULL,
  `interested` tinyint(1) NOT NULL,
  `willbethere` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `event`
--

INSERT INTO `event` (`ID`, `title`, `description`, `dateOfTheEvent`, `placeOfTheEvent`, `founderOfTheEvent`, `eventPicture`, `dateOfCreatingEvent`, `maxParticipants`, `tickets`, `endOfEvent`, `interested`, `willbethere`) VALUES
(1, 'TechXmas wMartin Books | Expo Center Pécs 12/25', 'Idén karácsonykor a Német techno királyát Martin Booksot hozzuk vissza nektek!', '2023-12-25 00:00:00', 'Pécs', 'Térd Dániel', NULL, '2023-11-28 00:00:00', 1000, 0, '2024-01-12 09:34:32', 0, 0),
(2, 'Nyulak ujjászületése', 'it is what it is', '2023-12-31 00:00:00', 'Pécs Radnóti', 'Fricskup Szabolcs', NULL, '2023-11-28 00:00:00', 10, 0, '2024-01-12 09:34:32', 0, 0),
(8, '02.03 februári exgó zenter', 'bulika lesz', '2023-02-03 00:00:00', 'Pécs Expo', 'Földvári Alex', NULL, '2024-01-12 00:00:00', 1000, 0, '2024-01-12 09:34:32', 0, 0);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `participants`
--

CREATE TABLE `participants` (
  `ID` int(11) NOT NULL,
  `eventID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `typeOfParticipation` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `participants`
--

INSERT INTO `participants` (`ID`, `eventID`, `userID`, `typeOfParticipation`) VALUES
(1, 1, 1, 2),
(2, 2, 1, 1),
(3, 1, 2, 2),
(6, 7, 12, 2);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `subscriptions`
--

CREATE TABLE `subscriptions` (
  `ID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `typeOfSubscription` int(1) NOT NULL,
  `dateOfSubscription` datetime NOT NULL,
  `subscriptionEndOfDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `subscriptions`
--

INSERT INTO `subscriptions` (`ID`, `userID`, `typeOfSubscription`, `dateOfSubscription`, `subscriptionEndOfDate`) VALUES
(1, 1, 2, '2023-11-28 01:00:00', '0000-00-00 00:00:00'),
(2, 9, 2, '2023-12-06 10:35:10', '2024-01-05 10:35:10'),
(3, 12, 1, '2023-12-12 14:19:22', '2024-01-11 14:19:22'),
(4, 13, 1, '2024-01-12 09:38:26', '2024-02-11 09:38:26'),
(5, 13, 1, '2024-01-12 09:40:11', '2024-02-11 09:40:11');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `fullName` varchar(150) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(150) NOT NULL,
  `subscriptionType` int(1) NOT NULL,
  `subscriptionEndOfDate` datetime DEFAULT NULL,
  `isAdmin` tinyint(1) NOT NULL DEFAULT 0,
  `isBanned` tinyint(1) NOT NULL DEFAULT 0,
  `isMuted` tinyint(1) NOT NULL DEFAULT 0,
  `profilePicture` blob DEFAULT NULL,
  `phoneNumber` char(12) NOT NULL,
  `dateOfRegister` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- A tábla adatainak kiíratása `user`
--

INSERT INTO `user` (`userID`, `fullName`, `email`, `password`, `subscriptionType`, `subscriptionEndOfDate`, `isAdmin`, `isBanned`, `isMuted`, `profilePicture`, `phoneNumber`, `dateOfRegister`) VALUES
(1, 'Biskup Szabolcs', 'biskupszabi123@gmail.com', 'fc3840e12213398b28ef964905b6b286deed1a6f2a27ede1c587bdb08ca07731', 1, '2025-03-29 00:00:00', 1, 1, 1, NULL, '06303252065', '2023-11-28 00:00:00'),
(2, 'Földvári Alex', 'foldvarialex@gmail.com', '3b849b1bec2c0acc7cf7b9abcfeacc4a0088ec7e224597fd1a546d80dae14da5', 1, '2024-11-28 00:00:00', 0, 0, 0, NULL, '06301234567', '2023-11-28 00:00:00'),
(6, 'MeetWave Developer Account', 'developer@meetwave.hu', '00063465a83b15fbda6cc865d42f1dda0479371bcf83b47e67858caa8c31967c', 2, '2024-02-03 00:00:00', 1, 0, 0, NULL, '06708845584', '2023-11-28 19:02:28'),
(9, 'Péter Jozo', 'jozoapepe@gmail.com', '539924f6ab19641b630e621a64b03300c064a07d4001d71ebb4c20ca9e22b166', 2, '2025-02-01 00:00:00', 0, 0, 1, NULL, '', '2023-11-30 16:49:48'),
(11, 'TesztEmber', 'tesztEmber@gmail.com', '26992d9bdb0f2555a4e304631e5d703580c3d44de5cf8b2daa9c8d3b4c4ebe32', 0, NULL, 0, 0, 0, NULL, '3630322323', '2023-12-06 09:23:31'),
(13, 'Fledrich Greg', 'flerdrich@gmail.com', '0512b9ad82455922876d22184705b0ae2d468ace15e969d53a6a3a67a6846640', 0, NULL, 0, 0, 0, NULL, '+3630512000', '2024-01-12 10:37:37');

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `participants`
--
ALTER TABLE `participants`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `subscriptions`
--
ALTER TABLE `subscriptions`
  ADD PRIMARY KEY (`ID`);

--
-- A tábla indexei `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `comments`
--
ALTER TABLE `comments`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT a táblához `event`
--
ALTER TABLE `event`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT a táblához `participants`
--
ALTER TABLE `participants`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT a táblához `subscriptions`
--
ALTER TABLE `subscriptions`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT a táblához `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

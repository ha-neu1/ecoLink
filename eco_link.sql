-- 회원
CREATE TABLE `member` (
	`memId` VARCHAR(36) NOT NULL PRIMARY KEY,
	`memPw`	VARCHAR(50)	NOT NULL,
	`memNick`	VARCHAR(50)	NOT NULL,
	`memEmail`	VARCHAR(50)	NOT NULL CHECK (memEmail like '%@%'),
	`memType`	VARCHAR(20)	NOT NULL,
	`memRegtime`	TIMESTAMP	DEFAULT CURRENT_TIMESTAMP,
	`memName`	VARCHAR(20)	NOT NULL
);

select * from member;

INSERT INTO member values(
'admin',
'2819',
'관리자1',
'temp@gmail.com',
'admin',
default,
'관리자1'
);

INSERT INTO member values(
'normal',
'1111',
'일반회원1',
'temp12@gmail.com',
'normal',
default,
'일반회원1'
);

INSERT INTO member values(
'enter',
'2222',
'기업회원1',
'enter@gmail.com',
'enter',
default,
'기업회원1'
);

-- 기업회원
CREATE TABLE enterprise (
    entCrn    VARCHAR(20)    NOT NULL PRIMARY KEY,
    entPhone    VARCHAR(50)    NOT NULL,
    memId    VARCHAR(36)    NOT NULL,
    entdMainPic    VARCHAR(255)    NULL,
    entdShort    VARCHAR(255)    NULL,
    entdURL    VARCHAR(255)    NULL,
    entdIntro    VARCHAR(1000)    NULL,
    entdIntroPic    VARCHAR(255)    NULL,
    entdPic1    VARCHAR(255)    NOT NULL,
    entdPic2    VARCHAR(255)    NULL,
    entdPic3    VARCHAR(255)    NULL,
    entdExplain1    VARCHAR(500)    NOT NULL,
    entdExplain2    VARCHAR(500)    NULL,
    entdExplain3    VARCHAR(500)    NULL,
    entdConfirm    boolean    NOT NULL,
    foreign key(memId) references member(memId)
);

select * from enterprise;

INSERT INTO enterprise values(
'110-00-10101',
'02-555-3555',
'enter',
'mainpic.jpg',
'짧은 설명입니다. 짧은 설명입니다. 짧은 설명입니다. 짧은 설명입니다. 짧은 설명입니다. 짧은 설명입니다. 짧은 설명입니다. 짧은 설명입니다. 짧은 설명입니다.',
'http://www.temp.com/',
'긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.긴 설명입니다.',
'intropic.jpg',
'entdPic1.jpg',
'entdPic2.jpg',
'entdPic3.jpg',
'entdExplain1',
'entdExplain2',
'entdExplain3',
true
);

-- 배너
CREATE TABLE `banner` (
	`bannerId`	VARCHAR(36) DEFAULT (UUID()) PRIMARY KEY,
	`bannerPic`	VARCHAR(255) NOT NULL,
	`memId`	VARCHAR(36)	NOT NULL,
    foreign key(`memId`) references `member`(`memId`)
);

select * from banner;

INSERT INTO banner values(
default,
'bannerpic.jpg',
'admin'
);

-- 게시판
CREATE TABLE `board` (
	`boardId`	INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`boardTitle`	VARCHAR(50)	NULL,
	`boardViewCount`	INT UNSIGNED NOT NULL,
	`boardRegtime`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`boardUpdateTime`	DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`boardContents`	VARCHAR(1000) NOT NULL,
	`memId`	VARCHAR(36)	NOT NULL,
	`boardType`	VARCHAR(20)	NOT NULL,
    foreign key(`memId`) references `member`(`memId`)
);

select * from board;

-- boardType 컬럼 >> 뉴스(news) 공유(share) 리뷰(review) 팁(tip) 정보 중 하나
INSERT INTO board values(
default,
'제목1',
0,
default,
default,
'내용내용내용내용내용내용내용내용내용',
'normal',
'share'
);

-- 파일
CREATE TABLE `file` (
	`fileIdx`	VARCHAR(36) DEFAULT (UUID()) PRIMARY KEY,
	`filePath`	VARCHAR(255) NOT NULL,
	`fileName`	VARCHAR(255) NOT NULL,
	`fileType`	VARCHAR(10)	NOT NULL,
	`boardId`	INT UNSIGNED NOT NULL,
    foreign key(`boardId`) references `board`(`boardId`)
);

select * from file;

INSERT INTO file values(
default,
'/ddd/sss',
'dddd',
'.jpg',
'1'
);

-- 게시판좋아요
CREATE TABLE `board_like` (
	`memId`	VARCHAR(36)	NOT NULL,
	`boardId`	INT UNSIGNED NOT NULL,
    foreign key(`memId`) references `member`(`memId`),
    foreign key(`boardId`) references `board`(`boardId`)
);

select * from board_like;

INSERT INTO board_like values(
'normal',
'1'
);

-- 브랜드후기댓글
CREATE TABLE `brand_comment` (
	`brcId`	INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`brcRate`	INT	NOT NULL,
	`brcContents`	VARCHAR(255) NOT NULL,
	`brcRegtime`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`brcUpdateTime`	DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	`entCrn`	VARCHAR(20)	NOT NULL,
	`memId`	VARCHAR(36)	NOT NULL,
    foreign key(`memId`) references `member`(`memId`),
    foreign key(`entCrn`) references `enterprise`(`entCrn`)
);


select * from brand_comment;

INSERT INTO brand_comment values(
default,
10,
'내용~~~',
default,
default,
'110-00-10101',
'normal'
);

-- 일반게시판댓글
CREATE TABLE `board_comment` (
	`bcId`	INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`boardId` INT UNSIGNED NOT NULL,
	`bcContents`	VARCHAR(255) NOT NULL,
	`memId`	VARCHAR(36)	NOT NULL,
	`bcRegtime`	TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	`bcUpdateTime`	DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    foreign key(`memId`) references `member`(`memId`),
    foreign key(`boardId`) references `board`(`boardId`)
);

select * from board_comment;

INSERT INTO board_comment values(
default,
1,
'내용~~~',
'normal',
default,
default
);

-- 브랜드북마크
CREATE TABLE `enterprise_bookmark` (
	`memId`	VARCHAR(36)	NOT NULL,
	`entCrn`	VARCHAR(20)	NOT NULL,
    foreign key(`entCrn`) references `enterprise`(`entCrn`),
    foreign key(`memId`) references `member`(`memId`)
);

select * from enterprise_bookmark;

INSERT INTO enterprise_bookmark values(
'normal',
'110-00-10101'
);








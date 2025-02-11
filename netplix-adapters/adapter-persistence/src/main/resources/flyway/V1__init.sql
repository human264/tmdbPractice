-- 사용자 테이블
DROP TABLE IF EXISTS `netplix`.`users`;
CREATE TABLE `netplix`.`users`
(
    USER_ID     VARCHAR(255) NOT NULL COMMENT '사용자 ID (UUID)',
    USER_NAME   VARCHAR(50)  NOT NULL COMMENT '사용자 이름',
    PASSWORD    VARCHAR(255) NOT NULL COMMENT '사용자 비밀번호 (암호화)',
    EMAIL       VARCHAR(255) NOT NULL COMMENT '이메일',
    PHONE       VARCHAR(255) NOT NULL COMMENT '전화번호',

    CREATED_AT  DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY  VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (USER_ID)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;

-- 소셜 사용자 테이블
DROP TABLE IF EXISTS `netplix`.`social_users`;
CREATE TABLE `netplix`.`social_users`
(
    SOCIAL_USER_ID VARCHAR(255) NOT NULL COMMENT '소셜 사용자 ID (UUID)',
    USER_NAME      VARCHAR(50)  NOT NULL COMMENT '소셜 사용자 이름',
    PROVIDER       VARCHAR(255) NOT NULL COMMENT '소셜 프로바이더 (구글, 카카오, 네이버 등)',
    PROVIDER_ID    VARCHAR(255) NOT NULL COMMENT '프로바이더 ID',

    CREATED_AT     DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY     VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT    DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY    VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (SOCIAL_USER_ID)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;

-- 사용자 이력 테이블
DROP TABLE IF EXISTS `netplix`.`user_histories`;
CREATE TABLE `netplix`.`user_histories`
(
    USER_HISTORY_ID BIGINT       NOT NULL AUTO_INCREMENT COMMENT '사용자 이력 ID',
    USER_ID         VARCHAR(255) NOT NULL COMMENT '사용자 ID',
    USER_ROLE       VARCHAR(255) NOT NULL COMMENT '사용자 역할',
    REQ_IP          VARCHAR(255) NOT NULL COMMENT '요청 IP',
    REQ_METHOD      VARCHAR(255) NOT NULL COMMENT '요청 메소드',
    REQ_URL         VARCHAR(255) NOT NULL COMMENT '요청 URL',
    REQ_HEADER      TEXT         NOT NULL COMMENT '요청 헤더',
    REQ_PAYLOAD     TEXT         NOT NULL COMMENT '요청 바디',

    CREATED_AT      DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY      VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT     DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY     VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (USER_HISTORY_ID)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;

-- 사용자 구독 테이블
DROP TABLE IF EXISTS `netplix`.`user_subscriptions`;
CREATE TABLE `netplix`.`user_subscriptions`
(
    USER_SUBSCRIPTION_ID VARCHAR(255) NOT NULL COMMENT '사용자 구독 ID',
    USER_ID              VARCHAR(255) NOT NULL COMMENT '사용자 ID',
    SUBSCRIPTION_NAME    VARCHAR(255) NOT NULL COMMENT '구독권 이름',
    START_AT             DATETIME     NOT NULL COMMENT '시작 일시 (yyyyMMdd HH:mm:dd)',
    END_AT               DATETIME     NOT NULL COMMENT '종료 일시 (yyyyMMdd HH:mm:dd)',
    VALID_YN             TINYINT(1)   NOT NULL COMMENT '구독권 유효 여부',

    CREATED_AT           DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY           VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT          DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY          VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (USER_SUBSCRIPTION_ID)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;

-- 토큰 테이블
DROP TABLE IF EXISTS `netplix`.`tokens`;
CREATE TABLE `netplix`.`tokens`
(
    TOKEN_ID                 VARCHAR(255) NOT NULL COMMENT '토큰 PK',
    USER_ID                  VARCHAR(255) NOT NULL COMMENT '사용자 ID',
    ACCESS_TOKEN             VARCHAR(255) COMMENT '액세스 토큰',
    REFRESH_TOKEN            VARCHAR(255) COMMENT '리프레시 토큰',
    ACCESS_TOKEN_EXPIRES_AT  DATETIME     COMMENT '액세스 토큰 만료시간',
    REFRESH_TOKEN_EXPIRES_AT DATETIME     COMMENT '리프레시 토큰 만료시간',

    CREATED_AT               DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY               VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT              DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY              VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (TOKEN_ID)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;

-- 영화 테이블
DROP TABLE IF EXISTS `netplix`.`movies`;
CREATE TABLE `netplix`.`movies`
(
    MOVIE_ID    VARCHAR(255) NOT NULL COMMENT '영화 ID',
    MOVIE_NAME  VARCHAR(255) NOT NULL COMMENT '영화 명',
    IS_ADULT    TINYINT(1)   COMMENT '성인영화 여부',
    GENRE       VARCHAR(255) COMMENT '장르',
    OVERVIEW    VARCHAR(255) COMMENT '설명',
    RELEASED_AT VARCHAR(255) COMMENT '출시일자',

    CREATED_AT  DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY  VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (MOVIE_ID)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;

-- 사용자 영화 좋아요 테이블
DROP TABLE IF EXISTS `netplix`.`user_movie_likes`;
CREATE TABLE `netplix`.`user_movie_likes`
(
    USER_MOVIE_LIKE_ID VARCHAR(255) NOT NULL COMMENT 'PK',
    USER_ID            VARCHAR(255) NOT NULL COMMENT '사용자 ID',
    MOVIE_ID           VARCHAR(255) NOT NULL COMMENT '영화 ID',
    LIKE_YN            TINYINT(1)   COMMENT '좋아요 여부',

    CREATED_AT         DATETIME     NOT NULL COMMENT '생성일자',
    CREATED_BY         VARCHAR(50)  NOT NULL COMMENT '생성자',
    MODIFIED_AT        DATETIME     NOT NULL COMMENT '수정일자',
    MODIFIED_BY        VARCHAR(50)  NOT NULL COMMENT '수정자',

    PRIMARY KEY (USER_MOVIE_LIKE_ID)
)
    ENGINE=InnoDB
    DEFAULT CHARSET=utf8mb4
    COLLATE=utf8mb4_unicode_ci;

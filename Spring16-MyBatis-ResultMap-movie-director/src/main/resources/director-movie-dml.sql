-- director-movie-dml.sql
-- 통계 SQL( statistics) 감독별 통계 정보 조회 : SUM, ROUND(AVG)
-- director_id 감독 아이디 director_name 감독명 totalAttendance 총관객수 averageAttendance 평균관객수

SELECT director.director_id AS directorId, director_name AS directorName,
	SUM(attendance) AS totalAttendance, ROUND(AVG(attendance), 0) AS averageAttendance
FROM director join movie on director.director_id = movie.director_id
GROUP BY directorId, directorName
ORDER BY totalAttendance DESC

-- GROUP BY d.director_id, d.director_name : 


-- movie_id로 movie 정보만 조회해본다
SELECT *
FROM movie
WHERE movie_id=1

SELECT *
FROM director
WHERE director_id=1
-- 위 두 SQL을 join하여 한번에 1번 아이디 영화의 영화정보와 해당 감독정보를 함께 조회
-- movie_id, title, genre, attendance, director_id, director_name, intro

-- 개별 영화(감독정보 포함) 조회 : 상세 게시물 조회 (많은 컬럼을 상세히 조회)
SELECT movie_id, title, genre, attendance, director.director_id, director_name, intro
FROM director join movie on director.director_id = movie.director_id
WHERE movie_id = 1

-- 전체 영화(감독정보 포함) 리스트 : 게시물 리스트 화면에 필요한 컬럼들만 조회 
SELECT movie_id, title, director.director_id, director_name
FROM director join movie on director.director_id = movie.director_id
ORDER BY movie_id ASC
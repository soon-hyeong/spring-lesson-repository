package org.kosa.myproject.runner;

import java.util.List;
import java.util.Map;

import org.kosa.myproject.mapper.DirectorMapper;
import org.springframework.boot.CommandLineRunner;

//@Component // test 마무리 되어서 실행 안되게 주석 처리
//@Order(2)
public class DirectorStudyRunner implements CommandLineRunner {
	private final DirectorMapper directorMapper;
	public DirectorStudyRunner(DirectorMapper directorMapper) {
		super();
		this.directorMapper = directorMapper;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("DirectorStudyRunner");
		/**
		 * TABLE Director의 리스트 정보를 반환받아 출력
		 * org.kosa.myproject.mapper.DirectorMapper
		 * DirectorMapper interface와 DirectorMapper xml을 정의하여
		 * 아래 메서드가 정상적으로 수행하여 출력되도록 구현
		 */
		
//		List<Director> list = directorMapper.findAllList();
//		for(Director director:list) {
//			System.out.println(director);
//		}
		
		// 통계 sql 조회
		List<Map<String, Object>> list = directorMapper.findDirectorStatistics();
		for(Map<String, Object>map:list)
			// ROUND(AVG(m.attendance), 0) AS averageAttendance와 AS로 별칭을 준 이름이 map의 key로 반환된다
			System.out.println(map.get("directorId") + " " + map.get("directorName") + " "
					 + map.get("totalAttendance") + " " + map.get("averageAttendance"));
	}


	
}

package com.adventuretube;

;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AdventuretubeMain {

    public static void main(String[] args) {
        SpringApplication.run(AdventuretubeMain.class, args);
    }


//    @Bean
//    CommandLineRunner runner(AdventureTubeDataRepository repository) {
//        return args -> {
//
//            Place place1 = new Place(List.of("geocaching"),
//                    2,
//                    new Double[]{-38.360894999999999, 141.60411550000001},
//                    "no placeID",
//                    "Portland"
//            );
//            Place place2 = new Place(List.of("hiking"),
//                    45,
//                    new Double[]{-38.361903819882578, 141.60609304904938},
//                    "no placeID",
//                    "Short Street Takeaway"
//            );
//
//            Place place3 = new Place(List.of("geocaching"),
//                    373,
//                    new Double[]{-38.359968908026708, 141.60569071769714},
//                    "no placeID",
//                    "Portland RSL Memorial\\nBowling Club"
//            );
//
//            Place place4 = new Place(List.of("geocaching"),
//                    686,
//                    new Double[]{-38.357810999675671, 141.60386681556702},
//                    "no placeID",
//                    "Waan’s Thai Massage"
//            );
//            Place place5 = new Place(List.of("geocaching", "campfire"),
//                    493,
//                    new Double[]{-38.360557799718563, 141.60277247428894},
//                    "no placeID",
//                    "New Leaf Landscaping"
//            );
//            Place place6 = new Place(List.of("hiking"),
//                    89,
//                    new Double[]{-38.358881023186555, 141.60402238368988},
//                    "no placeID",
//                    "Juma"
//            );
//
//
//            Chapter chapter1 = new Chapter(place1, "GKx2TFgqgVc", 2, List.of("geocaching"));
//            Chapter chapter2 = new Chapter(place2, "GKx2TFgqgVc", 45, List.of("hiking"));
//            Chapter chapter3 = new Chapter(place3, "GKx2TFgqgVc", 373, List.of("geocaching"));
//            Chapter chapter4 = new Chapter(place4, "GKx2TFgqgVc", 686, List.of("geocaching"));
//            Chapter chapter5 = new Chapter(place5, "GKx2TFgqgVc", 493, List.of("geocaching", "campfire"));
//            Chapter chapter6 = new Chapter(place6, "GKx2TFgqgVc", 489, List.of("geocaching", "campfire"));
//
//
//            AdventureTubeData adventureTubeData = new AdventureTubeData(List.of("campfire", "geocaching", "hiking"),
//                    List.of(place1, place2, place3, place4, place5, place6),
//                    "singleday",
//                    "",
//                    "Sovereign Winter WonderLight Festival",
//                    List.of(chapter1, chapter2, chapter3, chapter4, chapter5, chapter6),
//                    "single",
//                    "9E85E72A-16F9-48C6-928E-931DB2DBDE8C",
//                    "eYkLRol_RFA"
//            );
//
//            repository.findAdventureTubeDataByYoutubeContentID(adventureTubeData.getYoutubeContentID())
//                            .ifPresentOrElse(returnedADdata -> {
//                                System.out.println("youtubeId " + returnedADdata.getYoutubeContentID() +" already exist ") ;
//                            },() ->{
//                                System.out.println("youtubeId " +adventureTubeData.getYoutubeContentID() +" will be saved");
//                                repository.insert(adventureTubeData);
//                            });
//        };
//    }

//	@Bean
//	CommandLineRunner runner(StudentRepositorty repository,
//							 MongoTemplate mongoTemplate){
//		return args -> {
//			Address address = new Address(
//					"Australia",
//					"Melbourne",
//					"VIC3338"
//			);
//			String email = "strider.lee@gmail.com";
//			Student student = new Student(
//			        	"Chris",
//					   "Lee",
//					   email,
//					   Gender.MALE,
//					   address,
//					   List.of("computer science" , "maths"),
//					   BigDecimal.TEN,
//					   LocalDateTime.now()
//			   );
//			//usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);
//			repository.findStudentByEmail(email)
//					.ifPresentOrElse(s -> {
//						System.out.println(s + " already exist ");
//					},() -> {
//						System.out.println("Inserting student");
//						repository.insert(student);
//					});
//		};
//	}

//    private void usingMongoTemplateAndQuery(StudentRepositorty repository, MongoTemplate mongoTemplate, String email, Student student) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("email").is(email));
//        List<Student> students = mongoTemplate.find(query, Student.class);
//        if (students.size() > 1) {
//            throw new IllegalStateException("found many students with email " + email);
//        }
//        if (students.isEmpty()) {
//            System.out.println("Inserting student");
//            repository.insert(student);
//        } else {
//            System.out.println(student + " already exist ");
//        }
//    }
}

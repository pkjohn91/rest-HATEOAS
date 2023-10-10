package com.example.resthateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private static final String TEMPLATE = "Hello, %s!";

	/*
	 * 참고: https://spring.io/guides/gs/rest-hateoas/
	 * 
	 * @RequestMapping은 기본적으로 모든 HTTP 작업을 매핑하므로 GET vs PUT, POST등을 지정하지 않는다.
	 * 
	 * @GetMapping("/greeting") 은 좁은 맵핑이다. 이 경우
	 * import ort.springframework.web.bind.annotation.GetMapping; 을 해주어야 한다.
	 */
	@RequestMapping("/greeting")
	public HttpEntity<Greeting> greeting(
			@RequestParam(value = "name", defaultValue = "World") String name) {

		Greeting greeting = new Greeting(String.format(TEMPLATE, name));
		greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());

		return new ResponseEntity<>(greeting, HttpStatus.OK);
	}

	/*
	 * @RequestParam의 쿼리 문자열 매개변수 값을 메서드의 매개변수 'name'에 바인딩한다. 이 쿼리 문자열 매개변수는
	 * 속성 사용으로 인해 암시적으로 발생하지 않는다. 요청이 없으면 defaultValue 값인 'World'가 사용된다.
	 * 
	 * @RestController 주석이 클래스에 존재하므로 암시적 @Responseody 주석이 greeting() 메서드에 추가된다.
	 * 이로 인해 Spring MVC는 반환된 HttpENtity와 해당 페이로드(greeting메서드에 포함된 인스턴스들)를
	 * 응답에 직접 렌더링한다.
	 * 
	 * 이 메서드 구현에 가장 흥미로운 부분은 컨트롤러 메서드를 가리키는 링크를 생성하는 방법과
	 * 이를 표현 모델에 추가하는 방법이다.
	 * 
	 * linkTo(...)와 methodOn(...)은 모두 컨트롤러에서 메서드 호출을 가자로 만들 수 있는
	 * ControllerLinkBuilder의 정적 메서드이다.
	 * 반환된 ControllerLinkBuilder 메서드는 LinkBuilder 컨트롤러 메서드의 매핑 annotation을 검사하여 메서드가
	 * 매핑되는 uri를
	 * 정확하게 구축한다.
	 */

	/*
	 * Spring HATEOAS는 다양한 X-FORWARDED-헤더를 존중한다. Spring HATEOAS 서비스를 프록시 뒤에 배치하고
	 * X-FORWARDED-HOST 헤더로 올바르게 수성하면 결과 링크의 형식이 올바르게 지정된다.
	 */

}

package com.example.resthateoas;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting extends RepresentationModel<Greeting> {

	private final String content;

	/*
	 * @JsonCreator: Jackson이 이 POJO 인스턴스를 생성할 수 있는 방법을 나타낸다.
	 * 
	 * @JsonProperty: Jackson이 생성자 인수(argument)를 입력해야 하는 필드를 표시한다.
	 * 
	 * Spring은 Jackson JSON 라이브러리를 사용하여 자동적으로 Greeting 타입의 인스턴스들을 정렬시킨다.
	 */
	@JsonCreator
	public Greeting(@JsonProperty("content") String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}

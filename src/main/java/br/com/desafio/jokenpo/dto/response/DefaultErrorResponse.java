package br.com.desafio.jokenpo.dto.response;

public class DefaultErrorResponse {

	private String message;
	private Integer code;

	public DefaultErrorResponse() {
		super();
	}

	public DefaultErrorResponse(String message, Integer code) {
		super();
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

}

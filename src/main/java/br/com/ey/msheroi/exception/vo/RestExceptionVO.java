package br.com.ey.msheroi.exception.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(content = Include.NON_NULL)
public class RestExceptionVO {

	private List<String> arrayMsg;
	private String mensagem;
	private int status;
	
	public RestExceptionVO(String mensagem, int status) {
		this.mensagem = mensagem;
		this.status = status;
	}
	
	public void addMsg(String msg) {
		if(CollectionUtils.isEmpty(arrayMsg)) {
			arrayMsg = new ArrayList<>();
		}
		arrayMsg.add(msg);
	}
}

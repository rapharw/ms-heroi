package br.com.ey.msheroi.common.vo;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Token implements Serializable {

    private String value;
}

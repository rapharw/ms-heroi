package br.com.ey.msheroi.common.enums;

import lombok.Getter;

@Getter
public enum TipoUniversoEnum {
    EY_COMICS(1),
    TRAINEE_COMICS(2),
    OUTROS(3);

    private Integer id;

    TipoUniversoEnum(int id) {
        this.id = id;
    }
}

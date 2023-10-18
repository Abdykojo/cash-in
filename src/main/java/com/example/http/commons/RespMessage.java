package com.example.http.commons;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public enum RespMessage {
    OK("200", "Пользователь найден"),
    FOR_PROCESSING("249", "Платеж находится в процессе обработки"),
    QID_IS_PRESENT("420", "Дублирование платежа"),
    NOT_MONEY ("103","Недостаточно средств для списания"),
    LARGE_SUM("202", "Сумма превышает остаток по кредиту, измените сумму оплаты"),
    COMMITTED("250","Платежное поручение успешно создано"),
    BAD_REQUEST("301", "Неправильные реквизиты"),
    CREDIT_LIMITS("310","Данные кредита не прошли проверку, измените срок или сумму"),
    USER_NOT_FOUND("302", "Клиент не найден"),
    SERVICE_NOT_RABOTAT("400", "Недокументированная ошибка"),

    FAILED("500","Платеж неуспешный"),
    NOT_AUTH("304","Авторизация не прошла"),
    GOOD("449","График успешно расчитан"),
    CARDS("178","Карта успешно создана"),
    NOT_REGISTER("303", "Регистраия не прошла"),
    CREDIT_GET("350","Кредит успешно получен");

    @XmlElement
    private String status;
    @XmlElement
    private String message;

}

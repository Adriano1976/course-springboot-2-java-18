package com.workshop.course.entities.enums;

/**
 * Enunciado responsável pelo controle do status da ordem de compra.
 */
public enum OrderStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    PACKING(3),
    SHIPPED(4),
    DELIVERED(5),
    CANCELED(6);

    private final int code;

    OrderStatus(int code) {
        this.code = code;
    }

    public static OrderStatus valueOf(int code) {

        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }

        throw new IllegalArgumentException("Invalid OrderStatus code");
    }

    public int getCode() {
        return code;
    }
}

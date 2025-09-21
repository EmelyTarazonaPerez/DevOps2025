package com.arka.cotizador.domain.service.impl;

import com.arka.cotizador.domain.service.CalculadoraPrecios;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceCalculatorImpl implements CalculadoraPrecios {

    private static final BigDecimal DESCUENTO_CLIENTE_REGULAR = BigDecimal.valueOf(0.05); // 5%
    private static final BigDecimal DESCUENTO_CLIENTE_VIP = BigDecimal.valueOf(0.10);    // 10%
    private static final BigDecimal IMPUESTO_IVA = BigDecimal.valueOf(0.19); // 19%


    @Override
    public BigDecimal calcularPrecioConDescuento(BigDecimal precioBase, Integer cantidad, String tipoCliente) {
        BigDecimal subtotal = precioBase.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal descuento = calcularDescuentoTotal(subtotal, tipoCliente);

        return subtotal.subtract(descuento).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcularImpuestos(BigDecimal subtotal) {
        return subtotal.multiply(IMPUESTO_IVA).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calcularDescuentoTotal(BigDecimal subtotal, String tipoCliente) {
        BigDecimal porcentajeDescuento = obtenerPorcentajeDescuento(tipoCliente);
        return subtotal.multiply(porcentajeDescuento).setScale(2, RoundingMode.HALF_UP);
    }
    private BigDecimal obtenerPorcentajeDescuento(String tipoCliente) {
        if ("VIP".equalsIgnoreCase(tipoCliente)) {
            return DESCUENTO_CLIENTE_VIP;
        }
        if ("REGULAR".equalsIgnoreCase(tipoCliente)) {
            return DESCUENTO_CLIENTE_REGULAR;
        }
        return BigDecimal.ZERO;
    }
}

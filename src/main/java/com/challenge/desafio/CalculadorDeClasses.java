package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

    @Override
    public BigDecimal somar(Object obj) {
        BigDecimal resultado = BigDecimal.ZERO;

        Field [] fields = obj.getClass().getDeclaredFields();

        for (Field field: fields){
            field.setAccessible(true);

            if (field.isAnnotationPresent(Somar.class) && field.getType().equals(BigDecimal.class)){
                try {
                    resultado = resultado.add((BigDecimal) field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultado;
    }

    @Override
    public BigDecimal subtrair(Object obj) {
        BigDecimal resultado = BigDecimal.ZERO;

        Field [] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields){
            field.setAccessible(true);
            if (field.isAnnotationPresent(Subtrair.class) && field.getType().equals(BigDecimal.class)){
                try {
                    resultado = resultado.add( (BigDecimal) field.get(obj));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultado;
    }

    @Override
    public BigDecimal totalizar(Object obj) {
        return somar(obj).subtract(subtrair(obj));
    }
}

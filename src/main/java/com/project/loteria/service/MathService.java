package com.project.loteria.service;

public class MathService {
    public static double factorial(double num){
        double result = 1;
        for (int i = 2; i <= num ; i++) {
            result *= i;
        }
        return result;
    }

    public static double combination(double n, double r){
        if (r > n){
            return 0;
        }
        double numerator = factorial(n);
        double denominator = factorial(r) * factorial(n - r);

        return numerator / denominator;
    }
}

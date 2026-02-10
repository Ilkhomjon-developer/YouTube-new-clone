package com.example.demo.util;

import java.util.Random;

public class RandomCodeGenerator {

    private RandomCodeGenerator() {
    }

    public static Integer generatedCode(){
        return new Random().nextInt(10000,99999);
    }
}

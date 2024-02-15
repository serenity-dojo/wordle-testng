package com.serenitydojo.model;

import com.github.javafaker.Faker;

public record Player(String name, String email, String password) {
    public static Player somePlayer() {
        return new Player(Faker.instance().name().username(),
                Faker.instance().internet().emailAddress(),
                "T0Ps3cr&t");
    }

}

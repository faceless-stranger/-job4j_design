package ru.job4j.assertj.io.duplicates;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class JSON {
    public static void main(String[] args) {
        StoreAuto storeAuto = new StoreAuto("Садовая", "Ростов");
        String[] telephone = {"+79888888", "+7999999"};
        Auto auto = new Auto("Skoda", false, 250, storeAuto, telephone);
        /* Преобразование объекта в JSON */
        final Gson gson = new GsonBuilder().create();
        String json = gson.toJson(auto);
        System.out.println("Преобразование объекта в JSON - " + json);
        /* Десериализация JSON в объект */
        final Auto autoBack = gson.fromJson(json, Auto.class);
        System.out.println("Десериализация JSON в объект - " + autoBack);
    }

    public static class Auto {
        private String brand;
        private boolean fourWheelDrive;
        private int power;
        private StoreAuto storeAuto;
        private String[] telephone;

        public Auto(String brand, boolean fourWheelDrive, int power, StoreAuto storeAuto, String[] telephone) {
            this.brand = brand;
            this.fourWheelDrive = fourWheelDrive;
            this.power = power;
            this.storeAuto = storeAuto;
            this.telephone = telephone;
        }

        @Override
        public String toString() {
            return "Auto{"
                    + "brand='" + brand + '\''
                    + ", FourWheelDrive=" + fourWheelDrive
                    + ", power=" + power
                    + ", storeAuto=" + storeAuto
                    + ", telephone=" + Arrays.toString(telephone)
                    + '}';
        }
    }

    public static class StoreAuto {
        private String street;
        private String city;

        public StoreAuto(String street, String city) {
            this.street = street;
            this.city = city;
        }
    }
}

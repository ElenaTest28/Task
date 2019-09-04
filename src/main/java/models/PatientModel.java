package models;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PatientModel {

    private String lastName;
    private String firstName;
    private String dayOfBitrh;
    private String monthOfBirth;
    private String yearOfBirth;
    private String country;


    public PatientModel generateRandomPatientModel() {
        Faker faker = new Faker();
        return this
                .setDayOfBitrh("1")
                .setMonthOfBirth("January")
                .setYearOfBirth("2000")
                .setFirstName(faker.name().firstName())
                .setLastName(faker.name().lastName())
                .setCountry(faker.country().name());
    }

}

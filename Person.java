package ExceptionsFinalTest;

class Person {
    private String surname;
    private String name;
    private String patronymic;
    private String dob;
    private long phoneNumber;
    private char gender;

    public Person(String surname, String name, String patronymic, String dob, long phoneNumber, char gender) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return surname + " " + name + " " + patronymic + " " + dob + " " + phoneNumber + " " + gender;
    }
}


package controller.mainMenu;

import controller.SingInMenu.SingInMenu;
import controller.registrationMenu.RegistrationMenu;
import verification.ValueVerification;

public class MainMenu {

    ValueVerification valueVerification = new ValueVerification();


    public void menu() {
        System.out.println("1) Войти");
        System.out.println("2) Зарегистрироваться");
        System.out.println("3) Выйти из программы");
        startMenu();
    }

    private void startMenu() {
        int i = valueVerification.checkInt();

        switch (i) {
            case 1: {
                SingInMenu singMenu = new SingInMenu();
                singMenu.menu();
            }
            case 2: {
                RegistrationMenu registrationMenu = new RegistrationMenu();
                registrationMenu.menu();
            }
            case 3: {
                System.out.println("Выход из программы ...");
                System.exit(0);
            }
            default: {
                System.out.println("Такого пункта в меню нет");
                menu();
            }
        }
    }
}

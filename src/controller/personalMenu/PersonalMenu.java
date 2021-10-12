package controller.personalMenu;

import controller.personalMenu.partsOfPersonal.CategoryMenu;
import controller.personalMenu.partsOfPersonal.TaskMenu;
import controller.personalMenu.partsOfPersonal.UserMenu;
import controller.mainMenu.MainMenu;
import verification.ValueVerification;

public class PersonalMenu {

    ValueVerification valueVerification = new ValueVerification();

    public void startMenu() {
        System.out.println("1) Личный кабинет");
        System.out.println("2) Категории");
        System.out.println("3) Задачи");
        System.out.println("4) Выйти из профиля");
        System.out.println("5) Выйти из программы");
        int i;
        do {
            i = valueVerification.checkInt();
        } while (i != 1 && i != 2 && i != 3 && i != 4 && i != 5);
        if (i == 1) {
            UserMenu userMenu = new UserMenu();
            userMenu.menu();
        }
        if (i == 2) {
            CategoryMenu categoryMenu = new CategoryMenu();
            categoryMenu.menu();
        }
        if (i == 3) {
            TaskMenu taskMenu = new TaskMenu();
            taskMenu.menu();
        }
        if (i == 4) {
            MainMenu mainMenu = new MainMenu();
            mainMenu.menu();
        }
        if (i == 5) {
            System.exit(0);
        }
    }
}


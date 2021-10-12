package controller.registrationMenu;

import controller.mainMenu.MainMenu;
import service.Imp.RegistrationServiceImp;

public class RegistrationMenu {

    RegistrationServiceImp registrationServiceImp = new RegistrationServiceImp();
    public void menu() {
        registrationServiceImp.createUser();
        MainMenu mainMenu = new MainMenu();
        mainMenu.menu();
    }
}

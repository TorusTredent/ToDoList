package controller.SingInMenu;

import controller.personalMenu.PersonalMenu;
import service.Imp.SingInServiceImp;

public class SingInMenu {

    SingInServiceImp sing = new SingInServiceImp();

    public void menu() {
        sing.singIn();
        PersonalMenu personalMenu = new PersonalMenu();
        personalMenu.startMenu();
    }
}

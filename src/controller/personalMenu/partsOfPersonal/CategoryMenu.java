package controller.personalMenu.partsOfPersonal;

import controller.personalMenu.PersonalMenu;
import service.Imp.CategoryServiceImp;
import verification.ValueVerification;

public class CategoryMenu {

    ValueVerification valueVerification = new ValueVerification();
    CategoryServiceImp category = new CategoryServiceImp();


    public void menu() {
        System.out.println("1) Добавить категорию");
        System.out.println("2) Изменить категорию");
        System.out.println("3) Показать определенную категорию");
        System.out.println("4) Показать категории");
        System.out.println("5) Удалить");
        System.out.println("6) Выйти в основное меню");
        System.out.println("7) Выход из программы");
        selectCategoryMenu();
    }

    private void selectCategoryMenu() {
        int i = valueVerification.checkInt();

        switch (i) {
            case 1: {
                category.addCategory();
                menu();
            }
            case 2: {
                category.updateCategoryByName();
                menu();
            }
            case 3: {
                category.getCategoryByName();
                menu();
            }
            case 4: {
                category.getAllCategories();
                menu();
            }
            case 5: {
                System.out.println("===========================");
                System.out.println("1) Удалить определенную категорию");
                System.out.println("2) Удалить все категории");
                removeCategory();
            }
            case 6: {
                PersonalMenu personalMenu = new PersonalMenu();
                personalMenu.startMenu();
            }
            case 7: {
                System.out.println("Выход из программы ...");
                System.exit(0);
            }
            default: {
                System.out.println("Такой пункт в меню отсутсвует! ");
                menu();
            }
        }
    }



    private void removeCategory() {
        int i = valueVerification.checkInt();

        switch (i) {
            case 1: {
                category.removeCategoryByName();
                menu();
            }
            case 2: {
                category.removeAllCategories();
                menu();
            }
            default: {
                System.out.println("Такой пункт в меню отсутсвует! ");
                menu();
            }
        }
    }
}

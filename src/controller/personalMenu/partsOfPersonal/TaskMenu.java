package controller.personalMenu.partsOfPersonal;

import controller.personalMenu.PersonalMenu;
import service.Imp.TaskServiceImp;
import verification.ValueVerification;

public class TaskMenu {

    TaskServiceImp task = new TaskServiceImp();
    ValueVerification valueVerification = new ValueVerification();

    public void menu() {
        System.out.println("1) Добавить задачу");
        System.out.println("2) Изменить задачу");
        System.out.println("3) Показать определенную задачу");
        System.out.println("4) Показать все задачи");
        System.out.println("5) Удалить");
        System.out.println("6) Выйти в основное меню");
        System.out.println("7) Выход из программы");
        selectTaskMenu();
    }

    private void selectTaskMenu() {
        int i = valueVerification.checkInt();

        switch (i) {
            case 1: {
                task.addTask();
                menu();
            }
            case 2: {
                System.out.println("===========================");
                System.out.println("1) Изменить полностью задачу");
                System.out.println("2) Изменить статус задачи");
                System.out.println("3) Изменить категорию задачи");
                updateTask();
            }
            case 3: {
                task.getTaskByName();
                menu();
            }
            case 4: {
                task.getAllTasks();
                menu();

            }
            case 5: {
                System.out.println("===========================");
                System.out.println("1) Удалить определенную задачу");
                System.out.println("2) Удалить все задачи");
                removeTask();
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

    private void updateTask() {
        int i = valueVerification.checkInt();

        switch (i) {
            case 1: {
                task.updateTaskByName();
                menu();
            }
            case 2: {
                task.updateTaskStatusByName();
                menu();
            }
            case 3: {
                task.updateTaskCategoryByName();
                menu();
            }
            default: {
                System.out.println("Такой пункт в меню отсутсвует! ");
                menu();
            }
        }
    }

    private void removeTask( ) {
        int i = valueVerification.checkInt();

        switch (i) {
            case 1: {
                task.removeTaskByName();
                menu();
            }
            case 2: {
                task.removeAllTasks();
                menu();
            }
            default: {
                System.out.println("Такой пункт в меню отсутсвует! ");
                menu();
            }
        }
    }
}

package ru.edu.java.tasks;

public class EmployeeImpl implements Employee {


    private String firsname;
    private String lastname;
    private int salary;

    private Employee manager;

    public EmployeeImpl(){
        this.firsname = "undefine";
        this.lastname = "undefine";
        this.salary = 1000;

        this.manager = null;

    }

    public EmployeeImpl(String firsname, String lastname, int salary) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.salary = salary;
    }

    public EmployeeImpl(String firsname, String lastname, int salary, Employee manager) {
        this.firsname = firsname;
        this.lastname = lastname;
        this.salary = salary;
        this.manager = manager;
    }

    /**
     * @return Зарплата сотрудника на настоящий момент.
     */
    @Override
    public int getSalary() {
        return this.salary;
    }

    /**
     * Увеличивает зарплату сотрудника на заданное значение
     *
     * @param value Значение, на которое нужно увеличить
     */
    @Override
    public void increaseSalary(int value) {
        this.salary += value;
    }

    /**
     * @return Имя сотрудника
     */
    @Override
    public String getFirstName() {
        return this.firsname;
    }

    /**
     * Устанавливает имя сотрудника
     *
     * @param firstName Новое имя
     */
    @Override
    public void setFirstName(String firstName) {
        this.firsname = firstName;
    }

    /**
     * @return Фамилия сотрудника
     */
    @Override
    public String getLastName() {
        return lastname;
    }

    /**
     * Устанавливает фамилию сотрудника
     *
     * @param lastName Новая фамилия
     */
    @Override
    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    /**
     * @return Имя и затем фамилия сотрудника, разделенные символом " " (пробел)
     */
    @Override
    public String getFullName() {
        return this.firsname + " " + this.lastname;
    }

    /**
     * Устанавливает Менеджера сотрудника.
     *
     * @param manager Сотрудник, являющийся менеджером данного сотрудника.
     *                НЕ следует предполагать, что менеджер является экземпляром класса EmployeeImpl.
     */
    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    /**
     * @return Имя и фамилия Менеджера, разделенные символом " " (пробел).
     * Если Менеджер не задан, возвращает строку "No manager".
     */
    @Override
    public String getManagerName() {

        if(this.manager == null)
            return "No manager";
        return this.manager.getFullName();
    }

    /**
     * Возвращает Менеджера верхнего уровня, т.е. вершину иерархии сотрудников,
     * в которую входит данный сотрудник.
     * Если над данным сотрудником нет ни одного менеджера, возвращает данного сотрудника.
     * Замечание: поскольку менеджер, установленный методом {@link #setManager(Employee)},
     * может быть экзепляром другого класса, при поиске топ-менеджера нельзя обращаться
     * к полям класса EmployeeImpl. Более того, поскольку в интерфейсе Employee не объявлено
     * метода getManager(), поиск топ-менеджера невозможно организовать в виде цикла.
     * Вместо этого нужно использовать рекурсию (и это "более объектно-ориентированно").
     */
    @Override
    public Employee getTopManager() {

        if(this.manager == null)
            return this;

        return this.manager.getTopManager();

    }
}

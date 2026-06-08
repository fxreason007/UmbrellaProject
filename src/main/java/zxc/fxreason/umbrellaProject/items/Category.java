package zxc.fxreason.umbrellaProject.items;

public enum Category {
    MEDICINE("Медицина"),
    FOOD("Еда"),
    SURVIVAL("Выживание"),
    ELECTRONICS("Электроника"),
    GUNS("Оружия"),
    CHEMICAL("Химия"),
    REAGENTS("Реагенты"),
    EQUIPMENT("Снаряжение");

    private String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

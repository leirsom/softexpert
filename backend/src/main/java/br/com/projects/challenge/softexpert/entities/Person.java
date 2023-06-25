package br.com.projects.challenge.softexpert.entities;

import java.util.ArrayList;
import java.util.List;

public class Person {

    private String name;
    private List<Item> items;

    public Person() {
        this.items = new ArrayList<>();
    }

    public Person(String name, List<Item> items) {
        this.name = name;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }
}
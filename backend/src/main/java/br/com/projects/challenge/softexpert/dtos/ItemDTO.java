package br.com.projects.challenge.softexpert.dtos;


import br.com.projects.challenge.softexpert.entities.Item;

public class ItemDTO {

    private String name;
    private double price;

    public ItemDTO() {
    }

    public ItemDTO(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public ItemDTO(Item item){
        this.name = item.getName();
        this.price = item.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

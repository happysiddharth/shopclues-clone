package com.example.shopclues_clone.models;

public class ProductResponse {

        private String image;
        private Object price;
        private String description;
        private int id;
        private String title;
        private String category;

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage(){
            return image;
        }

        public Object getPrice(){
            return price;
        }

        public String getDescription(){
            return description;
        }

        public int getId(){
            return id;
        }

        public String getTitle(){
            return title;
        }

        public String getCategory(){
            return category;
        }
}

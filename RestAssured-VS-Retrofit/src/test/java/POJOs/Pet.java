package POJOs;

import java.util.List;

public class Pet {
    private Integer id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;

    // getters
    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public static class Category{
        private Integer id;
        private String name;

        public Category(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        // getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        //setters
        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public class Tag{
        private Integer id;
        private String name;

        public Tag(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        // getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        // setters
        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

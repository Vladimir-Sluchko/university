package dto;

public class GroupWhithOutStudent {
    private Integer id;
    private String name;

    public GroupWhithOutStudent() {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupWhithOutStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

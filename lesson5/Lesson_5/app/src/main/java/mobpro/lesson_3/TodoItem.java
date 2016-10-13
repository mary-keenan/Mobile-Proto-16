package mobpro.lesson_3;

/**
 * Created by mary on 9/18/16.
 */
public class TodoItem {
    private String name; //text in to do item
    // Great use of an ID in TodoItem to be able to access it in SQL later!
    private Integer id; //every to do has an id we use to edit/delete it with later

    public TodoItem(String name, Integer id) {
        // Put some spaces here
        this.name = name;
        this.id = id;
    }

    //getters and setters for attributes
    public long getId() {
        return this.id;
    }

    public void setId(long ID){
        this.id = (int) ID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String new_name){
        this.name = new_name;
    }

}

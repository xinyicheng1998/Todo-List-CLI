package Model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Todo is a class that keeps track of the information of a Todo, with the information provided. And
 * builder pattern is applied.
 */
public class Todo {

  /** id of this todo */
  private Integer id;
  /** a description of the task to be done */
  private String text;
  /** whether the task is completed or incomplete. */
  private Boolean completed;
  /** a due date */
  private LocalDate due;
  /** the priority of the todo */
  private Integer priority;
  /** a user-specified String that can be used to group related todos */
  private String category;

  /** The field id should be generated in CRUD.addNewRow() */
  private static final Integer DEFAULT_ID = null;
  /** If not specified, the field completed should be false by default. */
  private static final Boolean DEFAULT_COMPLETED = false;
  /** If not specified, the field due should be null by default. */
  private static final LocalDate DEFAULT_DUE = null;
  /** The lower bound of the priority */
  private static final Integer PRIORITY_HIGHEST = 1;
  /** The upper bound of the priority */
  private static final Integer DEFAULT_PRIORITY = 3;
  /** If not specified, the field category should be null by default. */
  private static final String DEFAULT_CATEGORY = null;

  /**
   * Constructor, creating a new Todo object, by the information provided by builder pattern.
   * @param builder - the information provider, encoded as Builder
   */
  private Todo(Builder builder) {
    this.id = builder.id;
    this.text = builder.text;
    this.completed = builder.completed;
    this.due = builder.due;
    this.priority = builder.priority;
    this.category = builder.category;
  }

  /**
   * Get the id of this Todo
   * @return the id of this Todo, encoded as Integer
   */
  public Integer getId() {
    return id;
  }

  /**
   * Set the id of this Todo to the given id
   * @param id the given id, encoded as Integer
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Get the text content of this Todo
   * @return the text content of this Todo, encoded as String
   */
  public String getText() {
    return text;
  }

  /**
   * Get the state of the completed of this Todo
   * @return the state of the completed of this Todo, encoded as Boolean
   */
  public Boolean getCompleted() {
    return completed;
  }

  /**
   * Set the state of the completed of this Todo to true
   */
  public void setCompleted() {
    this.completed = true;
  }

  /**
   * Get the due date of this Todo
   * @return the due date of this Todo, encoded as LocalDate
   */
  public LocalDate getDue() {
    return due;
  }

  /**
   * Get the priority of this Todo
   * @return the priority of this Todo, encoded as Integer
   */
  public Integer getPriority() {
    return priority;
  }

  /**
   * Get the category of this Todo
   * @return the category of this Todo, encoded as String
   */
  public String getCategory() {
    return category;
  }

  /**
   * Check if this object is equal to the given object
   * @param o - the given object
   * @return true if this object is equal to the given object, and false otherwise
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Todo)) {
      return false;
    }
    Todo todo = (Todo) o;
    return Objects.equals(id, todo.id) && Objects.equals(text, todo.text)
        && Objects.equals(completed, todo.completed) && Objects.equals(due,
        todo.due) && Objects.equals(priority, todo.priority) && Objects.equals(
        category, todo.category);
  }

  /**
   * Get the hashcode of this object
   * @return the hashcode of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, text, completed, due, priority, category);
  }

  /**
   * Get the string of the object
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "Model.Todo{" +
        "id=" + id +
        ", text='" + text + '\'' +
        ", completed=" + completed +
        ", due=" + due +
        ", priority=" + priority +
        ", category='" + category + '\'' +
        '}';
  }

  /**
   * Builder is a static class that keeps track of the information of a Todo to prevent endless
   * overloading constructor. The only required parameter is text. Other fields are optional.
   */
  public static class Builder {

    /** id of this todo */
    private Integer id;
    /** a description of the task to be done */
    private String text;
    /** whether the task is completed or incomplete. */
    private Boolean completed;
    /** a due date */
    private LocalDate due;
    /** the priority of the todo */
    private Integer priority;
    /** a user-specified String that can be used to group related todos */
    private String category;

    /**
     * Constructor, creating a new Builder of Todo. When a builder of Todo is initialized, the only
     * required parameter is text, and other fields have corresponding values.
     * @param text - the required parameter that represents a description of the task to be done,
     *             encoded as String
     * @throws InvalidTodoException when the Builder cannot be initialized correctly
     */
    public Builder(String text) throws InvalidTodoException {
      this.id = DEFAULT_ID;
      this.text = text;
      this.completed = DEFAULT_COMPLETED;
      this.due = DEFAULT_DUE;
      this.priority = DEFAULT_PRIORITY;
      this.category = DEFAULT_CATEGORY;
    }

    /**
     * When the id should be initialized synchronously with the required field, this method would
     * be used.
     * @param id - the id should be assigned to the Todo, encoded as Integer.
     * @return the Builder with the given id
     */
    public Builder addID(Integer id) {
      this.id = id;
      return this;
    }

    /**
     * When the completed should be initialized synchronously with the required field, this method
     * would be used.
     * @param completed - the state of the completed should be assigned to the Todo, encoded as
     *                 Boolean.
     * @return the Builder with the given state of completed
     */
    public Builder addCompleted(Boolean completed) {
      if (completed == null) this.completed = DEFAULT_COMPLETED;
      else this.completed = completed;
      return this;
    }

    /**
     * When the due should be initialized synchronously with the required field, this method would
     * be used.
     * @param due - the due should be assigned to the Todo, encoded as LocalDate
     * @return the Builder with the given due
     */
    public Builder addDue(LocalDate due) {
      this.due = due;
      return this;
    }

    /**
     * When the priority should be initialized synchronously with the required field, this method
     * would be used.
     * @param priority - the priority should be assigned to the Todo, encoded as Integer
     * @return the Builder with the given priority
     * @throws InvalidTodoException when the given priority is not valid
     */
    public Builder addPriority(Integer priority) throws InvalidTodoException {
      this.priority = isValidPriority(priority);
      return this;
    }

    /**
     * If the user chooses to specify a priority, it must be between 1 and 3, with 1 being the
     * highest priority. If no priority is specified, the todo can be treated as lowest priority
     * (i.e., 3).
     * @param priority - the given priority, encoded as Integer
     * @return the priority's value that meets the requirements
     * @throws InvalidTodoException when the given priority is not valid
     */
    private Integer isValidPriority(Integer priority) throws InvalidTodoException {
      if (priority == null) {
        return DEFAULT_PRIORITY;
      }
      if (priority >= PRIORITY_HIGHEST && priority <= DEFAULT_PRIORITY) {
        return priority;
      }
      throw new InvalidTodoException("The priority must be between 1 and 3");
    }

    /**
     * When the category should be initialized synchronously with the required field, this method
     * would be used.
     * @param category the category should be assigned to the Todo, encoded as String
     * @return the Builder with the given category
     */
    public Builder addCategory(String category) {
      this.category = category;
      return this;
    }

    /**
     * The method is used to build the Todo with the given Builder object's fields.
     * @return a Todo with the given Builder object's fields.
     */
    public Todo build() {
      return new Todo(this);
    }
  }
}

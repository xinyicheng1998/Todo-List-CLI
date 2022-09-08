package Controller;


import java.util.Objects;

/**
 * Class DisplayTodo represents the information of one "display to do" command
 */
public class DisplayTodo {
  private Boolean isDisplay;
  private Boolean isShowIncomplete;
  private String showCategory;
  private Boolean isSortByDate;
  private Boolean isSortByPriority;

  /**
   * Constructs a DisplayTodo.
   * @param isDisplay - whether the display command is asked, as Boolean.
   * @param isShowIncomplete - whether the show incomplete command is asked, as Boolean.
   * @param showCategory - whether the show category command is asked, as String.
   * @param isSortByDate - whether the sort by date command is asked, as Boolean.
   * @param isSortByPriority - whether the sort by priority command is asked, as Boolean.
   */
  public DisplayTodo(Boolean isDisplay, Boolean isShowIncomplete, String showCategory, Boolean isSortByDate,
      Boolean isSortByPriority) {
    this.isDisplay = isDisplay;
    this.isShowIncomplete = isShowIncomplete;
    this.showCategory = showCategory;
    this.isSortByDate = isSortByDate;
    this.isSortByPriority = isSortByPriority;
  }

  /**
   * Getter for isDisplay.
   * @return - the value of isDisplay, as Boolean.
   */
  public Boolean getDisplay() {
    return isDisplay;
  }

  /**
   * Getter for isShowIncomplete.
   * @return - the value of isShowIncomplete, as Boolean.
   */
  public Boolean getShowIncomplete() {
    return isShowIncomplete;
  }

  /**
   * Getter for showCategory.
   * @return - the value of showCategory, as String.
   */
  public String getShowCategory() {
    return showCategory;
  }

  /**
   * Getter for isSortByDate.
   * @return - the value of isSortByDate, as Boolean.
   */
  public Boolean getSortByDate() {
    return isSortByDate;
  }

  /**
   * Getter for isSortByPriority.
   * @return - the value of isSortByPriority, as Boolean.
   */
  public Boolean getSortByPriority() {
    return isSortByPriority;
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
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DisplayTodo that = (DisplayTodo) o;
    return Objects.equals(isDisplay, that.isDisplay) && Objects.equals(
        isShowIncomplete, that.isShowIncomplete) && Objects.equals(showCategory,
        that.showCategory) && Objects.equals(isSortByDate, that.isSortByDate)
        && Objects.equals(isSortByPriority, that.isSortByPriority);
  }

  /**
   * Get the hashcode of this object
   * @return the hashcode of this object
   */
  @Override
  public int hashCode() {
    return Objects.hash(isDisplay, isShowIncomplete, showCategory, isSortByDate, isSortByPriority);
  }

  /**
   * Get the string of the object
   * @return the string of the object
   */
  @Override
  public String toString() {
    return "DisplayTodo{" +
        "isDisplay=" + isDisplay +
        ", isShowIncomplete=" + isShowIncomplete +
        ", showCategory='" + showCategory + '\'' +
        ", isSortByDate=" + isSortByDate +
        ", isSortByPriority=" + isSortByPriority +
        '}';
  }
}

TODO Application
This project builds a command-line todo application that allows users to add and track the status of todos in a CSV file. The CSV file is a plain text file, containing data organized into columns separated by a comma. The first line of the file contains the headers for each column, which represent the information of each todo: text, completed, due, priority and category. 


Functionality:
* Add a new todo: updating the csv file with a new todo provided by the user.
* Complete an existing todo: updating the completed status of one or multiple existing todos.
* Display todos: displaying existing todos based on the provided format from the user, including incomplete filter, category filter, sorting by date, and sorting by priority.


Structure:
* MVC Architecture Pattern
   * Model:  model package that loads and stores data.
   * View:  view package that presents data in a user-friendly format.
   * Controller:  controller package that interprets user actions.
* Builder Pattern
   * Location: Todo class in Model package
   * Reason: To separate the information of a new todo from the user and build the new todo object more easily with specific spaces.
* Singleton Pattern
   * Location: FileState in FileIO package
   * Reason: Implements singleton pattern by eager initialization and ensures thread-safety.
* Functional Programming
   * Location: CriteriaCategory and CriteriaPriority in View Package
   * Reason: To filter class using stream operations.
* Filter Pattern
   * Location:  ICriteria Interface in View package
   * Reason:  To filter a set of objects using different criteria and chaining them in a decoupled way through logical operations.
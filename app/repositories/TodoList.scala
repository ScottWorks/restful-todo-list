package repositories

import models.{Todo}
import javax.inject.{Singleton}

@Singleton
class TodoList {
  private var todoList = List(
    Todo(5, 1552004460, "Go to grocery store", false),
    Todo(4, 1552004330, "Reply to emails from Danielle", true),
    Todo(3, 1552004660, "Call Michael, RE: the thing", false),
    Todo(2, 1552004450, "Fix bike tire flat", false),
    Todo(1, 1552003460, "Study for exam", true)
  )

  def getAllTodos: List[Todo] = {
    todoList
  }

  def getTodos(id: Int): Option[Todo] = {
    todoList.collectFirst({
      case t if t.id == id => t
    })
  }

  def addTodo(content: String): List[Todo] = {
    val id = todoList.length + 1
    val timestamp: Long = System.currentTimeMillis / 1000

    todoList = Todo(id, timestamp, content, false) +: todoList
    todoList
  }

  def updateTodo(id: Int, content: String): Option[List[Todo]] = {
    if(id <= todoList.length) {
      todoList = todoList.map(todo => {
        if(todo.id == id) {
          todo.copy(content = content)
        } else {
          todo
        }
      })

      Some(todoList)
    } else {
      None
    }
  }

  def removeTodo(id: Int): Option[List[Todo]] = {
    if(id <= todoList.length){
      todoList = todoList.filter(todo => todo.id != id).map(todo => {
        if(todo.id > id) {
          todo.copy(id = todo.id - 1)
        } else {
          todo
        }
      })

      Some(todoList)
    } else {
      None
    }
  }
}
import React, {useState, useEffect} from 'react';
import TodoList from './TodoList'
import Form from './Form'
const TodoListForm = (props) => {
    const [inputText, setInputText] = useState("");
    const [todos, setTodos] = useState([]);
    const [status, setStatus] = useState("all")
    const [filteredTodos, setFilteredTodos] = useState([])
    useEffect(()=>{
        console.log("First")
        // getLocalTodos()
      }, [])

    useEffect(() => {
        filterHandler()
        // saveLocalTodos()
      }, [todos,status])

    const saveLocalTodos = () => {
        if(todos.length > 0)
          localStorage.setItem("todos", JSON.stringify(todos))
    }

    const getLocalTodos = () => {
      if (localStorage.getItem("todos") === null){
        localStorage.setItem("todos", JSON.stringify([]))
      }
      else{
        let todoLocal = JSON.parse(localStorage.getItem("todos"))
        setTodos(todoLocal)
      }
    }
    const deleteHandler = () => {
      console.log(props.todoLists)
      props.setTodoLists(props.todoLists.filter(el => el.id !== props.todoList.id))
  }
    const filterHandler = () => {
        switch(status){
          case 'completed':
            setFilteredTodos(todos.filter(todo => todo.completed))
            break;
          case 'uncompleted':
            setFilteredTodos(todos.filter(todo => !todo.completed))
            break;
          default:
            setFilteredTodos(todos)
            break;
        }
      }
    return(
        <div style={{border:"2px solid black", borderRadius:"50px"}}>
        <h2 style={{marginTop:"0.5rem",marginBottom:"0.5rem"}} className='formClass'>{props.name}<button onClick={deleteHandler} style={{marginLeft:"0.5rem"}} className="trash-btn"><i className="fas fa-trash"></i></button></h2>        <Form setStatus={setStatus} todos={todos} setTodos={setTodos} setInputText={setInputText} inputText={inputText} className="todoForm"/>
        <TodoList filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}/>
        </div>
    )
}
export default TodoListForm
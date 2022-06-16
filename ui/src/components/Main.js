import React, { useState, useEffect } from 'react'
import { Navigate } from 'react-router-dom'
import Form from './Form';
import TodoList from './TodoList'
import TodoListForm from './TodoListForm'
import FormList from './FormList'
const Main = () => {
  const [inputText, setInputText] = useState("");
  const [todoLists, setTodoLists] = useState([])
   
  const isAuth = localStorage.getItem("auth");
    return (
        <>
       <header>Microservices Todo List</header>
        <FormList setTodoLists={setTodoLists} todoLists={todoLists} setInputText={setInputText} inputText={inputText}/>
       <div className="container-class">
        {todoLists.map(todoList => (
                <TodoListForm name={todoList.name}/>
            ))}
       </div>
        
        </>
    )
}
export default Main;
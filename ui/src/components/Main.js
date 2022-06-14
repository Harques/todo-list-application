import React, { useState, useEffect } from 'react'
import { Navigate } from 'react-router-dom'
import Form from './Form';
import TodoList from './TodoList'
import TodoListForm from './TodoListForm'
const Main = () => {
  const [inputText, setInputText] = useState("");
  const [todos, setTodos] = useState([]);
  const [status, setStatus] = useState("all")
  const [filteredTodos, setFilteredTodos] = useState([])

  useEffect(()=>{
    console.log("First")
    getLocalTodos()
  }, [])

  useEffect(() => {
    filterHandler()
    saveLocalTodos()
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
   
  const isAuth = localStorage.getItem("auth");
    return (
        <>
       <header>Microservices Todo List</header>
       <div className="container-class">
       <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        <TodoListForm setStatus={setStatus}setInputText={setInputText} inputText={inputText} filteredTodos={filteredTodos} setTodos={setTodos} todos={todos}></TodoListForm>
        
       </div>
        
        </>
    )
}
export default Main;
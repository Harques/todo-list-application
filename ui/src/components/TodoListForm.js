import React from 'react';
import TodoList from './TodoList'
import Form from './Form'
const TodoListForm = (props) => {
    return(
        <div style={{border:"2px solid black", borderRadius:"50px"}}>
        <Form setStatus={props.setStatus} todos={props.todos} setTodos={props.setTodos} setInputText={props.setInputText} inputText={props.inputText} className="todoForm"/>
        <TodoList filteredTodos={props.filteredTodos} setTodos={props.setTodos} todos={props.todos}/>
        </div>
    )
}
export default TodoListForm
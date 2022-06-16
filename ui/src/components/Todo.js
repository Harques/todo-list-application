import React, {useEffect} from "react";
import $ from 'jquery'
import 'jquery-ui-dist/jquery-ui';
import DatePicker from 'react-datepicker'

const Todo = (props) => {

    useEffect(() => {
        $("#date").datepicker()
    }, [])
    const deleteHandler = () => {
        props.setTodos(props.todos.filter(el => el.id !== props.todo.id))
    }
    const completeHandler = () => {
        props.setTodos(props.todos.map(item => {
            if(item.id === props.todo.id){
                return {
                    ...item, completed: !item.completed
                }
            }
            return item;
        }))
    }
    return(
        <div className="todo">
            <li className={`todo-item ${props.todo.completed ? "completed" : ""}`}>{props.text}</li>
            <button onClick={completeHandler} className={props.todo.completed? "uncomplete-btn" :"complete-btn"}><i className={`fas ${props.todo.completed? "fa-times" : "fa-check"}`}></i></button>
            <button id="date" className="calendar-btn"><i className="fas fa-calendar"></i></button>
            <button onClick={deleteHandler} className="trash-btn"><i className="fas fa-trash"></i></button>

        
        </div>
    );

}

export default Todo;

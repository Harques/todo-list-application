import React, {useEffect} from "react";
import $ from 'jquery'
import 'jquery-ui-dist/jquery-ui';
import DatePicker from 'react-datepicker'

const Todo = (props) => {

    useEffect(() => {
        $("#date").datepicker({
            minDate:0,
            dateFormat:"dd-mm-yy",
            showButtonPanel: true,
            closeText: "Clear/Cancel",
            onClose: function(dateText, obj ){
                if ($(window.event.srcElement).hasClass('ui-datepicker-close'))
                $("#date").val('\uf133');
            }
            
        })
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
            <input value="&#xf133;" type="button" id="date" className="calendar-btn"></input>
            <button onClick={deleteHandler} className="trash-btn"><i className="fas fa-trash"></i></button>

        
        </div>
    );

}

export default Todo;

import React, {useEffect, useState} from "react";
import $ from 'jquery'
import 'jquery-ui-dist/jquery-ui';
import DatePicker from 'react-datepicker'

const Todo = (props) => {
    const [inputDate, setInputDate] = useState("\uf133")


    useEffect(() => {
        $("#date"+(Math.floor(props.todo.id).toString())).datepicker({
            minDate:0,
            dateFormat:"dd-mm-yy",
            showButtonPanel: true,
            closeText: "Clear/Cancel",
            onClose: function(dateText, obj ){
                if ($(window.event.srcElement).hasClass('ui-datepicker-close')){
                    $("#date"+(Math.floor(props.todo.id)).toString()).val('\uf133');
                    inputDateDeleteHandler($("#date"+(Math.floor(props.todo.id)).toString()).val())
                }
                else{
                    inputDateAddHandler($("#date"+(Math.floor(props.todo.id)).toString()).val())
                }
            }
            
        })
    }, [])

    const inputDateAddHandler = (e) => {
        setInputDate(e);
        props.setTodos(props.todos.map(item => {
            if(item.id === props.todo.id){
                return {
                    ...item, date : e
                }
            }
            return item;

        }))

    }
    const inputDateDeleteHandler = (e) => {
        setInputDate(e);
        props.setTodos(props.todos.map(item => {
            if(item.id === props.todo.id){
                return {
                    ...item, date : ""
                }
            }
            return item;

        }))

    }

    const deleteHandler = () => {
        fetch('http://localhost:9502/delete/', {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + localStorage.getItem("token"),
            'Content-Type': 'application/json'
          }),
        body: JSON.stringify({userEmail: JSON.parse(localStorage.getItem("userEmail")), id: props.todo.id, toDoListid: props.toDoList.id}) 
    }).then(response => {
        let ok = true
        if(response.status !== 200){
            ok = false
            alert("An error occured while deleting the todo.")
        }
        if(ok === true){
            props.setTodos(props.todos.filter(el => el.id !== props.todo.id))
        }
    }).catch(function(){    

    });

        
  }
    const completeHandler = () => {
        fetch('http://localhost:9502/complete/', {
        method: 'POST',
        headers: new Headers({
            'Authorization': 'Bearer ' + localStorage.getItem("token"),
            'Content-Type': 'application/json'
          }),
        body: JSON.stringify({userEmail: JSON.parse(localStorage.getItem("userEmail")), id: props.todo.id, toDoListid: props.toDoList.id}) 
    }).then(response => {
        let ok = true
        if(response.status !== 200){
            ok = false
            alert("An error occured while completing/uncompleting the todo.")
        }
        if(ok === true){
            props.setTodos(props.todos.map(item => {
                if(item.id === props.todo.id){
                    return {
                        ...item, completed: !item.completed
                    }
                }
                return item;
            }))
        }
    }).catch(function(){    

    });
    }
    const dateHandler = () => {
        console.log("Something in the way")

    }
    return(
        <div className="todo">
            <li className={`todo-item ${props.todo.completed ? "completed" : ""}`}>{props.text}</li>
            <button onClick={completeHandler} className={props.todo.completed? "uncomplete-btn" :"complete-btn"}><i className={`fas ${props.todo.completed? "fa-times" : "fa-check"}`}></i></button>
            <input value={inputDate} type="button" id={`date${Math.floor(props.todo.id).toString()}`} className="dans calendar-btn"></input>
            <button onClick={deleteHandler} className="trash-btn"><i className="fas fa-trash"></i></button>
        </div>
    );

}

export default Todo;

import React from 'react'


const Form = (props) => {
    const inputTextHandler = (e) => {
        props.setInputText(e.target.value);
    }
    const submitTodoHandler = (e) => {
        e.preventDefault();
        if(props.inputText === ""){
          alert("Invalid todo name.")
          return
      }
        props.setTodos([
            ...props.todos, {
                text: props.inputText, completed: false, id: Math.random() * 1000
            }
        ])
        props.setInputText("");
    }
    const statusHandler = (e) => {
        props.setStatus(e.target.value)
    }
    return(
<form style={{marginTop:"0"}} className='formClass'>
      <input style={{marginLeft:"1rem"}} value={props.inputText} onChange={inputTextHandler} type="text" className="todo-input formInput" />
      <button onClick={submitTodoHandler} className="todo-button formButton" type="submit">
        <i className="fas fa-plus-square"></i>
      </button>
      <div className="select">
        <select onChange={statusHandler} name="todos" className="filter-todo">
          <option value="all">All</option>
          <option value="completed">Completed</option>
          <option value="uncompleted">Uncompleted</option>
        </select>
      </div>
    </form>
    )
}
export default Form;
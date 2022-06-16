import React from 'react'


const FormList = (props) => {
    const inputTextHandler = (e) => {
        props.setInputText(e.target.value);
    }
    const submitListHandler = (e) => {
        e.preventDefault();
        props.setTodoLists([
            ...props.todoLists, {
                name: props.inputText,toDos:[],id: Math.random() * 1000
            }
        ])
        props.setInputText("");
    }
    return(
<form className='formClass'>
      <input style={{marginLeft:"1rem"}} value={props.inputText} onChange={inputTextHandler} type="text" className="todo-input formInput" />
      <button onClick={submitListHandler} className="todo-button formButton" type="submit">
        <i className="fas fa-plus-square"></i>
      </button>
    </form>
    )
}
export default FormList;
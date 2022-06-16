import React from 'react'


const FormList = (props) => {
    const inputTextHandler = (e) => {
        props.setInputText(e.target.value);
    }
    const submitListHandler = (e) => {
        e.preventDefault();
        if(props.inputText === ""){
            alert("Invalid todo list name.")
            return
        }
        props.setTodoLists([
            ...props.todoLists, {
                name: props.inputText, toDos:[], id: Math.random() * 1000
            }
        ])
        props.setInputText("");
    }
    return(
        <>        
                <h1 className="formClass" style={{minHeight:"0", marginBottom:"0",marginTop:"1.75rem", paddingLeft:"0.75rem"}}>Todo List Name</h1>
        <form style={{marginTop:"0.50rem", transform:"scale(1.25)"}} className='formClass'>
                <input style={{marginLeft:"1rem",flexDirection:"column"}} value={props.inputText} onChange={inputTextHandler} type="text" className="todo-input formInput" name="todoList" />
        <button onClick={submitListHandler} className="todo-button formButton" type="submit">
  <i className="fas fa-plus-square"></i>
</button>
</form>
        </>
    )
}
export default FormList;
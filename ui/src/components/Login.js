import React, { Component, useContext } from 'react'
import {Context} from './Context'
export default class Login extends Component {
  
  constructor() {
    super();
    this.handleSubmit = this.handleSubmit.bind(this);
    
  }

  handleSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    
    fetch('http://localhost:9501/login', {
      method: 'POST',
      body: data,
    }).then(response => {
        if(response.status == 200){
            this.context.setAuth = true;
            console.log(context.auth)
            //window.location.href = '/deneme'

        }
        throw new Error(response.status)
    }).catch(function(){
        document.getElementById("error").style.display = "block"
    });
  }
  render() {
    const context = useContext(Context);
    return (
      <form onSubmit={this.handleSubmit}>
        <h3>Sign In</h3>
        <div className="mb-3">
          <label>Email address</label>
          <input
            id="email"
            name="email"
            type="email"
            className="form-control"
            placeholder="Enter email"
          />
        </div>
        <div className="mb-3">
          <label>Password</label>
          <input
            id="password"
            name="password"
            type="password"
            className="form-control"
            placeholder="Enter password"
          />
        </div>

        <div className="d-grid">
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
      </form>
    )
  }
}

// const rmCheck = document.getElementById("rememberMe"),
//     emailInput = document.getElementById("email");

// if (localStorage.checkbox && localStorage.checkbox !== "") {
//   rmCheck.setAttribute("checked", "checked");
//   emailInput.value = localStorage.username;
// } else {
//   rmCheck.removeAttribute("checked");
//   emailInput.value = "";
// }

// function lsRememberMe() {
//   if (rmCheck.checked && emailInput.value !== "") {
//     localStorage.username = emailInput.value;
//     localStorage.checkbox = rmCheck.value;
//   } else {
//     localStorage.username = "";
//     localStorage.checkbox = "";
//   }
// }
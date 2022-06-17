import React, { Component, useContext } from 'react'
import jwt_decode from 'jwt-decode'
export default class Login extends Component {
  
  constructor() {
    super();
    this.handleSubmit = this.handleSubmit.bind(this);
    
  }

  handleSubmit(event) {
    event.preventDefault();
    const data = new FormData(event.target);
    
    fetch('http://localhost:9500/auth/login', {
      method: 'POST',
      body: data,
    }).then(response => response.json()).then(json => {

          console.log(json)
          console.log(json.jwtToken)
          let decodedToken = jwt_decode(json.jwtToken)
          localStorage.setItem("userEmail",JSON.stringify(decodedToken.sub))
          localStorage.setItem("expire", JSON.stringify(decodedToken.exp))
          localStorage.setItem("token", json.jwtToken)
          // console.log(currentDate.getTime())
          // if(decodedToken.exp * 1000 < currentDate.getTime()){
          //   console.log("Token expired")
          // }
          window.location.href = '/main'
    }).catch(function(){
        localStorage.clear("token");
        document.getElementById("error").style.display = "block"
    });
  }
  render() {
    return (
      <div className="auth-wrapper">
      <div className="auth-inner">
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
        <div className="mb-3">
            <h6 id="error" style={{display:"none", color:"red", textAlign:"center"}}>Invalid email or password.</h6>
        </div>
        <div className="d-grid">
          <button type="submit" className="btn btn-primary">
            Submit
          </button>
        </div>
      </form>
      </div>
      </div>
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
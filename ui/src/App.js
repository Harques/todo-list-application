import React, { useContext } from 'react'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import { BrowserRouter as Router, Routes, Route, Link, useLocation, Navigate } from 'react-router-dom'
import Login from './components/Login'
import Register from './components/Register'
import Main from './components/Main'

function App() {
  
const logOff = () => {
  localStorage.clear()
  window.location.href = '/sign-in'
}
const location = useLocation();
  return (
      <div className="App">
        <nav className="navbar navbar-expand-lg navbar-light fixed-top">
          <div className="container">
            <Link className="navbar-brand" to={location.pathname != "/deneme" && '/sign-in'}>
              To-Do-List
            </Link>
            <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
              <ul className="navbar-nav mr-auto">
                <li className="nav-item">
                  {location.pathname != "/deneme" && <Link className="nav-link" to={'/sign-in'}>
                    Login
                  </Link>}
                </li>
                <li className="nav-item">
                  {location.pathname != "/deneme" && <Link className="nav-link" to={'/sign-up'}>
                    Register
                  </Link>}
                </li>
              </ul>
              <ul className="navbar-nav ms-auto">
			<li className="nav-item">
			  {location.pathname === "/deneme" && <a className="nav-link" aria-current="page" onClick={logOff}>Log Off</a>}
			</li>	
		  </ul>	
            </div>
          </div>         
        </nav>
            <Routes>
              <Route exact path="/" element={<Login/>}/>
              <Route exact path="/main" element={<Main />} />
              <Route path="/sign-in" element={<Login />} />
              <Route path="/sign-up" element={<Register />} />
            </Routes>
      </div>
  )
}
export default App
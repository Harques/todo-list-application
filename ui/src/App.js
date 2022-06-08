import React, { useContext } from 'react'
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import './App.css'
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom'
import Login from './components/Login'
import Register from './components/Register'
import Deneme from './components/Deneme'

export const Context = React.createContext("auth");

function App() {
  const {auth, setAuth, userId, setUserId} = useContext(Context);
  return (
    <Context.Provider value = {{auth, setAuth, userId, setUserId}}>
    <Router>
      <div className="App">
        <nav className="navbar navbar-expand-lg navbar-light fixed-top">
          <div className="container">
            <Link className="navbar-brand" to={'/sign-in'}>
              To-Do-List
            </Link>
            <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
              <ul className="navbar-nav ml-auto">
                <li className="nav-item">
                  <Link className="nav-link" to={'/sign-in'}>
                    Login
                  </Link>
                </li>
                <li className="nav-item">
                  <Link className="nav-link" to={'/sign-up'}>
                    Register
                  </Link>
                </li>
              </ul>
            </div>
          </div>
        </nav>
        <div className="auth-wrapper">
          <div className="auth-inner">
            <Routes>
              <Route exact path="/deneme" element={<Deneme />} />
              <Route path="/sign-in" element={<Login />} />
              <Route path="/sign-up" element={<Register />} />
            </Routes>
          </div>
        </div>
      </div>
    </Router>
    </Context.Provider>
  )
}
export default App
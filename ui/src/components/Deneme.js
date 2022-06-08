import React, { Component } from 'react'
import { Navigate } from 'react-router-dom'
export default class Deneme extends Component {
  render() {
    const isAuth = localStorage.getItem("auth");
    if(!isAuth || isAuth === null){
      return (
        <Navigate to="/sign-in"/>
      )
    }
    return (
        <>
        <h2>Giriş başarılı</h2>
        </>
    )
  }
}
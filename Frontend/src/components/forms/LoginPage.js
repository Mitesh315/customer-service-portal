import React from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router";
import { useState } from "react";
import { toast, ToastContainer } from "react-toastify";
import { Form, FormControl, Button } from 'react-bootstrap';


const LoginPage = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const login = (e) => {
    e.preventDefault();

    const item = {};
    item["email"] = email;
    item["password"] = password;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:8080/api/v1/validate-login", false);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.onload = function () {
      console.log("DONE: ", xmlhttp.status);
    };
    xmlhttp.send(JSON.stringify(item));
    if (xmlhttp.status === 200) {
      navigate("/");
      toast.success("Logged In Successfully !", {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
          } else {
      toast.error("Login Failed", {
        position: "top-center",
        autoClose: 5000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "light",
      });
  }
}

  return (
  
    <div className="login-form-box">
    <h1 className="login-form-title">Login</h1>
    <Form onSubmit={login}>
      <FormControl
        type="email"
        placeholder="Email"
        className="login-form-input"
        onChange={(e) => setEmail(e.target.value)}
      />
      
      <FormControl
        type="password"
        placeholder="Password"
        className="login-form-input"
        onChange={(e) => setPassword(e.target.value)}
      />
      <Button type="submit" className="login-form-button">
        Login
      </Button>


      <ToastContainer
        position="top-center"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />

      <ToastContainer
        position="top-center"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />
    
    </Form>
  </div>
  );
};

export default LoginPage;

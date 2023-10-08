import React from "react";
import { useNavigate } from "react-router";
import { useState } from "react";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Form, FormControl, Button } from 'react-bootstrap';

const SignupForm = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [uname, setUname] = useState("");
  const [mobile, setMobile] = useState("");

  const submitForm = (e) => {
    e.preventDefault();

    const item = {};
    item["customerName"] = uname;
    item["email"] = email;
    item["password"] = password;
    item["mobileNumber"] = mobile;

    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:8080/api/v1/customer", false);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.onload = function () {
      console.log("DONE: ", xmlhttp.status);
    };
    xmlhttp.send(JSON.stringify(item));
    if (xmlhttp.status === 200) {
      navigate("/");
      toast.success("Account created successfully !", {
        // position: "top-center",
        // autoClose: 5000,
        // hideProgressBar: false,
        // closeOnClick: true,
        // pauseOnHover: true,
        // draggable: true,
        // progress: undefined,
        // theme: "light",
      });
          } else {
      toast.error("User already exists, Try with another E-mail", {
        // position: "top-center",
        // autoClose: 5000,
        // hideProgressBar: false,
        // closeOnClick: true,
        // pauseOnHover: true,
        // draggable: true,
        // progress: undefined,
        // theme: "light",
      });
    }
  };

  return (

    <div className="signup-form-box">
    <h1 className="signup-form-title">Sign Up</h1>
    <Form onSubmit={submitForm}>
      <FormControl
        type="email"
        placeholder="Email"
        className="signup-form-input"
        onChange={(e) => setEmail(e.target.value)}
      />
      <FormControl
        type="text"
        placeholder="Name"
        className="signup-form-input"
        onChange={(e) => setUname(e.target.value)}
      />
      <FormControl
        type="text"
        placeholder="Mobile"
        className="signup-form-input"
        onChange={(e) => setMobile(e.target.value)}
      />
      <FormControl
        type="password"
        placeholder="Password"
        className="signup-form-input"
        onChange={(e) => setPassword(e.target.value)}
      />
      <Button type="submit" className="signup-form-button">
        Sign Up
      </Button>

      <a href="/LoginPage">Already have an account ? Login Here...</a>

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

export default SignupForm;

// import logo from './logo.svg';
import './App.css';
import LoginPage from './components/forms/LoginPage';
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
import SignupForm from './components/forms/SignupForm';
import Dashboard from './components/Dashboard';


function App() {
  return (
    <Router>
    <div className="App">
      {/* <header className="App-header">
        Customer Services
      </header> */}
      <Routes>
        <Route exact path="/" element={<LoginPage/>} />
        <Route exact path="/SignupForm" element={<SignupForm/>} />
        <Route exact path="/Dashboard" element={<Dashboard/>} />
      </Routes>
    </div>
    </Router>
  );
}

export default App;

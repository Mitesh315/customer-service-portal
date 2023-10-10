import React from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const Dashboard = () => {
  return (
    <div>
      <Navbar bg="dark" data-bs-theme="dark">
        <Container>
          <Navbar.Brand href="#home" className="pe-5">Customer Service Portal</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="#home" className="px-4">Home</Nav.Link>
            <Nav.Link href="#features" className="px-4">About</Nav.Link>
            <Nav.Link href="#pricing" className="px-4">Report</Nav.Link>
            <Nav.Link href="#pricing" className="px-4">History</Nav.Link>
            <Nav.Link href="#pricing" className="px-4">Contact</Nav.Link>
            <Nav.Link href="#pricing" className="px-4">FAQ</Nav.Link>
            <Nav.Link href="#pricing" className="px-4">Service centers</Nav.Link>
            <Navbar.Collapse className="justify-content-end">
              <Navbar.Text>
              Signed in as: <a href="#login">Mark Otto</a>
              </Navbar.Text>
            </Navbar.Collapse>
            
          </Nav>
        </Container>
      </Navbar>
    </div>
  )
}

export default Dashboard